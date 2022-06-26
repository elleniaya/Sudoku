package model;

import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuGenerator {

    private final int OFFSET = 3;
    private final int maxMix = 10;

    private final int ROWS = 9;
	private final int COLUMNS = 9;

    Random randomGenerator = new Random();

    String easy = "easy";
    String normal = "normal";
    String hard = "hard";

    String[] generatedMatrix = new String[ROWS * COLUMNS];
    boolean[] mark = new boolean[ROWS * COLUMNS];

	public String[] generateRandomSudoku(String level) {

        try {
        if (level.equals(easy)) {
            BufferedReader br = new BufferedReader(new FileReader("EASY.txt"));
            readPuzzle(br);
            br.close();
        } else if (level.equals(normal)) {
            BufferedReader br = new BufferedReader(new FileReader("NORMAL.txt"));
            readPuzzle(br);
            br.close();
        } else {
            BufferedReader br = new BufferedReader(new FileReader("HARD.txt"));
            readPuzzle(br);
            br.close();
        }
        } catch (IOException e) {
			e.printStackTrace();
		}
		
        mixMatrix();

        markPuzzle();

		return generatedMatrix;
	}

    public void markPuzzle() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!generatedMatrix[i * ROWS + j].equals("0")) {
                    mark[i * ROWS + j] = true;
                } else mark[i * ROWS + j] = false;
            }
        }
    }

    private boolean isMarked(int row, int column) {
        return mark[row * ROWS + column];
    }

    private void readPuzzle (BufferedReader br) {
    try {
        int line = randomGenerator.nextInt((10 - 1) + 1) + 1;
        String buff_line = new String();
        int count = 0;
        while (br.ready()) {
            count++;
            buff_line = br.readLine();
            if (count == line) {
                break;
            }
        }
        generatedMatrix = buff_line.split("");
    }  catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void mixMatrix () {
        for (int i = 0; i < maxMix; i++) {
            swaprandomColumn();
            swaprandomRows();
            transposingMatrix();
            swaprandomcolumnArea();
            swaprandomrowsArea();
        }
    }

    private void transposingMatrix () {
        String[] matrix = new String[COLUMNS * ROWS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                String value = generatedMatrix[j * ROWS + i];
                matrix[i * ROWS + j] = value;
            }
        }
    }

    private void swaprandomColumn () {

        int randomArea = randomGenerator.nextInt(COLUMNS / OFFSET);

        int randomfirstCol = randomGenerator.nextInt(COLUMNS / OFFSET);

        int randomsecondCol = randomGenerator.nextInt(COLUMNS / OFFSET);

        while (randomfirstCol == randomsecondCol) {
            randomsecondCol = randomGenerator.nextInt(COLUMNS / OFFSET);
        }

        int numCol1 = randomArea * OFFSET + randomfirstCol;
        int numCol2 = randomArea * OFFSET + randomsecondCol;

        for (int i = 0; i < ROWS; i++) {
            String buff1 = generatedMatrix[numCol1 + i * ROWS];
            generatedMatrix[numCol1 + i * ROWS] = generatedMatrix[numCol2 + i * ROWS];
            generatedMatrix[numCol2 + i * ROWS] = buff1;
        }
    }

    private void swaprandomRows () {
        transposingMatrix();
        swaprandomColumn();
        transposingMatrix();
    }

    private void swaprandomcolumnArea () {

        int randomArea1 = randomGenerator.nextInt(COLUMNS / OFFSET);
        int randomArea2 = randomGenerator.nextInt(COLUMNS / OFFSET);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(COLUMNS / OFFSET);
        }

        for (int i = 0; i < OFFSET; i++) {
            for (int j = 0; j < ROWS; j++) {
                String buff1 = generatedMatrix[randomArea1 * OFFSET + i + j * ROWS];
                generatedMatrix[randomArea1 * OFFSET + i + j * ROWS] = generatedMatrix[randomArea2 * OFFSET + i + j * ROWS];
                generatedMatrix[randomArea2 * OFFSET + i + j * ROWS] = buff1;
            }
        }
    }

    private void swaprandomrowsArea () {
        transposingMatrix();
        swaprandomcolumnArea();
        transposingMatrix();
    }

    public String[] updatePuzzle (String value, int row, int column) {
        if (!isMarked(row, column) && isValidMove(value, row, column)) {
            generatedMatrix[row * ROWS + column] = value;
        }
        return generatedMatrix;
    }

    private boolean isValidMove(String value, int row, int column) {
        return !usedinRow(value, row) && !usedinColumn(value, column) && !usedinBox(value, row, column);
    }

    private boolean usedinRow(String value, int row) {
        for (int col = 0; col < COLUMNS; col++) {
            if (generatedMatrix[row * ROWS + col].equals(value)) return true;
        }
        return false;
    }

    private boolean usedinColumn(String value, int column) {
        for (int row = 0; row < ROWS; row++) {
            if (generatedMatrix[row * ROWS + column].equals(value)) return true;
        }
        return false;
    }

    private boolean usedinBox(String value, int row, int column) {
        int boxRow = row / OFFSET;
		int boxCol = column / OFFSET;
			
		int startingRow = boxRow * OFFSET;
		int startingCol = boxCol * OFFSET;

        for(int r = startingRow; r <= (startingRow + OFFSET) - 1; r++) {
            for(int c = startingCol; c <= (startingCol + OFFSET) - 1; c++) {
                if(generatedMatrix[r * ROWS + c].equals(value)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

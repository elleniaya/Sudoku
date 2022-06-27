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

    String[] generatedPuzzle = new String[ROWS * COLUMNS];
    boolean[] mark = new boolean[ROWS * COLUMNS];

	public String[] generateRandomSudoku(String level) {

        if (level.equals(easy)) {
            readPuzzle("src/RESOURCES/EASY.txt");
        } else if (level.equals(normal)) {
            readPuzzle("src/RESOURCES/NORMAL.txt");
        } else {
            readPuzzle("src/RESOURCES/HARD.txt");
        }
		
        mixPuzzle();

        markPuzzle();

		return generatedPuzzle;
	}

    public boolean decided() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (generatedPuzzle[i * ROWS + j].equals("0")) return false;
            }
        }
        return true;
    }

    public void markPuzzle() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!generatedPuzzle[i * ROWS + j].equals("0")) {
                    mark[i * ROWS + j] = true;
                } else mark[i * ROWS + j] = false;
            }
        }
    }

    private boolean isMarked(int row, int column) {
        return mark[row * ROWS + column];
    }

    private void readPuzzle (String levelFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(levelFile));
            int line = randomGenerator.nextInt((10 - 1) + 1) + 1;
            String buff_line = new String();
            int count = 0;
            while (br.ready()) {
                count++;
                buff_line = br.readLine();
                if (count == line) break;
        }
        generatedPuzzle = buff_line.split("");
        br.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mixPuzzle () {
        for (int i = 0; i < maxMix; i++) {
            swaprandomColumn();
            swaprandomRows();
            transposingPuzzle();
            swaprandomcolumnArea();
            swaprandomrowsArea();
        }
    }

    private void transposingPuzzle () {
        String[] puzzle = new String[COLUMNS * ROWS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                String value = generatedPuzzle[j * ROWS + i];
                puzzle[i * ROWS + j] = value;
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
            String buff1 = generatedPuzzle[numCol1 + i * ROWS];
            generatedPuzzle[numCol1 + i * ROWS] = generatedPuzzle[numCol2 + i * ROWS];
            generatedPuzzle[numCol2 + i * ROWS] = buff1;
        }
    }

    private void swaprandomRows () {
        transposingPuzzle();
        swaprandomColumn();
        transposingPuzzle();
    }

    private void swaprandomcolumnArea () {

        int randomArea1 = randomGenerator.nextInt(COLUMNS / OFFSET);
        int randomArea2 = randomGenerator.nextInt(COLUMNS / OFFSET);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(COLUMNS / OFFSET);
        }

        for (int i = 0; i < OFFSET; i++) {
            for (int j = 0; j < ROWS; j++) {
                String buff1 = generatedPuzzle[randomArea1 * OFFSET + i + j * ROWS];
                generatedPuzzle[randomArea1 * OFFSET + i + j * ROWS] = generatedPuzzle[randomArea2 * OFFSET + i + j * ROWS];
                generatedPuzzle[randomArea2 * OFFSET + i + j * ROWS] = buff1;
            }
        }
    }

    private void swaprandomrowsArea () {
        transposingPuzzle();
        swaprandomcolumnArea();
        transposingPuzzle();
    }

    public String[] updatePuzzle (String value, int row, int column) {
        if (!isMarked(row, column) && isValidMove(value, row, column)) {
            generatedPuzzle[row * ROWS + column] = value;
        }
        return generatedPuzzle;
    }

    private boolean isValidMove(String value, int row, int column) {
        return !usedinRow(value, row) && !usedinColumn(value, column) && !usedinBox(value, row, column);
    }

    private boolean usedinRow(String value, int row) {
        for (int col = 0; col < COLUMNS; col++) {
            if (generatedPuzzle[row * ROWS + col].equals(value)) return true;
        }
        return false;
    }

    private boolean usedinColumn(String value, int column) {
        for (int row = 0; row < ROWS; row++) {
            if (generatedPuzzle[row * ROWS + column].equals(value)) return true;
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
                if(generatedPuzzle[r * ROWS + c].equals(value)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

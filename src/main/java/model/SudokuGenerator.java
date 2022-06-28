package model;

import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuGenerator {

    private static final int MAX_MIX = 10;
    private static final String EASY = "easy";
    private static final String NORMAL = "normal";

    private final int rows;
	private final int columns;
    private final int boxHeight;

    private final Random randomGenerator = new Random();

    private String[] generatedPuzzle;
    private boolean[] mark;

    public SudokuGenerator(int rows, int columns, int boxHeight) {
        this.rows = rows;
        this.columns = columns;
        this.boxHeight = boxHeight;
        this.generatedPuzzle = new String[rows * columns];
        this.mark = new boolean[rows * columns];
    }

	public String[] generateRandomSudoku(String level) {

        if (level.equals(EASY)) {
            readPuzzle("src/main/java/RESOURCES/EASY.txt");
        } else if (level.equals(NORMAL)) {
            readPuzzle("src/main/java/RESOURCES/NORMAL.txt");
        } else {
            readPuzzle("src/main/java/RESOURCES/HARD.txt");
        }
		
        mixPuzzle();

        markPuzzle();

		return generatedPuzzle;
	}

    public boolean decided() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (generatedPuzzle[i * rows + j].equals("0")) return false;
            }
        }
        return true;
    }

    public void markPuzzle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!generatedPuzzle[i * rows + j].equals("0")) {
                    mark[i * rows + j] = true;
                } else mark[i * rows + j] = false;
            }
        }
    }

    private boolean isMarked(int row, int column) {
        return mark[row * rows + column];
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
        for (int i = 0; i < MAX_MIX; i++) {
            swapRandomColumn();
            swapRandomRows();
            transposingPuzzle();
            swapRandomColumnArea();
            swapRandomRowsArea();
        }
    }

    private void transposingPuzzle () {
        String[] puzzle = new String[columns * rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String value = generatedPuzzle[j * rows + i];
                puzzle[i * rows + j] = value;
            }
        }
    }

    private void swapRandomColumn () {

        int randomArea = randomGenerator.nextInt(columns / boxHeight);

        int randomfirstCol = randomGenerator.nextInt(columns / boxHeight);

        int randomsecondCol = randomGenerator.nextInt(columns / boxHeight);

        while (randomfirstCol == randomsecondCol) {
            randomsecondCol = randomGenerator.nextInt(columns / boxHeight);
        }

        int numCol1 = randomArea * boxHeight + randomfirstCol;
        int numCol2 = randomArea * boxHeight + randomsecondCol;

        for (int i = 0; i < rows; i++) {
            String buff1 = generatedPuzzle[numCol1 + i * rows];
            generatedPuzzle[numCol1 + i * rows] = generatedPuzzle[numCol2 + i * rows];
            generatedPuzzle[numCol2 + i * rows] = buff1;
        }
    }

    private void swapRandomRows () {
        transposingPuzzle();
        swapRandomColumn();
        transposingPuzzle();
    }

    private void swapRandomColumnArea () {

        int randomArea1 = randomGenerator.nextInt(columns / boxHeight);
        int randomArea2 = randomGenerator.nextInt(columns / boxHeight);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(columns / boxHeight);
        }

        for (int i = 0; i < boxHeight; i++) {
            for (int j = 0; j < rows; j++) {
                String buff1 = generatedPuzzle[randomArea1 * boxHeight + i + j * rows];
                generatedPuzzle[randomArea1 * boxHeight + i + j * rows] = generatedPuzzle[randomArea2 * boxHeight + i + j * rows];
                generatedPuzzle[randomArea2 * boxHeight + i + j * rows] = buff1;
            }
        }
    }

    private void swapRandomRowsArea () {
        transposingPuzzle();
        swapRandomColumnArea();
        transposingPuzzle();
    }

    public String[] updatePuzzle (String value, int row, int column) {
        if (!isMarked(row, column) && isValidMove(value, row, column)) {
            generatedPuzzle[row * rows + column] = value;
        }
        return generatedPuzzle;
    }

    private boolean isValidMove(String value, int row, int column) {
        return !usedInRow(value, row) && !usedInColumn(value, column) && !usedInBox(value, row, column);
    }

    private boolean usedInRow(String value, int row) {
        for (int col = 0; col < columns; col++) {
            if (generatedPuzzle[row * rows + col].equals(value)) return true;
        }
        return false;
    }

    private boolean usedInColumn(String value, int column) {
        for (int row = 0; row < rows; row++) {
            if (generatedPuzzle[row * rows + column].equals(value)) return true;
        }
        return false;
    }

    private boolean usedInBox(String value, int row, int column) {
        int boxRow = row / boxHeight;
		int boxCol = column / boxHeight;
			
		int startingRow = boxRow * boxHeight;
		int startingCol = boxCol * boxHeight;

        for(int r = startingRow; r <= (startingRow + boxHeight) - 1; r++) {
            for(int c = startingCol; c <= (startingCol + boxHeight) - 1; c++) {
                if(generatedPuzzle[r * rows + c].equals(value)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

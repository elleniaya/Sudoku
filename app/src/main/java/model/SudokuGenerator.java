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

    protected String[] generatedPuzzle;
    protected boolean[] mark;

    public SudokuGenerator(int rows, int columns, int boxHeight) {
        this.rows = rows;
        this.columns = columns;
        this.boxHeight = boxHeight;
        this.generatedPuzzle = new String[rows * columns];
        this.mark = new boolean[rows * columns];
    }

	public String[] generateRandomSudoku(String level) {

        if (level.equals(EASY)) {
            readPuzzle("src/main/resources/EASY.txt");
        } else if (level.equals(NORMAL)) {
            readPuzzle("src/main/resources/NORMAL.txt");
        } else {
            readPuzzle("src/main/resources/HARD.txt");
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

    protected void markPuzzle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!generatedPuzzle[i * rows + j].equals("0")) {
                    mark[i * rows + j] = true;
                } else mark[i * rows + j] = false;
            }
        }
    }

    protected boolean isMarked(int row, int column) {
        return mark[row * rows + column];
    }

    protected void readPuzzle (String levelFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(levelFile));
            int line = randomGenerator.nextInt((10 - 1) + 1) + 1;
            String buff_line = "";
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

    protected void mixPuzzle () {
        for (int i = 0; i < MAX_MIX; i++) {
            swapRandomColumn();
            swapRandomRows();
            generatedPuzzle = transposingPuzzle();
            swapRandomColumnArea();
            swapRandomRowsArea();
        }
    }

    protected String[] transposingPuzzle () {
        String[] puzzle = new String[columns * rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String value = generatedPuzzle[j * rows + i];
                puzzle[i * rows + j] = value;
            }
        }

        return puzzle;
    }

    protected void swapRandomColumn () {

        int randomArea = randomGenerator.nextInt(columns / boxHeight);

        int randomfirstCol = randomGenerator.nextInt(columns / boxHeight);

        int randomsecondCol = randomGenerator.nextInt(columns / boxHeight);

        while (randomfirstCol == randomsecondCol) {
            randomsecondCol = randomGenerator.nextInt(columns / boxHeight);
        }

        swapColumn(randomArea, randomfirstCol, randomsecondCol);
    }

    protected void swapColumn(int area, int firstCol, int secondCol) {
        int numCol1 = area * boxHeight + firstCol;
        int numCol2 = area * boxHeight + secondCol;

        for (int i = 0; i < rows; i++) {
            String buff1 = generatedPuzzle[numCol1 + i * rows];
            generatedPuzzle[numCol1 + i * rows] = generatedPuzzle[numCol2 + i * rows];
            generatedPuzzle[numCol2 + i * rows] = buff1;
        }
    }

    protected void swapRandomRows () {
        int randomArea = randomGenerator.nextInt(columns / boxHeight);

        int randomfirstRow = randomGenerator.nextInt(columns / boxHeight);

        int randomsecondRow = randomGenerator.nextInt(columns / boxHeight);

        swapRows(randomArea, randomfirstRow, randomsecondRow);
    }

    protected void swapRows(int area, int firstRow, int secondRow) {
        generatedPuzzle = transposingPuzzle();
        swapColumn(area, firstRow, secondRow);
        generatedPuzzle = transposingPuzzle();
    }

    protected void swapRandomColumnArea () {

        int randomArea1 = randomGenerator.nextInt(columns / boxHeight);
        int randomArea2 = randomGenerator.nextInt(columns / boxHeight);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(columns / boxHeight);
        }

        swapColumnArea(randomArea1, randomArea2);
    }

    protected void swapColumnArea(int randomArea1, int randomArea2) {
        for (int i = 0; i < boxHeight; i++) {
            for (int j = 0; j < rows; j++) {
                String buff1 = generatedPuzzle[randomArea1 * boxHeight + i + j * rows];
                generatedPuzzle[randomArea1 * boxHeight + i + j * rows] = generatedPuzzle[randomArea2 * boxHeight + i + j * rows];
                generatedPuzzle[randomArea2 * boxHeight + i + j * rows] = buff1;
            }
        }
    }

    protected void swapRandomRowsArea () {
        int randomArea1 = randomGenerator.nextInt(columns / boxHeight);
        int randomArea2 = randomGenerator.nextInt(columns / boxHeight);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(columns / boxHeight);
        }

        swapRowsArea(randomArea1, randomArea2);
    }

    protected void swapRowsArea(int randomArea1, int randomArea2) {
        generatedPuzzle = transposingPuzzle();
        swapColumnArea(randomArea1, randomArea2);
        generatedPuzzle = transposingPuzzle();
    }

    public String[] updatePuzzle (String value, int row, int column) {
        if (!isMarked(row, column) && isValidMove(value, row, column)) {
            generatedPuzzle[row * rows + column] = value;
        }
        return generatedPuzzle;
    }

    protected boolean isValidMove(String value, int row, int column) {
        return !usedInRow(value, row) && !usedInColumn(value, column) && !usedInBox(value, row, column);
    }

    protected boolean usedInRow(String value, int row) {
        for (int col = 0; col < columns; col++) {
            if (generatedPuzzle[row * rows + col].equals(value)) return true;
        }
        return false;
    }

    protected boolean usedInColumn(String value, int column) {
        for (int row = 0; row < rows; row++) {
            if (generatedPuzzle[row * rows + column].equals(value)) return true;
        }
        return false;
    }

    protected boolean usedInBox(String value, int row, int column) {
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

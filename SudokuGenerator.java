import java.util.ArrayList;
import java.util.Random;

public class SudokuGenerator {

    private final int OFFSET = 3;
    Random randomGenerator = new Random();
    private final int maxMix = 10;

	public SudokuPuzzle generateRandomSudoku(SudokuPuzzleType puzzleType) {
		SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), puzzleType.getColumns(), puzzleType.getBoxWidth(), puzzleType.getBoxHeight(), puzzleType.getValidValues());
		int column = puzzleType.getColumns();
        int rows = puzzleType.getRows();

        ArrayList<Integer> generatedMatrix = new ArrayList<Integer>(puzzleType.getRows() * puzzleType.getColumns());

        int val = 0;
        for (int i = 0; i < puzzleType.getRows(); i++) {
            if (i != 0) val += OFFSET;
            if (i != 0 && i % OFFSET == 0) val++;
            for (int j = 0; j < puzzleType.getColumns(); j++) {
                val = (val % puzzleType.getColumns()) + 1;
                generatedMatrix.add(val);
            }
        }
		
        generatedMatrix = mixMatrix(generatedMatrix, column, rows);

        for (int i = 0; i < puzzleType.getRows(); i++) {
            for (int j = 0; j < puzzleType.getColumns(); j++) {
                puzzle.addValue(Integer.toString(generatedMatrix.get(i * puzzleType.getRows() + j)), i, j);
            }
        }

		return puzzle;
	}

    private ArrayList<Integer> mixMatrix (ArrayList<Integer> generatedMatrix, int column, int rows) {

        for (int i = 0; i < maxMix; i++) {
            generatedMatrix = swaprandomColumn(generatedMatrix, column, rows);
            generatedMatrix = swaprandomRows(generatedMatrix, column, rows);
            generatedMatrix = transposingMatrix(generatedMatrix, column, rows);
            generatedMatrix = swaprandomcolumnArea(generatedMatrix, column, rows);
            generatedMatrix = swaprandomrowsArea(generatedMatrix, column, rows);
        }

        return generatedMatrix;
    }

    private ArrayList<Integer> transposingMatrix (ArrayList<Integer> generatedMatrix, int column, int rows) {
        ArrayList<Integer> matrix = new ArrayList<Integer>(column * rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                int value = generatedMatrix.get(j * rows + i);
                matrix.add(i * rows + j, value);
            }
        }

        return matrix;
    }

    private ArrayList<Integer> swaprandomColumn (ArrayList<Integer> generatedMatrix, int column, int rows) {

        int randomArea = randomGenerator.nextInt(column / OFFSET);

        int randomfirstCol = randomGenerator.nextInt(column / OFFSET);

        int randomsecondCol = randomGenerator.nextInt(column / OFFSET);

        while (randomfirstCol == randomsecondCol) {
            randomsecondCol = randomGenerator.nextInt(column / OFFSET);
        }

        int numCol1 = randomArea * OFFSET + randomfirstCol;
        int numCol2 = randomArea * OFFSET + randomsecondCol;

        for (int i = 0; i < rows; i++) {
            int buff1 = generatedMatrix.get(numCol1 + i * rows);
            generatedMatrix.set(numCol1 + i * rows, generatedMatrix.get(numCol2 + i * rows));
            generatedMatrix.set(numCol2 + i * rows, buff1);
        }

        return generatedMatrix;
    }

    private ArrayList<Integer> swaprandomRows (ArrayList<Integer> generatedMatrix, int column, int rows) {
        generatedMatrix = transposingMatrix(generatedMatrix, column, rows);
        generatedMatrix = swaprandomColumn(generatedMatrix, column, rows);
        generatedMatrix = transposingMatrix(generatedMatrix, column, rows);
        return generatedMatrix;
    }

    private ArrayList<Integer> swaprandomcolumnArea (ArrayList<Integer> generatedMatrix, int column, int rows) {

        int randomArea1 = randomGenerator.nextInt(column / OFFSET);
        int randomArea2 = randomGenerator.nextInt(column / OFFSET);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(column / OFFSET);
        }

        for (int i = 0; i < OFFSET; i++) {
            for (int j = 0; j < rows; j++) {
                int buff1 = generatedMatrix.get(randomArea1 * OFFSET + i + j * rows);
                generatedMatrix.set(randomArea1 * OFFSET + i + j * rows, generatedMatrix.get(randomArea2 * OFFSET + i + j * rows));
                generatedMatrix.set(randomArea2 * OFFSET + i + j * rows, buff1);
            }
        }
        return generatedMatrix;
    }

    private ArrayList<Integer> swaprandomrowsArea (ArrayList<Integer> generatedMatrix, int column, int rows) {
        generatedMatrix = transposingMatrix(generatedMatrix, column, rows);
        generatedMatrix = swaprandomcolumnArea(generatedMatrix, column, rows);
        generatedMatrix = transposingMatrix(generatedMatrix, column, rows);
        return generatedMatrix;
    }

    
}

package SudokuTest;

import model.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuTest {

    private final int ROWS = 9;
    private final int COLUMNS = 9;
    private final int BOX_HEIGHT = 3;
    
    private final String[] PUZZLE = {
        "2", "0", "0", "0", "0", "0", "0", "7", "9",
        "4", "6", "7", "0", "0", "0", "0", "0", "0",
        "0", "9", "0", "7", "0", "0", "1", "6", "2",
        "7", "2", "0", "0", "6", "0", "9", "1", "0",
        "5", "0", "9", "3", "4", "0", "0", "8", "7",
        "3", "0", "0", "0", "9", "0", "0", "0", "0",
        "1", "7", "0", "0", "0", "6", "0", "4", "0",
        "0", "4", "0", "8", "0", "1", "7", "2", "0",
        "0", "5", "2", "4", "7", "3", "0", "0", "0"};

    SudokuGeneratorTest newGenerator = new SudokuGeneratorTest(PUZZLE);

    @Test 
    public void decideTest() {
        assertFalse(newGenerator.decidedTest());
    }

    @Test
    public void markPuzzleTest() {
        boolean[] markTest = {
            true, false, false, false, false, false, false, true, true,
            true, true, true, false, false, false, false, false, false,
            false, true, false, true, false, false, true, true, true,
            true, true, false, false, true, false, true, true, false,
            true, false, true, true, true, false, false, true, true,
            true, false, false, false, true, false, false, false, false,
            true, true, false, false, false, true, false, true, false,
            false, true, false, true, false, true, true, true, false,
            false, true, true, true, true, true, false, false, false
        };
    
        assertArrayEquals(newGenerator.markPuzzleTest(), markTest);
    }

    @Test
    public void isMarkedTest() {
        assertTrue(newGenerator.isMarkedTest(0, 0));
        assertTrue(newGenerator.isMarkedTest(0, 7));
        assertTrue(newGenerator.isMarkedTest(5, 4));
        assertFalse(newGenerator.isMarkedTest(8, 0));
        assertFalse(newGenerator.isMarkedTest(4, 1));
    }

    @Test
    public void transposingPuzzleTest() {
        String[] transposedMatrix = {
            "2", "4", "0", "7", "5", "3", "1", "0", "0",
            "0", "6", "9", "2", "0", "0", "7", "4", "5",
            "0", "7", "0", "0", "9", "0", "0", "0", "2",
            "0", "0", "7", "0", "3", "0", "0", "8", "4",
            "0", "0", "0", "6", "4", "9", "0", "0", "7",
            "0", "0", "0", "0", "0", "0", "6", "1", "3",
            "0", "0", "1", "9", "0", "0", "0", "7", "0",
            "7", "0", "6", "1", "8", "0", "4", "2", "0",
            "9", "0", "2", "0", "7", "0", "0", "0", "0"
        };

        assertArrayEquals(newGenerator.transposingPuzzleTest(), transposedMatrix);
    }

    private class SudokuGeneratorTest extends SudokuGenerator {
        public SudokuGeneratorTest(String[] puzzle) {
            super(ROWS, COLUMNS, BOX_HEIGHT);
            this.generatedPuzzle = puzzle;
            markPuzzle();
        }

        public boolean decidedTest() {
            return newGenerator.decided();
        }

        public boolean[] markPuzzleTest() {
            return newGenerator.mark;
        }

        public boolean isMarkedTest(int row, int column) {
            return isMarked(row, column);
        }

        public String[] transposingPuzzleTest() {
            return transposingPuzzle();
        }
    }
}

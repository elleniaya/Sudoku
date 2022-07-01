import model.*;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

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

    SudokuGeneratorTest newGenerator;
    RecordsTest newRecords;

    @Test 
    public void decideTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);
        assertFalse(newGenerator.decidedTest());
    }

    @Test
    public void markPuzzleTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

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
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        assertTrue(newGenerator.isMarkedTest(0, 0));
        assertTrue(newGenerator.isMarkedTest(0, 7));
        assertTrue(newGenerator.isMarkedTest(5, 4));
        assertFalse(newGenerator.isMarkedTest(8, 0));
        assertFalse(newGenerator.isMarkedTest(4, 1));
    }

    @Test
    public void transposingPuzzleTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

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

    @Test
    public void updatePuzzleTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        String[] puzzleUpdate1 = {
            "2", "0", "0", "0", "0", "0", "0", "7", "9",
            "4", "6", "7", "0", "0", "0", "0", "0", "0",
            "8", "9", "0", "7", "0", "0", "1", "6", "2",
            "7", "2", "0", "0", "6", "0", "9", "1", "0",
            "5", "0", "9", "3", "4", "0", "0", "8", "7",
            "3", "0", "0", "0", "9", "0", "0", "0", "0",
            "1", "7", "0", "0", "0", "6", "0", "4", "0",
            "0", "4", "0", "8", "0", "1", "7", "2", "0",
            "0", "5", "2", "4", "7", "3", "0", "0", "0"};

        String[] puzzleUpdate2 = {
            "2", "0", "0", "0", "0", "0", "0", "7", "9",
            "4", "6", "7", "0", "0", "0", "3", "0", "0",
            "8", "9", "0", "7", "0", "0", "1", "6", "2",
            "7", "2", "0", "0", "6", "0", "9", "1", "0",
            "5", "0", "9", "3", "4", "0", "0", "8", "7",
            "3", "0", "0", "0", "9", "0", "0", "0", "0",
            "1", "7", "0", "0", "0", "6", "0", "4", "0",
            "0", "4", "0", "8", "0", "1", "7", "2", "0",
            "0", "5", "2", "4", "7", "3", "0", "0", "0"};

        assertArrayEquals(newGenerator.updatePuzzleTest("8", 2, 0), puzzleUpdate1);
        assertArrayEquals(newGenerator.updatePuzzleTest("3", 1, 6), puzzleUpdate2);

        assertArrayEquals(newGenerator.updatePuzzleTest("9", 6, 4), puzzleUpdate2);
        assertArrayEquals(newGenerator.updatePuzzleTest("2", 2, 4), puzzleUpdate2);
        assertArrayEquals(newGenerator.updatePuzzleTest("8", 6, 4), puzzleUpdate2);
    }

    @Test
    public void usedInRowTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        assertFalse(newGenerator.usedInRowTest("1", 0));
        assertFalse(newGenerator.usedInRowTest("3", 7));

        assertTrue(newGenerator.usedInRowTest("7", 3));
        assertTrue(newGenerator.usedInRowTest("4", 6));
    }

    @Test
    public void usedInColumnTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        assertFalse(newGenerator.usedInColumnTest("6", 0));
        assertFalse(newGenerator.usedInColumnTest("2", 6));

        assertTrue(newGenerator.usedInColumnTest("9", 8));
        assertTrue(newGenerator.usedInColumnTest("7", 2));
    }

    @Test
    public void usedInBoxTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        assertFalse(newGenerator.usedInBoxTest("3", 6, 2));
        assertFalse(newGenerator.usedInBoxTest("1", 3, 3));

        assertTrue(newGenerator.usedInBoxTest("3", 3, 3));
        assertTrue(newGenerator.usedInBoxTest("6", 0, 6));
    }

    @Test
    public void isValidMoveTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        assertFalse(newGenerator.isValidMoveTest("1", 4, 6));
        assertFalse(newGenerator.isValidMoveTest("3", 5, 2));

        assertTrue(newGenerator.isValidMoveTest("1", 5, 1));
        assertTrue(newGenerator.isValidMoveTest("3", 3, 8));
    }

    @Test
    public void swapColumnAreaTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        String[] matrix = {
            "0", "0", "0", "2", "0", "0", "0", "7", "9",
            "0", "0", "0", "4", "6", "7", "0", "0", "0",
            "7", "0", "0", "0", "9", "0", "1", "6", "2",
            "0", "6", "0", "7", "2", "0", "9", "1", "0",
            "3", "4", "0", "5", "0", "9", "0", "8", "7",
            "0", "9", "0", "3", "0", "0", "0", "0", "0",
            "0", "0", "6", "1", "7", "0", "0", "4", "0",
            "8", "0", "1", "0", "4", "0", "7", "2", "0",
            "4", "7", "3", "0", "5", "2", "0", "0", "0"};

        newGenerator.swapColumnAreaTest(0, 1);

        assertArrayEquals(newGenerator.getPuzzle(), matrix);
    }

    @Test
    public void swapColumnTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        String[] matrix = {
            "2", "0", "0", "0", "0", "0", "0", "9", "7",
            "4", "6", "7", "0", "0", "0", "0", "0", "0",
            "0", "9", "0", "7", "0", "0", "1", "2", "6",
            "7", "2", "0", "0", "6", "0", "9", "0", "1",
            "5", "0", "9", "3", "4", "0", "0", "7", "8",
            "3", "0", "0", "0", "9", "0", "0", "0", "0",
            "1", "7", "0", "0", "0", "6", "0", "0", "4",
            "0", "4", "0", "8", "0", "1", "7", "0", "2",
            "0", "5", "2", "4", "7", "3", "0", "0", "0"};

        newGenerator.swapColumnTest(2, 1, 2);

        assertArrayEquals(newGenerator.getPuzzle(), matrix);
    }

    @Test
    public void swapRowTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        String[] matrix = {
            "2", "0", "0", "0", "0", "0", "0", "7", "9",
            "4", "6", "7", "0", "0", "0", "0", "0", "0",
            "0", "9", "0", "7", "0", "0", "1", "6", "2",
            "3", "0", "0", "0", "9", "0", "0", "0", "0",
            "5", "0", "9", "3", "4", "0", "0", "8", "7",
            "7", "2", "0", "0", "6", "0", "9", "1", "0",
            "1", "7", "0", "0", "0", "6", "0", "4", "0",
            "0", "4", "0", "8", "0", "1", "7", "2", "0",
            "0", "5", "2", "4", "7", "3", "0", "0", "0"};

        newGenerator.swapRowTest(1, 0, 2);

        assertArrayEquals(newGenerator.getPuzzle(), matrix);
    }

    @Test
    public void swapRowsAreaTest() {
        newGenerator = new SudokuGeneratorTest(PUZZLE);

        String[] matrix = {
            "1", "7", "0", "0", "0", "6", "0", "4", "0",
            "0", "4", "0", "8", "0", "1", "7", "2", "0",
            "0", "5", "2", "4", "7", "3", "0", "0", "0",
            "7", "2", "0", "0", "6", "0", "9", "1", "0",
            "5", "0", "9", "3", "4", "0", "0", "8", "7",
            "3", "0", "0", "0", "9", "0", "0", "0", "0",
            "2", "0", "0", "0", "0", "0", "0", "7", "9",
            "4", "6", "7", "0", "0", "0", "0", "0", "0",
            "0", "9", "0", "7", "0", "0", "1", "6", "2"};

        newGenerator.swapRowsAreaTest(0, 2);

        assertArrayEquals(newGenerator.getPuzzle(), matrix);
    }

    @Test 
    public void parseLineTest() {
        newRecords = new RecordsTest();

        String user1 = "Kate 90";
        String user2 = "Mark 2";
        String user3 = "Sasha 10";

        Pair userPair1 = new Pair("Kate", 90);
        Pair userPair2 = new Pair("Mark", 2);
        Pair userPair3 = new Pair("Sasha", 10);

        Pair result1 = newRecords.parseLineTest(user1);
        Pair result2 = newRecords.parseLineTest(user2);
        Pair result3 = newRecords.parseLineTest(user3);

        assertEquals(result1.name, userPair1.name);
        assertEquals(result1.points, userPair1.points);

        assertEquals(result2.name, userPair2.name);
        assertEquals(result2.points, userPair2.points);

        assertEquals(result3.name, userPair3.name);
        assertEquals(result3.points, userPair3.points);
    }

    @Test
    public void nameUsedTest() {
        newRecords = new RecordsTest();

        Pair userPair1 = new Pair("test1", 90);
        Pair userPair2 = new Pair("test2", 2);
        Pair userPair3 = new Pair("test3", 10);

        newRecords.addData(userPair1);
        newRecords.addData(userPair2);
        newRecords.addData(userPair3);

        assertEquals(newRecords.nameUsedTest("test1"), newRecords.getDataSize() - 3);
        assertEquals(newRecords.nameUsedTest("test2"), newRecords.getDataSize() - 2);
        assertEquals(newRecords.nameUsedTest("test3"), newRecords.getDataSize() - 1);
    }

    /*@Test
    public void addRecord() {
        newRecords = new RecordsTest();

        Pair userPair1 = new Pair("test1", 90);
        Pair userPair2 = new Pair("test2", 2);
        Pair userPair3 = new Pair("test3", 10);

        newRecords.addData(userPair1);
        newRecords.addData(userPair2);
        newRecords.addData(userPair3);

        newRecords.addRecord("test1", 90);
        newRecords.addRecord("test2", 2);
        newRecords.addRecord("test3", 10);

        newRecords.addRecord("test2", 2);

        assertEquals(newRecords.getData().get(newRecords.getDataSize() - 2).points, 4);
    }*/

    private class SudokuGeneratorTest extends SudokuGenerator {
        public SudokuGeneratorTest(String[] puzzle) {
            super(ROWS, COLUMNS, BOX_HEIGHT);
            this.generatedPuzzle = puzzle;
            markPuzzle();
        }

        public boolean decidedTest() {
            return decided();
        }

        public boolean[] markPuzzleTest() {
            return mark;
        }

        public boolean isMarkedTest(int row, int column) {
            return isMarked(row, column);
        }

        public String[] transposingPuzzleTest() {
            return transposingPuzzle();
        }

        public String[] updatePuzzleTest(String value, int row, int column) {
            return updatePuzzle(value, row, column);
        }

        public boolean usedInRowTest(String value, int row) {
            return usedInRow(value, row);
        }

        public boolean usedInColumnTest(String value, int column) {
            return usedInColumn(value, column);
        }

        public boolean usedInBoxTest(String value, int row, int column) {
            return usedInBox(value, row, column);
        }

        public boolean isValidMoveTest(String value, int row, int column) {
            return isValidMove(value, row, column);
        }

        public void swapColumnAreaTest(int area1, int area2) {
            swapColumnArea(area1, area2);
        }

        public String[] getPuzzle() {
            return generatedPuzzle;
        }

        public void swapColumnTest(int area, int firstCol, int secondCol) {
            swapColumn(area, firstCol, secondCol);
        }

        public void swapRowTest(int area, int firstRow, int secondRow) {
            swapRows(area, firstRow, secondRow);
        }

        public void swapRowsAreaTest(int randomArea1, int randomArea2) {
            swapRowsArea(randomArea1, randomArea2);
        }
    }

    private class RecordsTest extends Records {
        public Pair parseLineTest(String line) {
            return parseLine(line);
        }

        public int nameUsedTest(String name) {
            return nameUsed(name);
        }

        public ArrayList<Pair> getData() {
            return data;
        }

        public int getDataSize() {
            return data.size();
        }

        public void addData(Pair user) {
            data.add(user);
        }
    }
}

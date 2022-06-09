import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

	public SudokuPuzzle generateRandomSudoku(SudokuPuzzleType puzzleType) {
		SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), puzzleType.getColumns(), puzzleType.getBoxWidth(), puzzleType.getBoxHeight(), puzzleType.getValidValues());
        
        List<String> usedValue =  new ArrayList<String>();
		
		Random randomGenerator = new Random();

        for (int i = 0; i < puzzle.getNumRows() * puzzle.getNumColumns(); i++) {
            int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
			int randomColumn = randomGenerator.nextInt(puzzle.getNumColumns());
            int randomValue = randomGenerator.nextInt(9);
            if (!usedValue.contains(Integer.toString(randomValue))) {
                usedValue.add(Integer.toString(randomValue));
                puzzle.addValue(Integer.toString(randomValue), randomRow, randomColumn);
            }
        }
		
		return puzzle;
	}
}

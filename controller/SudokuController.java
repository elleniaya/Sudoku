package controller;

import model.SudokuGenerator;

import view.SudokuFrame;
import view.NewGameListener;

public class SudokuController implements Runnable, NewGameListener{
    private final SudokuFrame frame;
    private final int ROWS = 9;
    private final int COLUMNS = 9;
    private final String easyLevel = "easy";
    private final String normalLevel = "normal";
    private final String hardLevel = "hard";

    String[] puzzle = new String[ROWS * COLUMNS];

    public SudokuController() {
        this.frame = new SudokuFrame(this);
    }

    public void run() {
        newGameEasy();
    }

    @Override
    public void newGameEasy() {
        SudokuGenerator newSudoku = new SudokuGenerator(easyLevel);
        puzzle = newSudoku.generateRandomSudoku();
        frame.update(puzzle);
    }

    @Override
    public void newGameNormal() {
        SudokuGenerator newSudoku = new SudokuGenerator(normalLevel);
        puzzle = newSudoku.generateRandomSudoku();
        frame.update(puzzle);
    }

    @Override
    public void newGameHard() {
        SudokuGenerator newSudoku = new SudokuGenerator(hardLevel);
        puzzle = newSudoku.generateRandomSudoku();
        frame.update(puzzle);
    }
}

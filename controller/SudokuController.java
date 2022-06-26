package controller;

import model.SudokuGenerator;

import view.SudokuFrame;
import view.NewGameListener;
import view.ClickListener;

public class SudokuController implements Runnable, NewGameListener, ClickListener {
    private final SudokuFrame frame;
    private final String easyLevel = "easy";
    private final String normalLevel = "normal";
    private final String hardLevel = "hard";
    SudokuGenerator newSudoku;

    private final int ROWS = 9;
    private final int COLUMNS = 9;

    String[] puzzle = new String[ROWS * COLUMNS];

    public SudokuController() {
        this.frame = new SudokuFrame(this, this);
        newSudoku = new SudokuGenerator();
    }

    public void run() {
        newGameEasy();
    }

    @Override
    public void newGameEasy() {
        puzzle = newSudoku.generateRandomSudoku(easyLevel);
        newSudoku.markPuzzle();
        frame.update(puzzle);
    }

    @Override
    public void newGameNormal() {
        puzzle = newSudoku.generateRandomSudoku(normalLevel);
        newSudoku.markPuzzle();
        frame.update(puzzle);
    }

    @Override
    public void newGameHard() {
        puzzle = newSudoku.generateRandomSudoku(hardLevel);
        newSudoku.markPuzzle();
        frame.update(puzzle);
    }

    @Override
    public void onClick(String value, int row, int column) {
        puzzle = newSudoku.updatePuzzle(value, row, column);
        frame.update(puzzle);
    }
}

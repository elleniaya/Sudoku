package controller;

import model.SudokuGenerator;
import model.Records;

import view.SudokuFrame;
import view.NewGameListener;
import view.ClickListener;
import view.PassListener;
import view.ExitListener;

public class SudokuController implements Runnable, NewGameListener, ClickListener, PassListener, ExitListener {
    private final SudokuFrame frame;
    private final String easyLevel = "easy";
    private final String normalLevel = "normal";
    private final String hardLevel = "hard";

    SudokuGenerator newSudoku;
    Records newRecords = new Records();

    public SudokuController() {
        this.frame = new SudokuFrame(this, this, this, this);
        newSudoku = new SudokuGenerator();
    }

    public void run() {
        newGameEasy();
    }

    @Override
    public void newGameEasy() {
        frame.update(newSudoku.generateRandomSudoku(easyLevel));
    }

    @Override
    public void newGameNormal() {
        frame.update(newSudoku.generateRandomSudoku(normalLevel));
    }

    @Override
    public void newGameHard() {
        frame.update(newSudoku.generateRandomSudoku(hardLevel));
    }

    @Override
    public void onClick(String value, int row, int column) {
        frame.update(newSudoku.updatePuzzle(value, row, column));
    }

    @Override
    public void passbuttonListener() {
        if (newSudoku.decided()) { 
            //newRecords.addRecord(frame.getNameUser(), 9);
            frame.update(newSudoku.generateRandomSudoku(easyLevel));
        } else {
            frame.errorEndGame();
        }
    }

    @Override
    public void exitTheGame() {
        newRecords.writeRecords();
        System.exit(0);
    }
}

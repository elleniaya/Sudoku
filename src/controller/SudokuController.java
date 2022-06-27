package controller;

import model.SudokuGenerator;
import model.Records;

import view.SudokuFrame;
import view.NewGameListener;
import view.Listener;
import view.RecordsFrame;

public class SudokuController implements Runnable, NewGameListener, Listener {
    private final SudokuFrame frame;
    private final String easyLevel = "easy";
    private final String normalLevel = "normal";
    private final String hardLevel = "hard";

    private final int easyPoints = 2;
    private final int normalPoints = 5;
    private final int hardPoints = 7;

    private int POINTS;
    private String currentLevel;

    SudokuGenerator newSudoku;
    Records newRecords = new Records();

    public SudokuController() {
        this.frame = new SudokuFrame(this, this);
        newSudoku = new SudokuGenerator();
    }

    public void run() {
        newGameEasy();
    }

    @Override
    public void newGameEasy() {
        POINTS = easyPoints;
        currentLevel = easyLevel;
        frame.update(newSudoku.generateRandomSudoku(easyLevel));
    }

    @Override
    public void newGameNormal() {
        POINTS = normalPoints;
        currentLevel = normalLevel;
        frame.update(newSudoku.generateRandomSudoku(normalLevel));
    }

    @Override
    public void newGameHard() {
        POINTS = hardPoints;
        currentLevel = hardLevel;
        frame.update(newSudoku.generateRandomSudoku(hardLevel));
    }

    @Override
    public void clickNumberButton(String value, int row, int column) {
        frame.update(newSudoku.updatePuzzle(value, row, column));
    }

    @Override
    public void passbuttonListener() {
        if (newSudoku.decided()) { 
            newRecords.addRecord(frame.getNameUser(), POINTS);
            frame.sudokuSolved();
            frame.update(newSudoku.generateRandomSudoku(currentLevel));
        } else {
            frame.errorEndGame();
        }
    }

    @Override
    public void exitTheGame() {
        newRecords.writeRecords();
        System.exit(0);
    }

    @Override
    public void recordsButton() {
        new RecordsFrame(newRecords.getDataTable(), newRecords.getcolumnsHeader());
    }
}

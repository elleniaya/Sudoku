package controller;

import model.SudokuGenerator;
import model.Records;

import view.SudokuFrame;
import view.NewGameListener;
import view.Listener;
import view.RecordsFrame;
import java.io.IOException;

import main.GameSettings;

public class SudokuController implements Runnable, NewGameListener, Listener {
    private final SudokuFrame frame;
    private static final String EASY_LEVEL = "easy";
    private static final String NORMAL_LEVEL = "normal";
    private static final String HARD_LEVEL = "hard";

    private static final int EASY_POINTS = 2;
    private static final int NORMAL_POINTS = 5;
    private static final int HARD_POINTS = 7;

    private int points;
    private String currentLevel;

    private final SudokuGenerator newSudoku;
    private final Records newRecords = new Records();
    private final GameSettings settings;

    public SudokuController() throws IOException {
        this.settings = new GameSettings();
        this.frame = new SudokuFrame(this, this, settings);
        newSudoku = new SudokuGenerator(settings.rows, settings.columns, settings.boxHeight);
    }

    public void run() {
        newGameEasy();
    }

    @Override
    public void newGameEasy() {
        points = EASY_POINTS;
        currentLevel = EASY_LEVEL;
        frame.update(newSudoku.generateRandomSudoku(EASY_LEVEL));
    }

    @Override
    public void newGameNormal() {
        points = NORMAL_POINTS;
        currentLevel = NORMAL_LEVEL;
        frame.update(newSudoku.generateRandomSudoku(NORMAL_LEVEL));
    }

    @Override
    public void newGameHard() {
        points = HARD_POINTS;
        currentLevel = HARD_LEVEL;
        frame.update(newSudoku.generateRandomSudoku(HARD_LEVEL));
    }

    @Override
    public void clickNumberButton(String value, int row, int column) {
        frame.update(newSudoku.updatePuzzle(value, row, column));
    }

    @Override
    public void passButtonListener() {
        if (newSudoku.decided()) { 
            newRecords.addRecord(frame.getNameUser(), points);
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
        new RecordsFrame(newRecords.getDataTable(), newRecords.getColumnsHeader(), settings.recordsFrameWidth, settings.recordsFrameHeight);
    }
}

package main;

import controller.SudokuController; 

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        //GameSettings settings = new GameSettings();
        SudokuController controller = new SudokuController();
        controller.run();
    }
}

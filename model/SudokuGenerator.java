package model;

import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuGenerator {

    private final int OFFSET = 3;
    private final int maxMix = 10;

    private final int ROWS = 9;
	private final int COLUMNS = 9;

    private final String LEVEL;
    Random randomGenerator = new Random();

    String easy = "easy";
    String normal = "normal";
    String hard = "hard";

    String[] generatedMatrix = new String[ROWS * COLUMNS];

    public SudokuGenerator(String level) {
        this.LEVEL = level;
    }

	public String[] generateRandomSudoku() {

        try {
        if (LEVEL.equals(easy)) {
            BufferedReader br = new BufferedReader(new FileReader("EASY.txt"));
            readPuzzle(br);
            br.close();
        } else if (LEVEL.equals(normal)) {
            BufferedReader br = new BufferedReader(new FileReader("NORMAL.txt"));
            readPuzzle(br);
            br.close();
        } else {
            BufferedReader br = new BufferedReader(new FileReader("HARD.txt"));
            readPuzzle(br);
            br.close();
        }
        } catch (IOException e) {
			e.printStackTrace();
		}
		
        mixMatrix();

		return generatedMatrix;
	}

    private void readPuzzle (BufferedReader br) {
    try {
        int line = randomGenerator.nextInt((10 - 1) + 1) + 1;
        String buff_line = new String();
        int count = 0;
        while (br.ready()) {
            count++;
            buff_line = br.readLine();
            if (count == line) {
                break;
            }
        }
        generatedMatrix = buff_line.split("");
    }  catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void mixMatrix () {

        for (int i = 0; i < maxMix; i++) {
            swaprandomColumn();
            swaprandomRows();
            transposingMatrix();
            swaprandomcolumnArea();
            swaprandomrowsArea();
        }
    }

    private void transposingMatrix () {
        String[] matrix = new String[COLUMNS * ROWS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                String value = generatedMatrix[j * ROWS + i];
                matrix[i * ROWS + j] = value;
            }
        }
    }

    private void swaprandomColumn () {

        int randomArea = randomGenerator.nextInt(COLUMNS / OFFSET);

        int randomfirstCol = randomGenerator.nextInt(COLUMNS / OFFSET);

        int randomsecondCol = randomGenerator.nextInt(COLUMNS / OFFSET);

        while (randomfirstCol == randomsecondCol) {
            randomsecondCol = randomGenerator.nextInt(COLUMNS / OFFSET);
        }

        int numCol1 = randomArea * OFFSET + randomfirstCol;
        int numCol2 = randomArea * OFFSET + randomsecondCol;

        for (int i = 0; i < ROWS; i++) {
            String buff1 = generatedMatrix[numCol1 + i * ROWS];
            generatedMatrix[numCol1 + i * ROWS] = generatedMatrix[numCol2 + i * ROWS];
            generatedMatrix[numCol2 + i * ROWS] = buff1;
        }
    }

    private void swaprandomRows () {
        transposingMatrix();
        swaprandomColumn();
        transposingMatrix();
    }

    private void swaprandomcolumnArea () {

        int randomArea1 = randomGenerator.nextInt(COLUMNS / OFFSET);
        int randomArea2 = randomGenerator.nextInt(COLUMNS / OFFSET);

        while (randomArea1 == randomArea2) {
            randomArea2 = randomGenerator.nextInt(COLUMNS / OFFSET);
        }

        for (int i = 0; i < OFFSET; i++) {
            for (int j = 0; j < ROWS; j++) {
                String buff1 = generatedMatrix[randomArea1 * OFFSET + i + j * ROWS];
                generatedMatrix[randomArea1 * OFFSET + i + j * ROWS] = generatedMatrix[randomArea2 * OFFSET + i + j * ROWS];
                generatedMatrix[randomArea2 * OFFSET + i + j * ROWS] = buff1;
            }
        }
    }

    private void swaprandomrowsArea () {
        transposingMatrix();
        swaprandomcolumnArea();
        transposingMatrix();
    }
}

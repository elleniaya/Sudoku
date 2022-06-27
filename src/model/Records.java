package model;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Records {
    private String[] columnsHeader = new String[] {"Name", "Points"};
    private static int NOTUSED = -1;
    String[][] dataTable;

    ArrayList<Pair> data = new ArrayList<>();

    public Records() {
        readRecords();
    }

    private void readRecords() {
        try{
            String fileName = "src/RESOURCES/records.txt";
            Path path = Paths.get(fileName);
            Scanner scanner = new Scanner(path);
            scanner.useDelimiter(System.getProperty("line.separator"));
            while(scanner.hasNextLine()){
                data.add(parseLine(scanner.nextLine()));
            }
            scanner.close();
            sortTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Pair parseLine(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(" ");
        String name = scanner.next();
        String points = scanner.next();
        scanner.close();
        return new Pair(name, Integer.parseInt(points));
    }

    public void writeRecords() {
        try {
            FileWriter writer = new FileWriter("records.txt");
            for (Pair pair : data) {
                writer.write(pair.name + " " + pair.points + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRecord(String name, int glasses) {
        if (nameUsed(name) != NOTUSED) {
            data.get(nameUsed(name)).updatePoints(glasses);
        } else {
            data.add(new Pair(name, glasses));
        }
        sortTable();
        dataTable = new String[data.size()][2];
        updateDataTable();
    }

    private int nameUsed(String name) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).name.equals(name)) {
                return i;
            }
        }
        return NOTUSED;
    }

    public String[] getcolumnsHeader() {
        return columnsHeader;
    }

    public String[][] getDataTable() {
        dataTable = new String[data.size()][2];
        updateDataTable();
        return dataTable;
    }

    private void updateDataTable() {
        for (int i = 0; i < data.size(); i++) {
            dataTable[i][0] = data.get(i).name;
            dataTable[i][1] = Integer.toString(data.get(i).points);
        }
    }

    private void sortTable() {
        Collections.sort(data, Comparator.comparingInt(Pair::getPoints));
        Collections.reverse(data);
    }
}

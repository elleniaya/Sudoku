package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GameSettings {

    public int sudokuFrameHeight;
    public int sudokuFrameWidth;
    public int windowPanelHeight;
    public int windowPanelWidth;
    public int buttonSelectionPanelHeight;
    public int buttonSelectionPanelWidth;
    public int passButtonPanelHeight;
    public int passButtonPanelWidth;
    public int passButtonHeight;
    public int passButtonWidth;
    public int numberButtonHeight;
    public int numberButtonWidth;
    public int recordsFrameHeight;
    public int recordsFrameWidth;
    public int aboutFrameHeight;
    public int aboutFrameWidth;
    public int rows;
    public int columns;
    public int boxHeight;
    public int boxWidth;


    private final Properties properties = new Properties();

    public GameSettings() throws IOException{
        FileInputStream file = new FileInputStream("src/RESOURCES/settings.properties");
        properties.load(file);

        sudokuFrameHeight = getInteger(properties, "sudokuFrameHeight");
        sudokuFrameWidth = getInteger(properties, "sudokuFrameWidth");
        windowPanelHeight = getInteger(properties, "windowPanelHeight");
        windowPanelWidth = getInteger(properties, "windowPanelWidth");
        buttonSelectionPanelHeight = getInteger(properties, "buttonSelectionPanelHeight");
        buttonSelectionPanelWidth = getInteger(properties, "buttonSelectionPanelWidth");
        passButtonPanelHeight = getInteger(properties, "passButtonPanelHeight");
        passButtonPanelWidth = getInteger(properties, "passButtonPanelWidth");
        passButtonHeight = getInteger(properties, "passButtonHeight");
        passButtonWidth = getInteger(properties, "passButtonWidth");
        numberButtonHeight = getInteger(properties, "numberButtonHeight");
        numberButtonWidth = getInteger(properties, "numberButtonWidth");
        recordsFrameHeight = getInteger(properties, "recordsFrameHeight");
        recordsFrameWidth = getInteger(properties, "recordsFrameWidth");
        aboutFrameHeight = getInteger(properties, "aboutFrameHeight");
        aboutFrameWidth = getInteger(properties, "aboutFrameWidth");
        rows = getInteger(properties, "rows");
        columns = getInteger(properties, "columns");
        boxHeight = getInteger(properties, "boxHeight");
        boxWidth = getInteger(properties, "boxWidth");
    }

    private int getInteger(Properties property, String s) {
        return Integer.parseInt(property.getProperty(s));
    }
    
}

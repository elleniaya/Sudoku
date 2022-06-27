package view;

import javax.swing.*;
import java.awt.*;

public class RecordsFrame extends JFrame {
    private static final String NAME = "Records";

    public RecordsFrame(String[][] data, String[] columnsHeader) {
        super(NAME);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(new Dimension(650, 650));

        JTable table = new JTable(data, columnsHeader);

        table.setRowHeight(30);
        table.setEnabled(false);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));
        setContentPane(contents);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

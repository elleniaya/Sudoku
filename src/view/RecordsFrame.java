package view;

import javax.swing.*;
import java.awt.*;

public class RecordsFrame extends JFrame {
    private static final String NAME = "Records";

    public RecordsFrame() {
        super(NAME);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(new Dimension(620,650));

        RecordsPanel panel = new RecordsPanel();
        this.add(panel);

        this.setLocationRelativeTo(null);
		    this.setVisible(true);
    }
}

package view;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {
    private static final String NAME = "About";

    public AboutFrame() {
        super(NAME);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//this.setMinimumSize(new Dimension(620,600));
        this.setSize(new Dimension(620,600));

        AboutPanel panel = new AboutPanel();
        this.add(panel);

        this.setLocationRelativeTo(null);
		this.setVisible(true);
    }
}

package view;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {
    private static final String NAME = "About";

    public AboutFrame(int width, int height) {
        super(NAME);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(new Dimension(width,height));

        AboutPanel panel = new AboutPanel();
        
        this.add(panel);

        this.setLocationRelativeTo(null);
		this.setVisible(true);
    }
}

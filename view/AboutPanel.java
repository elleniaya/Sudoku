package view;

import java.awt.*;
import javax.swing.*;

public class AboutPanel extends JPanel {
    @Override
	protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("rules.png").getImage();
        g.drawImage(image, 0, 0, this);
    }
}

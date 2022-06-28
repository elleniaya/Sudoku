package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import javax.swing.JPanel;

public class SudokuPanel extends JPanel {

	private final int ROWS;
	private final int COLUMNS;
	private final int BOX_WIDTH;
	private final int BOX_HEIGHT;
	private int selectedCol;
	private int selectedRow;

	private String[] puzzle;

	private int usedWidth;
	private int usedHeight;
	private int Size;
	
	public SudokuPanel(int rows, int columns, int boxWidth, int boxHeight, int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseListener(new SudokuPanelMouseAdapter());
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.BOX_HEIGHT = boxHeight;
		this.BOX_WIDTH = boxWidth;
		puzzle = new String[ROWS * COLUMNS];
		usedWidth = 0;
		usedHeight = 0;
		Size = 26;
	}
	
	public void newSudokuPuzzle(String[] puzzle) {
		this.puzzle = puzzle;
	}
	
	public void setFontSize(int Size) {
		this.Size = Size;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(1.0f,1.0f,1.0f));
		
		int slotWidth = this.getWidth()/COLUMNS;
		int slotHeight = this.getHeight()/ROWS;
		
		usedWidth = (this.getWidth()/COLUMNS)*COLUMNS;
		usedHeight = (this.getHeight()/ROWS)*ROWS;
		
		g2d.fillRect(0, 0,usedWidth,usedHeight);
		
		g2d.setColor(new Color(0.0f, 0.0f, 0.0f)); 
		for (int x = 0; x <= usedWidth; x += slotWidth) {
			if((x / slotWidth) % BOX_WIDTH == 0) {
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine(x, 0, x, usedHeight);
			}
			else {
				g2d.setStroke(new BasicStroke(1));
				g2d.drawLine(x, 0, x, usedHeight);
			}
		}

		for (int y = 0; y <= usedHeight; y += slotHeight) {
			if((y / slotHeight) % BOX_HEIGHT == 0) {
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine(0, y, usedWidth, y);
			}
			else {
				g2d.setStroke(new BasicStroke(1));
				g2d.drawLine(0, y, usedWidth, y);
			}
		}
		
		Font f = new Font("Times New Roman", Font.PLAIN, Size);
		g2d.setFont(f);
		FontRenderContext fContext = g2d.getFontRenderContext();
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if (!puzzle[row * ROWS + col].equals("0")) {
				    int textWidth = (int) f.getStringBounds(puzzle[row * ROWS + col], fContext).getWidth();
				    int textHeight = (int) f.getStringBounds(puzzle[row * ROWS + col], fContext).getHeight();
				    g2d.drawString(puzzle[row * ROWS + col], (col*slotWidth)+((slotWidth/2)-(textWidth/2)), (row*slotHeight)+((slotHeight/2)+(textHeight/2)));
				}
			}
		}

		if(selectedCol != -1 && selectedRow != -1) {
			g2d.setColor(new Color(0.0f,0.0f,1.0f,0.3f));
			g2d.fillRect(selectedCol * slotWidth, selectedRow * slotHeight, slotWidth, slotHeight);
		}
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public int getSelectedCol() {
		return selectedCol;
	}

	private class SudokuPanelMouseAdapter extends MouseInputAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				int slotWidth = usedWidth / COLUMNS;
				int slotHeight = usedHeight / ROWS;
				selectedRow = e.getY() / slotHeight;
				selectedCol = e.getX() / slotWidth;
				e.getComponent().repaint();
			}
		}
	}
}

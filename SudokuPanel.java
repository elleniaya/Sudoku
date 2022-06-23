import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;

import javax.swing.JPanel;

public class SudokuPanel extends JPanel {

	private SudokuPuzzle puzzle;
	private int usedWidth;
	private int usedHeight;
	private int Size;
	
	public SudokuPanel() {
		this.setPreferredSize(new Dimension(540,450));
		this.puzzle = new SudokuGenerator().generateRandomSudoku(SudokuPuzzleType.NINEBYNINE);
		usedWidth = 0;
		usedHeight = 0;
		Size = 26;
	}
	
	
	public SudokuPanel(SudokuPuzzle puzzle) {
		this.setPreferredSize(new Dimension(540,450));
		this.puzzle = puzzle;
		usedWidth = 0;
		usedHeight = 0;
		Size = 26;
	}
	
	public void newSudokuPuzzle(SudokuPuzzle puzzle) {
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
		
		int slotWidth = this.getWidth()/puzzle.getNumColumns();
		int slotHeight = this.getHeight()/puzzle.getNumRows();
		
		usedWidth = (this.getWidth()/puzzle.getNumColumns())*puzzle.getNumColumns();
		usedHeight = (this.getHeight()/puzzle.getNumRows())*puzzle.getNumRows();
		
		g2d.fillRect(0, 0,usedWidth,usedHeight);
		
		g2d.setColor(new Color(0.0f, 0.0f, 0.0f)); 
		for (int x = 0; x <= usedWidth; x += slotWidth) {
			if((x / slotWidth) % puzzle.getBoxWidth() == 0) {
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine(x, 0, x, usedHeight);
			}
			else {
				g2d.setStroke(new BasicStroke(1));
				g2d.drawLine(x, 0, x, usedHeight);
			}
		}

		for (int y = 0; y <= usedHeight; y += slotHeight) {
			if((y / slotHeight) % puzzle.getBoxHeight() == 0) {
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
		for(int row = 0; row < puzzle.getNumRows(); row++) {
			for(int col = 0;col < puzzle.getNumColumns(); col++) {
				int textWidth = (int) f.getStringBounds(puzzle.getValue(row, col), fContext).getWidth();
				int textHeight = (int) f.getStringBounds(puzzle.getValue(row, col), fContext).getHeight();
				g2d.drawString(puzzle.getValue(row, col), (col*slotWidth)+((slotWidth/2)-(textWidth/2)), (row*slotHeight)+((slotHeight/2)+(textHeight/2)));
			}
		}
	}
}

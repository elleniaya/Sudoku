import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.*;

public class SudokuFrame extends JFrame {

	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setMinimumSize(new Dimension(800,600));

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");

		JMenu newGame = new JMenu("New Game");

		JMenuItem levelEasy = new JMenuItem("Easy");
		levelEasy.addActionListener(new NewGameListener(SudokuPuzzleType.NINEBYNINE, 26));

		JMenuItem levelNormal = new JMenuItem("Normal");
		levelNormal.addActionListener(new NewGameListener(SudokuPuzzleType.NINEBYNINE, 26));

		JMenuItem levelHard = new JMenuItem("Hard");
		levelHard.addActionListener(new NewGameListener(SudokuPuzzleType.NINEBYNINE, 26));

	    JMenuItem exit = new JMenuItem("Exit", new ImageIcon("exit.png"));

		exit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.exit(0);
                }
            });

		//newGame.add(nineByNineGame);

		file.add(newGame);
		file.addSeparator();
		file.add(exit);

		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90,500));

		sPanel = new SudokuPanel();
		
		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);
		
		rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);

		//регистрация
		String name;                      
        name = JOptionPane.showInputDialog("Enter your name:");

		String full;                 
		full = "Hello " + name + "!" + "\n" + "Rules of the game:" + "\n" + "You need to fill in the free cells with numbers from 1 to 9 so that in each row, in each column, and in each small 3x3 square, each number occurs only once.";

		JOptionPane.showMessageDialog(null, full);
	}

	public void rebuildInterface(SudokuPuzzleType puzzleType, int fontSize) {
		SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateRandomSudoku(puzzleType);
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}
	
	private class NewGameListener implements ActionListener {

		private SudokuPuzzleType puzzleType;
		private int Size;
		public NewGameListener(SudokuPuzzleType puzzleType, int Size) {
			this.puzzleType = puzzleType;
			this.Size = Size;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(puzzleType, Size);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SudokuFrame frame = new SudokuFrame();
				frame.setVisible(true);
			}
		});
	}
}

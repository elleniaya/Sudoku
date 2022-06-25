package view;

import java.awt.*;
import javax.swing.*;

public class SudokuFrame extends JFrame {

	private static final String NAME = "Sudoku";
	private static final String GAME = "Game";
	private static final String NEWGAME = "New Game";
	private static final String EXIT = "Exit";
	private static final String EASY = "Easy";
	private static final String NORMAL = "Normal";
	private static final String HARD = "Hard";
	private static final String RULES = "You need to fill in the free cells with numbers from 1 to 9 so that in each row, in each column, and in each small 3x3 square, each number occurs only once.";

	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	private final NewGameListener newGameListener;
	
	public SudokuFrame(NewGameListener newGameListener) {
		super(NAME);

		this.newGameListener = newGameListener;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));

		messageDialog();
		setupMenu();
		createPanel();

		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}

	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu(GAME);

		JMenu newGame = new JMenu(NEWGAME);

		JMenuItem levelEasy = new JMenuItem(EASY);
		levelEasy.addActionListener(event -> newGameListener.newGameEasy());

		JMenuItem levelNormal = new JMenuItem(NORMAL);
		levelNormal.addActionListener(event -> newGameListener.newGameNormal());

		JMenuItem levelHard = new JMenuItem(HARD);
		levelHard.addActionListener(event -> newGameListener.newGameHard());

	    JMenuItem exit = new JMenuItem(EXIT, new ImageIcon("exit.png"));
		exit.addActionListener(event -> System.exit(0));

		newGame.add(levelEasy);
		newGame.addSeparator();
		newGame.add(levelNormal);
		newGame.addSeparator();
		newGame.add(levelHard);

		menu.add(newGame);
		menu.addSeparator();
		menu.add(exit);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	private void createPanel() {
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));

		//buttonSelectionPanel = new JPanel();
		//buttonSelectionPanel.setPreferredSize(new Dimension(90,500));

		sPanel = new SudokuPanel();
		
		windowPanel.add(sPanel);
		//windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);

	}

	private void messageDialog() {
		String name;                      
        name = JOptionPane.showInputDialog("Enter your name:");

		String full;                 
		full = "Hello " + name + "!" + "\n" + "Rules of the game:" + "\n" + RULES;

		JOptionPane.showMessageDialog(null, full);
	}

	public void update(String[] puzzle) {
		sPanel.newSudokuPuzzle(puzzle);
		sPanel.repaint();
	}
}

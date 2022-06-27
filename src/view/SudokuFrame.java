package view;

import java.awt.*;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SudokuFrame extends JFrame {

	private static final String NAME = "Sudoku";
	private static final String GAME = "Game";
	private static final String NEWGAME = "New Game";
	private static final String ABOUT = "About";
	private static final String RECORDS = "Records";
	private static final String EXIT = "Exit";
	private static final String EASY = "Easy";
	private static final String NORMAL = "Normal";
	private static final String HARD = "Hard";
	private static final String PASS = "PASS";

	private static final String MESSAGEPASS = "Please fill in all empty cells!";
	private static final String MESSAGENAME = "Please enter your name!";
	private static final String MESSAGESUDOKUSOLVED = "Congratulations! You solved the Sudoku!";

    private static final String[] VALIDVALUES = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

	private JPanel buttonSelectionPanel, passButtonPanel;
	private SudokuPanel sPanel;

	private final NewGameListener newGameListener;
	private final Listener listener;

	private String name;
	
	public SudokuFrame(NewGameListener newGameListener, Listener listener) {
		super(NAME);

		this.newGameListener = newGameListener;
		this.listener = listener;

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
				listener.exitTheGame();
			}
        });

		this.setMinimumSize(new Dimension(900,600));

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

		JMenuItem About = new JMenuItem(ABOUT);
		About.addActionListener(event -> new AboutFrame());

		JMenuItem Records = new JMenuItem(RECORDS);
		Records.addActionListener(event -> listener.recordsButton());

		JMenuItem levelEasy = new JMenuItem(EASY);
		levelEasy.addActionListener(event -> newGameListener.newGameEasy());

		JMenuItem levelNormal = new JMenuItem(NORMAL);
		levelNormal.addActionListener(event -> newGameListener.newGameNormal());

		JMenuItem levelHard = new JMenuItem(HARD);
		levelHard.addActionListener(event -> newGameListener.newGameHard());

	    JMenuItem exit = new JMenuItem(EXIT);
		exit.addActionListener(event -> listener.exitTheGame());

		newGame.add(levelEasy);
		newGame.addSeparator();
		newGame.add(levelNormal);
		newGame.addSeparator();
		newGame.add(levelHard);

		menu.add(newGame);
		menu.addSeparator();
		menu.add(About);
		menu.addSeparator();
		menu.add(Records);
		menu.addSeparator();
		menu.add(exit);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	private void createPanel() {
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800, 600));

		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90, 450));

		passButtonPanel = new JPanel();
		passButtonPanel.setPreferredSize(new Dimension(100, 60));

		sPanel = new SudokuPanel();
		
		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		windowPanel.add(passButtonPanel);
		this.add(windowPanel);
	}

	private void messageDialog() {                   
        name = JOptionPane.showInputDialog("Enter your name:");
		while (name.isEmpty()) {
			JOptionPane.showMessageDialog(null, MESSAGENAME);
			name = JOptionPane.showInputDialog("Enter your name:");
		}
	}

	public String getNameUser() {
		return name;
	}

	public void update(String[] puzzle) {
		sPanel.newSudokuPuzzle(puzzle);
		buttonSelectionPanel.removeAll();
		for(String value : VALIDVALUES) {
			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(50,40));
			b.addActionListener(event -> listener.clickNumberButton(b.getText(), sPanel.getselectedRow(), sPanel.getselectedCol()));
			buttonSelectionPanel.add(b);
		}

		JButton passButton = new JButton(PASS);
		passButton.setPreferredSize(new Dimension(90, 50));
		passButton.addActionListener(event -> listener.passbuttonListener());
		passButtonPanel.add(passButton);
		passButtonPanel.repaint();

		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}

	public void errorEndGame() {
		JOptionPane.showMessageDialog(null, MESSAGEPASS);
	}

	public void sudokuSolved() {
		JOptionPane.showMessageDialog(null, MESSAGESUDOKUSOLVED);
	}
}

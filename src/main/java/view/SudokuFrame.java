package view;

import java.awt.*;
import javax.swing.*;

import main.GameSettings;

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

	private static final String MESSAGE_PASS = "Please fill in all empty cells!";
	private static final String MESSAGE_NAME = "Please enter your name!";
	private static final String MESSAGE_SUDOKU_SOLVED = "Congratulations! You solved the Sudoku!";

    private static final String[] VALID_VALUES = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

	private JPanel buttonSelectionPanel, passButtonPanel;
	private SudokuPanel sPanel;

	private final NewGameListener newGameListener;
	private final Listener listener;
	private final GameSettings settings;

	private String name;
	
	public SudokuFrame(NewGameListener newGameListener, Listener listener, GameSettings settings) {
		super(NAME);

		this.newGameListener = newGameListener;
		this.listener = listener;
		this.settings = settings;

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
				listener.exitTheGame();
			}
        });

		//this.setMinimumSize(new Dimension(900,600));
		this.setMinimumSize(new Dimension(settings.sudokuFrameWidth,settings.sudokuFrameHeight));

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

		JMenuItem about = new JMenuItem(ABOUT);
		about.addActionListener(event -> new AboutFrame(settings.aboutFrameWidth, settings.aboutFrameHeight));

		JMenuItem records = new JMenuItem(RECORDS);
		records.addActionListener(event -> listener.recordsButton());

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
		menu.add(about);
		menu.addSeparator();
		menu.add(records);
		menu.addSeparator();
		menu.add(exit);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	private void createPanel() {
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		//windowPanel.setPreferredSize(new Dimension(800, 600));
		windowPanel.setPreferredSize(new Dimension(settings.windowPanelWidth, settings.windowPanelHeight));

		buttonSelectionPanel = new JPanel();

		//buttonSelectionPanel.setPreferredSize(new Dimension(90, 450));
		buttonSelectionPanel.setPreferredSize(new Dimension(settings.buttonSelectionPanelWidth, settings.buttonSelectionPanelHeight));

		passButtonPanel = new JPanel();

		//passButtonPanel.setPreferredSize(new Dimension(100, 60));
		passButtonPanel.setPreferredSize(new Dimension(settings.passButtonPanelWidth, settings.passButtonPanelHeight));

		sPanel = new SudokuPanel(settings.rows, settings.columns, settings.boxWidth, settings.boxHeight, settings.windowPanelWidth, settings.windowPanelHeight);
		
		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		windowPanel.add(passButtonPanel);
		this.add(windowPanel);
	}

	private void messageDialog() {                   
        name = JOptionPane.showInputDialog("Enter your name:");
		while (name.isEmpty()) {
			JOptionPane.showMessageDialog(null, MESSAGE_NAME);
			name = JOptionPane.showInputDialog("Enter your name:");
		}
	}

	public String getNameUser() {
		return name;
	}

	public void update(String[] puzzle) {
		sPanel.newSudokuPuzzle(puzzle);
		buttonSelectionPanel.removeAll();
		for(String value : VALID_VALUES) {
			JButton b = new JButton(value);
			//b.setPreferredSize(new Dimension(50,40));
			b.setPreferredSize(new Dimension(settings.numberButtonWidth,settings.numberButtonHeight));
			b.addActionListener(event -> listener.clickNumberButton(b.getText(), sPanel.getSelectedRow(), sPanel.getSelectedCol()));
			buttonSelectionPanel.add(b);
		}

		JButton passButton = new JButton(PASS);
		//passButton.setPreferredSize(new Dimension(90, 50));
		passButton.setPreferredSize(new Dimension(settings.passButtonWidth, settings.passButtonHeight));
		passButton.addActionListener(event -> listener.passButtonListener());
		passButtonPanel.add(passButton);
		passButtonPanel.repaint();

		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}

	public void errorEndGame() {
		JOptionPane.showMessageDialog(null, MESSAGE_PASS);
	}

	public void sudokuSolved() {
		JOptionPane.showMessageDialog(null, MESSAGE_SUDOKU_SOLVED);
	}
}

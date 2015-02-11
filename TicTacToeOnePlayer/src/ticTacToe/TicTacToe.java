package ticTacToe;

import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {

	static Random rand = new Random();
	static int[][] gameDataBase = PlayerFirst.dataBase;
	static boolean foundMatch = false;
	static int computerMoveInteger;
	static int[] game = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static boolean rotateInitialized = false;
	static final int CLOCKWISE = 1;
	static final int COUNTERCLOCKWISE = 2;
	static final int OPPOSITE = 3;
	static final int NONE = 4;
	static int rotationConstant;
	static int[] clockwise = { 0, 7, 4, 1, 8, 5, 2, 9, 6, 3 };
	static int[] opposite = { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
	static int[] counterClockWise = { 0, 3, 6, 9, 2, 5, 8, 1, 4, 7 };
	static boolean gameOver = false;
	static int winSituation;
	static boolean XTurn = true;
	static String turnChar;
	static int turnNumber = 1;
	final static int NOWIN = 0;
	final static int XWIN = 1;
	final static int OWIN = 2;
	final static int TIE = 3;
	static int turnCount = 0;
	static boolean computerTurn = false;
	JPanel[] row = new JPanel[5];
	static JButton[] button = new JButton[11];
	static String[] buttonString = { "New Game: X", "-", "-", "-", "-", "-",
			"-", "-", "-", "-", "New Game: O" };
	int[] DimW = { 110, 55 };
	int[] DimH = { 50 };
	Dimension newGameButtonDimension = new Dimension(DimW[0], DimH[0]);
	Dimension playButtonDimension = new Dimension(DimW[1], DimH[0]);
	public static boolean playerTurn = true;
	private final static int PLAYERO = 1;
	private final static int PLAYERX = 2;
	static JTextArea display = new JTextArea(2, 15);
	public static int rotatedComputerMoveInteger;
	private static int arrayTestNumber = 0;

	TicTacToe() {
		super("TicTacToe");
		ImageIcon icon = new ImageIcon("tic-tac-pic.png");
		setIconImage(icon.getImage());
		setSize(250, 275);
		setResizable(false);
		setDesign();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		GridLayout grid = new GridLayout(5, 3);
		setLayout(grid);
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 1, 1);
		button[0] = new JButton();
		button[0].setText(buttonString[0]);
		button[0].setForeground(Color.BLACK);
		button[0].setBackground(Color.WHITE);
		button[0].addActionListener(this);
		button[0].setPreferredSize(newGameButtonDimension);
		button[10] = new JButton();
		button[10].setText(buttonString[10]);
		button[10].setForeground(Color.BLACK);
		button[10].setBackground(Color.WHITE);
		button[10].addActionListener(this);
		button[10].setPreferredSize(newGameButtonDimension);
		for (int i = 1; i < 10; i++) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setForeground(Color.BLACK);
			button[i].setBackground(Color.WHITE);
			button[i].addActionListener(this);
			button[i].setPreferredSize(playButtonDimension);
		}

		for (int i = 0; i < 5; i++) {
			row[i] = new JPanel();
			row[i].setLayout(flow);
		}
		display.setEditable(false);
		row[0].add(button[0]);
		row[0].add(button[10]);
		for (int i = 1; i < 4; i++)
			row[1].add(button[i]);
		for (int i = 4; i < 7; i++)
			row[2].add(button[i]);
		for (int i = 7; i < 10; i++)
			row[3].add(button[i]);
		row[4].add(display);
		for (int i = 0; i < 5; i++)
			add(row[i]);
		setVisible(true);
		System.out.println("Board Initiated");
		newGame(PLAYERX);
	}

	public static void newGame(int gameType) {
		turnCount = 0;
		turnNumber = 1;
		System.out.println("newGame()");
		if (gameType == PLAYERX) {
			gameDataBase = PlayerFirst.dataBase;
			playerTurn = true;
			System.out.println("Set DataBase Player X");
		} else if (gameType == PLAYERO) {
			playerTurn = false;
			gameDataBase = CompFirst.dataBase;
			rotateInitialized = true;
			rotationConstant = rand.nextInt(4) + 1;
			System.out.println("Set DataBase Player O");
		}
		rotateInitialized = false;
		for (int i = 0; i < 9; i++) {
			game[i] = 0;
			System.out.println("game[" + i + "] set to 0");
		}
		display.setText("X's Turn!");
		TicTacToe.XTurn = true;
		TicTacToe.gameOver = false;
		for (int i = 1; i < 10; i++) {
			TicTacToe.buttonString[i] = "-";
			TicTacToe.button[i].setText(TicTacToe.buttonString[i]);
		}
		if (gameType == PLAYERO) {
			compTurn();
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == button[0]) {
			System.out.println("New Game X Clicked");
			newGame(PLAYERX);
		}
		if (!gameOver && playerTurn) {
			if (ae.getSource() == button[1]) {
				System.out.println("Button 1 Press");
				turn(1);
			}
			if (ae.getSource() == button[2]) {
				System.out.println("Button 2 Press");
				turn(2);
			}
			if (ae.getSource() == button[3]) {
				System.out.println("Button 3 Press");
				turn(3);
			}
			if (ae.getSource() == button[4]) {
				System.out.println("Button 4 Press");
				turn(4);
			}
			if (ae.getSource() == button[5]) {
				System.out.println("Button 5 Press");
				turn(5);
			}
			if (ae.getSource() == button[6]) {
				System.out.println("Button 6 Press");
				turn(6);
			}
			if (ae.getSource() == button[7]) {
				System.out.println("Button 7 Press");
				turn(7);
			}
			if (ae.getSource() == button[8]) {
				System.out.println("Button 8 Press");
				turn(8);
			}
			if (ae.getSource() == button[9]) {
				System.out.println("Button 9 Press");
				turn(9);
			}
		}
		if (ae.getSource() == button[10]) {
			System.out.println("New Game O Clicked");
			newGame(PLAYERO);
		}

	}

	public static void setDesign() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
	}

	public static String turnChar() {
		System.out.println("turnChar()");
		if (XTurn) {
			turnChar = "X";
		} else {
			turnChar = "O";
		}
		XTurn = !XTurn;
		System.out.println("turnChar = " + turnChar);
		return turnChar;

	}

	public static int checkWin() {
		System.out.println("Checkwin()");
		turnCount++;
		winSituation = NOWIN;
		if ((buttonString[1].equals(buttonString[2]))
				&& (buttonString[2].equals(buttonString[3]))) {
			if (buttonString[1].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[1].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[4].equals(buttonString[5]))
				&& (buttonString[5].equals(buttonString[6]))) {
			if (buttonString[4].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[4].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[7].equals(buttonString[8]))
				&& (buttonString[8].equals(buttonString[9]))) {
			if (buttonString[7].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[7].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[1].equals(buttonString[4]))
				&& (buttonString[4].equals(buttonString[7]))) {
			if (buttonString[1].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[1].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[2].equals(buttonString[5]))
				&& (buttonString[5].equals(buttonString[8]))) {
			if (buttonString[2].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[2].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[3].equals(buttonString[6]))
				&& (buttonString[6].equals(buttonString[9]))) {
			if (buttonString[3].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[3].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[1].equals(buttonString[5]))
				&& (buttonString[5].equals(buttonString[9]))) {
			if (buttonString[1].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[1].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((buttonString[3].equals(buttonString[5]))
				&& (buttonString[5].equals(buttonString[7]))) {
			if (buttonString[3].equals("X")) {
				winSituation = XWIN;
			} else if (buttonString[3].equals("O")) {
				winSituation = OWIN;
			}
		}
		if ((turnCount == 9) && (winSituation == NOWIN)) {
			winSituation = TIE;
		}
		System.out.println(winSituation);
		return winSituation;
	}

	public static void compTurn() {
		if (display.getText().equals("X's Turn!")) {
			System.out.println("Set Text O Turn");
			display.setText("O's Turn!");
		} else if (display.getText().equals("O's Turn!")) {
			System.out.println("Set Text X Turn");
			display.setText("X's Turn!");
		}
		arrayTestNumber = 0;
		computerMoveInteger = 0;
		int[] tempGameArray = new int[turnNumber - 1];
		int[] tempDataBaseArray = new int[turnNumber - 1];
		foundMatch = false;
		tempGameArray = Arrays.copyOf(game, turnNumber - 1);
		Object[] tempGameArray2 = { tempGameArray };
		if (!(turnNumber == 1)) {
			while (!foundMatch) {
				System.out.println("TestingArray " + arrayTestNumber);
				for (int x = 0; x < turnNumber - 1; x++) {
					// System.out.println("Setting tempDataBaseArray[" + x +
					// "] to" + gameDataBase[arrayTestNumber][x]);
					tempDataBaseArray[x] = gameDataBase[arrayTestNumber][x];
				}
				Object[] tempDataBaseArray2 = { tempDataBaseArray };
				if (Arrays.deepEquals(tempGameArray2, tempDataBaseArray2)) {
					computerMoveInteger = gameDataBase[arrayTestNumber][turnNumber - 1];
					foundMatch = true;
				}
				arrayTestNumber++;
			}
		} else {
			computerMoveInteger = 1;
		}
		game[turnNumber - 1] = computerMoveInteger;
		rotatedComputerMoveInteger = outputBoardRotation(computerMoveInteger);
		buttonString[rotatedComputerMoveInteger] = turnChar();
		button[rotatedComputerMoveInteger]
				.setText(buttonString[rotatedComputerMoveInteger]);
		turnNumber++;
		playerTurn = true;
		switch (checkWin()) {
		case XWIN:
			gameOver = true;
			display.setText("Nice Job Xs! You Win!");
			break;
		case OWIN:
			gameOver = true;
			display.setText("Nice Job Os! You Win!");
			break;
		case NOWIN:
			break;
		case TIE:
			gameOver = true;
			display.setText("You Tied! Play Again!");
			break;
		}
	}

	public static void turn(int buttonNum) {
		System.out.println("turn(" + buttonNum + ")");
		int buttonNumber = buttonNum;
		if (buttonString[buttonNumber].equals("-")) {
			System.out.println("Valid Turn");
			game[turnNumber - 1] = boardRotation(buttonNumber);
			System.out.println("Game Array Corrected");
			buttonString[buttonNumber] = turnChar();
			System.out.println("string values updated, buttonString["
					+ buttonNum + "] = " + buttonString[buttonNumber]);
			button[buttonNumber].setText(buttonString[buttonNumber]);
			System.out.println("Board Updated");
			if (display.getText().equals("X's Turn!")) {
				System.out.println("Set Text O Turn");
				display.setText("O's Turn!");
			} else if (display.getText().equals("O's Turn!")) {
				System.out.println("Set Text X Turn");
				display.setText("X's Turn!");
			}
			System.out.println("checkwin:");
			switch (checkWin()) {
			case XWIN:
				gameOver = true;
				display.setText("Nice Job Xs! You Win!");
				break;
			case OWIN:
				gameOver = true;
				display.setText("Nice Job Os! You Win!");
				break;
			case NOWIN:
				break;
			case TIE:
				gameOver = true;
				display.setText("You Tied! Play Again!");
				break;
			}
			turnNumber++;
			playerTurn = false;
			System.out.println("CompTurn()");
			if (!gameOver) {
				compTurn();
			}
		}
	}

	private static int outputBoardRotation(int input) {
		switch (rotationConstant) {
		case NONE:
			return input;
		case CLOCKWISE:
			return counterClockWise[input];
		case OPPOSITE:
			return opposite[input];
		case COUNTERCLOCKWISE:
			return clockwise[input];
		}
		return 0;
	}

	public static int boardRotation(int input) {
		if (!rotateInitialized) {
			if (input == 1 || input == 2) {
				rotationConstant = NONE;
				rotateInitialized = true;
			}
			if (input == 5) {
				rotationConstant = rand.nextInt(4) + 1;
				rotateInitialized = true;

			}
			if (input == 3 || input == 6) {
				rotationConstant = CLOCKWISE;
				rotateInitialized = true;
			}
			if (input == 9 || input == 8) {
				rotationConstant = OPPOSITE;
				rotateInitialized = true;
			}
			if (input == 4 || input == 7) {
				rotationConstant = COUNTERCLOCKWISE;
				rotateInitialized = true;
			}

		}
		switch (rotationConstant) {
		case NONE:
			return input;
		case CLOCKWISE:
			return clockwise[input];
		case OPPOSITE:
			return opposite[input];
		case COUNTERCLOCKWISE:
			return counterClockWise[input];
		}
		return 0;
	}

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
	}

}

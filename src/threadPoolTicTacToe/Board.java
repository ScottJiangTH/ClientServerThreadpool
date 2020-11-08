package threadPoolTicTacToe;
/**
 * 
 * Provides a text-based game board for the "tic-tac-toe" game, and enables adding marks
 * while providing methods to the game board and methods to check if any Player object 
 * has reached a win or tie situation.
 * 
 * 
 * @author Scott Tianhan Jiang
 * @version 2.0
 * @since Nov 8, 2020
 *
 */
public class Board implements Constants {
	private char theBoard[][];
	private int markCount;

	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Checks and returns the mark in current cell.
	 * @param row represents the row number
	 * @param col represents the column number
	 * @return the mark as X, O or blank space in the current cell
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Indicates the current fullness status of the board with a boolean value.
	 * @return yes if full, no if not full.
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Checks if player X wins.
	 * @return yes if X wins, no if O wins.
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Checks if player O wins.
	 * @return yes if O wins, no if X wins.
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Prints out the entire 3 row X 3 column game board with X and/or O marks.
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	 * Adds marks passed from arguments to the specific cell of theBoard object, 
	 * and increment the mark count.
	 * @param row represents the row number
	 * @param col represents the column number
	 * @param mark is the parameter with value of X or O
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Resets game board content, replace all existing marks with space character 
	 * and reset the mark count parameter to 0.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * The algorithm that checks if the argument mark is the winner.
	 * @param mark with value X or O
	 * @return integer number 0 or 1, representing not win and win, respectively.
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Prints out 3 column headers
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/**
	 * Prints out 3 horizontal lines with hyphens.
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

	/**
	 * Prints out 3 vertical lines with "|".
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}

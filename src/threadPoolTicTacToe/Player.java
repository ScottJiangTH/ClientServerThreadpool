package threadPoolTicTacToe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * Provides a command line user interface to prompt current Player to enter cell
 * coordinates, add corresponding mark to Board object, checks winning status,
 * and pass the turn to opponents.
 * 
 * @author Scott Tianhan Jiang
 * @version 2.0
 * @since Nov 8, 2020
 *
 */
public class Player {

	private String name;
	private Board board;
	private Player opponent;
	private char mark;

	public Player(String name, char letterX) {
		this.name = name;
		this.mark = letterX;
	}

	/**
	 * Calls makeMove() method to get user input cell coordinates, check winning
	 * status and pass turn to the opponent of current Player object.
	 * 
	 * @throws IOException from makeMove() method.
	 */
	public void play() throws IOException {
		makeMove();
		board.display();
		if (board.xWins()) {
			System.out.println("THE GAME IS OVER: whoever using X is the winner!");
		} else if (board.oWins()) {
			System.out.println("THE GAME IS OVER: whoever using O is the winner!");
		} else if (board.isFull()) {
			System.out.println("THE GAME IS OVER: it's a tie, you all lose!!!!!!");
		} else {
			opponent.play();
		}
	}

	/**
	 * Prompt for and receive user interface, check legitimacy, and add mark to
	 * Board object.
	 * 
	 * @throws IOException since this method uses BufferReader
	 */
	public void makeMove() throws IOException {
		BufferedReader stdin;
		stdin = new BufferedReader(new InputStreamReader(System.in));

		int row = -1;
		int col = -1;
		System.out.print(name + ", what row should your next " + mark + " be placed in?\n");
		String rowString = stdin.readLine();
		row = Integer.parseInt(rowString);
		System.out.print(name + ", what column should your next " + mark + " be placed in?\n");
		String colString = stdin.readLine();
		col = Integer.parseInt(colString);

		if (row < 0 || col < 0 || row > 2 || col > 2) {
			System.out.println("Be serious, enter integer 0 - 2, let's try again\n");
			makeMove();
		} else if (board.getMark(row, col) != ' ') {
			System.out.println("Cheating not allowed! Let's enter again\n");
			makeMove();
		} else {
			board.addMark(row, col, mark);
		}
	}

	/**
	 * Sets the argument Player object as the opponent attribute of current Player
	 * object.
	 * 
	 * @param opponent represents the argument Player object.
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	/**
	 * Sets the argument Board object to the board attribute of current object.
	 * 
	 * @param theBoard represents the argument Board object.
	 */
	public void setBoard(Board theBoard) {
		this.board = theBoard;

	}

}

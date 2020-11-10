package threadPoolTicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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
	private PrintWriter socketOut;
	private BufferedReader socketIn;

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
			socketOut.println("THE GAME IS OVER: whoever using X is the winner!");
			opponent.socketOut.println("THE GAME IS OVER: whoever using X is the winner!");
		} else if (board.oWins()) {
			socketOut.println("THE GAME IS OVER: whoever using O is the winner!");
			opponent.socketOut.println("THE GAME IS OVER: whoever using O is the winner!");
		} else if (board.isFull()) {
			socketOut.println("THE GAME IS OVER: it's a tie, you all lose!!!!!!");
			opponent.socketOut.println("THE GAME IS OVER: it's a tie, you all lose!!!!!!");
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

		int row;
		int col;
		socketOut.println(name + ", what row should your next " + mark + " be placed in? Enter an integer: \n");
		String rowString = socketIn.readLine();
		row = Integer.parseInt(rowString);
		socketOut.println(name + ", what column should your next " + mark + " be placed in? Enter an integer: \n");
		String colString = socketIn.readLine();
		col = Integer.parseInt(colString);

		if (row < 0 || col < 0 || row > 2 || col > 2) {
			socketOut.println("Be serious, enter integer 0 - 2, let's try again. Enter an integer: \n");
			makeMove();
		} else if (board.getMark(row, col) != ' ') {
			socketOut.println("Cheating not allowed! Let's enter again. Enter an integer: \n");
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

	public void setSocket(PrintWriter socketOut, BufferedReader socketIn) {
		this.socketOut = socketOut;
		this.socketIn = socketIn;
	}

}

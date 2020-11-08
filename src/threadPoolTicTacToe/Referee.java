package threadPoolTicTacToe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 
 * Initiates the game by displaying the board, sets opponent relationships and calls the 
 * play method for the X-Player who is always the first player.
 * 
 * @author Scott Tianhan Jiang
 * @version 2.0
 * @since Nov 8, 2020
 *
 */
public class Referee {

	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	private PrintWriter socketOut1;
	private PrintWriter socketOut2;
	private BufferedReader socketIn1;
	private BufferedReader socketIn2;

	/**
	 * Sets opponent of Player objects to each other, display Board object, and initiate the 
	 * game from Player with mark X.
	 * @throws IOException from play() method of Player class.
	 */
	public void runTheGame() throws IOException {
		xPlayer.setSocket(socketOut1, socketIn1);
		oPlayer.setSocket(socketOut2, socketIn2);
		board.setSocket(socketOut1, socketOut2);
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		socketOut1.println("Referee started the game...");
		socketOut2.println("Referee started the game...");
		board.display();
		xPlayer.play();
		socketOut1.println("Game Ended ...");
		socketOut2.println("Game Ended ...");
	}

	/**
	 * Sets the argument object to current Board object.
	 * @param theBoard represents the argument, i.e. Board object passed from Game class.
	 */
	public void setBoard(Board theBoard) {
		this.board = theBoard;
	}

	/**
	 * Sets the argument object to current oPlayer object.
	 * @param oPlayer represents the argument, i.e. Player object passed from Game class.
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}

	/**
	 * Sets the argument object to current xPlayer object.
	 * @param xPlayer represents the argument, i.e. Player object passed from Game class.
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}

	public void setSocket(PrintWriter socketOut1, BufferedReader socketIn1, PrintWriter socketOut2,
			BufferedReader socketIn2) {
		this.socketOut1 = socketOut1;
		this.socketIn1 = socketIn1;
		this.socketOut2 = socketOut2;
		this.socketIn2 = socketIn2;
	}

}

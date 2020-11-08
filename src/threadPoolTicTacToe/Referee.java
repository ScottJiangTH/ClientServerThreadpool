package threadPoolTicTacToe;
import java.io.IOException;
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
	
	/**
	 * Sets opponent of Player objects to each other, display Board object, and initiate the 
	 * game from Player with mark X.
	 * @throws IOException from play() method of Player class.
	 */
	public void runTheGame() throws IOException {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		System.out.println("Referee started the game...");
		board.display();
		xPlayer.play();
		System.out.println("Game Ended ...");
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

}

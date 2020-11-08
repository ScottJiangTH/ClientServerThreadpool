package threadPoolTicTacToe;

import java.io.*;

/**
 * 
 * Provides a command line user interface to receive user input and constructs
 * Player objects, Referee object, and Board object.
 * 
 * @author Scott Tianhan Jiang
 * @version 2.0
 * @since Nov 8, 2020
 *
 */
public class Game implements Constants, Runnable {

	private Board theBoard;
	private Referee theRef;
	private PrintWriter socketOut1, socketOut2;
	private BufferedReader socketIn1, socketIn2;

	public Game(PrintWriter socketOut1, BufferedReader socketIn1, PrintWriter socketOut2, BufferedReader socketIn2) {
		this.socketIn1 = socketIn1;
		this.socketOut1 = socketOut1;
		this.socketIn2 = socketIn2;
		this.socketOut2 = socketOut2;
		theBoard = new Board();
	}

	/**
	 * A method to pass the object in argument to the static variable theRef and
	 * give over the control to this Referee object.
	 * 
	 * @param r is the argument object with type Referee
	 * @throws IOException from runTheGame() method in Referee class.
	 */
	public void appointReferee(Referee r) throws IOException {
		theRef = r;
		theRef.runTheGame();
	}

	@Override
	public void run() {
		Referee theRef;
		Player xPlayer, oPlayer;
		String name1, name2;

		try {
			socketOut1.println(
					"\nYour mark is assigned as \'X\'. Sorry you can't choose, but perhaps that's the story of life. Now enter your name: ");
			name1 = socketIn1.readLine();

			while (name1 == null) {
				socketOut1.println("Please try again: ");
				name1 = socketIn1.readLine();
			}

			xPlayer = new Player(name1, LETTER_X);
			xPlayer.setBoard(this.theBoard);

			socketOut2.println(
					"\nYour mark is assigned as \'O\'. Sorry you can't choose, but perhaps that's the story of life. Now enter your name: ");
			name2 = socketIn2.readLine();

			while (name2 == null) {
				socketOut2.println("Please try again: ");
				name2 = socketIn2.readLine();
			}

			oPlayer = new Player(name2, LETTER_O);
			oPlayer.setBoard(this.theBoard);

			theRef = new Referee();
			theRef.setSocket(socketOut1, socketIn1, socketOut2, socketIn2);
			theRef.setBoard(this.theBoard);
			theRef.setoPlayer(oPlayer);
			theRef.setxPlayer(xPlayer);

			this.appointReferee(theRef);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

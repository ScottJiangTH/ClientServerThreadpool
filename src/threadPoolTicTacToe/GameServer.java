package threadPoolTicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class implements a server to create a new game between two clients
 * (players) when they request for a connection to pairing up and also to run
 * the game when the connection request gets accepted through a socket.
 * 
 * @author Sonia Islam and Scott Tianhan Jiang
 *
 */
public class GameServer {

	private Socket socket1, socket2;
	private ServerSocket serverSocket;
	private PrintWriter socketOut1, socketOut2;
	private BufferedReader socketIn1, socketIn2;
	private ExecutorService pool;

	private int maxPair;
	private static int clientPairCount = 0;
	private static int clientCount = 0;

	/**
	 * This constructor creates a socket through a specific port of a local machine
	 * and also creates a threads pool considering the maximum number of requested
	 * clients (player) pair.
	 * 
	 * @param maxPair -maximum number of requested client (player) pair
	 */
	public GameServer(int maxPair) {
		this.maxPair = maxPair;
		try {
			serverSocket = new ServerSocket(9999);
		} catch (IOException e) {
			System.err.println("Server error: Failed to create ServerSocket.");
		} // new socket has been created through 9999 port of localhost.
		pool = Executors.newFixedThreadPool(maxPair);// creates thread pool of working threads
	}

	/**
	 * This method sets and starts the new game between the players through sockets.
	 */
	public void runServer() {

		try {

			while (clientPairCount <= maxPair) {
				socket1 = serverSocket.accept();
				System.out.println(String.format("Client %d connected.", clientCount + 1));
				clientCount++;
				if ((clientCount - clientPairCount * 2) == 1) {
					socketIn1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
					socketOut1 = new PrintWriter(socket1.getOutputStream(), true);
					socketOut1.println(String.format("Game room %d Player %d joined", clientPairCount + 1,
							clientCount - clientPairCount * 2));
				}

				socket2 = serverSocket.accept();
				System.out.println(String.format("Client %d connected.", clientCount + 1));
				clientCount++;
				if ((clientCount - clientPairCount * 2) == 2) {
					socketIn2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
					socketOut2 = new PrintWriter(socket2.getOutputStream(), true);
					socketOut2.println(String.format("Game room %d Player %d joined", clientPairCount + 1,
							clientCount - clientPairCount * 2));

					System.out.println("New game started at server!");
					socketOut1.println("Welcome to TicTacToe. Game started at server!");
					socketOut2.println("Welcome to TicTacToe. Game started at server!");
					Game game = new Game(socketOut1, socketIn1, socketOut2, socketIn2);
					pool.execute(game);
					clientPairCount++;
				}
			}
		} catch (IOException e) {
			System.err.println("Server error: Client connection lost.");
		}
		pool.shutdown();
		System.out.println(socket1);
		System.out.println(socket2);
		try {
			// closes the server after being used
			socketIn1.close();
			socketOut1.close();
			socketIn2.close();
			socketOut2.close();
		} catch (IOException e) {
			System.err.println("Server error: Failed to close client socket.");
		}

	}

	public static void main(String[] args) throws IOException {

		GameServer myServer = new GameServer(10);
		myServer.runServer();
	}

}

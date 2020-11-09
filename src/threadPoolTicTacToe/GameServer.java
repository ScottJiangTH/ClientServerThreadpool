package threadPoolTicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

	private Socket socket1, socket2;
	private ServerSocket serverSocket;
	private PrintWriter socketOut1, socketOut2;
	private BufferedReader socketIn1, socketIn2;
	private ExecutorService pool;

	private int maxPair;
	private static int clientPairCount = 0;
	private static int clientCount = 0;

	public GameServer(int maxPair) {
		this.maxPair = maxPair;
		try {
			serverSocket = new ServerSocket(9999);
			pool = Executors.newFixedThreadPool(maxPair);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runServer() {

		try {

			while (clientPairCount <= maxPair) {
					socket1 = serverSocket.accept();
					System.out.println(String.format("Client %d connected.", clientCount+1));
					clientCount++;
				if ((clientCount - clientPairCount * 2) == 1) {
					socketIn1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
					socketOut1 = new PrintWriter(socket1.getOutputStream(), true);
					socketOut1.println(String.format("Game room %d Player %d joined", clientPairCount + 1,
							clientCount - clientPairCount * 2));
				}
				
					socket2 = serverSocket.accept();
					System.out.println(String.format("Client %d connected.", clientCount+1));
					clientCount++;
				if ((clientCount - clientPairCount * 2) == 2) {
					socketIn2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
					socketOut2 = new PrintWriter(socket2.getOutputStream(), true);
					socketOut2.println(String.format("Game room %d Player %d joined", clientPairCount + 1,
							clientCount - clientPairCount * 2));

					System.out.println("Game started at server!");
					socketOut1.println("Welcome to TicTacToe. Game started at server!");
					socketOut2.println("Welcome to TicTacToe. Game started at server!");
					Game game = new Game(socketOut1, socketIn1, socketOut2, socketIn2);
					pool.execute(game);
					clientPairCount++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// pool.shutdown();
		System.out.println(socket1);
		System.out.println(socket2);
		try {
			socketIn1.close();
			socketOut1.close();
			socketIn2.close();
			socketOut2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		GameServer myServer = new GameServer(10);
		myServer.runServer();
	}

}

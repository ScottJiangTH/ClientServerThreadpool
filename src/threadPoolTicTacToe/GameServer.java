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

	public GameServer() {
		try {
			serverSocket = new ServerSocket(9898);
			pool = Executors.newFixedThreadPool(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runServer() {

		try {
			while (true) {
				socket1 = serverSocket.accept();
				System.out.println("Client 1 connection established.");
				if (socket1 != null) {
					socketIn1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
					socketOut1 = new PrintWriter(socket1.getOutputStream(), true);
					socketOut1.println("Connected to server, waiting for another player to join.");
				}
				socket2 = serverSocket.accept();
				System.out.println("Client 2 connection established.");
				if (socket2 != null) {
					socketIn2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
					socketOut2 = new PrintWriter(socket2.getOutputStream(), true);
					socketOut2.println("Connected to server, another player has been waiting for you.");
				}
				if (socket1 != null && socket2 != null) {
					Game game = new Game(socketOut1, socketIn1, socketOut2, socketIn2);
					pool.execute(game);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool.shutdown();
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

		GameServer myServer = new GameServer();
		myServer.runServer();
	}

}

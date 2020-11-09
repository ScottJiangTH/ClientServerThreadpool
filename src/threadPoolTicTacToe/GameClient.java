package threadPoolTicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {

	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;

	public GameClient(String serverName, int portNumber) {

		try {
			aSocket = new Socket(serverName, portNumber);
			// keyboard input stream
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void prompt() {
		String userInput = "";
		String serverMessage = "";

		do {
			try {
				do {
					serverMessage = socketIn.readLine(); // read response form the socket
				} while (serverMessage == "");
				System.out.println(serverMessage);
				if (serverMessage.contains(":")) {
					userInput = stdIn.readLine();
					socketOut.println(userInput);
				}

				serverMessage = "";
				userInput = "";

			} catch (IOException e) {
				e.printStackTrace();
			} // reading the input from the user (i.e. the keyboard);

		} while (!serverMessage.contains("THE GAME IS OVER") || !userInput.contains("QUIT"));
		closeSocket();
		System.out.println("checkpoint");
	}

	private void closeSocket() {

		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		GameClient aClient = new GameClient("localhost", 9999);
		aClient.prompt();
	}

}

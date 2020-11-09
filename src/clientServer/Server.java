package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements a server that is running on port 8099 of the "localhost". It also reads messages
 * from the socket, checks the palindrome words and provide a appropriate feedback to the client server.
 * 
 * @author Sonia Islam and Scott Tianhan Jiang 
 *
 */
public class Server {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	public Server() { //creates new server on port 8099 of the localhost with IOException in case of any other inappropriate inputs.
		try {
			serverSocket = new ServerSocket(8099);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/**
 * This method accepts the request from the socket and activates the server for that client 
 * and checks for any palindrome word.
 */

	public void runServer() {

		try {
			while (true) {
				aSocket = serverSocket.accept(); //accepts the client request
				System.out.println("Server is now running.");
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter(aSocket.getOutputStream(), true);
				checkPalindrome();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			socketIn.close(); //closes the server after being used
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
/**
 * This method implements the functionalities for finding the inputed palindrome word in client server through a socket with
 * appropriate feedback.
 */
	public void checkPalindrome() {
		String line = null;
		//IOException has been used for running the program smoothly while reading the line from socket
		//if there is any inappropriate form of input.
		while (true) {
			try {
				line = socketIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			int i = 0;
			int len = line.length();
			while (i < len / 2 && line.charAt(i) == line.charAt(len - i - 1)) { // finds the palindrome word by matching the words from both sides.
				i++;
			}
			if (i >= len / 2)
				socketOut.println(line + " is a Palindrome.");
			else
				socketOut.println(line + " is not a Palindrome.");
		}
	}

	public static void main(String[] args) throws IOException {

		Server myServer = new Server();
		myServer.runServer();
	}

}

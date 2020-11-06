package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	public Server() {
		try {
			serverSocket = new ServerSocket(8099);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runServer() {

		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Server is now running.");
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter(aSocket.getOutputStream(), true);
				checkPalindrome ();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void checkPalindrome () {
		String line = null;
		while (true) {
			try {
				line = socketIn.readLine();
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					socketOut.println(line);
					break;
				}
				int i = 0;
				int len = line.length();
				while (i < len/2 && line.charAt(i) == line.charAt(len-i-1)) {
					i++;
				}
				if (i >= len/2)
					socketOut.println(line + " is a Palindrome.");
				else
					socketOut.println(line + " is not a Palindrome.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //reading from the client
			
			
		}
	}
	
	public static void main(String[] args) throws IOException {

		Server myServer = new Server();
		myServer.runServer();
	}

}

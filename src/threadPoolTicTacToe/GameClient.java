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
	
	public GameClient (String serverName, int portNumber) {
		
		try {
			aSocket = new Socket (serverName, portNumber);
			//keyboard input stream
			stdIn = new BufferedReader (new InputStreamReader (System.in));
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter (aSocket.getOutputStream(), true);
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void prompt () {
		String line = "";
		String response = "";
		
		while (!line.equals("QUIT")) {
			try {
				response = socketIn.readLine();  //read response form the socket
				System.out.println(response);
				line = stdIn.readLine();
				socketOut.println(line);
			} catch (IOException e) {
				e.printStackTrace();
			} //reading the input from the user (i.e. the keyboard);
			
		}
		closeSocket ();
		
	}
	private void closeSocket () {
		
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main (String [] args) throws IOException {
		GameClient aClient = new GameClient ("localhost", 9898);
		aClient.prompt();
	}

}

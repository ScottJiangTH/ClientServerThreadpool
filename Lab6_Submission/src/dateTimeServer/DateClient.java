package dateTimeServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * This class implements a client server which connects to the date server through a socket. Depending on the 
 * client's selection, this client can receive DATE or TIME from the date server.
 * 
 * @author Sonia Islam and Scott Tianhan Jiang
 *
 */

public class DateClient {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	/**
	 * This constructor creates a socket and a port to connect to the date server through that socket.
	 * @param serverName -is the server that the client wants to connect
	 * @param portNumber -is the specific way through which the socket is created to communicate between them
	 */
	public DateClient(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber); //here, the server name is "dateTimeServer" and port number is 9090.
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * This function is implemented to communicate and get response from the date server with an IOException
	 * to avoid the program being crashed for any inappropriate input.
	 */
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("Please select an option (DATE/TIME)");
				line = stdIn.readLine();
				if (!line.equals("QUIT")){ // until the client input "QUIT" program will run
					System.out.println(line);
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);	
				}else{
					running = false;
				}
				
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close(); //closes the server after being used
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}

	public static void main(String[] args) throws IOException  {
		DateClient aClient = new DateClient("localhost", 9090);
		aClient.communicate();
	}
}

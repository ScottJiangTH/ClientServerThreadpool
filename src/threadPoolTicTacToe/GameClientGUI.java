package threadPoolTicTacToe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameClientGUI extends JFrame implements ActionListener {

	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	private JLabel messageLabel = new JLabel("Message Window:");
	private JLabel nameLabel = new JLabel("User Name:");
	private JLabel playerMarkLabel = new JLabel("Player:");
	private JTextArea messageDisplay;
	private JTextArea playerMarkDisplay;
	private JTextField nameEntry;
	private JButton r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2;
	
	public GameClientGUI(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		app();
	}
	
	public void prompt() {
		String serverMessage = "";

		do {
			try {
				do {
					serverMessage = socketIn.readLine(); // read response form the socket
				} while (serverMessage == "");
				messageDisplay.append(serverMessage);
				
				serverMessage = "";

			} catch (IOException e) {
				e.printStackTrace();
			} // reading the input from the user (i.e. the keyboard);

		} while (!serverMessage.contains("THE GAME IS OVER"));
		closeSocket();
	}

	private void closeSocket() {

		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void app() {
		messageDisplay = new JTextArea("", 5, 12);
		playerMarkDisplay = new JTextArea("", 1, 1);
		
		nameEntry = new JTextField(10);
		nameEntry.addActionListener(this);
		
		r0c0 = new JButton("");
		r0c1 = new JButton("");
		r0c2 = new JButton("");
		r1c0 = new JButton("");
		r1c1 = new JButton("");
		r1c2 = new JButton("");
		r2c0 = new JButton("");
		r2c1 = new JButton("");
		r2c2 = new JButton("");
		
		r0c0.addActionListener(this);
		r0c1.addActionListener(this);
		r0c2.addActionListener(this);
		r1c0.addActionListener(this);
		r1c1.addActionListener(this);
		r1c2.addActionListener(this);
		r2c0.addActionListener(this);
		r2c1.addActionListener(this);
		r2c2.addActionListener(this);
		
		JPanel panelW = new JPanel();
		panelW.setLayout(new GridLayout(3,3));
		panelW.add(r0c0);
		panelW.add(r0c1);
		panelW.add(r0c2);
		panelW.add(r1c0);
		panelW.add(r1c1);
		panelW.add(r1c2);
		panelW.add(r2c0);
		panelW.add(r2c1);
		panelW.add(r2c2);
		
		JPanel panelS = new JPanel();
		panelS.setLayout(new GridLayout(2,2));
		panelS.add(playerMarkLabel);
		panelS.add(playerMarkDisplay);
		panelS.add(nameLabel);
		panelS.add(nameEntry);
		
		JPanel panelE = new JPanel();
		panelE.setLayout(new GridLayout(2,1));
		panelE.add(messageLabel);
		panelE.add(messageDisplay);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add("West", panelW);
		contentPane.add("South", panelS);
		contentPane.add("East", panelE);
		
		setSize(800, 600);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameEntry) {
			socketOut.println(nameEntry.getText());
		} else if (e.getSource() == r0c0) {
			socketOut.println("0");
			socketOut.println("0");
		} else if (e.getSource() == r0c1) {
			socketOut.println("0");
			socketOut.println("1");
		} else if (e.getSource() == r0c2) {
			socketOut.println("0");
			socketOut.println("2");
		} else if (e.getSource() == r1c0) {
			socketOut.println("1");
			socketOut.println("0");
		} else if (e.getSource() == r1c1) {
			socketOut.println("1");
			socketOut.println("1");
		} else if (e.getSource() == r1c2) {
			socketOut.println("1");
			socketOut.println("2");
		} else if (e.getSource() == r2c0) {
			socketOut.println("2");
			socketOut.println("0");
		} else if (e.getSource() == r2c1) {
			socketOut.println("2");
			socketOut.println("1");
		} else if (e.getSource() == r2c2) {
			socketOut.println("2");
			socketOut.println("2");
		}
	}

	public static void main(String[] args) {
		GameClientGUI aClient = new GameClientGUI("localhost", 9999);
		aClient.prompt();
	}
}

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/**
 * 
 * This class create Tic Tac Toe game client GUI and shared a GameServer with
 * CLI version of this game.
 * 
 * @author Sonia Islam and Scott Tianhan Jiang
 * @version 2.0
 * @since Nov 8, 2020
 *
 */
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
	private static String serverMessage = "";

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
		loadUI();
	}

	/**
	 * Creates all swing components for GUI.
	 */
	private void loadUI() {
		messageDisplay = new JTextArea("", 8, 12);
		messageDisplay.setWrapStyleWord(true);
		messageDisplay.setLineWrap(true);
		messageDisplay.setAutoscrolls(true);
		JScrollPane scrollPane = new JScrollPane(messageDisplay);

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

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.add(r0c0);
		buttonPanel.add(r0c1);
		buttonPanel.add(r0c2);
		buttonPanel.add(r1c0);
		buttonPanel.add(r1c1);
		buttonPanel.add(r1c2);
		buttonPanel.add(r2c0);
		buttonPanel.add(r2c1);
		buttonPanel.add(r2c2);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

		JPanel panelS = new JPanel();
		panelS.setLayout(new GridLayout(2, 2));
		panelS.add(playerMarkLabel);
		panelS.add(playerMarkDisplay);
		panelS.add(nameLabel);
		panelS.add(nameEntry);
		panelS.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 600));

		JPanel panelC = new JPanel();
		panelC.setLayout(new GridLayout(2, 2));
		panelC.add(messageLabel);
		panelC.add(scrollPane);
		panelC.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

		JPanel panelN = new JPanel();
		panelN.setLayout(new GridLayout(1, 1));

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add("West", buttonPanel);
		contentPane.add("South", panelS);
		contentPane.add("Center", panelC);

		setSize(1200, 400);
		setVisible(true);
	}

	/**
	 * Defines all actions when certain changes happen on GUI components.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameEntry) {
			socketOut.println(nameEntry.getText());
			messageDisplay.append("Your name is set as " + nameEntry.getText() + ".\n");
		} else if (e.getSource() == r0c0) {
			socketOut.println("0");
			socketOut.println("0");
			if (r0c0.getText() == "")
				r0c0.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r0c1) {
			socketOut.println("0");
			socketOut.println("1");
			if (r0c1.getText() == "")
				r0c1.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r0c2) {
			socketOut.println("0");
			socketOut.println("2");
			if (r0c2.getText() == "")
				r0c2.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r1c0) {
			socketOut.println("1");
			socketOut.println("0");
			if (r1c0.getText() == "")
				r1c0.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r1c1) {
			socketOut.println("1");
			socketOut.println("1");
			if (r1c1.getText() == "")
				r1c1.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r1c2) {
			socketOut.println("1");
			socketOut.println("2");
			if (r1c2.getText() == "")
				r1c2.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r2c0) {
			socketOut.println("2");
			socketOut.println("0");
			if (r2c0.getText() == "")
				r2c0.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r2c1) {
			socketOut.println("2");
			socketOut.println("1");
			if (r2c1.getText() == "")
				r2c1.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		} else if (e.getSource() == r2c2) {
			socketOut.println("2");
			socketOut.println("2");
			if (r2c2.getText() != "")
				r2c2.setText(playerMarkDisplay.getText());
			messageDisplay.append("Mark set. Now wait for opponent's move.");
		}
	}

	/**
	 * Send and receive strings from server through socket, and send/receive strings
	 * to corresponding GUI components on both client side.
	 */
	public void commWithServer() {

		do {
			try {
				do { // keep listening when message is empty
					serverMessage = socketIn.readLine(); // read response form the socket
				} while (serverMessage == "");

				if (serverMessage.contains("row 0")) {
					r0c0.setText(Character.toString(serverMessage.charAt(13)));
					r0c1.setText(Character.toString(serverMessage.charAt(19)));
					r0c2.setText(Character.toString(serverMessage.charAt(25)));
				} else if (serverMessage.contains("row 1")) {
					r1c0.setText(Character.toString(serverMessage.charAt(13)));
					r1c1.setText(Character.toString(serverMessage.charAt(19)));
					r1c2.setText(Character.toString(serverMessage.charAt(25)));
				} else if (serverMessage.contains("row 2")) {
					r2c0.setText(Character.toString(serverMessage.charAt(13)));
					r2c1.setText(Character.toString(serverMessage.charAt(19)));
					r2c2.setText(Character.toString(serverMessage.charAt(25)));
				}

				// cases other than displaying board
				if (serverMessage.contains("X")) {
					playerMarkDisplay.setText("X");
				} else if (serverMessage.contains("O")) {
					playerMarkDisplay.setText("O");
				}

				if (serverMessage.contains("what row")) {
					messageDisplay.append(nameEntry.getText() + ", please make your move.");
					serverMessage = "";
				} else if (serverMessage.contains("what column")) {
					serverMessage = "";
				} else if (!serverMessage.contains("-") && !serverMessage.contains("|")) {
					messageDisplay.append(serverMessage + "\n");
				}
				serverMessage = "";
			} catch (IOException e) {
				System.err.println("Client UI error: Failed to send/receive through socket.");
			} // reading the input from the user (i.e. the keyboard);

		} while (!serverMessage.contains("THE GAME IS OVER"));
		closeSocket();
	}

	/**
	 * Closes all sockets after certain conditions met.
	 */
	private void closeSocket() {

		try {
			aSocket.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.err.println("Client UI error: Failed to close socket.");
		}
	}

	public static void main(String[] args) {
		GameClientGUI aClient = new GameClientGUI("localhost", 9999);
		aClient.commWithServer();
	}

}

package com.ame.bus3.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.Utilities;
import com.ame.bus3.common.Variables;

public class ConnectionHandler {
	private static Connection server = null;
	
	/**
	 * Connects to the ip and port specified in the variables class.
	 */
	public static void connect() {
		if (server == null) {
			ConnectionHandler.promptIPPort();
			server = new Connection();
			try {
				server.connection = new Socket(Variables.serverIP, Variables.port);
			} 
			catch (UnknownHostException e) {
				Utilities.popupMessage("Connection Error", "The server could not be resolved. \nPlease enter a new server to connect to.");
				promptIPPort();
			}
			catch (IOException e) {		// It's throwing this exception and I don't know why.
				Utilities.popupMessage("Connection Error", "The server refused the connection. \nPlease enter a new server to connect to.");
			}
			
			server.thread = new Thread(server);
			server.thread.start();
		}
		else
			throw new IllegalArgumentException("Already connected to a server.");
	}
	
	/**
	 * Disconnects from the server.
	 */
	public static void disconnect() {
		server.disconnect();
		server = null;
		Variables.renderMode = "mainmenu";
	}
	
	/**
	 * Prompts the user for the IP and port to connect to. Doesn't actually connect though.
	 */
	public static void promptIPPort() {
		String[] serverPortIPSplit;
		String serverPortIP;
		
		while (true) {	// Prompt the user for the ip and port.
			serverPortIP = Utilities.popupPrompt("Buildstation", "Enter a IP/Port to connect to:", "127.0.0.1/25566");
			try {
				serverPortIPSplit = serverPortIP.split("/");
			}
			catch (NullPointerException e) {
				Utilities.popupMessage("Invalid Input", "Cancel is not a valid option.");
				continue;
			}
			
			if (serverPortIPSplit.length == 2) {
				if (Utilities.validIP(serverPortIPSplit[0]))
					Variables.serverIP = serverPortIPSplit[0];
				else {
					Utilities.popupMessage("Invalid Input", serverPortIPSplit[0] + " Is not a valid IP address.");
					continue;	// Checks the ip.
				}
				
				try {
					Variables.port = Integer.parseInt(serverPortIPSplit[1]);	// Then the port.
				}
				catch (NumberFormatException e) {
					Utilities.popupMessage("Invalid Input", serverPortIPSplit[1] + " Is not a valid port.");
					continue;
				}
				
				break;	// If all the checks go well, continue on to the program.
			}
			Utilities.popupMessage("Invalid Input", serverPortIP + " Contains more than or less than 1 slash [/].");
		}
	}
}

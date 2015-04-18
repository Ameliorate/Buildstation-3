package com.ame.bus3.client;

import com.ame.bus3.common.Utilities;
import com.ame.bus3.common.Variables;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

/**
 * @author Amelorate
 * Controlls client side net trafic.
 */
public class ClientNetworkController {
	public ClientNetworkController() {
		while (true) {
			promptIPPort();
			client = new Client();
			client.start();
			try {
				client.connect(5000, Variables.serverIP, Variables.tcpPort, Variables.udpPort);
			}
			catch (IOException e) {
				Utilities.popupMessage("BuildStation", "Invalid Input\nThe server refused the connection or an unknown error occurred.");
				e.printStackTrace();
				promptIPPort();
				continue;
			}
			break;
		}
	}

	public Client client;

	/**
	 * Prompts the user for the IP and port to connect to. Doesn't actually connect though.
	 */
	public static void promptIPPort() {
		String[] serverPortIPSplit = new String[0];
		String serverPortIP;

		while (true) {	// Prompt the user for the ip and port.
			serverPortIP = Utilities.popupPrompt("BuildStation", "Enter a IP/Port to connect to:", "127.0.0.1/25566");
			try {
				serverPortIPSplit = serverPortIP.split("/");
			}
			catch (NullPointerException e) {
				System.exit(0);
			}

			if (serverPortIPSplit.length == 2) {
				if (Utilities.validIP(serverPortIPSplit[0]))
					Variables.serverIP = serverPortIPSplit[0];
				else {
					Utilities.popupMessage("Invalid Input", serverPortIPSplit[0] + " Is not a valid IP address.");
					continue;	// Checks the ip.
				}

				try {
					Variables.tcpPort = Integer.parseInt(serverPortIPSplit[1]);	// Then the port.
					Variables.udpPort = Variables.tcpPort + 1;
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

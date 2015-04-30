package com.ame.bus3.util;

import com.ame.bus3.client.ClientNetworkController;
import com.ame.bus3.common.Variables;
import com.ame.bus3.common.netlisteners.ListenerList;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

/**
 * @author Amelorate
 * Allows doing client side networking without popups. You will need to also set the port and IP manually.
 */
public class HeadlessClientNetworkController extends ClientNetworkController {
	/**
	 * @throws IOException If the server refused the connection.
	 */
	public HeadlessClientNetworkController() throws IOException {
		client = new Client();
		client.start();
		client.connect(5000, Variables.serverIP, Variables.port);
		client.getKryo().setRegistrationRequired(false);
		ListenerList.addListeners(client);
		isHeadless = true;	// I wish I could avoid this, but for some reason, I can't override promptIPPort.
	}

	public Client client;
}

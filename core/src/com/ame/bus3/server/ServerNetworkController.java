package com.ame.bus3.server;

import com.ame.bus3.common.Variables;
import com.ame.bus3.common.netlisteners.ListenerList;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

/**
 * @author Amelorate
 * Controlls server side networking.
 */
public class ServerNetworkController {
	public ServerNetworkController() {
		server = new Server();
		server.start();
		try {
			server.bind(Variables.tcpPort, Variables.udpPort);
		}
		catch (IOException e) {
			System.out.println("[Error] Could not bind server. Another server is probably open on the same port. Exiting.");
			System.exit(1);
		}
		server.getKryo().setRegistrationRequired(false);
		ListenerList.addListeners(server);
	}

	public Server server;
}

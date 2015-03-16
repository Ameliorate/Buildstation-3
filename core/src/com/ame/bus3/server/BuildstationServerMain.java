package com.ame.bus3.server;

import com.ame.bus3.common.TileRegisterer;
import com.ame.bus3.common.Variables;
import com.ame.bus3.common.packetsorters.SorterList;

/**
 * Main class for the server. Handles initalisation.
 * @author Grant
 *
 */
public class BuildstationServerMain {
	/**
	 * Starts the server.
	 */
	public void create() {
		Variables.isServer = true;	// Make sure this is done first.

		TileRegisterer.load();		// Registerers tiles.

		SorterList.loadSorters();		// Begin networking
		Variables.port = 25566;
		ConnectionAccepter accepter = new ConnectionAccepter();
		Thread accepterThread = new Thread(accepter, "ConnectionAccepter");
		accepterThread.start();		// End networking
		
		System.out.println("[Info] Finished initalising");
	}
}

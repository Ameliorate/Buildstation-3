package com.ame.bus3.server;

import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.TileRegisterer;
import com.ame.bus3.common.Tiles.Wall;
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
		System.out.println("[Info] Loaded tiles.");

		Variables.map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(new Coordinate(0, 0, 0, "temp")));
		System.out.println("[Info] Populated map.");

		SorterList.loadSorters();		// Begin networking
		Variables.port = 25566;
		ConnectionAcceptor acceptor = new ConnectionAcceptor();
		Thread acceptorThread = new Thread(acceptor, "ConnectionAcceptor");
		acceptorThread.start();		// End networking
		System.out.println("[Info] Loaded networking.");
		
		System.out.println("[Info] Finished initialising.");
	}
}

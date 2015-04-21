package com.ame.bus3.server;

import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.TileRegisterer;
import com.ame.bus3.common.Tiles.Wall;
import com.ame.bus3.common.Variables;

/**
 * Main class for the server. Handles initalisation.
 * @author Grant
 *
 */
public class BuildstationServerMain {
	public static ServerNetworkController serverNetworkController;

	/**
	 * Starts the server.
	 */
	public void create() {
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.

		Variables.isServer = true;	// Make sure this is done first.

		TileRegisterer.load();		// Registerers tiles.
		System.out.println("[Info] Loaded tiles.");

		Variables.map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(new Coordinate(0, 0, 0, "temp")));
		System.out.println("[Info] Populated map.");

		Variables.tcpPort = 25566;
		Variables.udpPort = 25567;
		serverNetworkController = new ServerNetworkController();
		System.out.println("[Info] Loaded networking.");
		
		System.out.println("[Info] Finished initialising.");
	}
}

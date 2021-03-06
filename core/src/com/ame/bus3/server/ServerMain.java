package com.ame.bus3.server;

import com.ame.bus3.common.*;
import com.ame.bus3.common.Tiles.Wall;

/**
 * Main class for the server. Handles initialisation.
 * @author Grant
 *
 */
public class ServerMain {
	public static ServerMain instance;
	public static boolean isActive = false;

	public static ServerMain getInstance() {
		if (instance == null) {
			instance = new ServerMain();
			return instance;
		}
		else {
			return instance;
		}

	}

	@SuppressWarnings("unused")
	public ServerNetworkController serverNetworkController;
	public World world = new World(true);

	/**
	 * Starts the server.
	 */
	public void create() {
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.

		isActive = true;
		instance = this;

		TileRegistry.load();		// Registers tiles.
		System.out.println("[Info] Loaded tiles.");

		world.map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall());
		System.out.println("[Info] Populated map.");

		Variables.port = 25566;
		serverNetworkController = new ServerNetworkController();
		System.out.println("[Info] Loaded networking.");
		
		System.out.println("[Info] Finished initialising.");
	}
}

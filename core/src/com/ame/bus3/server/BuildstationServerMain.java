package com.ame.bus3.server;

import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.GameMap;
import com.ame.bus3.common.TileRegisterer;
import com.ame.bus3.common.Tiles.Wall;
import com.ame.bus3.common.Variables;

/**
 * Main class for the server. Handles initalisation.
 * @author Grant
 *
 */
public class BuildstationServerMain {
	public static BuildstationServerMain instance;
	public static boolean isActive = false;

	public static BuildstationServerMain getInstance() {
		if (instance == null) {
			instance = new BuildstationServerMain();
			return instance;
		}
		else {
			return instance;
		}

	}

	public ServerNetworkController serverNetworkController;
	public GameMap map = new GameMap(true);

	/**
	 * Starts the server.
	 */
	public void create() {
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.

		isActive = true;
		instance = this;

		TileRegisterer.load(true);		// Registerers tiles.
		System.out.println("[Info] Loaded tiles.");

		map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(true));
		System.out.println("[Info] Populated map.");

		Variables.port = 25566;
		serverNetworkController = new ServerNetworkController();
		System.out.println("[Info] Loaded networking.");
		
		System.out.println("[Info] Finished initialising.");
	}
}

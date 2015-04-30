package com.ame.bus3.util;

import com.ame.bus3.client.BuildstationClientMain;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.GameMap;
import com.ame.bus3.common.TileRegisterer;
import com.ame.bus3.common.Tiles.Wall;
import com.ame.bus3.server.BuildstationServerMain;
import com.ame.bus3.server.ServerNetworkController;

import java.io.IOException;

/**
 * @author Amelorate
 * Allows swapping between client and server modes without restarting.
 */
public class ServerClientSet {
	/**
	 * Do note that this is null if in server mode.
	 */
	public static HeadlessClientNetworkController clientNetworkController;

	/**
	 * This, unlike it's client counterpart is never null if you have everything setup right.
	 */
	public static ServerNetworkController serverNetworkController;

	/**
	 * Do note that this may not wipe all kinds of persistence in variables, only the map. This also starts a server for the client to work with.
	 */
	public static void becomeClient() {
		BuildstationClientMain.instance = new BuildstationClientMain();
		BuildstationServerMain.instance = new BuildstationServerMain();
		BuildstationServerMain.getInstance().map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(true));
		serverNetworkController = new ServerNetworkController();
		BuildstationServerMain.getInstance().serverNetworkController = serverNetworkController;
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.
		BuildstationClientMain.isActive = true;
		TileRegisterer.load(false);
		try {
			clientNetworkController = new HeadlessClientNetworkController();
			BuildstationClientMain.getInstance().clientNetworkController = clientNetworkController;
		}
		catch (IOException e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * Do note that this may not wipe all kinds of persistence in variables, only the map.
	 */
	public static void becomeServer() {
		BuildstationServerMain.getInstance().map = new GameMap(true);
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.
		BuildstationServerMain.isActive = true;
		TileRegisterer.load(true);
		BuildstationServerMain.getInstance().map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(new Coordinate(0, 0, 0, "temp"), true));
		serverNetworkController = new ServerNetworkController();
		BuildstationServerMain.getInstance().serverNetworkController = serverNetworkController;
	}
}

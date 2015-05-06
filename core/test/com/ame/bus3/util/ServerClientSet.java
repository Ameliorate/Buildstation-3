package com.ame.bus3.util;

import com.ame.bus3.client.ClientMain;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.GameMap;
import com.ame.bus3.common.TileRegistry;
import com.ame.bus3.common.Tiles.Wall;
import com.ame.bus3.server.ServerMain;
import com.ame.bus3.server.ServerNetworkController;

import java.io.IOException;

/**
 * @author Amelorate
 * Allows swapping between client and server modes without restarting.
 */
@SuppressWarnings("WeakerAccess")
public class ServerClientSet {
	/**
	 * Do note that this is null if in server mode.
	 */
	@SuppressWarnings("WeakerAccess")
	public static HeadlessClientNetworkController clientNetworkController;

	/**
	 * This, unlike it's client counterpart is never null if you have everything setup right.
	 */
	@SuppressWarnings("WeakerAccess")
	public static ServerNetworkController serverNetworkController;

	/**
	 * Do note that this may not wipe all kinds of persistence in variables, only the map. This also starts a server for the client to work with.
	 */
	@SuppressWarnings("unused")
	public static void becomeClient() {
		ClientMain.instance = new ClientMain();
		ServerMain.instance = new ServerMain();
		ServerMain.getInstance().map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(true));
		serverNetworkController = new ServerNetworkController();
		ServerMain.getInstance().serverNetworkController = serverNetworkController;
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.
		ClientMain.isActive = true;
		TileRegistry.load(false);
		try {
			clientNetworkController = new HeadlessClientNetworkController();
			ClientMain.getInstance().clientNetworkController = clientNetworkController;
		}
		catch (IOException e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * Do note that this may not wipe all kinds of persistence in variables, only the map.
	 */
	@SuppressWarnings("unused")
	public static void becomeServer() {
		ServerMain.getInstance().map = new GameMap(true);
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.
		ServerMain.isActive = true;
		TileRegistry.load(true);
		ServerMain.getInstance().map.fill(new Coordinate(0, 0, 0, "default"), new Coordinate(15, 15, 0, "default"), new Wall(new Coordinate(0, 0, 0, "temp"), true));
		serverNetworkController = new ServerNetworkController();
		ServerMain.getInstance().serverNetworkController = serverNetworkController;
	}
}

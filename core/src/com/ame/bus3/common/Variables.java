package com.ame.bus3.common;

/**
 * Stores variables important to multiple pieces of code in a single location.
 * @author Amelorate
 */
public class Variables {
	/**
	 * Stores if it is running in client or server mode.
	 */
	public static boolean isServer;
	
	/**
	 * The IP address of the server being connected to.
	 */
	public static String serverIP;
	
	/**
	 * Which port to use when connecting to a client/server.
	 */
	public static int port = 0;
	
	/**
	 * What the renderer will render.
	 * Valid options are "tiles" and "mainmenu" for now.
	 */
	public static String renderMode = "mainmenu";

	/**
	 * The main map of the game.
	 */
	public static GameMap map = new GameMap();
}

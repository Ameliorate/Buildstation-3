package com.ame.bus3.common;

/**
 * @author Amelorate
 * Contains information about general game state.
 */
public class World {
	public World(boolean isServer) {
		map = new GameMap(this);
		this.isServer = isServer;
	}

	public boolean isServer() {
		return isServer;
	}

	private boolean isServer;
	public GameMap map;
}

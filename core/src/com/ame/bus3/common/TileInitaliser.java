package com.ame.bus3.common;

/**
 * Used to create tile spawners. You only need one of these per tile package.
 * @author Amelorate
 */
public interface TileInitaliser {
	/**
	 * Called during initialisation. You should create tile spawners here.
	 * @param map The game map. This in needed because it lacks a valid reference to the game map.
	 */
	public void create(GameMap map);
}

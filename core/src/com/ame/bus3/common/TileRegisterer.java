package com.ame.bus3.common;

import com.ame.bus3.common.Tiles.Wall;

import java.util.HashMap;

/**
 * Loads tiles.
 * @author Amelorate
 */
public class TileRegisterer {
	/**
	 * Used for creating and loading new tiles.
	 */
	public static void load() {
		tiles.put("Wall", new Wall(new Coordinate(0, 0, 0, "temp")));	// New tiles go here.
	}

	/**
	 * Get a tile template to spawn.
	 */
	public static Tile getTileTemplate(String getting) {
		return tiles.get(getting);
	}

	private static HashMap<String, Tile> tiles = new HashMap<String, Tile>();
}

package com.ame.bus3.common;

import com.ame.bus3.common.Tiles.Wall;

import java.util.HashMap;

/**
 * Loads tiles.
 * @author Amelorate
 */
public class TileRegistry {
	/**
	 * Used for creating and loading new tiles.
	 */
	public static void load() {
		tiles.put("Wall", new Wall());	// New tiles go here.
	}

	/**
	 * Get a tile template to spawn.
	 */
	public static Tile getTileTemplate(String getting) {
		return tiles.get(getting);
	}

	@SuppressWarnings("CanBeFinal")
	private static HashMap<String, Tile> tiles = new HashMap<>();
}

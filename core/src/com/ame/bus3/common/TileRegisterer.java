package com.ame.bus3.common;

import com.ame.bus3.common.Tiles.Wall;

/**
 * Loads tiles.
 * @author Amelorate
 */
public class TileRegisterer {
	/**
	 * Used for creating and loading new tiles.
	 */
	public static void load() {
		loadLogic(new Wall());	// New tiles go here.
	}

	private static void loadLogic(Tile loading) {
		loading.setPosition(new Coordinate(0, 0, 0, loading.getType() + "_Spawner"));
	}
}

package com.ame.bus3.common;

import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * The map of the game. Also will have a few useful methods for map manipulation.
 * @author Amelorate
 */
public class GameMap {
	private HashMap<Coordinate, Tile> map = new HashMap<Coordinate, Tile>();

	/**
	 * Gets the tile at the given location.
	 */
	public Tile get(Coordinate location) {
		return map.get(location);
	}

	/**
	 * Puts the given tile in the given location.
	 */
	public void place(Tile placing, Coordinate location) {
		map.put(location, placing);
	}

	/**
	 * Removes the tile at the given location.
	 */
	public void remove(Coordinate location) {
		map.remove(location);
	}

	/**
	 * Moves the tile at the source to the destination.
	 */
	public void move(Coordinate source, Coordinate destination) {
		Tile moving = get(source);
		moving.setPosition(destination);	// This will eventually end up calling moveRaw, if you were wondering. Or they could do the steps of moveRaw themselves if they were crazy.
	}

	/**
	 * Moves the tile at the source to the destination. If you aren't the tile being moved, don't call this.
	 */
	public void moveRaw(Coordinate source, Coordinate destination) {
		Tile moving = get(source);
		remove(source);
		place(moving, destination);
	}

	/**
	 * Spawns a tile at the given location.
	 */
	public void spawn(Coordinate location, String tileName) {
		Tile spawner = TileRegisterer.getTileTemplate(tileName);
		if (spawner == null)
			throw new IllegalArgumentException("Nonexistent tile");
		else
			spawner.clone(location);
	}

	/**
	 * Spawns a new tile at the given location. Also gives the data to said object.
	 */
	public void spawn(Coordinate location, String tileName, JSONObject data) {
		spawn(location, tileName);
		get(location).setData(data);
	}

	/**
	 * Fills a square area with the filling tile.
	 * @param finish Note that the level value of this is ignored.
	 * @param fillingTile The tile you are filling the area with.
	 */
	public void fill(Coordinate start, Coordinate finish, Tile fillingTile) {
		for (int x = start.getX(); x <= finish.getX(); x++)
			for (int y = start.getY(); y <= finish.getX(); y++)
				for (int z = start.getZ(); z <= finish.getZ(); z++) {
					fillingTile.clone(new Coordinate(x, y, z, start.getLevel()));
				}
	}
}

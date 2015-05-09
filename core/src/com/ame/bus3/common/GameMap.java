package com.ame.bus3.common;

import com.ame.bus3.client.ClientMain;
import com.ame.bus3.common.netlisteners.GetChunk;

import java.util.WeakHashMap;

/**
 * The map of the game. Also will have a few useful methods for map manipulation.
 * @author Amelorate
 */
public class GameMap {
	/**
	 * @param containingWorld The world containing this gamemap object.
	 */
	public GameMap(World containingWorld) {
		this.containingWorld = containingWorld;
	}

	@SuppressWarnings("CanBeFinal")
	private WeakHashMap<Coordinate, Chunk> mapChunkView = new WeakHashMap<>();
	private final World containingWorld;

	/**
	 * Loop over an x, y, and z plane using lambdas. Removes quite a bit of code sometimes.
	 */
	public static void threeDimensionalForEach(Coordinate start, Coordinate finish, ThreeDimensionalIterator action) {
		for (int x = start.getX(); x <= finish.getX(); x++) {
			for (int y = start.getY(); y <= finish.getX(); y++) {
				for (int z = start.getZ(); z <= finish.getZ(); z++) {
					action.invoke(x, y, z);
				}
			}
		}
	}

	/**
	 * Gets a chunk over the network if it isn't locally stored.
	 * @param chunkLocation The location of the chunk. Make sure to divide x and y by 16, and z is ignored.
	 */
	public Chunk getChunk(Coordinate chunkLocation) {
		chunkLocation = chunkLocation.setZ(0);
		Chunk gettingChunk = mapChunkView.get(chunkLocation);

		if (gettingChunk == null && !containingWorld.isServer()) {
			GetChunk.sendWait(chunkLocation, ClientMain.getInstance().clientNetworkController.client);
			gettingChunk = mapChunkView.get(chunkLocation);
			return gettingChunk;
		}
		else if (gettingChunk == null) {
			gettingChunk = Chunk.loadChunkFromDisc(chunkLocation);
			mapChunkView.put(chunkLocation, gettingChunk);
			return gettingChunk;
		}

		return gettingChunk;
	}

	/**
	 * Gets the tile at the given location.
	 */
	public Tile get(Coordinate location) {
		Coordinate chunkLocation = new Coordinate(location.getX() / 16, location.getY() / 16, 0, location.getLevel());
		Coordinate relativeTilePosition = new Coordinate(location.getX() % 16, location.getY() % 16, location.getZ(), location.getLevel());		// Since the location of the tiles in a chunk are relative, I need to use the % operator to find which tile it is at.
		Chunk gettingChunk = getChunk(chunkLocation);
		Tile gettingTile = gettingChunk.tiles.get(relativeTilePosition);

		if (gettingTile == null && location.getZ() == 0 && containingWorld.isServer()) {
			spawn(location, "Wall");
			gettingTile = get(location);	// Hopefully this won't break anything or cause infinite loops.
		}

		return gettingTile;
	}

	/**
	 * Puts the given tile in the given location.
	 */
	public void place(Tile placing, Coordinate location) {
		Coordinate chunkLocation = new Coordinate(location.getX() / 16, location.getY() / 16, 0, location.getLevel());
		Coordinate relativeTilePosition = new Coordinate(location.getX() % 16, location.getY() % 16, location.getZ(), location.getLevel());		// Since the location of the tiles in a chunk are relative, I need to use the % operator to find which tile it is at.
		Chunk gettingChunk = getChunk(chunkLocation);

		gettingChunk.tiles.put(relativeTilePosition, placing);
	}

	/**
	 * Place the given chunk in the given location.
	 */
	public void placeChunk(Chunk placing, Coordinate location) {
		mapChunkView.put(location, placing);
	}

	/**
	 * Removes the tile at the given location.
	 */
	@SuppressWarnings("WeakerAccess")
	public void remove(Coordinate location) {
		Coordinate chunkLocation = new Coordinate(location.getX() / 16, location.getY() / 16, 0, location.getLevel());
		Coordinate relativeTilePosition = new Coordinate(location.getX() % 16, location.getY() % 16, location.getZ(), location.getLevel());		// Since the location of the tiles in a chunk are relative, I need to use the % operator to find which tile it is at.
		Chunk gettingChunk = getChunk(chunkLocation);

		gettingChunk.tiles.remove(relativeTilePosition);
	}

	/**
	 * Moves the tile at the source to the destination.
	 */
	@SuppressWarnings("unused")
	public void move(Coordinate source, Coordinate destination) {
		Tile moving = get(source);
		moving.setPosition(destination, containingWorld);	// This will eventually end up calling moveRaw, if you were wondering. Or they could do the steps of moveRaw themselves if they were crazy.
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
	@SuppressWarnings("WeakerAccess")
	public void spawn(Coordinate location, @SuppressWarnings("SameParameterValue") String tileName) {
		Tile spawner = TileRegistry.getTileTemplate(tileName);
		if (spawner == null)
			throw new IllegalArgumentException("Nonexistent tile");
		else
			spawner.clone(location, containingWorld);
	}

	/**
	 * Fills a square area with the filling tile.
	 * @param finish Note that the level value of this is ignored.
	 * @param fillingTile The tile you are filling the area with.
	 */
	public void fill(Coordinate start, Coordinate finish, Tile fillingTile) {
		threeDimensionalForEach(start, finish, (x, y, z) -> fillingTile.clone(new Coordinate(x, y, z, start.getLevel()), containingWorld));
	}

	public void tileForEach(Coordinate start, Coordinate finish, ThreeDimensionalTileIterator action) {
		threeDimensionalForEach(start, finish, (x, y, z) -> action.invoke(get(new Coordinate(x, y, z, start.getLevel()))));		// I'm really starting to like lambdas.
	}
}

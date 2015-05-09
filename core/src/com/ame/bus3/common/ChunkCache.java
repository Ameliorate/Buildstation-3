package com.ame.bus3.common;

import java.util.HashMap;

/**
 * @author Amelorate
 * Allows keeping a certan radius of chunks on the client at all times.
 */
public class ChunkCache {
	public ChunkCache(Coordinate topLeft, int diameter, World world) {
		this.topLeft = topLeft;
		this.diameter = diameter;
		refresh(world);
	}

	private Coordinate topLeft;
	private int diameter;
	private HashMap<CacheKey, Chunk> cache = new HashMap<>();

	/**
	 * @param newTopLeft The new top left. This is in chunk coordinate notation.
	 */
	public void setTopLeft(Coordinate newTopLeft, World world) {
		topLeft = newTopLeft;
		refresh(world);
	}

	public void setDiameter(int newDiameter, World world) {
		diameter = newDiameter;
		refresh(world);
	}

	/**
	 * Call this often to keep it up to date the cache the chunks change.
	 */
	public void refresh(World world) {
		cache.clear();
		Coordinate bottomRight = new Coordinate(topLeft.getX() + diameter, topLeft.getY() + diameter, 0, topLeft.getLevel());
		world.map.chunkForEach(topLeft, bottomRight, (chunk, location) -> cache.put(new CacheKey(location.getX(), location.getY()), chunk));
	}

	private class CacheKey {
		public CacheKey(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@SuppressWarnings("CanBeFinal")
		public int x;
		@SuppressWarnings("CanBeFinal")
		public int y;

		@Override
		public int hashCode() {
			return x + y;
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof CacheKey && x == ((CacheKey) obj).x && y == ((CacheKey) obj).y;
		}
	}
}

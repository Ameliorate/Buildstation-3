package com.ame.bus3.common;

/**
 * @author Amelorate
 * Allows iterating over chunks.
 * @see com.ame.bus3.common.GameMap
 */
@FunctionalInterface
public interface ThreeDimensionalChunkIterator {
	void invoke(Chunk chunk, Coordinate location);
}

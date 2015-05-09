package com.ame.bus3.common;

/**
 * @author Amelorate
 * Allows iterating over tiles. Used in a gamemap utility method.
 */
@FunctionalInterface
public interface ThreeDimensionalTileIterator {
	void invoke(Tile tile, Coordinate location);
}

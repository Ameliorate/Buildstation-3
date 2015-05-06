package com.ame.bus3.common;

/**
 * A basic tile. You probably don't want to implement this directly unless you want to do something weird. Use an abstract class instead.
 * @author Amelorate
 */
public interface Tile {
	/**
	 * Creates a new copy of the tile in a location.
	 * @param location The location to clone this to.
	 */
    void clone(Coordinate location, boolean isServer);

	/**
	 * Called whenever the tile is being rendered. Can be used for animations or the like.
	 * @return Returns the object's current spritestate.
	 */
	SpriteState renderTick();

	/**
	 * Sets the location of the object to the given location.
	 */
	void setPosition(Coordinate location, boolean isServer);

	/**
	 * Gets the position of the tile.
	 */
	Coordinate getPosition();

	/**
	 * Gets the type of the tile.
	 */
	@SuppressWarnings("SameReturnValue")
	String getType();
}

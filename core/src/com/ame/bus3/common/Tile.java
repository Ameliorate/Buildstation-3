package com.ame.bus3.common;

import org.json.simple.JSONObject;

/**
 * A basic tile. You probably don't want to implement this directly unless you want to do something weird. Use an abstract class instead.
 * @author Amelorate
 */
public interface Tile {
	/**
	 * Creates a new copy of the tile in a location.
	 * @param location The location to clone this to.
	 */
    public void clone(Coordinate location);

	/**
	 * Called whenever the tile is being rendered. Can be used for animations or the like.
	 * @return Returns the object's current spritestate.
	 */
	public SpriteState renderTick();

	/**
	 * Sets the location of the object to the given location.
	 */
	public void setPosition(Coordinate location);

	/**
	 * Gets the position of the tile.
	 */
	public Coordinate getPosition();

	/**
	 * Gets data necessary to sync the client and server's instances of the tile.
	 */
	public  JSONObject getData();

	/**
	 * Sets the internal data of the class to what is specified inside the json object.
	 */
	public void setData(JSONObject data);
}

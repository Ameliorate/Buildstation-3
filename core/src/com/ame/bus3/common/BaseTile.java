package com.ame.bus3.common;

import org.json.simple.JSONObject;

/**
 * The abstract class for basic tiles.
 * @author Amelorate
 */
public abstract class BaseTile implements Tile {
	public SpriteState spriteState = new SpriteState();	// This is meant to be public to allow other classes to change it's sprite.
	protected Coordinate position = new Coordinate();

	@Override
	public void clone(Coordinate location) {
		/*
		If this class wasn't abstract, the code here would be:
		Variables.map.place(new BaseTile(), location);
		For more detailed classes you could use your getData and setData classes.
		*/
	}

	@Override
	public SpriteState renderTick() {
		return spriteState;
	}

	@Override
	public void setPosition(Coordinate location) {
		Variables.map.moveRaw(position, location);	// Make sure to use raw, as the non-raw one will cause a stack overflow.
		position = location;
	}

	@Override
	public Coordinate getPosition() {
		return position;
	}

	@Override
	public JSONObject getData() {
		return new JSONObject();
		/*
		Since this has no data to send, it just needs to send a new json object.
		Note that null is not a reasonable return value.
		*/
	}

	@Override
	public void setData(JSONObject data) {
		// Since there is no data it needs, just do nothing.
	}
}

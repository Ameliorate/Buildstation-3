package com.ame.bus3.common;

import com.ame.bus3.client.BuildstationClientMain;
import com.ame.bus3.server.BuildstationServerMain;

/**
 * The abstract class for basic tiles.
 * @author Amelorate
 */
public abstract class BaseTile implements Tile {
	public BaseTile() {}

	public BaseTile(Coordinate location, boolean isServer) {
		position = location;
		if (isServer) {
			BuildstationServerMain.getInstance().map.place(this, position);
		}
		if (!isServer) {
			BuildstationClientMain.getInstance().map.place(this, position);
		}
	}

	public SpriteState spriteState = new SpriteState();	// This is meant to be public to allow other classes to change it's sprite.
	protected Coordinate position = new Coordinate();

	@Override
	public SpriteState renderTick() {
		return spriteState;
	}

	@Override
	public void setPosition(Coordinate location, boolean isServer) {
		if (isServer) {
			BuildstationServerMain.getInstance().map.moveRaw(position, location);
		}
		if (!isServer) {
			BuildstationClientMain.getInstance().map.moveRaw(position, location);
		}
		position = location;
	}

	@Override
	public Coordinate getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return getType() + "@" + position.toString();
	}
}

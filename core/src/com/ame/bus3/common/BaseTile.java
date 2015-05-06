package com.ame.bus3.common;

import com.ame.bus3.client.ClientMain;
import com.ame.bus3.server.ServerMain;

/**
 * The abstract class for basic tiles.
 * @author Amelorate
 */
public abstract class BaseTile implements Tile {
	protected BaseTile() {}

	@SuppressWarnings("WeakerAccess")
	public BaseTile(Coordinate location, boolean isServer) {
		position = location;
		if (isServer) {
			ServerMain.getInstance().map.place(this, position);
		}
		if (!isServer) {
			ClientMain.getInstance().map.place(this, position);
		}
	}

	@SuppressWarnings("CanBeFinal")
	protected SpriteState spriteState = new SpriteState();
	@SuppressWarnings("WeakerAccess")
	protected Coordinate position = new Coordinate();

	@Override
	public SpriteState renderTick() {
		return spriteState;
	}

	@Override
	public void setPosition(Coordinate location, boolean isServer) {
		if (isServer) {
			ServerMain.getInstance().map.moveRaw(position, location);
		}
		if (!isServer) {
			ClientMain.getInstance().map.moveRaw(position, location);
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

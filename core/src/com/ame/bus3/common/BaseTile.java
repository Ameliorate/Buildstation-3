package com.ame.bus3.common;

/**
 * The abstract class for basic tiles.
 * @author Amelorate
 */
public abstract class BaseTile implements Tile {
	protected BaseTile() {}

	@SuppressWarnings("WeakerAccess")
	public BaseTile(Coordinate location, World world) {
		position = location;
		world.map.place(this, position);
	}

	@SuppressWarnings("CanBeFinal")
	protected SpriteState spriteState = new SpriteState();
	@SuppressWarnings("WeakerAccess")
	protected Coordinate position = new Coordinate(0, 0, 0, "temp");

	@Override
	public SpriteState renderTick() {
		return spriteState;
	}

	@Override
	public void setPosition(Coordinate location, World world) {
		world.map.moveRaw(position, location);
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

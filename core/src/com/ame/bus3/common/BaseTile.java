package com.ame.bus3.common;

/**
 * The abstract class for basic tiles.
 * @author Amelorate
 */
public abstract class BaseTile implements Tile {
	public BaseTile() {}

	public BaseTile(Coordinate location) {
		position = location;
		Variables.map.place(this, position);
	}

	public SpriteState spriteState = new SpriteState();	// This is meant to be public to allow other classes to change it's sprite.
	protected Coordinate position = new Coordinate();

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
	public String toString() {
		return getType() + "@" + position.toString();
	}
}

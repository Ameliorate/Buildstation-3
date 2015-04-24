package com.ame.bus3.common;

/**
 * Holds the location of something on the game map.
 * @author Amelorate
 *
 */
public class Coordinate {
	public Coordinate() {}

	public Coordinate(int x, int y, int z, String level) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.level = level;
	}

	public Coordinate(Coordinate coordinate) {
		x = coordinate.x;
		y = coordinate.y;
		z = coordinate.z;
		level = coordinate.level;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public String getLevel() {
		return level;
	}

	public Coordinate setX(int newValue) {
		return new Coordinate(newValue, y, z, level);
	}

	public Coordinate setY(int newValue) {
		return new Coordinate(x, newValue, z, level);
	}

	public Coordinate setZ(int newValue) {
		return new Coordinate(x, y, newValue, level);
	}

	public Coordinate setLevel(String newValue) {
		return new Coordinate(x, y, z, newValue);
	}

	@Override
	public String toString() {
		Integer x = this.x;
		Integer y = this.y;
		Integer z = this.z;		// I do this because you can't call toString on a primitive like an int.

		return x.toString() + "," + y.toString() + "," + z.toString() + "," + level;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();	// This probably does have it's flaws, but it's better than the alternatives.
	}
	
	private int x;
	private int y;
	private int z;
	private String level;


}

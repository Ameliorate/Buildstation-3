package com.ame.bus3.common;

/**
 * @author Amelorate
 * Basically a direct copy from coordinate used for chunk positioning.
 */
public class ChunkCoordinate {
	private ChunkCoordinate() {}

	public ChunkCoordinate(int x, int y, int z, String level) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.level = level;
	}

	/**
	 * @param convert Weather to divide z and y by 16 while assigning.
	 */
	public ChunkCoordinate(Coordinate coordinate, boolean convert) {
		if (!convert) {
			x = coordinate.getX();
			y = coordinate.getY();
			z = coordinate.getZ();
			level = coordinate.getLevel();
		}
		else {
			x = coordinate.getX() / 16;
			y = coordinate.getY() / 16;
			z = getZ();
			level = getLevel();
		}
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

	@SuppressWarnings("unused")
	public ChunkCoordinate setX(int newValue) {
		return new ChunkCoordinate(newValue, y, z, level);
	}

	@SuppressWarnings("unused")
	public ChunkCoordinate setY(int newValue) {
		return new ChunkCoordinate(x, newValue, z, level);
	}

	public ChunkCoordinate setZ(@SuppressWarnings("SameParameterValue") int newValue) {
		return new ChunkCoordinate(x, y, newValue, level);
	}

	@SuppressWarnings("unused")
	public ChunkCoordinate setLevel(String newValue) {
		return new ChunkCoordinate(x, y, z, newValue);
	}

	public Coordinate toCoordinate() {
		return new Coordinate(x, y, z,level);
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate asCoordinate = (Coordinate) obj;
		return asCoordinate.getX() == x && asCoordinate.getY() == y && asCoordinate.getZ() == z && asCoordinate.getLevel().equals(level);
	}

	private int x;
	private int y;
	private int z;
	private String level;
}

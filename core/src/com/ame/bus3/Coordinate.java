package com.ame.bus3;

public class Coordinate {
	
	/**
	 * Holds the location of something on the game map.
	 */
	public Coordinate(int x, int y, int z, String level) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.level = level;
	}
	
	@Override
	public String toString() {
		Integer x = this.x;
		Integer y = this.y;
		Integer z = this.z;		// I do this because you can't call toString on a primitive like an int.
		
		return x.toString() + "," + y.toString() + "," + z.toString() + "," + level;
	}
	
	public int x;
	public int y;
	public int z;
	public String level;
}

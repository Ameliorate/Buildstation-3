package com.ame.bus3.common.packetsorters;

/**
 * Contains all of the packetsorters.
 * @author Amelorate
 *
 */
public class SorterList {
	public static TerminalDisplay terminalDisplay;	// Put new packet sorters here.
	public static PlaceTile placeTile;
	public static GetTile getTile;
	public static Waituntill waitUntill;

	private static boolean loaded = false; // Used to make sure packetsorters aren't loaded twice. Could probably cause issues if they were.
	/**
	 * Loads all the sorters. Needs to be called at the start of every program.
	 */
	public static void loadSorters() {
		if (loaded == false) {
			terminalDisplay = new TerminalDisplay();	// And here too.
			placeTile = new PlaceTile();
			getTile = new GetTile();
			waitUntill= new Waituntill();
		}
	}
	

}

package com.ame.bus3.common.packetsorters;

/**
 * Contains all of the packetsorters.
 * @author Amelorate
 *
 */
public class SorterList {
	private static boolean loaded = false; // Used to make sure packetsorters aren't loaded twice. Could probably cause issues if they were.
	/**
	 * Loads all the sorters. Needs to be called at the start of every program.
	 */
	public static void loadSorters() {
		if (loaded == false) {
			terminalDisplay = new TerminalDisplay();
		}
	}
	
	public static TerminalDisplay terminalDisplay;
}

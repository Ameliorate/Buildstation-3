package com.ame.bus3.common.packetsorters;

import org.json.simple.JSONObject;

/**
 * Allows implementing classes to receive and act upon network traffic.
 * @author Amelorate
 *
 */
public interface PacketSorter {
	/**
	 * Acts upon the given packet.
	 * @param packet The packet you want to process.
	 */
	public void sort(JSONObject packet);
}

package com.ame.bus3.common.packetsorters;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.PacketSorterTracker;

/**
 * Displays a string to the connection's console.
 * @author Amelorate
 *
 */
public class TerminalDisplay implements PacketSorter {
	public TerminalDisplay() {
		PacketSorterTracker.register("TermWrite", this);
	}
	
	/**
	 * Acts upon the given packet.
	 * @param packet The packet you want to send
	 */
	@Override
	public void sort(JSONObject packet, Connection sending) {
		// It has one value, message.
		System.out.println(packet.get("message"));	// It is safe to assume it is in the right format. It would just print null if the key didn't exist.
	}
	
	/**
	 * Sends the given packet to the given client.
	 * @param sending The connection you are sending the packet to.
	 * @param message The message you are displaying in the connection's terminal.
	 */
	@SuppressWarnings("unchecked")
	public void send(Connection sending, String message) {
		JSONArray sendingPacket = new JSONArray();

		sendingPacket.add(getInnerPacket(message));
		sending.send(sendingPacket);
	}

	/**
	 * Gets the inner packet that would be sent to the client.
	 * @param message The message you are displaying in the connection's terminal.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getInnerPacket(String message) {
		JSONObject value = new JSONObject();

		value.put("message", message);
		value.put("sorter", "TermWrite");

		return value;
	}
}

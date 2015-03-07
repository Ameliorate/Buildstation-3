package com.ame.bus3.common.packetsorters;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.PacketSorterTracker;


public class TerminalDisplay implements PacketSorter {
	public TerminalDisplay() {
		PacketSorterTracker.register("TermWrite", this);
	}
	
	/**
	 * Acts upon the given packet.
	 * @param packet The packet you want to send
	 */
	public void sort(JSONObject packet) {
		// It has one value, message.
		System.out.println(packet.get("message"));	// It is safe to assume it is in the right format.
	}
	
	/**
	 * Sends the given packet to the given client.
	 * @param sending The connection you are sending the packet to.
	 * @param message The message you are displaying in the connection's terminal.
	 */
	@SuppressWarnings("unchecked")
	public void send(Connection sending, String message) {
		JSONArray sendingPacket = new JSONArray();
		JSONObject value = new JSONObject();
		
		value.put("message", message);
		value.put("sorter", "TermWrite");
		sendingPacket.add(value);		// I think I like this json library more than the libgdx one.
		
		
		sending.send(sendingPacket);	// The final packet would be along the lines of "[ { message : "foo" } ]" if the message was "foo"
	}
	
	
	
}

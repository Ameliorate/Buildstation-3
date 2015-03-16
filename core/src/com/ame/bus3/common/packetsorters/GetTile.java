package com.ame.bus3.common.packetsorters;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.PacketSorterTracker;
import com.ame.bus3.common.Variables;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Gets a tile from the server.
 * @author Amelorate
 */
public class GetTile implements PacketSorter {
	public GetTile() {
		PacketSorterTracker.register("GetTile", this);
	}

	@Override
	public void sort(JSONObject packet, Connection sending) {
		try {
			Coordinate location = new Coordinate((JSONObject) packet.get("location"));
			SorterList.placeTile.send(sending, Variables.map.get(location));
		}
		catch (ClassCastException e) {
			System.out.println("[Error] Malformed packet. Full packet text: \n" + packet.toString());
		}
	}

	/**
	 * Gets a tile from the server.
	 */
	public void send(Connection connection, Coordinate location) {
		JSONArray packet = new JSONArray();
		JSONObject innerPacket = new JSONObject();

		innerPacket.put("sorter", "GetTile");
		innerPacket.put("location", location);

		packet.add(innerPacket);
		connection.send(packet);
	}
}

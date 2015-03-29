package com.ame.bus3.common.packetsorters;

import com.ame.bus3.common.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Places a tile on the game map.
 * @author Amelorate
 */
public class PlaceTile implements PacketSorter {
	public PlaceTile() {
		PacketSorterTracker.register("PlaceTile", this);
	}

	@Override
	public void sort(JSONObject packet, Connection sending) {
		if (Variables.isServer == false) {
			JSONObject tileData = null;
			String tileType = null;
			Coordinate tilePosition = null;
			try {
				tileData = (JSONObject) packet.get("data");
				tileType = (String) packet.get("type");
				tilePosition = new Coordinate((JSONObject) packet.get("location"));
			}
			catch (ClassCastException e) {} // This and other issues are handled below \/
			if (tileData != null && tilePosition != null && tileType != null) {
				Variables.map.spawn(tilePosition, tileType, tileData);
			}
			else
				System.out.println("[Error] Malformed packet. Packet text: \n" + packet.toString());
		}
	}

	/**
	 * Tells the client to place the tile.
	 * @param connection The client you are sending the packet to.
	 * @param placing The tile you are placing.
	 */
	@SuppressWarnings("unchecked")
	public void send(Connection connection, Tile placing) {
		JSONArray packet = new JSONArray();

		packet.add(getInnerPacket(placing));
		connection.send(packet);
	}

	/**
	 * Gets the packet send would send to the client
	 * @param placing The tile you are placing.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getInnerPacket(Tile placing) {
		JSONObject tileData = placing.getData();
		Coordinate position = placing.getPosition();
		String tileType = placing.getType();
		JSONObject innerPacket = new JSONObject();

		innerPacket.put("sorter", "PlaceTile");
		innerPacket.put("data", tileData);
		innerPacket.put("type", tileType);
		innerPacket.put("location", position);

		return innerPacket;
	}
}

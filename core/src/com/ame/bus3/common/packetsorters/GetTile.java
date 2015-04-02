package com.ame.bus3.common.packetsorters;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.PacketSorterTracker;
import com.ame.bus3.common.Tile;
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
	@SuppressWarnings("unchecked")
	public void sort(JSONObject packet, Connection sending) {
		try {
			Coordinate location = new Coordinate((JSONObject) packet.get("location"));
			Tile getting;
			JSONArray outerPacket = new JSONArray();

			while (true) {
				getting = Variables.map.get(location);
				if (getting == null && location.z != 0)
					break;
				else if (getting == null && location.z == 0) { // TODO: Replace placing a wall tile with a gamemodecontroler place tile call when that is added.
					Variables.map.spawn(location, "Wall");
					getting = Variables.map.get(location);
				}

				outerPacket.add(SorterList.placeTile.getInnerPacket(getting));

				location.z++;
			}

			outerPacket.add(SorterList.waitUntill.getInnerPacket("got"));
			sending.send(outerPacket);

		}
		catch (ClassCastException e) {
			System.out.println("[Error] Malformed packet. Full packet text: \n" + packet.toString());
		}
	}

	/**
	 * Gets a tile from the server.
	 */
	@SuppressWarnings("unchecked")
	public void send(Connection connection, Coordinate location) {
		JSONArray packet = new JSONArray();

		packet.add(getInnerPacket(location));
		connection.send(packet);
	}

	/**
	 * Gets the packet that send would send to the server.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getInnerPacket(Coordinate location) {
		JSONObject innerPacket = new JSONObject();

		innerPacket.put("sorter", "GetTile");
		innerPacket.put("location", location);

		return innerPacket;
	}
}

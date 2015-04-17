package com.ame.bus3.common.netlisteners;

import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.Tile;
import com.ame.bus3.common.Variables;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Gets a tile from the server.
 * @author Amelorate
 */
public class GetTile extends Listener {

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof GetTilePacket) {
			Tile tile = Variables.map.get(((GetTilePacket) object).location);
			PlaceTile.send(tile, connection);
		}
	}

	/**
	 * Gets a tile from the server.
	 */
	public static void send(Coordinate location, Connection connection) {
		connection.sendTCP(new GetTilePacket(location));
	}

	private static class GetTilePacket {
		public GetTilePacket() {}

		public GetTilePacket(Coordinate location) {
			this.location = location;
		}

		public Coordinate location;
	}
}

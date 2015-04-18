package com.ame.bus3.common.netlisteners;

import com.ame.bus3.common.Tile;
import com.ame.bus3.common.Variables;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Places a tile on the game map.
 * @author Amelorate
 */
public class PlaceTile extends Listener {
	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof PlaceTilePacket)
			Variables.map.place(((PlaceTilePacket) object).placing, ((PlaceTilePacket) object).placing.getPosition());
	}

	public static void send(Tile tile, Connection connection) {
		connection.sendTCP(tile);
	}

	private static class PlaceTilePacket {
		public PlaceTilePacket() {}
		public PlaceTilePacket(Tile placing) {
			this.placing = placing;
		}

		public Tile placing;
	}
}

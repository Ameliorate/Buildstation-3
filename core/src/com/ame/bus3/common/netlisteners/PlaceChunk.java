package com.ame.bus3.common.netlisteners;

import com.ame.bus3.client.BuildstationClientMain;
import com.ame.bus3.common.Chunk;
import com.ame.bus3.common.Tile;
import com.ame.bus3.common.Variables;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Places a tile on the game map.
 * @author Amelorate
 */
public class PlaceChunk extends Listener {
	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof Chunk) {
			BuildstationClientMain.getInstance().map.placeChunk((Chunk) object, ((Chunk) object).location);
		}
	}

	public static void send(Chunk chunk, Connection connection) {
		connection.sendTCP(chunk);
	}
}

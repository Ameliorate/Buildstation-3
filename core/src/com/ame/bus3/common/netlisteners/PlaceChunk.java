package com.ame.bus3.common.netlisteners;

import com.ame.bus3.client.ClientMain;
import com.ame.bus3.common.Chunk;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Places a tile on the game map.
 * @author Amelorate
 */
@SuppressWarnings("WeakerAccess")
public class PlaceChunk extends Listener {
	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof Chunk) {
			ClientMain.getInstance().world.map.placeChunk((Chunk) object, ((Chunk) object).location);
			// It is mildly unsafe to get world like this, but since I can't get it through parameters, this is the only way. It should be fine here.
		}
	}

	public static void send(Chunk chunk, Connection connection) {
		connection.sendTCP(chunk);
	}
}

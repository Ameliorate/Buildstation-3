package com.ame.bus3.common.netlisteners;

import com.ame.bus3.common.Coordinate;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Gets a tile from the server.
 * @author Amelorate
 */
public class GetTile extends Listener {

	@Override
	public void received(Connection connection, Object object) {
	}

	/**
	 * Gets a tile from the server.
	 */
	public static void send(Coordinate location, Connection connection) {
	}
}

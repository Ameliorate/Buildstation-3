package com.ame.bus3.common.netlisteners;

import com.ame.bus3.common.Coordinate;
import com.ame.bus3.server.ServerMain;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.Random;

/**
 * Gets a tile from the server.
 * @author Amelorate
 */
public class GetChunk extends Listener {

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof GetChunkPacket) {
			PlaceChunk.send(ServerMain.getInstance().world.map.getChunk(((GetChunkPacket) object).location), connection);
			WaitUntil.send(connection, ((GetChunkPacket) object).waitingString);
		}
	}

	/**
	 * Gets a tile from the server.
	 */
	@SuppressWarnings("unused")
	public static void send(Coordinate location, Connection connection) {
		connection.sendTCP(new GetChunkPacket(location, "got"));	// This isn't thread safe, but it is backwards compatible and doesn't need an extra argument.
	}

	/**
	 * A thread safe way of getting a tile from the server and waiting until it has been received.
	 */
	public static void sendWait(Coordinate location, Connection connection) {
		Random random = new Random();
		int randInt = random.nextInt();

		connection.sendTCP(new GetChunkPacket(location, "got" + randInt));
		WaitUntil.wait("got" + randInt);
	}

	public static class GetChunkPacket {
		@SuppressWarnings("unused")
		private GetChunkPacket() {}

		public GetChunkPacket(Coordinate location, String waitingString) {
			this.location = location;
			this.waitingString = waitingString;
		}

		public Coordinate location;
		public String waitingString;
	}
}

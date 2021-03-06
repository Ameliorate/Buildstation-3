package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Displays a string to the connection's console.
 * @author Amelorate
 *
 */
@SuppressWarnings("WeakerAccess")
public class TerminalDisplay extends Listener {
	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof TerminalDisplayPacket)
			System.out.println(((TerminalDisplayPacket) object).message);
	}

	@SuppressWarnings("unused")
	public static void send(String message, Connection connection) {
		connection.sendTCP(new TerminalDisplayPacket(message));
	}

	private static class TerminalDisplayPacket {
		@SuppressWarnings("unused")
		private TerminalDisplayPacket() {}
		public TerminalDisplayPacket(String message) {
			this.message = message;
		}
		public String message;
	}
}

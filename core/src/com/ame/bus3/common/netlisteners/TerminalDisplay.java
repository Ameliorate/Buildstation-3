package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Displays a string to the connection's console.
 * @author Amelorate
 *
 */
public class TerminalDisplay extends Listener {
	@Override
	public void received(Connection connection, Object object) {
	}

	public static void send(String message, Connection connection) {
	}
}

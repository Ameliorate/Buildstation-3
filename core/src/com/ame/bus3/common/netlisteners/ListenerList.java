package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryonet.EndPoint;

/**
 * @author Amelorate
 * Registerers all network listeners.
 */
public class ListenerList {
	public static void addListeners(EndPoint addingClientOrServer) {	// Listeners are added here.
		addingClientOrServer.addListener(new GetChunk());
		addingClientOrServer.addListener(new PlaceChunk());
		addingClientOrServer.addListener(new TerminalDisplay());
		addingClientOrServer.addListener(new WaitUntil());
	}
}

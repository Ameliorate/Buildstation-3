package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.HashMap;

/**
 * Allows waiting until a connection says it has finished a task.
 * @author Amelorate
 */
public class WaitUntil extends Listener {
	private static HashMap<String, Boolean> finished = new HashMap<String, Boolean>();	// Find a better way to do this.

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof WaitUntilPacket)
			finished.put(((WaitUntilPacket) object).condition, true);
	}

	/**
	 * Call this before using the listener. Make sure to do these in the same order.
	 * @param kryo The kryo instance used by the client/server.
	 */
	public void register(Kryo kryo) {
		kryo.register(WaitUntilPacket.class);
	}

	/**
	 * Sends the specified packet.
	 * @param finished What you have finished doing.
	 */
	public static void send(Connection sending, String finished) {
		sending.sendTCP(new WaitUntilPacket(finished));
	}

	/**
	 * Waits for a certain task to complete. Not thread safe with the same keys.
	 */
	public static void wait(String waitingCondition) {
		int oldPriority = Thread.currentThread().getPriority();
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);	// We don't want to be clogging up the cpu with an empty loop.
		finished.put(waitingCondition, false);

		//noinspection StatementWithEmptyBody
		while (!finished.get(waitingCondition)) {}

		Thread.currentThread().setPriority(oldPriority);
	}

	private static class WaitUntilPacket {
		public WaitUntilPacket() {
		}

		public WaitUntilPacket(String condition) {
			this.condition = condition;
		}

		public String condition;
	}

}

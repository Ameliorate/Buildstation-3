package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.HashMap;

/**
 * Allows waiting until a connection says it has finished a task.
 * @author Amelorate
 */
@SuppressWarnings("WeakerAccess")
public class WaitUntil extends Listener {
	@SuppressWarnings("CanBeFinal")
	private static HashMap<String, Boolean> finished = new HashMap<>();	// Find a better way to do this.
	private static final Object lock = new Object();

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof WaitUntilPacket) {
			finished.put(((WaitUntilPacket) object).condition, true);
			synchronized (lock) {
				lock.notify();
			}
		}
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
		synchronized (lock) {
			while (true) {
				try {
					lock.wait();
				}
				catch (InterruptedException e) {
					if (finished.get(waitingCondition)) {
						break;
					}
				}
			}
		}
	}

	private static class WaitUntilPacket {
		@SuppressWarnings("unused")
		private WaitUntilPacket() {}

		public WaitUntilPacket(String condition) {
			this.condition = condition;
		}

		public String condition;
	}

}

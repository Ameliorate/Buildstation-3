package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.ArrayList;

/**
 * Allows waiting until a connection says it has finished a task.
 * @author Amelorate
 */
public class WaitUntil extends Listener {
	private static ArrayList<String> finished = new ArrayList<String>();

	@Override
	public void received(Connection connection, Object object) {
	}

	/**
	 * Sends the specified packet.
	 * @param finished What you have finished doing.
	 */
	public static void send(Connection sending, String finished) {
		sending.sendTCP(new WaitUntilPacket(finished));
	}

	/**
	 * Waits for a certain task to complete.
	 */
	public static void wait(String waitingCondition) {
		int oldPriority = Thread.currentThread().getPriority();
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);	// We don't want to be clogging up the cpu with an empty loop.

		//noinspection StatementWithEmptyBody
		while (!finished.contains(waitingCondition)) {}

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

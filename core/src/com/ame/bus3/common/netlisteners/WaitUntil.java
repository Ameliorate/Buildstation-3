package com.ame.bus3.common.netlisteners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
	@SuppressWarnings("unchecked")
	public static void send(Connection sending, String finished) {
	}

	/**
	 * Waits for a certain task to complete.
	 */
	public static void wait(String waitingCondition) {
	}
}

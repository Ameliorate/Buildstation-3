package com.ame.bus3.common.packetsorters;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.PacketSorterTracker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.text.Keymap;
import java.util.HashMap;

/**
 * Allows waiting until a connection says it has finished a task.
 * @author Amelorate
 */
public class Waituntill implements PacketSorter {
	public Waituntill() {
		PacketSorterTracker.register("WaitUntill", this);
	}

	private HashMap<String, Boolean> finished = new HashMap<String, Boolean>();

	@Override
	public void sort(JSONObject packet, Connection sending) {
		String taskFinished;
		try {
			taskFinished = (String) packet.get("finished");
		}
		catch (ClassCastException e) {
			System.out.println("[Error] Malformed packet. Full text:\n" + packet.toString());
			return;		// Just learnt this today. Finally I don't need a if the thing isn't null block.
		}
		finished.put(taskFinished, true);
	}

	/**
	 * Sends the specified packet.
	 * @param finished What you have finished doing.
	 */
	@SuppressWarnings("unchecked")
	public void send(Connection sending, String finished) {
		JSONArray packet = new JSONArray();

		packet.add(getInnerPacket(finished));
		sending.send(packet);
	}

	/**
	 * Gets the inner packet that would be sent to the connection. Doesn't send it.
	 * @param finished What you have finished doing.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getInnerPacket(String finished) {
		JSONObject innerPacket = new JSONObject();

		innerPacket.put("sorter", "WaitUntill");
		innerPacket.put("finished", finished);

		return innerPacket;
	}

	/**
	 * Waits for a certain task to complete.
	 */
	public void wait(String waitingCondition) {
		finished.put(waitingCondition, false);
		int previousPriority = Thread.currentThread().getPriority();
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		while(true) {
			if (finished.get(waitingCondition) == false)
				continue;
			else {
				Thread.currentThread().setPriority(previousPriority);
				break;
			}
		}

	}
}

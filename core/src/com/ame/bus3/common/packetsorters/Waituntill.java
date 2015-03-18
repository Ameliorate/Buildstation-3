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
	 * @param finished What you have finished.
	 */
	public void send(Connection sending, String finished) {
		JSONObject innerPacket = new JSONObject();
		JSONArray packet = new JSONArray();

		innerPacket.put("sorter", "WaitUntill");
		innerPacket.put("finished", finished);

		packet.add(innerPacket);
		sending.send(packet);
	}

	/**
	 * Waits for a certain task to complete.
	 */
	public void wait(String waitingCondition) {
		finished.put(waitingCondition, false);

		while(true) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				continue;
			}

			if (finished.get(waitingCondition) == false)
				continue;
			else {
				break;
			}
		}

	}
}

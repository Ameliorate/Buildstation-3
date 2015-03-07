package com.ame.bus3.common.packetsorters;

import org.json.simple.JSONObject;

public interface PacketSorter {
	public void sort(JSONObject packet);
}

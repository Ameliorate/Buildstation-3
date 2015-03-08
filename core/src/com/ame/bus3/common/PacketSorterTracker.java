package com.ame.bus3.common;

import java.util.Collection;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.ame.bus3.common.packetsorters.PacketSorter;

/**
 * Tracks packet sorters and allows calling them by string.
 * @author Amelorate
 *
 */
public class PacketSorterTracker {
	private static HashMap<String, PacketSorter> sorters = new HashMap<String, PacketSorter>();
	
	/**
	 * Allows registering a packetsorter with a name.
	 * @param name The name of the packetsorter.
	 * @param sorter A reference to the sorter. You can use "this" if this is being called inside the class of the sorter you are registering.
	 */
	public static void register(String name, PacketSorter sorter) {
		sorters.put(name, sorter);
	}
	
	/**
	 * Sorts a basic network packet before it has been converted to a json object and calls all the necessary functions.
	 * @param packet The packet you are sorting.
	 */
	public static void sort(String packet) {
		JSONArray packetArray = (JSONArray) JSONValue.parse(packet);
		
		for (int i = 0; i < packetArray.size(); i++) {
			sort((JSONObject) packetArray.get(i));
		}
	}
	
	/**
	 * Sorts a network packet after it has been converted and separated.
	 * @param packet The packet you want to sort.
	 */
	public static void sort(JSONObject packet) {
		@SuppressWarnings("unchecked")
		Collection<JSONObject> values = (Collection<JSONObject>) packet.values();
		JSONObject[] valuesArray = (JSONObject[]) values.toArray();
		String sorter;
		JSONObject sorting;
		
		for (int i = 0; i < valuesArray.length; i++) {
			sorter = (String) valuesArray[i].get("sorter");
			sorting = valuesArray[i];
			if (sorters.get(sorter) != null) {	// Accounting for if a malformed packet is sent.
				sorters.get(sorter).sort(sorting);
			}
				
		}
	}
}

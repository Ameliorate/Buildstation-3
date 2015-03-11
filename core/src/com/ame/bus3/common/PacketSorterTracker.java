package com.ame.bus3.common;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

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
		JSONArray packetArray = new JSONArray();
		
		try {
			packetArray = (JSONArray) JSONValue.parseWithException(packet);
		}
		catch (ClassCastException e) {  // Malformed packet, we can safely ignore this with an error message.
			System.out.println("[Error] Malformed packet. Can't be casted. Full packet text:\n" + packet);
		}
		catch (ParseException e) {	// Same here.
			System.out.println("[Error] Malformed packet. Unable to parse. Full packet text:\n" + packet);
			e.printStackTrace();
		}
		
		JSONObject sorting = null;
		String sorter;
		if (packetArray != null)		// I sortof wish java had goto statements for situations like this.
			for (int i = 0; i < packetArray.size(); i++) {
				/* Process outline
				 Get an object being sorted. Call it sorting
				 Get it's sorter field.
				 Call the sorter with that name with sorting. 
				*/ 
				try {
					sorting = (JSONObject) packetArray.get(i);	// Get the object being sorted.
					sorter = (String) sorting.get("sorter");	// Sometimes I wish there were more words to acturatly describe a variable.
				}
				catch (ClassCastException e) {	// Malformed packet checking.
					System.out.println("[Error] Malformed packet. Can't be casted. Full packet text:\n" + packet);
					continue;	// It is better to drop a packet than to crash. Servers should be atleast moderately difficult to crash.
				}
				
				sorter = sorter.trim();
				System.out.println(sorter);
				
				try {
					sorters.get(sorter).sort(sorting);
				}
				catch (NullPointerException e) {
					System.out.println("[Error] Malformed packet. sorterBeingRun is null. Full packet text:\n" + packet);
					continue;
				}
			}
	}
}

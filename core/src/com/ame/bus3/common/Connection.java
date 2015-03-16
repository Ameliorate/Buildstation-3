package com.ame.bus3.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.JSONArray;

/**
 * Stores a connection to a client or server and constantly checks it for new traffic and acts upon said traffic.
 * @author Amelorate
 *
 */
public class Connection implements Runnable {
	
	public Socket connection;
	public Thread thread;

	@Override
	public void run() {
		if (connection == null)
			throw new IllegalArgumentException("No connection was given");
		else {
			DataInputStream connectionGet = null;
			try {
				connectionGet = new DataInputStream(connection.getInputStream());
			} 
			catch (IOException e) {}
			String data;
			
			System.out.println("[Info] Connection " + toString() + " has opened.");
			
			while (true) {
				try {
					data = connectionGet.readUTF();
				} 
				catch (IOException e) {
					disconnect();
					break;	// The client has disconnected, so end the loop.
				}
				PacketSorterTracker.sort(data, this);
			}
		}
	}
	
	@Override
	public String toString() {
		return Integer.toString(connection.hashCode()); // This can be and probably should be changed when I implement usernames and the like.
	}
	
	/**
	 * Disconnects and cleans up stuff. You still will need to clean up any references to this class though.
	 */
	public void disconnect() {
		try {
			connection.close();
		} 
		catch (IOException e) {}
		
		System.out.println("[Info] Connection " + toString() + " closed.");
		connection = null;	// I'm not sure if this is necessary, but it can't do any harm.
		thread = null;
	}
	
	/**
	 * Sends the packet to the connection. You should probably use a sorter's send method though.
	 * @param packet
	 */
	public void send(JSONArray packet) {
		DataOutputStream connectionOut = null;
		try {
			connectionOut = new DataOutputStream(connection.getOutputStream());
		} 
		catch (IOException e) {}
		String data = packet.toJSONString();
		
		try {
			connectionOut.writeUTF(data);
		} 
		catch (IOException e) {
			disconnect();
		}
	}
}

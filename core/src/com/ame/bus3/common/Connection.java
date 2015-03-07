package com.ame.bus3.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.JSONArray;

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
			
			while (true) {
				try {
					data = connectionGet.readUTF();
				} 
				catch (IOException e) {
					disconnect();
					break;	// The client has disconnected, so end the loop.
				}
				PacketSorterTracker.sort(data);
			}
		}
	}
	
	/**
	 * Disconnects and cleans up stuff. You still will need to clean up any references to this class though.
	 */
	public void disconnect() {
		try {
			connection.close();
		} 
		catch (IOException e) {}
		
		connection = null;	// I'm not sure if this is necessary, but it can't do any harm.
		thread = null;
		System.out.println("[Info] Connection " + connection.toString() + " closed.");
	}
	
	/**
	 * 
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

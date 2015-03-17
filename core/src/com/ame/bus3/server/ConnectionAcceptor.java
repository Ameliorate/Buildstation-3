package com.ame.bus3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.ame.bus3.common.Connection;
import com.ame.bus3.common.packetsorters.SorterList;

/**
 * Accepts incoming connections as a server.
 * @author Amelorate
 *
 */
public class ConnectionAcceptor implements Runnable {
	private ServerSocket serverSocket;

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(25566);
		} 
		catch (IOException e) { }	// Sometimes I wish java would let you have some things without a try catch block. But it's probably for the better.
		
		ArrayList<Connection> clients = new ArrayList<Connection>();
		Socket newClient;	// I declare newClient here because creating new variables in try catch blocks makes things not work.
		
		while (true) {
			try {
				newClient = serverSocket.accept();
			} 
			catch (IOException e) { 
				continue;
			}
			
			Connection client = new Connection();
			client.connection = newClient;
			client.thread = new Thread(client, "client-" + client.toString());
            client.thread.start();
			clients.add(client);		// Creates a new client, starts a new thread for it, then stores it.
			
			SorterList.terminalDisplay.send(client, "[Info] Successfully connected to server");
		}
	}
}

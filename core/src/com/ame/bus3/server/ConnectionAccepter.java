package com.ame.bus3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.ame.bus3.common.Connection;

/**
 * Accepts incoming connections as a server.
 * @author Amelorate
 *
 */
public class ConnectionAccepter implements Runnable {
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
			Thread clientThread = new Thread(client, newClient.toString());
			clientThread.start();
			client.thread = clientThread;
			clients.add(client);		// Creates a new client, starts a new thread for it, then stores it.
		}
	}
}

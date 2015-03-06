package com.ame.busc3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionAccepter implements Runnable {
	private ServerSocket serverSocket;

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(25566);
		} 
		catch (IOException e) { }	// Sometimes I wish java would let you have some things without a try catch block. But it's probably for the better.
		
		ArrayList<Client> clients = new ArrayList<Client>();
		ArrayList<Thread> clientThreads = new ArrayList<Thread>();
		Socket newClient;	// I declare newClient here because creating new variables in try catch blocks makes things not work.
		
		while (true) {
			try {
				newClient = serverSocket.accept();
			} 
			catch (IOException e) { 
				continue;
			}
			
			Client client = new Client();
			client.giveSocket(newClient);
			Thread clientThread = new Thread(client, newClient.toString());
			clientThread.start();
			clientThreads.add(clientThread);
		}
	}
}

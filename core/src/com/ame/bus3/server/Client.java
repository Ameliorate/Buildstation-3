package com.ame.bus3.server;

import java.net.Socket;

public class Client implements Runnable {
	
	public Socket client;
	public Thread thread;

	@Override
	public void run() {
		if (client == null)
			throw new IllegalArgumentException("No client was given");
		else {
			
		}
			
	}
}

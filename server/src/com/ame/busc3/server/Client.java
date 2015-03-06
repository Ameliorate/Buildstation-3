package com.ame.busc3.server;

import java.net.Socket;

public class Client implements Runnable {
	
	private Socket client;
	
	public void giveSocket(Socket client)  {
		this.client = client;
	}

	@Override
	public void run() {
		if (client == null)
			throw new IllegalArgumentException("No client was given");
		else {
			
		}
			
	}
}

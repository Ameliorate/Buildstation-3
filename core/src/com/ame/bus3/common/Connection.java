package com.ame.bus3.common;

import java.net.Socket;

import com.badlogic.gdx.utils.JsonValue;

public class Connection implements Runnable {
	
	public Socket connection;
	public Thread thread;

	@Override
	public void run() {
		if (client == null)
			throw new IllegalArgumentException("No client was given");
		else {
			
		}
			
	}
	
	public void send(JsonValue packet) {
		
	}
}

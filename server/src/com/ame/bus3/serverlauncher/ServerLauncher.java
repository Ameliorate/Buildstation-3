package com.ame.bus3.serverlauncher;

import com.ame.bus3.server.ServerMain;

@SuppressWarnings("WeakerAccess")
public class ServerLauncher {
	public static void main(String[] args) {
		ServerMain busSM = new ServerMain();
		busSM.create();
	}

}

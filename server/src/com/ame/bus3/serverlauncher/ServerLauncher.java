package com.ame.bus3.serverlauncher;

import com.ame.bus3.server.BuildstationServerMain;

public class ServerLauncher {
	public static void main(String[] args) {
		BuildstationServerMain busSM = new BuildstationServerMain();
		busSM.create();
	}

}

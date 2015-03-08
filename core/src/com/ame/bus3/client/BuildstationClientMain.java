package com.ame.bus3.client;

import com.ame.bus3.common.Utilities;
import com.ame.bus3.common.Variables;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main class for the client. Handles client initalition and rendering.
 * @author Amelorate
 *
 */
public class BuildstationClientMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		Variables.isServer = false;
		String[] serverPortIPSplit;
		String serverPortIP;
		
		while (true) {	// Prompt the user for the ip and port.
			serverPortIP = Utilities.popupPrompt("Buildstation", "Enter a IP/Port to connect to:", "127.0.0.1/25566");
			try {
				serverPortIPSplit = serverPortIP.split("/");
			}
			catch (NullPointerException e) {
				Utilities.popupMessage("Invalid Input", "Cancel is not a valid option.");
				continue;
			}
			
			if (serverPortIPSplit.length == 2) {
				if (Utilities.validIP(serverPortIPSplit[0]))
					Variables.serverIP = serverPortIPSplit[0];
				else {
					Utilities.popupMessage("Invalid Input", serverPortIPSplit[0] + " Is not a valid IP address.");
					continue;	// Checks the ip.
				}
				
				try {
					Variables.port = Integer.parseInt(serverPortIPSplit[1]);	// Then the port.
				}
				catch (NumberFormatException e) {
					Utilities.popupMessage("Invalid Input", serverPortIPSplit[1] + " Is not a valid port.");
					continue;
				}
				
				break;	// If all the checks go well, continue on to the program.
			}
			Utilities.popupMessage("Invalid Input", serverPortIP + " Contains more than or less than 1 slash [/].");
		}
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}

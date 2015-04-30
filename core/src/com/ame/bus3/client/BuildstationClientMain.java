package com.ame.bus3.client;

import com.ame.bus3.common.GameMap;
import com.ame.bus3.common.TileRegisterer;
import com.ame.bus3.common.Variables;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main class for the client. Handles client initalition and rendering.
 * @author Amelorate
 *
 */
public class BuildstationClientMain extends ApplicationAdapter {
	public static BuildstationClientMain instance;

	/**
	 * Weather or not there is an active client running cerrently.
	 */
	public static boolean isActive = false;

	public ClientNetworkController clientNetworkController;	// This is public so it can be messed with during tests.
	public GameMap map = new GameMap(false);
	private SpriteBatch batch;

	public static BuildstationClientMain getInstance() {
		if (instance != null) {
			return instance;
		}
		else {
			return new BuildstationClientMain();
		}
	}

	@Override
	public void create() {
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.

		isActive = true;
		instance = this;

		TileRegisterer.load(false);
		System.out.println("[Info] Loaded tiles.");

		clientNetworkController = new ClientNetworkController();
		System.out.println("[Info] Connected to server.");
		
		batch = new SpriteBatch();
		RendererControler.load();
		System.out.println("[Info] Loaded rendering.");

		System.out.println("[Info] Finished starting up.");
	}

	@Override
	public void render() {
		RendererControler.render(batch);
	}
}

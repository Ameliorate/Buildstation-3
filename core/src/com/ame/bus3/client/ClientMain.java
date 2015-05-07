package com.ame.bus3.client;

import com.ame.bus3.common.TileRegistry;
import com.ame.bus3.common.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main class for the client. Handles client initialisation and rendering.
 * @author Amelorate
 *
 */
public class ClientMain extends ApplicationAdapter {
	public static ClientMain instance;

	/**
	 * Weather or not there is an active client running currently.
	 */
	@SuppressWarnings("unused")
	public static boolean isActive = false;

	public ClientNetworkController clientNetworkController;	// This is public so it can be messed with during tests.
	@SuppressWarnings("CanBeFinal")
	public World world = new World(false);
	private SpriteBatch batch;

	public static ClientMain getInstance() {
		if (instance != null) {
			return instance;
		}
		else {
			return new ClientMain();
		}
	}

	@Override
	public void create() {
		//noinspection deprecation
		Runtime.runFinalizersOnExit(true);	// Yes, I know this is unsafe, but there isn't really any other way to run code on jvm exit and then remove the code at runtime.

		isActive = true;
		instance = this;

		TileRegistry.load();
		System.out.println("[Info] Loaded tiles.");

		clientNetworkController = new ClientNetworkController();
		System.out.println("[Info] Connected to server.");
		
		batch = new SpriteBatch();
		RendererController.load();
		System.out.println("[Info] Loaded rendering.");

		System.out.println("[Info] Finished starting up.");
	}

	@Override
	public void render() {
		RendererController.render(batch, world);
	}
}

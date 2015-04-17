package com.ame.bus3.client;

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
	SpriteBatch batch;
	
	@Override
	public void create() {
		Variables.isServer = false;

		TileRegisterer.load();

		// TODO: Networking. It goes here.
		
		batch = new SpriteBatch();
		RendererControler.load();

		System.out.println("[Info] Finished starting up.");
	}

	@Override
	public void render() {
		RendererControler.render(batch);
	}
}

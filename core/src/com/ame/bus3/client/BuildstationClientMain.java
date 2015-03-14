package com.ame.bus3.client;

import com.ame.bus3.common.Variables;
import com.ame.bus3.common.packetsorters.SorterList;
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
	public BuildstationClientMain(Class running) {

	}
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		SorterList.loadSorters();
		Variables.isServer = false;
		
		ConnectionHandler.connect();
		
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

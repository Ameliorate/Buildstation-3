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
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		SorterList.loadSorters();
		Variables.isServer = false;
		
		ConnectionHandler.connect();
		
		batch = new SpriteBatch();
		RendererControler.load();
	}

	@Override
	public void render() {
		RendererControler.render(batch);
	}
}

package com.ame.bus3.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Renders the tiles on the screen.
 * @author Amelorate
 */
public class TileRenderer implements Renderer {
	private Texture img;

	public TileRenderer() {
		RendererControler.register("TileRender", this);
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render(SpriteBatch batch) {		// TODO: Actually make this render tiles.
		Gdx.gl.glClearColor(0.5f, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}

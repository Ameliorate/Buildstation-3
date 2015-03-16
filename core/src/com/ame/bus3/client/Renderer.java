package com.ame.bus3.client;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Amelorate
 */
public interface Renderer {
	/**
	 * Called once per render tick.
	 */
	public void render(SpriteBatch batch);
}

package com.ame.bus3.client;

import com.ame.bus3.common.World;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Amelorate
 */
@SuppressWarnings("WeakerAccess")
public interface Renderer {
	/**
	 * Called once per render tick.
	 */
	void render(SpriteBatch batch, World world);
}

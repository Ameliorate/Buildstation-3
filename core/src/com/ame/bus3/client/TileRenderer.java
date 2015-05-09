package com.ame.bus3.client;

import com.ame.bus3.common.*;
import com.ame.bus3.common.Tiles.Wall;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Renders the tiles on the screen.
 * @author Amelorate
 */
public class TileRenderer implements Renderer {
	@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
	public Coordinate[] renderLayers = {new Coordinate(0, 0, 0, "default")};
	private static final int PIXELS_PER_TILE = 48;

	public TileRenderer() {
		RendererController.register("TileRender", this);
	}

	@Override
	public void render(SpriteBatch batch, World world) {
		throw new NotImplementedException();
	}
}

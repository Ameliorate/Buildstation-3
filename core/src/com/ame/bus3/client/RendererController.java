package com.ame.bus3.client;

import com.ame.bus3.common.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

/**
 * Contains data on renders and allows rendering stuff.
 * @author Amelorate
 */
@SuppressWarnings("WeakerAccess")
public class RendererController {
	/**
	 * Determines what renderer is used to render the screen.
	 */
	@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
	public static String renderMode = "TileRender";

	/**
	 * Call this to re-render the screen.
	 */
	public static void render(SpriteBatch batch, World world) {
		batch.begin();
		Gdx.gl.glClearColor(0.5f, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		try {
			renderers.get(renderMode).render(batch, world);
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException("renderMode is of an invalid or unregistered value: " + renderMode, e);
		}
		batch.end();
	}

	@SuppressWarnings("CanBeFinal")
	private static HashMap<String, Renderer> renderers = new HashMap<>();

	/**
	 * Renderers can call this to allow screens to be rendered with them.
	 */
	public static void register(@SuppressWarnings("SameParameterValue") String name, Renderer renderer) {
		renderers.put(name, renderer);
	}

	/**
	 * Call this to load the rendererers.
	 */
	public static void load() {
		new TileRenderer();	// Add new renderers here.
	}
}

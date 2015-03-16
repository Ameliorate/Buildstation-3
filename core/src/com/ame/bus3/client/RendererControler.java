package com.ame.bus3.client;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

/**
 * Contains data on renders and allows rendering stuff.
 * @author Amelorate
 */
public class RendererControler {
	/**
	 * Determines what renderer is used to render the screen.
	 */
	public static String renderMode = "TileRender";

	/**
	 * Call this to re-render the screen.
	 */
	public static void render(SpriteBatch batch) {
		try {
			renderers.get(renderMode).render(batch);
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException("renderMode is of an invalid or unregistered value: " + renderMode, e);
		}
	}

	private static HashMap<String, Renderer> renderers = new HashMap<String, Renderer>();

	/**
	 * Renderers can call this to allow screens to be rendered with them.
	 */
	public static void register(String name, Renderer renderer) {
		renderers.put(name, renderer);
	}

	private static TileRenderer tileRenderer; // New renderers go here.

	/**
	 * Call this to load the rendererers.
	 */
	public static void load() {
		tileRenderer = new TileRenderer();
	}
}

package com.ame.bus3.client;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Controls textures for tiles.
 * @author Amelorate
 */
public class TileTextureControler {
	private static HashMap<String, Texture> textureHashMap = new HashMap<String, Texture>();

	/**
	 * Registers a new texture.
	 */
	public static void register(String name, String texture) {
		if (get(name) == null)
			textureHashMap.put(name, new Texture(texture));
	}

	/**
	 * Gets a texture based on it's name.
	 */
	public static Texture get(String name) {
		return textureHashMap.get(name);
	}
}

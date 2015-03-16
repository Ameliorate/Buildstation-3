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
	public static void register(String name, Texture texture) {
		textureHashMap.put(name, texture);
	}

	/**
	 * Gets a texture based on it's name.
	 */
	public static void get(String name) {
		textureHashMap.get(name);
	}
}

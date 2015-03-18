package com.ame.bus3.common;

/**
 * Stores details about how a tile is rendered.
 * @author Amelorate
 */
public class SpriteState {
	/**
	 * The texture used to render the sprite.
	 */
	public String texture;

	/**
	 * The amount of rotation in degrees to be applied to the texture.
	 */
	public int rotation;

	/**
	 * Flip the texture horizontally?
	 */
	public boolean flipX = false;

	/**
	 * Flip the texture vertically?
	 */
	public boolean flipY = false;
}

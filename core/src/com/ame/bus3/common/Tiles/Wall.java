package com.ame.bus3.common.Tiles;

import com.ame.bus3.client.TileTextureControler;
import com.ame.bus3.common.BaseTile;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.Variables;
import com.badlogic.gdx.graphics.Texture;

/**
 * A simple wall.
 * @author Amelorate
 */
public class Wall extends BaseTile {
	public Wall() {
		if (!Variables.isServer)
			TileTextureControler.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	public Wall(Coordinate location) {
		super(location);
		if (!Variables.isServer)
			TileTextureControler.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	@Override
	public void clone(Coordinate location) {
		Variables.map.place(new Wall(location), location);
	}

	@Override
	public String getType() {
		return "Wall";
	}
}

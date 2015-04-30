package com.ame.bus3.common.Tiles;

import com.ame.bus3.client.BuildstationClientMain;
import com.ame.bus3.client.TileTextureControler;
import com.ame.bus3.common.BaseTile;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.Variables;
import com.ame.bus3.server.BuildstationServerMain;
import com.badlogic.gdx.graphics.Texture;

/**
 * A simple wall.
 * @author Amelorate
 */
public class Wall extends BaseTile {
	public Wall(boolean isServer) {
		if (!isServer)
			TileTextureControler.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	public Wall(Coordinate location, boolean isServer) {
		super(location, isServer);
		if (!isServer)
			TileTextureControler.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	@Override
	public void clone(Coordinate location, boolean isServer) {
		if (isServer) {
			BuildstationServerMain.getInstance().map.place(new Wall(location, isServer), location);
		}
		else {
			BuildstationClientMain.getInstance().map.place(new Wall(location, isServer), location);
		}
	}

	@Override
	public String getType() {
		return "Wall";
	}
}

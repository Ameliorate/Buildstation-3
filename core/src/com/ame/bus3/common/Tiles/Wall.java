package com.ame.bus3.common.Tiles;

import com.ame.bus3.client.ClientMain;
import com.ame.bus3.client.TileTextureRegistry;
import com.ame.bus3.common.BaseTile;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.server.ServerMain;

/**
 * A simple wall.
 * @author Amelorate
 */
public class Wall extends BaseTile {
	public Wall(boolean isServer) {
		if (!isServer)
			TileTextureRegistry.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	public Wall(Coordinate location, boolean isServer) {
		super(location, isServer);
		if (!isServer)
			TileTextureRegistry.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	@Override
	public void clone(Coordinate location, boolean isServer) {
		if (isServer) {
			ServerMain.getInstance().map.place(new Wall(location, true), location);
		}
		else {
			ClientMain.getInstance().map.place(new Wall(location, false), location);
		}
	}

	@Override
	public String getType() {
		return "Wall";
	}
}

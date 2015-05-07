package com.ame.bus3.common.Tiles;

import com.ame.bus3.client.ClientMain;
import com.ame.bus3.client.TileTextureRegistry;
import com.ame.bus3.common.BaseTile;
import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.World;
import com.ame.bus3.server.ServerMain;

/**
 * A simple wall.
 * @author Amelorate
 */
public class Wall extends BaseTile {
	public Wall() {
		TileTextureRegistry.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	public Wall(Coordinate location, World world) {
		super(location, world);
		TileTextureRegistry.register("Wall", "Wall.jpg");
		spriteState.texture = "Wall";
	}

	@Override
	public void clone(Coordinate location, World world) {
		world.map.place(new Wall(location, world), location);
	}

	@Override
	public String getType() {
		return "Wall";
	}
}

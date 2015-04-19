package com.ame.bus3.client;

import com.ame.bus3.common.Coordinate;
import com.ame.bus3.common.SpriteState;
import com.ame.bus3.common.Tile;
import com.ame.bus3.common.Tiles.Wall;
import com.ame.bus3.common.Variables;
import com.ame.bus3.common.netlisteners.GetTile;
import com.ame.bus3.common.netlisteners.WaitUntil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Renders the tiles on the screen.
 * @author Amelorate
 */
public class TileRenderer implements Renderer {
	public Coordinate[] renderLayers = {new Coordinate(0, 0, 0, "default")};

	public TileRenderer() {
		RendererControler.register("TileRender", this);
	}

	@Override
	public void render(SpriteBatch batch) {
		Texture drawing;
		Tile drawingTile = new Wall(new Coordinate(0, 0, 0, "temp"));	// I initialise this with wall so that the loop works correctly.
		SpriteState state = null;
		int tileXPixel;
		int tileYPixel;
		float rotation;
		boolean flipX;
		boolean flipY;

		batch.begin();
		Gdx.gl.glClearColor(0.5f, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for(int i = 0; i <= renderLayers.length; i++)
			for (int x = renderLayers[i].getX(); x <= 15; x++)
				for (int y = renderLayers[i].getY(); y <= 15; y++)
					for (int z = 0; drawingTile != null; z++) {
						System.out.println("Render try.");

						drawingTile = Variables.map.get(new Coordinate(x, y, z, renderLayers[i].getLevel()));
						try {
							state = drawingTile.renderTick();
						}
						catch (NullPointerException e) {
							if (z == 0) {
								GetTile.send(new Coordinate(x, y, z, renderLayers[i].getLevel()), BuildstationClientMain.clientNetworkController.client);
								WaitUntil.wait("got");	// This can cause a bottleneck based on the speed of the network connection.

								drawingTile = new Wall(new Coordinate(0, 0, 0, "temp"));
								z--;
								e.printStackTrace();
								continue; // This and the above 2 statements makes the renderer retry rendering the tile.
							}
							else
								break;
						}

						drawing = TileTextureControler.get(state.texture);
						tileXPixel = x * 48;	// For various reasons, we assume sprites are 48 pixels in height/width.
						tileYPixel = y * 48;
						rotation = state.rotation;
						flipX = state.flipX;
						flipY = state.flipY;

						batch.draw(drawing, tileXPixel, tileYPixel, 24, 24, 48, 48, 1, 1, rotation, 0, 0, 48, 48, flipX, flipY);
						/*
						It's probably better to rewrite the above statement if you want to maintain this.
						This will help:
						https://stackoverflow.com/questions/24748350/libgdx-rotate-a-texture-when-drawing-it-with-spritebatch
						 */

						System.out.println("Render successful.");
					}

		batch.end();
	}
}

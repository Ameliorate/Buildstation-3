package com.ame.bus3.client;

import com.ame.bus3.common.*;
import com.ame.bus3.common.Tiles.Wall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

/**
 * Renders the tiles on the screen.
 * @author Amelorate
 */
public class TileRenderer implements Renderer {
	public Coordinate[] renderLayers = {new Coordinate(0, 0, 0, "default")};
	private HashMap<ChunkCacheKey, Chunk> chunkCache = new HashMap<ChunkCacheKey, Chunk>();
	private static final int PIXELS_PER_TILE = 48;

	public TileRenderer() {
		RendererControler.register("TileRender", this);
	}

	@Override
	public void render(SpriteBatch batch) {
		Texture drawing;
		Tile drawingTile = new Wall();	// I initialise this with wall so that the loop works correctly.
		SpriteState state;
		int tileXPixel;
		int tileYPixel;

		for(Coordinate cererntLayer : renderLayers) {
			for (int x = -1; x <= 1; x++)
				for (int y = -1; y <= 1; y++) {
					int xChunkPos = (cererntLayer.getX() / 16) + x;
					int yChunkPos = (cererntLayer.getY() / 16) + y;

					Chunk cacheingChunk = Variables.map.getChunk(new Coordinate(xChunkPos, yChunkPos, 0, cererntLayer.getLevel()));
					ChunkCacheKey key = new ChunkCacheKey(x, y, cererntLayer.getLevel());
					chunkCache.put(key, cacheingChunk);
				}

			for (int x = cererntLayer.getX(); x <= 15; x++)
				for (int y = cererntLayer.getY(); y <= 15; y++)
					for (int z = 0; drawingTile != null; z++) {
						System.out.println("Render try");
						drawingTile = Variables.map.get(new Coordinate(x, y, z, cererntLayer.getLevel()));
						System.out.println("boo");
						if (drawingTile == null) {
							System.out.println("null thingy");
							break;
						} else {
							state = drawingTile.renderTick();
							tileXPixel = drawingTile.getPosition().getX() * PIXELS_PER_TILE;
							tileYPixel = drawingTile.getPosition().getY() * PIXELS_PER_TILE;
							drawing = TileTextureControler.get(state.texture);

							batch.draw(drawing, tileXPixel, tileYPixel, 24, 24, 48, 48, 1, 1, state.rotation, 0, 0, 48, 48, state.flipX, state.flipY);
							/*
							It's probably better to rewrite the above statement if you want to maintain this.
							This will help:
							https://stackoverflow.com/questions/24748350/libgdx-rotate-a-texture-when-drawing-it-with-spritebatch
							 */
							System.out.println("Render!");
						}
					}
		}
	}

	private class ChunkCacheKey {
		public ChunkCacheKey(int x, int y, String level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}

		public int x;
		public int y;
		public String level;

		@Override
		public int hashCode() {
			return x + y + level.hashCode();
		}
	}
}

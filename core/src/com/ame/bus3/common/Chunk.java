package com.ame.bus3.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * @author Amelorate
 * Internal class used for containg a 16*16 square of tiles.
 */
public class Chunk {

	public Coordinate location;
	public HashMap<Coordinate, Tile> tiles = new HashMap<Coordinate, Tile>();

	@Override
	protected void finalize() throws Throwable {
		if (Variables.isServer) {
			Kryo kryo = new Kryo();		// It would probably be best to find another way to not have to create an entire kryo instance.
			Output output = new Output(new FileOutputStream("map/" + location.getLevel() + "/" + location.getX() + "/" + location.getY() + "chunk.busmap"));

			kryo.writeObject(output, this);
			output.close();
		}
		super.finalize();
	}

	/**
	 * Load a chunk from the disc at a specific point.
	 */
	public static Chunk loadChunkFromDisc(Coordinate location) {
		Input input;
		try {
			input = new Input(new FileInputStream("map/" + location.getLevel() + "/" + location.getX() + "/" + location.getY() + "chunk.busmap"));
		}
		catch (FileNotFoundException e) {
			return new Chunk();
		}
		Kryo kryo = new Kryo();
		Chunk chunk = (Chunk) kryo.readClassAndObject(input);

		input.close();

		return chunk;
	}
}

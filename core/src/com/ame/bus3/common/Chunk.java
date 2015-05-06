package com.ame.bus3.common;

import com.ame.bus3.server.ServerMain;
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

	@SuppressWarnings("unused")
	public Coordinate location;
	@SuppressWarnings("CanBeFinal")
	public HashMap<Coordinate, Tile> tiles = new HashMap<>();

	@Override
	protected void finalize() throws Throwable {
		if (ServerMain.isActive) {
			Kryo kryo = new Kryo();		// It would probably be best to find another way to not have to create an entire kryo instance.
			Output output = new Output(new FileOutputStream("map/" + location.getLevel() + "/" + location.getX() + "/" + location.getY() + "chunk.busmap"));

			kryo.writeObject(output, this);
			output.close();
		}
		super.finalize();
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return tiles.equals(((Chunk) obj).tiles) && location.equals(((Chunk) obj).location);
		}
		catch (ClassCastException e) {
			return false;
		}
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

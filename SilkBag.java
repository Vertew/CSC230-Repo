import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a Silk Bag.
 * 
 * @author Todd Dyer
 * @author Marta Piaszczynska
 * @version 1.0
 */
public class SilkBag {

	// An array list to store Tile objects.
	private ArrayList<Tile> tilesInBag;

	/**
	 * Constructs a instance of the SilkBag class. This constructor is used when
	 * starting a brand new game.
	 */
	public SilkBag() {
		this.tilesInBag = new ArrayList<Tile>();
	}

	/**
	 * Constructs an instance of the SilkBag class. This constructor is used when
	 * loading a game from a save file.
	 * 
	 * @param tiles The tiles that this SilkBag contains.
	 */
	public SilkBag(ArrayList<Tile> tiles) {
		this.tilesInBag = tiles;
	}

	/**
	 * Add a tile to this Silk Bag.
	 * 
	 * @param tile The tile to place in this Silk Bag.
	 */
	public void placeTileInBag(Tile tile) {
		this.tilesInBag.add(tile);
	}

	/**
	 * Randomly draw a tile from this Silk Bag. The random tile is removed from the
	 * Silk Bag once it is drawn.
	 * 
	 * @return A random tile from this Silk Bag.
	 */
	public Tile drawTile() {
		if (isEmpty()) {
			throw new NullPointerException();
		} else {
			Random rand = new Random();
			int index = rand.nextInt(tilesInBag.size());
			Tile toReturn = tilesInBag.get(index);
			tilesInBag.remove(index);
			return toReturn;
		}
	}

	/**
	 * @return The string representation of all the tiles in this Silk Bag.
	 */
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < tilesInBag.size() - 1; i++) {
			toReturn += tilesInBag.get(i).toString() + " ";
		}
		return toReturn;
	}

	/**
	 * @return A getter method for specific tiles in the tilesInBag array.
	 */

	public Tile getTileInBag(int index) {
		return tilesInBag.get(index);
	}

	/**
	 * @return A getter method for the tilesInBag array.
	 */

	public ArrayList<Tile> getTilesInBag() {
		return tilesInBag;
	}

	/**
	 * @return true if this Silk Bag is empty, false if otherwise.
	 */
	public Boolean isEmpty() {
		if (tilesInBag.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}

import java.util.ArrayList;

/**
 * Hand class defines the hand and what the user can hold in that hand
 * 
 * @author Mae Hedmann-Highmore
 * @version 1.0
 */

public class Hand {

	private ArrayList<Tile> holds = new ArrayList<Tile>();
	private Tile temp;
	private SilkBag bag;

	/**
	 * Constructor for Hand class
	 * 
	 * @param bag The silk bag that contains tiles
	 */
	public Hand(SilkBag bag) {
		setBag(bag);
	}

	/**
	 * Resets the bag that the user has
	 * 
	 * @param bag The silkbag the user has
	 */
	private void setBag(SilkBag bag) {
		this.bag = bag;
	}

	/**
	 * Draws a tile from the silk bag
	 * 
	 * @return the tile just drawn from the bag
	 */
	public Tile draw() {
		Tile justDrawn = bag.drawTile();
		holds.add(justDrawn);
		return justDrawn;
	}

	/**
	 * Checks whether the bag is empty
	 * 
	 * @return true if the bag is empty, false otherwise
	 */
	public boolean isEmpty() {
		return holds.isEmpty();
	}

	/**
	 * Removes a tile from the bag
	 * 
	 * @param toRemove the tile that needs to be removed
	 */
	public void remove(Tile toRemove) {
		holds.remove(toRemove);
	}

	/**
	 * Get the tile thats currently being held
	 * 
	 * @return the tile thats being held
	 */
	public ArrayList<Tile> getHolding() {
		return holds;
	}

	/**
	 * @param index The location in the bag that the tile is being taken from
	 * @return the tile from index location
	 */
	public Tile getAt(int index) {
		return holds.get(index);
	}

	/**
	 * Gets a tile at index location and then removes it
	 * 
	 * @param index The location in the bag that the tile is being taken from
	 * @return tile removed from the bag
	 */
	public Tile getAndRemove(int index) {
		temp = holds.get(index);
		holds.remove(index);
		return temp;
	}
}

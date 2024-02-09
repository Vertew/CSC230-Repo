import java.util.ArrayList;
import java.util.LinkedList;

/**
 * InEffect Class for Action Tiles
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @version 1.0
 */
public class InEffect {

	private LinkedList<Lasting> activeTiles = new LinkedList<Lasting>();

	/**
	 * Method to add effect to Action tile
	 * 
	 * @param inpTile  Action tile
	 * @param target   Target tile
	 * @param duration Effect duration
	 */
	public void addEffect(Lasting inpTile, ArrayList<Floor> target, int duration) {
		this.activeTiles.add(inpTile);
		inpTile.onActivated(target, duration);
	}

	/**
	 * Maintains tile's effect for a given time
	 */
	public void upkeep() {
		for (Lasting tile : activeTiles) {
			tile.decDuration();
			if (tile.getDuration() == 0) {
				tile.onDeactivated();
			}
		}
		// System.out.println("upkeep upkept");
	}

	/**
	 * @param index Location of the Action tile
	 * @return Action tile at the location
	 */
	public Lasting getActiveTile(int index) {
		return activeTiles.get(index);
	}

	/**
	 * @return active Action tiles
	 */
	public LinkedList<Lasting> getActiveTiles() {
		return activeTiles;
	}

}

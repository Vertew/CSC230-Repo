import java.util.ArrayList;

/**
 * Lasting Class determines behaviour of Action Tiles
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @version 1.0
 */

public class Lasting extends Tile {

	protected int duration;

	/**
	 * Creates Lasting class instance
	 */
	public Lasting() {

	}

	/**
	 * Shows effect of an Action tile on other tiles for a duration
	 * 
	 * @param target   Target tiles
	 * @param duration Effect duration
	 */
	public void onActivated(ArrayList<Floor> target, int duration) {

	}

	/**
	 * Change's tile's behaviour when deactivated
	 */
	public void onDeactivated() {

	}

	/**
	 * Decreases effect's duration
	 */
	public void decDuration() {
		duration--;
		// System.out.println(duration);
	}

	/**
	 * @return effect's duration
	 */
	public int getDuration() {
		return this.duration;
	}

}
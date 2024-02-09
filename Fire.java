import java.util.ArrayList;

/**
 * Fire class represents how the fire affect effects functions.
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @version 1.0
 */

public class Fire extends Lasting {

	private ArrayList<Floor> effected;

	/**
	 * Constructor for Fire class
	 */
	public Fire() {

	}

	/**
	 * Method to change fire tile behaviour when activated
	 * 
	 * @param target   The target floor tile that the fire tile is being placed on
	 * @param duration The duration of the fire on the tile
	 */
	@Override
	public void onActivated(ArrayList<Floor> target, int duration) {
		this.duration = duration;
		this.effected = target;
		for (int x = 0; x < effected.size(); x++) {
			effected.get(x).igniteTile();
		}
	}

	/**
	 * Method to change fire tile behaviour when deactivated and the fire is
	 * extinguished
	 */
	@Override
	public void onDeactivated() {
		for (int x = 0; x < effected.size(); x++) {
			effected.get(x).extinguish();
		}
	}
}

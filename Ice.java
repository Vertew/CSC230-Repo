import java.util.ArrayList;

/**
 * Ice class represents how the ice effects affect functions.
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @version 1.0
 */

public class Ice extends Lasting {

	private ArrayList<Floor> effected;

	/**
	 * Creates an instance of the Ice Class
	 */
	public Ice() {

	}

	/**
	 * Freezes targeted tiles for a period of time
	 * 
	 * @param target   The target floor tile that the ice tile is being placed on
	 * @param duration The duration of the ice on the tile
	 */
	@Override
	public void onActivated(ArrayList<Floor> target, int duration) {
		this.effected = target;
		this.duration = duration;
		for (int x = 0; x < target.size(); x++) {
			target.get(x).freeze();
		}
	}

	/**
	 * Removes the freeze effect when deactivated
	 */
	@Override
	public void onDeactivated() {
		for (int x = 0; x < effected.size(); x++) {
			effected.get(x).unfreeze();
		}
	}
}

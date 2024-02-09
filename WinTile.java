/**
 * This class represents how the win tile will interact with the game.
 * 
 * @authors Mae Hedmann-Highmore
 * @author Sam Tudberry.
 * @version 1.0
 */

class WinTile extends Floor {

	final boolean[] ORIGINAL_DIRECTIONS = { true, true, true, true };

	/**
	 * Constructor for the win tile.
	 * 
	 * @param rotation Rotation of win tile.
	 * @param fixed    True if win tile is fixed.
	 */
	public WinTile(int rotation, boolean fixed) {
		super(rotation, fixed);
		initDirections(rotation);
	}

	/**
	 * Defines the starting rotations and applies that rotation.
	 * 
	 * @param rotation The rotation of the win tile.
	 */
	private void initDirections(int rotation) {
		this.rotation = rotation;
		this.directions = applyRotation(rotation);
	}

	/**
	 * @return the direction of the win tile.
	 */
	@Override
	public boolean[] getDirections() {
		return ORIGINAL_DIRECTIONS;
	}

	/**
	 * Makes a new direction for the win tile.
	 * 
	 * @param direction the rotation of the win tile.
	 */
	@Override
	public void newRotation(int direction) {
		initDirections(direction);
	}

}

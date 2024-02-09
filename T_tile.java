/**
 * This class represents T-shaped tiles in the game.
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry.
 * @version 1.0
 */

class T_tile extends Floor {

	final boolean[] ORIGINAL_DIRECTIONS = { true, true, true, false };

	/**
	 * Constructs a new T_tile instnace.
	 * 
	 * @param rotation The rotation of the tile.
	 * @param fixed    True if the tile is fixed.
	 */
	public T_tile(int rotation, boolean fixed) {
		super(rotation, fixed);
		initDirections(rotation);
	}

	/**
	 * Defines the starting rotations and applies that rotation.
	 * 
	 * @param rotation The rotaion of the tile.
	 */
	private void initDirections(int rotation) {
		this.rotation = rotation;
		this.directions = applyRotation(rotation);
	}

	/**
	 * @return the direction of the tile movement.
	 */
	@Override
	public boolean[] getDirections() {
		return directions;
	}

	/**
	 * Makes a new direction for the tile.
	 * 
	 * @param rotation the rotation of the tile.
	 */
	@Override
	public void newRotation(int rotation) {
		initDirections(rotation);
	}

	/**
	 * Applies the rotation to the tile.
	 * 
	 * @param rotation The rotation that needs to be applied.
	 * @return the new rotation.
	 */
	@Override
	protected boolean[] applyRotation(int rotation) {

		boolean[] temp = new boolean[4];
		temp[0] = ORIGINAL_DIRECTIONS[0];
		temp[1] = ORIGINAL_DIRECTIONS[1];
		temp[2] = ORIGINAL_DIRECTIONS[2];
		temp[3] = ORIGINAL_DIRECTIONS[3];
		boolean store;
		for (int x = 1; x < rotation + 1; x++) {

			store = temp[0];
			temp[0] = temp[3];
			temp[3] = temp[2];
			temp[2] = temp[1];
			temp[1] = store;

			/*
			 * System.out.println("rotated: "+x); System.out.println("Orginal:");
			 * System.out.println(ORIGINAL_DIRECTIONS[0]);
			 * System.out.println(ORIGINAL_DIRECTIONS[1]);
			 * System.out.println(ORIGINAL_DIRECTIONS[2]);
			 * System.out.println(ORIGINAL_DIRECTIONS[3]);
			 * 
			 * System.out.println("Temp:"); System.out.println(temp[0]);
			 * System.out.println(temp[1]); System.out.println(temp[2]);
			 * System.out.println(temp[3]);
			 */

		}
		return temp;
	}
}
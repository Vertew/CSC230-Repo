/**
 * This class defines the floor tiles for the board
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @author Navid Reaz
 * @version 1.0
 */

abstract class Floor extends Tile {

	private boolean frozen;
	private boolean fixed;
	private boolean onFire;
	private boolean isPlayerOn = false;
	protected int rotation; // 0 = normal orientation,(1,2,3) = rotated (1,2,3) times to the right.
	protected boolean[] directions; // [North,South,East,West] (Implemented by subclasses)

	/**
	 * Constructor for Floor class
	 * 
	 * @param rotation The rotation of the floor piece
	 * @param fixed    True if the floor piece fixed, false othrerwise
	 */
	public Floor(int rotation, boolean fixed) {
		setRotation(rotation);
		setFixed(fixed);
	}

	/**
	 * Set a floor piece as fixed on the board
	 * 
	 * @param fixedPiece True if the floor piece is fixed
	 */
	private void setFixed(boolean fixedPiece) {
		this.fixed = fixedPiece;
	}

	/**
	 * @return true if the tile is fixed, false otherwise
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * @return rotation of a tile
	 */
	@Override
	public int getRotation() {
		return this.rotation;
	}

	/**
	 * Set the rotation of the floor piece
	 * 
	 * @param rotation The new rotation of the tile
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	/**
	 * Sets a tile on fire
	 */
	public void igniteTile() {
		this.onFire = true;
	}

	/**
	 * Freezes a tile
	 */
	public void freeze() {
		this.frozen = true;
	}

	/**
	 * Sets a player on a specific tile
	 * 
	 * @param isOn The player on the tile
	 */
	public void setPlayerOn(boolean isOn) {
		this.isPlayerOn = isOn;
	}

	/**
	 * @return true if a player is on the tile
	 */
	public boolean isPlayerOn() {
		return this.isPlayerOn;
	}

	/**
	 * Unfreezes a tile
	 */
	public void unfreeze() {
		this.frozen = false;
	}

	/**
	 * Extinguishes a tile
	 */
	public void extinguish() {
		this.onFire = false;
	}

	/**
	 * @return true if the tile can move (is not fixed or frozen)
	 */
	public boolean canMove() {
		return !(frozen || fixed);
	}

	/**
	 * @return true if the tile is frozen
	 */
	public boolean isFrozen() {
		return frozen;
	}

	/**
	 * @return true if the tile is on fire
	 */
	public boolean isOnFire() {
		return onFire;
	}

	/**
	 * When a tile is being rotated
	 * 
	 * @param rotation The new rotation of the tile
	 */
	public void newRotation(int rotation) {

	}

	/**
	 * @return empty array if directions
	 */
	public boolean[] getDirections() {
		return null;
	}

	/**
	 * Applies the rotation to the floor
	 * 
	 * @param rotation New rotation
	 * @return null
	 */
	protected boolean[] applyRotation(int rotation) {
		return null;
	}

}
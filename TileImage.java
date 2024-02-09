import javafx.scene.image.Image;

/**
 * TileImage Class that creates graphic representation of Floor Tiles
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry.
 * @version 1.0
 */

public class TileImage extends Image {

	private static final String TILE_CORNER_URL = "images/tiles/corner.png";
	private static final String TILE_T_URL = "images/tiles/t.png";
	private static final String TILE_GOAL_URL = "images/tiles/goal.png";
	private static final String TILE_STRAIGHT_URL = "images/tiles/straight.png";
	private static final String TILE_FIRE_URL = "images/tiles/fire.png";
	private static final String TILE_ICE_URL = "images/tiles/ice.png";
	private static final String TILE_DOUBLEMOVE_URL = "images/tiles/doubleMove.png";
	private static final String TILE_BACKTRACK_URL = "images/tiles/backTrack.png";

	private static final String TILE_CORNER_LOCK = "images/tiles/cornerLock.png";
	private static final String TILE_T_LOCK = "images/tiles/tLock.png";
	private static final String TILE_GOAL_LOCK = "images/tiles/goalLock.png";
	private static final String TILE_STRAIGHT_LOCK = "images/tiles/lineLock.png";

	/**
	 * Constructor for the tile image.
	 * 
	 * @param tile    The tile.
	 * @param canMove True if the tile can move.
	 */
	public TileImage(Tile tile, boolean canMove) {
		super(setTileType(tile, canMove));
	}

	/**
	 * Set tile type.
	 * 
	 * @param tile    The tile to reset
	 * @param canMove True if the tile can be moved
	 * @return tile type.
	 */
	private static String setTileType(Tile tile, boolean canMove) {
		if (tile.getClass() == Corner.class) {
			if (canMove) {
				return TILE_CORNER_URL;
			} else {
				return TILE_CORNER_LOCK;
			}
		} else if (tile.getClass() == T_tile.class) {
			if (canMove) {
				return TILE_T_URL;
			} else {
				return TILE_T_LOCK;
			}
		} else if (tile.getClass() == WinTile.class) {
			if (canMove) {
				return TILE_GOAL_URL;
			} else {
				return TILE_GOAL_LOCK;
			}
		} else if (tile.getClass() == Straight.class) {
			if (canMove) {
				return TILE_STRAIGHT_URL;
			} else {
				return TILE_STRAIGHT_LOCK;
			}
		} else if (tile.getClass() == Fire.class) {
			return TILE_FIRE_URL;
		} else if (tile.getClass() == Ice.class) {
			return TILE_ICE_URL;
		} else if (tile.getClass() == DoubleMove.class) {
			return TILE_DOUBLEMOVE_URL;
		} else if (tile.getClass() == BackTrack.class) {
			return TILE_BACKTRACK_URL;
		} else
			return TILE_CORNER_URL;
	}

}

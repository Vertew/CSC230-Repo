import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.Arrays;

/**
 * This class holds information about the location of each player and handles
 * movement
 * 
 * @author Mae Hedmann-Highmore 991360
 * @author Ben MAcdonald
 * @author Ben Frampton
 * @version 1.0
 */

public class PlayerPiece {

	private Floor position;
	private final boolean[] allTrue = { true, true, true, true };
	private Board board;
	private int x;
	private int y;
	// boolean player1;
	private int representing;
	private Game2 game;
	private final String P1ICON = "images/Player1.png";
	private final String P2ICON = "images/Player2.png";
	private final String P3ICON = "images/Player3.png";
	private final String P4ICON = "images/Player4.png";
	private boolean Victor;
	private int[] last2X = { -1, -1 };
	private int[] last2Y = { -1, -1 };
	private Profile profile;

	/**
	 * Constructs a new player piece.
	 * 
	 * @param x            The starting x location of the player
	 * @param y            The starting y location of the player
	 * @param board        The board class the game is using
	 * @param representing A variable to differentiate what player this represents
	 * @param game         The game class
	 */
	public PlayerPiece(int x, int y, Board board, int representing, Game2 game) {
		setGame(game);
		setRepresenting(representing);
		setBoard(board);
		setXY(x, y);

		// boolean[] currentDir = position.getDirections();
		// System.out.println(position.getRotation());
		// System.out.println("north: "+currentDir[0] + " east: " + currentDir[1]+"
		// south: " + currentDir[2]+" west: " + currentDir[3]);
	}
	
	/**
	 * Sets the profile of this player.
	 * @param p This players profile
	 */
	public void setProfile(Profile p) {
		this.profile = p;
	}
	
	/**
	 * @return This players profile.
	 */
	public Profile getProfile() {
		return this.profile;
	}

	/**
	 * Sets the Game
	 * 
	 * @param game the new Game
	 */
	private void setGame(Game2 game) {
		this.game = game;
	}

	/**
	 * Sets the position that the player will start at
	 * 
	 * @param tile The floor tile the player will start on
	 */
	private void setPosition(Floor tile) {
		this.position = tile;
	}

	/**
	 * Sets the board that the playerPiece uses
	 * 
	 * @param board The board that the playerPiece uses
	 */
	private void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the x axis position of the player
	 */
	public int getXpos() {
		return x;
	}

	/**
	 * @return the y axis position of the player
	 */
	public int getYpos() {
		return y;
	}

	/**
	 * Sets the representing position of the player
	 * 
	 * @param current The current representing position of the player
	 */
	private void setRepresenting(int current) {
		this.representing = current;
	}

	/**
	 * Sets the new winner of the game
	 * 
	 * @param newVictor True if there is a new winner
	 */
	public void setVictor(Boolean newVictor) {
		this.Victor = newVictor;
	}

	/**
	 * @return the winner
	 */
	public boolean getVictor() {
		return this.Victor;
	}

	/**
	 * @return the Tile position of the playerPiece
	 */
	public Tile getTileOn() {
		return position;
	}

	/**
	 * Checks which direction the player has moved
	 * 
	 * @param moved The direction the player has attempted to move
	 */
	public void checkMoved(int moved) {
		if (position != board.getTileAt(x, y)) {

			if (moved == 0) { // if down move
				try {
					setXY(x, y + 1);
				} catch (Exception e) {
					setXY(x, 0);
				}
			} else if (moved == 1) { // if left move
				try {
					setXY(x - 1, y);
				} catch (Exception e) {
					setXY(board.getGridSizeX() - 1, y);
				}
			} else if (moved == 2) {// if up move

				try {
					setXY(x, y - 1);
				} catch (Exception e) {
					setXY(x, board.getGridSizeY() - 1);
				}
			} else if (moved == 3) { // if right move

				try {
					setXY(x + 1, y);
				} catch (Exception e) {
					setXY(0, y);
				}
			}
		}
	}

	/**
	 * Takes the x and y coordinates and saves them along with the piece they
	 * represent
	 * 
	 * @param x The x coordinate to save
	 * @param y The y coordinate to save
	 */
	private void setXY(int x, int y) {
		try {
			position.setPlayerOn(false);
		} catch (Exception e) {
		}

		this.x = x;
		this.y = y;
		setPosition(board.getTileAt(x, y));
		position.setPlayerOn(true);
	}

	/**
	 * Takes a grid and add the player to the correct location on that grid
	 * 
	 * @param grid The grid in which the player should be added
	 */
	public void drawPlayer(GridPane grid) {
		Image imgToDraw = null;
		if (representing == 1) {
			imgToDraw = new Image(P1ICON);
		} else if (representing == 2) {
			imgToDraw = new Image(P2ICON);
		} else if (representing == 3) {
			imgToDraw = new Image(P3ICON);
		} else if (representing == 4) {
			imgToDraw = new Image(P4ICON);
		}

		ImageView imgView = new ImageView(imgToDraw);
		grid.add(imgView, x + 1, y + 1);
	}

	/**
	 * Takes a direction and move a player that way AS LONG AS THEY CAN
	 * 
	 * @param direction The string direction in which to move
	 */
	public void movement(String direction) {
		boolean[] temp = getDirections();

		updateLast2(this.x, this.y);
		if (direction.equals("up") && temp[0]) {
			setXY(x, y - 1);
			game.finishedMove();
		} else if (direction.equals("left") && temp[3]) {
			setXY(x - 1, y);
			game.finishedMove();
		} else if (direction.equals("right") && temp[1]) {
			setXY(x + 1, y);
			game.finishedMove();
		} else if (direction.equals("down") && temp[2]) {
			setXY(x, y + 1);
			game.finishedMove();
		}
		if (Arrays.equals(position.getDirections(), allTrue)) {
			game.winGame(representing);
		}
	}

	/**
	 * Checks if the user has gone back to a tile they just moved from
	 * 
	 * @return true if they moved back
	 */
	public boolean backTrack() {
		Boolean out = false;
		if (last2X[1] != -1 && last2Y[1] != -1) {
			if (!board.getTileAt(last2X[1], last2Y[1]).isOnFire()) {
				System.out.println("aaa");
				setXY(last2X[1], last2Y[1]);
				out = true;
			}
		}

		return out;
	}

	/**
	 * Updates the positions of the player
	 * 
	 * @param x The x axis position of the player
	 * @param y The y axis position of the player
	 */
	private void updateLast2(int x, int y) {
		if (last2X[0] == -1 && last2Y[0] == -1) {
			last2X[0] = x;
			last2Y[0] = y;
		} else if (last2X[1] == -1 && last2Y[1] == -1) {
			last2X[1] = x;
			last2Y[1] = y;
		} else {
			last2X[1] = last2X[0];
			last2Y[1] = last2Y[0];
			last2X[0] = x;
			last2Y[0] = y;
		}
	}

	/**
	 * Calculates which direction the player can move from the tile they
	 * are currently on
	 * 
	 * @return an array of directions that can be moved in (0 = north, 1 = east, 2 =
	 *         south, 3 = west)
	 */
	private boolean[] getDirections() {
		final boolean[] allFalse = { false, false, false, false };
		boolean[] out = { false, false, false, false };
		boolean[] currentDir = position.getDirections();
		try {
			out[0] = currentDir[0] && board.getTileAt(x, y - 1).getDirections()[2]
					&& !board.getTileAt(x, y - 1).isOnFire() && !board.getTileAt(x, y - 1).isPlayerOn();
		} catch (Exception e) {
		}
		try {
			out[1] = currentDir[1] && board.getTileAt(x + 1, y).getDirections()[3]
					&& !board.getTileAt(x + 1, y).isOnFire() && !board.getTileAt(x + 1, y).isPlayerOn();
		} catch (Exception e) {
		}
		try {
			out[2] = currentDir[2] && board.getTileAt(x, y + 1).getDirections()[0]
					&& !board.getTileAt(x, y + 1).isOnFire() && !board.getTileAt(x, y + 1).isPlayerOn();
		} catch (Exception e) {
		}
		try {
			out[3] = currentDir[3] && board.getTileAt(x - 1, y).getDirections()[1]
					&& !board.getTileAt(x - 1, y).isOnFire() && !board.getTileAt(x - 1, y).isPlayerOn();
		} catch (Exception e) {
		}
		if (Arrays.equals(out, allFalse)) {
			game.finishedMove();
		}
		return out;
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * Board class represents the game board and it's functions.
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @author Navid Reaz.
 * @version 1.2
 */

public class Board {

	private int gridSizeX;
	private int gridSizeY;
	private Floor grid[][];
	private SilkBag startingTiles = new SilkBag();
	private SilkBag gameTiles = new SilkBag();
	protected Hand p1hand;
	protected Hand p2hand;
	protected Hand p3hand;
	protected Hand p4hand;
	protected InEffect effects = new InEffect();
	public int fixedTileNum;
	public int tileNum;

	// Coordinates of each player on the grid
	int p1x;
	int p1y;
	int p2x;
	int p2y;
	int p3x;
	int p3y;
	int p4x;
	int p4y;

	/*
	 * // Old constructor that takes direct input, for testing purposes public
	 * Board(int x, int y) { gridSizeX = x; gridSizeY = y;
	 * 
	 * initGrid();
	 * 
	 * //////////////////////////////////////////////////// // Randomly Fill Board
	 * (For Testing Purposes Only)// for(int i = 0; i < x; i++) { for(int j = 0; j <
	 * y; j++) { startingTiles.placeTileInBag(new Corner(0, false)); } }
	 * ////////////////////////////////////////////////////
	 * 
	 * fillBoard(); }
	 */

	/**
	 * @return Tiles for the game
	 */
	public SilkBag getBag() {
		return gameTiles;
	}

	/**
	 * Constructor that builds a board from a previously saved game.
	 * 
	 * @param level The level file to create a board from
	 * @return built Board.
	 */
	public Board(File level, String type) {
		try {

			Scanner reader = new Scanner(level);
			reader.useDelimiter(",");
			String line = reader.nextLine();

			Scanner scanLine = new Scanner(line);
			scanLine.useDelimiter(",");

			gridSizeX = scanLine.nextInt();
			gridSizeY = scanLine.nextInt();

			initGrid();

			line = reader.nextLine();

			Scanner scanLine2 = new Scanner(line);
			fixedTileNum = scanLine2.nextInt();
			scanLine2.close();

			for (int i = 0; i < fixedTileNum; i++) {

				line = reader.nextLine();
				scanLine = new Scanner(line);
				scanLine.useDelimiter(",");

				int tileX = scanLine.nextInt();
				int tileY = scanLine.nextInt();
				String tileType = scanLine.next();
				int tileRotation = scanLine.nextInt();

				switch (tileType) {
				case ("CORNER"):
					setTileAt(new Corner(tileRotation, true), tileX, tileY);
					break;
				case ("STRAIGHT"):
					setTileAt(new Straight(tileRotation, true), tileX, tileY);
					break;
				case ("TPIECE"):
					setTileAt(new T_tile(tileRotation, true), tileX, tileY);
					break;
				case ("WIN"):
					setTileAt(new WinTile(tileRotation, true), tileX, tileY);
					break;
				}
			}

			line = reader.nextLine();
			Scanner scanLine3 = new Scanner(line);
			tileNum = scanLine3.nextInt();
			scanLine3.close();

			/*
			 * A new addition, since this constructor is loading a level and not generating
			 * a new one, all tiles must be placed exactly as they were instead of being
			 * placed randomly. Thus, this while loop achieves that task for the non-fixed
			 * tiles.
			 */
			while (reader.hasNextInt()) {

				line = reader.nextLine();
				scanLine = new Scanner(line);
				scanLine.useDelimiter(",");

				int tileX = scanLine.nextInt();
				int tileY = scanLine.nextInt();
				String tileType = scanLine.next();
				int tileRotation = scanLine.nextInt();

				switch (tileType) {
				case ("CORNER"):
					setTileAt(new Corner(tileRotation, false), tileX, tileY);
					break;
				case ("STRAIGHT"):
					setTileAt(new Straight(tileRotation, false), tileX, tileY);
					break;
				case ("TPIECE"):
					setTileAt(new T_tile(tileRotation, false), tileX, tileY);
					break;
				case ("WIN"):
					setTileAt(new WinTile(tileRotation, false), tileX, tileY);
					break;
				}
			}

			line = reader.nextLine();
			Scanner scanLine4 = new Scanner(line);
			scanLine4.useDelimiter(",");
			scanLine4.next();
			int bagNum = scanLine4.nextInt();
			scanLine4.close();

			// This for loop puts the tiles that should be in the silk bag into the silk bag
			for (int i = 0; i < bagNum; i++) {
				line = reader.nextLine();
				scanLine = new Scanner(line);
				String tileType = scanLine.next();
				int r = new Random().nextInt(3);

				switch (tileType) {
				case ("CORNER"):
					gameTiles.placeTileInBag(new Corner(r, false));
					break;
				case ("STRAIGHT"):
					gameTiles.placeTileInBag(new Straight(r, false));
					break;
				case ("TPIECE"):
					gameTiles.placeTileInBag(new T_tile(r, false));
					break;
				case ("WIN"):
					gameTiles.placeTileInBag(new WinTile(r, false));
					break;
				case ("FIRE"):
					gameTiles.placeTileInBag(new Fire());
					break;
				case ("ICE"):
					gameTiles.placeTileInBag(new Ice());
					break;
				case ("DOUBLEMOVE"):
					gameTiles.placeTileInBag(new DoubleMove());
					break;
				case ("BACKTRACK"):
					gameTiles.placeTileInBag(new BackTrack());
					break;
				}
			}

			line = reader.nextLine();
			Scanner scanLine5 = new Scanner(line);
			scanLine5.useDelimiter(",");
			p1x = scanLine5.nextInt();
			p1y = scanLine5.nextInt();
			scanLine5.close();

			line = reader.nextLine();
			Scanner scanLine9 = new Scanner(line);
			int handNum1 = scanLine9.nextInt();
			; // Number of items in player 1s hand
			scanLine9.close();

			// Item/s in player 1s hand (These for loops are unfinished and do not do
			// anything)
			for (int i = 0; i < handNum1; i++) {
				String inHand = reader.nextLine();
			}

			line = reader.nextLine();
			Scanner scanLine6 = new Scanner(line);
			scanLine6.useDelimiter(",");
			p2x = scanLine6.nextInt();
			p2y = scanLine6.nextInt();
			scanLine6.close();

			line = reader.nextLine();
			Scanner scanLine10 = new Scanner(line);
			int handNum2 = scanLine10.nextInt();
			; // Number of items in player 2s hand
			scanLine10.close();

			// Item/s in player 2s hand
			for (int i = 0; i < handNum2; i++) {
				String inHand = reader.nextLine();
			}

			line = reader.nextLine();
			Scanner scanLine7 = new Scanner(line);
			scanLine7.useDelimiter(",");
			p3x = scanLine7.nextInt();
			p3y = scanLine7.nextInt();
			scanLine7.close();

			line = reader.nextLine();
			Scanner scanLine11 = new Scanner(line);
			int handNum3 = scanLine11.nextInt();
			; // Number of items in player 3s hand
			scanLine11.close();

			// Item/s in player 3s hand
			for (int i = 0; i < handNum3; i++) {
				String inHand = reader.nextLine();
			}

			line = reader.nextLine();
			Scanner scanLine8 = new Scanner(line);
			scanLine8.useDelimiter(",");
			p4x = scanLine8.nextInt();
			p4y = scanLine8.nextInt();
			scanLine8.close();

			line = reader.nextLine();
			Scanner scanLine12 = new Scanner(line);
			int handNum4 = scanLine12.nextInt();
			; // Number of items in player 4s hand
			scanLine12.close();

			// Item/s in player 4s hand
			for (int i = 0; i < handNum4; i++) {
				String inHand = reader.nextLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("This file does not exist.");
		}
	}

	/**
	 * Constructor that builds a new board from a file.
	 * 
	 * @param level The level file to create a board from
	 * @return built Board.
	 */
	public Board(File level) {
		try {
			// A scanner that reads the whole file
			Scanner reader = new Scanner(level);
			reader.useDelimiter(",");
			String line = reader.nextLine();

			// A scanner that reads each individual line of the file
			Scanner scanLine = new Scanner(line);
			scanLine.useDelimiter(",");

			// Assigning the first two numbers to their respective gridSize variables
			gridSizeX = scanLine.nextInt();
			gridSizeY = scanLine.nextInt();

			// Initialising the grid with the newly updated gridSize values
			initGrid();

			// Moving to the next line
			line = reader.nextLine();

			// Reading the next line
			// This next line contains the number of subsequent relevant lines to be read
			Scanner scanLine2 = new Scanner(line);
			fixedTileNum = scanLine2.nextInt();
			scanLine2.close();

			// Looping through all lines containing fixed tiles and placing them
			// on the board.
			for (int i = 0; i < fixedTileNum; i++) {

				line = reader.nextLine();
				scanLine = new Scanner(line);
				scanLine.useDelimiter(",");

				int tileX = scanLine.nextInt();
				int tileY = scanLine.nextInt();
				String tileType = scanLine.next();
				int tileRotation = scanLine.nextInt();

				switch (tileType) {
				case ("CORNER"):
					setTileAt(new Corner(tileRotation, true), tileX, tileY);
					break;
				case ("STRAIGHT"):
					setTileAt(new Straight(tileRotation, true), tileX, tileY);
					break;
				case ("TPIECE"):
					setTileAt(new T_tile(tileRotation, true), tileX, tileY);
					break;
				case ("WIN"):
					setTileAt(new WinTile(tileRotation, true), tileX, tileY);
					break;
				}
			}

			line = reader.nextLine();
			Scanner scanLine3 = new Scanner(line);
			tileNum = scanLine3.nextInt();
			scanLine3.close();

			for (int i = 0; i < tileNum; i++) {
				line = reader.nextLine();
				scanLine = new Scanner(line);
				String tileType = scanLine.next();
				int r = new Random().nextInt(3);

				switch (tileType) {
				case ("CORNER"):
					startingTiles.placeTileInBag(new Corner(r, false));
					break;
				case ("STRAIGHT"):
					startingTiles.placeTileInBag(new Straight(r, false));
					break;
				case ("TPIECE"):
					startingTiles.placeTileInBag(new T_tile(r, false));
					break;
				case ("WIN"):
					startingTiles.placeTileInBag(new WinTile(r, false));
					break;
				case ("FIRE"):
					gameTiles.placeTileInBag(new Fire());
					break;
				case ("ICE"):
					gameTiles.placeTileInBag(new Ice());
					break;
				case ("DOUBLEMOVE"):
					gameTiles.placeTileInBag(new DoubleMove());
					break;
				case ("BACKTRACK"):
					gameTiles.placeTileInBag(new BackTrack());
					break;
				}
			}

			line = reader.nextLine();
			Scanner scanLine4 = new Scanner(line);
			scanLine4.useDelimiter(",");
			p1x = scanLine4.nextInt();
			p1y = scanLine4.nextInt();
			scanLine4.close();

			line = reader.nextLine();
			Scanner scanLine5 = new Scanner(line);
			scanLine5.useDelimiter(",");
			p2x = scanLine5.nextInt();
			p2y = scanLine5.nextInt();
			scanLine5.close();

			line = reader.nextLine();
			Scanner scanLine6 = new Scanner(line);
			scanLine6.useDelimiter(",");
			p3x = scanLine6.nextInt();
			p3y = scanLine6.nextInt();
			scanLine6.close();

			line = reader.nextLine();
			Scanner scanLine7 = new Scanner(line);
			scanLine7.useDelimiter(",");
			p4x = scanLine7.nextInt();
			p4y = scanLine7.nextInt();
			scanLine7.close();

			fillBoard();
			scanLine.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("This file does not exist.");
		}
	}

	/**
	 * Get the x axis grid size of the board
	 * 
	 * @return gridSizeX x axis size of grid
	 */
	public int getGridSizeX() {
		return gridSizeX;
	}

	/**
	 * Get the y axis grid size of the board
	 * 
	 * @return gridSizeY y axis size of grid
	 */
	public int getGridSizeY() {
		return gridSizeY;
	}

	/**
	 * Get the x coordinate of the player on the board
	 * 
	 * @param p The player number
	 * @return the x coordinate of the player stated
	 */
	public int getPlayerX(int p) {
		if (p == 1) {
			return p1x;
		} else if (p == 2) {
			return p2x;
		} else if (p == 3) {
			return p3x;
		} else if (p == 4) {
			return p4x;
		}
		return 0;
	}

	/**
	 * Get the y coordinate of the player on the board
	 * 
	 * @param p The player number
	 * @return the y coordinate of the player stated
	 */
	public int getPlayerY(int p) {
		if (p == 1) {
			return p1y;
		} else if (p == 2) {
			return p2y;
		} else if (p == 3) {
			return p3y;
		} else if (p == 4) {
			return p4y;
		}
		return 0;
	}

	/**
	 * Populates the grid
	 */
	private void initGrid() {

		grid = new Floor[gridSizeX][gridSizeY];

		// write code that populates the grid
	}

	/**
	 * Adds tile on specific position on the board.
	 * 
	 * @param tile      Tile to be added
	 * @param direction Direction of the tile
	 * @param position  Position on the board
	 */

	public Floor addTile(Floor tile, int direction, int position) {
		// (0/1/2/3) = inserted from (north/south/east/west)
		// a[0][0] a[0][1] a[0][2] a[0][3]

		// a[1][0] a[1][1] a[1][2] a[1][3]

		// a[2][0] a[2][1] a[2][2] a[3][3]

		Floor temp = null;
		switch (direction) {
		case (0):
			temp = grid[position][gridSizeY - 1];// stores
			for (int i = gridSizeY - 1; i > 0; i--) {
				grid[position][i] = grid[position][i - 1];
			}
			grid[position][0] = tile;
			break;
		case (1):
			temp = grid[0][position];
			for (int i = 0; i < gridSizeX - 1; i++) {
				grid[i][position] = grid[i + 1][position];
			}
			grid[gridSizeX - 1][position] = tile;
			break;
		case 2:
			temp = grid[position][0];
			for (int i = 0; gridSizeY - 1 > i; i++) {
				grid[position][i] = grid[position][i + 1];
			}
			grid[position][gridSizeY - 1] = tile;
			break;
		case 3:
			temp = grid[gridSizeX - 1][position];
			for (int i = gridSizeX - 1; i > 0; i--) {
				grid[i][position] = grid[i - 1][position];
			}
			grid[0][position] = tile;
			break;
		}
		return temp;
	}

	/**
	 * @return tile at position (x, y) on the grid
	 */
	public Floor getTileAt(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Places tile in position on the grid
	 * 
	 * @param tile Tile to be placed
	 * @param x    X axis coordinate
	 * @param y    Y axis coordinate
	 */
	public void setTileAt(Floor tile, int x, int y) {
		grid[x][y] = tile;
	}

	/**
	 * Fills the board
	 */
	public void fillBoard() {
		for (int i = 0; i < gridSizeX; i++) {
			for (int j = 0; j < gridSizeY; j++) {
				if (this.grid[i][j] == null) {
					this.grid[i][j] = (Floor) startingTiles.drawTile();
				}
			}
		}
		while (startingTiles.isEmpty() == false) {
			gameTiles.placeTileInBag(startingTiles.drawTile());
		}

	}
}

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.SnapshotParameters;

/**
 * This class compiles all of the others parts of the program and creats a new
 * game
 * 
 * @author Mae Hedmann-Highmore
 * @author Sam Tudberry
 * @version 1.0
 */
public class Game extends BorderPane {

	private static final double TILE_WIDTH = 80;
	private static final double TILE_HEIGHT = 80;

	private Board board;
	private Canvas canvas;
	private GraphicsContext gc;

	/**
	 * Constructor for Game class
	 * 
	 * @param board the board that the game is being played on
	 */
	public Game(Board board) {
		this.board = board;
		initGame();
		buildGUI();
		drawBoard();
	}

	/**
	 * Initializes the game, sets the size of the canvas
	 */
	private void initGame() {
		canvas = new Canvas(board.getGridSizeX() * TILE_WIDTH + 80, board.getGridSizeY() * TILE_HEIGHT + 80);
		gc = canvas.getGraphicsContext2D();
		this.setCenter(canvas);
	}

	/**
	 * Builds the GUI for the game sets the locations for the buttons on the screen
	 */
	private void buildGUI() {

		// Create GUI pane look nice
		VBox buttonBox = new VBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setMinWidth(300);
		buttonBox.setPadding(new Insets(0, 40, 0, 0));
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		// Create pause button
		Button pauseButton = new CustomButton("Pause", false);
		pauseButton.setOnAction(e -> {
			Main.showPauseMenu();
		});

		// Add button to GUI
		buttonBox.getChildren().add(pauseButton);

		// Add GUI to pane
		this.setRight(buttonBox);
	}

	/**
	 * Draws the board onto the screen sets the size of the grid and populates
	 */
	private void drawBoard() {
		for (int i = 0; i < board.getGridSizeX(); i++) {
			for (int j = 0; j < board.getGridSizeY(); j++) {
				double x = (i * TILE_WIDTH) + 40;
				double y = (j * TILE_HEIGHT) + 40;
				drawTile(board.getTileAt(i, j), (int) x, (int) y);
				gc.setStroke(Color.WHITE);
				gc.strokeRect(x, y, TILE_WIDTH, TILE_HEIGHT);
			}
		}
	}

	/**
	 * Draws a tile to the screen
	 * 
	 * @param tile the floor tile thats going to be drawn onto the screen
	 * @param x    the x axis location on the board
	 * @param y    the y axis location on the board
	 */
	private void drawTile(Floor tile, int x, int y) {
		Image imgToDraw = new TileImage(tile, tile.canMove());
		ImageView iv = new ImageView(imgToDraw);
		iv.setRotate(tile.getRotation() * 90);

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage = iv.snapshot(params, null);

		gc.drawImage(rotatedImage, x, y);

	}

}

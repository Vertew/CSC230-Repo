import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Game2 class displays all game elements and partially handles the turns of a game.
 * @author Mae Hedmann-Highmore 991360
 * @version 1.0
 */

public class Game2 extends BorderPane{

	private static final double TILE_WIDTH = 80;
	private static final double TILE_HEIGHT = 80;
	private SilkBag bag;
	private Board board;
	private Canvas canvas;
	private GraphicsContext gc;
	private Tile justDrawn = null;

	// Each player's hand
	private Hand p1Hand;
	private Hand p2Hand;
	private Hand p3Hand;
	private Hand p4Hand;

	private int currentFRotation = 0;

	private Hand currentHand;
	private int noOfPlayers = 4;
	private int currentPlayer;

	// Each player's position
	private PlayerPiece p1;
	private PlayerPiece p2;
	private PlayerPiece p3;
	private PlayerPiece p4;

	private TurnStructure currentTurn;
	private Floor currentFloor;
	private Tile currentAction;
	private InEffect effects = new InEffect();

	/**
	 * Takes a board class to construct a game around, initialises variables and
	 * draws the UI
	 * 
	 * @param board The board class that the game is made from
	 */
	public Game2(Board board) {
		setBoard(board);
		initHands();
		initGame();
		buildGUI();
		startGame();
		drawEverything();
	}

	/**
	 * Draws the 3 elements that are commonly redrawn (the board, hand and label)
	 */
	public void drawEverything() {
		drawBoard();
		drawHand();
		drawLabel();
	}

	/**
	 * Takes the board parameter and stores it to be referenced later, it also
	 * creates player pieces
	 * 
	 * @param board The board with which to save
	 */
	private void setBoard(Board board) {
		this.board = board;
		p1 = new PlayerPiece(board.getPlayerX(1),board.getPlayerY(1), board,1, this);
		p1.setProfile(new Profile("DefaultP1"));
		Main.loadProfileSelection(p1, 1);
		p2 = new PlayerPiece(board.getPlayerX(2),board.getPlayerY(2), board,2, this);
		p2.setProfile(new Profile("DefaultP2"));
		Main.loadProfileSelection(p2, 2);
		if (noOfPlayers== 3){
			p3 = new PlayerPiece(board.getPlayerX(3),board.getPlayerY(3),board,3, this);
			p3.setProfile(new Profile("DefaultP3"));
			Main.loadProfileSelection(p3, 3);
		}else if (noOfPlayers == 4){
			p3 = new PlayerPiece(board.getPlayerX(3),board.getPlayerY(3),board,3, this);
			p3.setProfile(new Profile("DefaultP3"));
			Main.loadProfileSelection(p3, 3);
			p4 = new PlayerPiece(board.getPlayerX(4),board.getPlayerY(4),board,4, this);
			p4.setProfile(new Profile("DefaultP4"));
			Main.loadProfileSelection(p4, 4);
		}
	}

	/**
	 * Creates a hand object for all players
	 */
	private void initHands() {
		this.bag = board.getBag();
		p1Hand = new Hand(bag);
		p2Hand = new Hand(bag);
		p3Hand = new Hand(bag);
		p4Hand = new Hand(bag);

		storeCurrentHand();
	}

	/**
	 * Checks whose turn it is and sets the current hand to that player's hand
	 */
	private void storeCurrentHand() {
		if (currentPlayer == 1) {
			currentHand = p1Hand;
		} else if (currentPlayer == 2) {
			currentHand = p2Hand;
		} else if (currentPlayer == 3) {
			currentHand = p3Hand;
		} else if (currentPlayer == 4) {
			currentHand = p4Hand;
		}
	}

	/**
	 * To be called when a player completes the move segment of the turn to make the
	 * turn structure go to the next part of the turn
	 */
	public void finishedMove() {
		drawEverything();
		currentTurn.moveDone();
	}

	/**
	 * Shows the winning screen when a player is in the winning position
	 * 
	 * @param winnerPnum Winner's player number
	 */
	public void winGame(int winnerPnum) {
		if (winnerPnum == 1) {
			p1.setVictor(true);
			Main.showVictoryScreen(p1.getProfile().getName() + " wins!");
			p1.getProfile().incrementWins();
			if(noOfPlayers == 2) {
				p2.getProfile().incrementLosses();
			}
			if(noOfPlayers == 3) {
				p3.getProfile().incrementLosses();
			}
			if(noOfPlayers == 4) {
				p4.getProfile().incrementLosses();
			}
		} else if (winnerPnum == 2) {
			p2.setVictor(true);
			Main.showVictoryScreen(p2.getProfile().getName() + " wins!");
			p2.getProfile().incrementWins();
			if(noOfPlayers == 2) {
				p1.getProfile().incrementLosses();
			}
			if(noOfPlayers == 3) {
				p3.getProfile().incrementLosses();
			}
			if(noOfPlayers == 4) {
				p4.getProfile().incrementLosses();
			}
		} else if (winnerPnum == 3) {
			p3.setVictor(true);
			Main.showVictoryScreen(p3.getProfile().getName() + " wins!");
			p3.getProfile().incrementWins();
			p1.getProfile().incrementLosses();
			p2.getProfile().incrementLosses();
			if(noOfPlayers == 4) {
				p4.getProfile().incrementLosses();
			}
		} else if (winnerPnum == 4) {
			p4.setVictor(true);
			Main.showVictoryScreen(p4.getProfile().getName() + " wins!");
			p1.getProfile().incrementWins();
			p1.getProfile().incrementLosses();
			p1.getProfile().incrementLosses();
			p1.getProfile().incrementLosses();
		}
		
		p1.getProfile().incrementGamesPlayed();
		p2.getProfile().incrementGamesPlayed();
		p3.getProfile().incrementGamesPlayed();
		p4.getProfile().incrementGamesPlayed();
		
		p1.getProfile().saveProfile();
		p2.getProfile().saveProfile();
		p3.getProfile().saveProfile();
		p4.getProfile().saveProfile();
	}

	/**
	 * Sets and shows the canvas and graphics context
	 */
	private void initGame() {
		canvas = new Canvas(board.getGridSizeX() * TILE_WIDTH + 80, board.getGridSizeY() * TILE_HEIGHT + 80);
		gc = canvas.getGraphicsContext2D();
		this.setCenter(canvas);
	}

	/**
	 * Makes it player1's turn and starts the first turn
	 */
	private void startGame() {
		currentPlayer = 1;
		startTurn();
	}

	/**
	 * Initialise a turn structure and starts it
	 */
	private void startTurn() {
		storeCurrentHand();
		currentTurn = new TurnStructure(currentPlayer, effects, currentHand, this);
		justDrawn = currentTurn.startTurn();
		drawEverything();
	}

	/**
	 * Ends the current turn
	 */
	public void turnOver() {
		currentPlayer++;
		if (currentPlayer > noOfPlayers) {
			currentPlayer = 1;
		}
		startTurn();
	}

	/**
	 * Takes a floor tile and stores it
	 * 
	 * @param tile the floor tile to be stored
	 */
	public void setCurrentFloor(Floor tile) {
		this.currentFloor = tile;
		currentFloor.setRotation(0);
		currentFRotation = 0;
	}

	/**
	 * Draws all the buttons for the GUI
	 */
	private void buildGUI() {
		// Create GUI pane look nice
		VBox buttonBox = new VBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setMinWidth(300);
		buttonBox.setPadding(new Insets(0, 40, 0, 0));
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		buttonBox.setSpacing(10);

		// Create pause button
		Button pauseButton = new CustomButton("Pause", false);
		pauseButton.setOnAction(e -> {
			Main.showPauseMenu();
		});

		// Create save game button
		Button saveButton = new CustomButton("Save Game", false);
		saveButton.setOnAction(e -> {
			Main.saveGame(board, p1, p2, p3, p4, p1Hand, p2Hand, p3Hand, p4Hand, effects);
		});

		// Create move left button
		Button leftButton = new CustomButton("Left", false);
		leftButton.setOnAction(e -> {
			if (currentTurn.getTurnTime() == 3) {
				if (currentPlayer == 1) {
					p1.movement("left");
				} else if (currentPlayer == 2) {
					p2.movement("left");
				} else if (currentPlayer == 3) {
					p3.movement("left");
				} else if (currentPlayer == 4) {
					p4.movement("left");
				}
			}
		});
		// Create move right button
		Button rightButton = new CustomButton("Right", false);
		rightButton.setOnAction(e -> {
			if (currentTurn.getTurnTime() == 3) {
				if (currentPlayer == 1) {
					p1.movement("right");
				} else if (currentPlayer == 2) {
					p2.movement("right");
				} else if (currentPlayer == 3) {
					p3.movement("right");
				} else if (currentPlayer == 4) {
					p4.movement("right");
				}
			}
		});
		// Create move up button
		Button upButton = new CustomButton("Up", false);
		upButton.setOnAction(e -> {
			if (currentTurn.getTurnTime() == 3) {
				if (currentPlayer == 1) {
					p1.movement("up");
				} else if (currentPlayer == 2) {
					p2.movement("up");
				} else if (currentPlayer == 3) {
					p3.movement("up");
				} else if (currentPlayer == 4) {
					p4.movement("up");
				}
			}
		});
		// Create move down button
		Button downButton = new CustomButton("Down", false);
		downButton.setOnAction(e -> {
			if (currentTurn.getTurnTime() == 3) {
				if (currentPlayer == 1) {
					p1.movement("down");
				} else if (currentPlayer == 2) {
					p2.movement("down");
				} else if (currentPlayer == 3) {
					p3.movement("down");
				} else if (currentPlayer == 4) {
					p4.movement("down");
				}
			}
		});

		// Add button to GUI
		buttonBox.getChildren().addAll(leftButton, rightButton, upButton, downButton, pauseButton, saveButton);

		// Add GUI to pane
		this.setRight(buttonBox);
	}

	/**
	 * Draws the help labels
	 */
	private void drawLabel() {
		GridPane text = new GridPane();
		drawCurrentPLabel(text);
		drawHelpLabel(text);
		this.setBottom(text);
	}

	/**
	 * Adds the help labels to grid pane
	 * 
	 * @param grid The grid pane to add the label to
	 */
	private void drawHelpLabel(GridPane grid) {
		Label label = new Label("");
		switch (currentTurn.getTurnTime()) {
		case 0:
			label = new Label("Setting up...");
			break;
		case 1:
			label = new Label("You drew a floor piece. You must place it");
			break;
		case 2:
			label = new Label("You may now play an action piece or skip");
			break;
		case 3:
			label = new Label("You must now move (click a move button to continue if there are no moves)");
			break;
		}

		if (currentPlayer == 1) {
			label.setTextFill(Color.web("#ff0000"));
		} else if (currentPlayer == 2) {
			label.setTextFill(Color.web("#0000ff"));
		} else if (currentPlayer == 3) {
			label.setTextFill(Color.web("#ffff00"));
		} else if (currentPlayer == 4) {
			label.setTextFill(Color.web("#00ff00"));
		}

		grid.add(label, 0, 1);
	}

	/**
	 * Adds the current player's turn label to grid pane
	 * 
	 * @param grid The grid pane to add the label to
	 */
	private void drawCurrentPLabel(GridPane grid) {
		Label showPlayer = new Label("");

		if (currentPlayer == 1) {
			showPlayer = new Label(p1.getProfile().getName() + "'s turn: ");
			showPlayer.setTextFill(Color.web("#ff0000"));
		} else if (currentPlayer == 2) {
			showPlayer = new Label(p2.getProfile().getName() + "'s turn: ");
			showPlayer.setTextFill(Color.web("#0000ff"));
		} else if (currentPlayer == 3) {
			showPlayer = new Label(p3.getProfile().getName() + "'s turn: ");
			showPlayer.setTextFill(Color.web("#ffff00"));
		} else if (currentPlayer == 4) {
			showPlayer = new Label(p4.getProfile().getName() + "'s turn: ");
			showPlayer.setTextFill(Color.web("#00ff00"));
		}
		showPlayer.setFont(new Font(20));

		grid.add(showPlayer, 0, 0);
	}

	/**
	 * Construct a grid of buttons and the arrows for placing buttons
	 */
	private void drawBoard() {
		boolean[] cantMoveX = new boolean[board.getGridSizeX()];
		boolean[] cantMoveY = new boolean[board.getGridSizeY()];
		int boardX = board.getGridSizeX() + 2;
		int boardY = board.getGridSizeY() + 2;
		GridPane grid = new GridPane();
		Floor temp;
		for (int x = 1; x < boardX - 1; x++) {
			for (int y = 1; y < boardY - 1; y++) {
				temp = board.getTileAt(x - 1, y - 1);
				if (!temp.canMove()) {
					cantMoveY[y - 1] = true;
					cantMoveX[x - 1] = true;
				}
				drawTile(grid, temp, x, y);
			}
		}
		for (int x = 1; x < boardX - 1; x++) {
			if (!cantMoveX[x - 1]) {
				drawArrow(grid, 0, x, 0);
				drawArrow(grid, 2, x, boardY - 1);
			}
		}
		for (int y = 1; y < boardY - 1; y++) {
			if (!cantMoveY[y - 1]) {
				drawArrow(grid, 1, boardX - 1, y);
				drawArrow(grid, 3, 0, y);
			}

		}
		drawPlayers(grid);

		this.setLeft(grid);
	}

	/**
	 * Draws players on the board
	 * 
	 * @param grid Game board grid
	 */
	private void drawPlayers(GridPane grid) {
		p1.drawPlayer(grid);
		p2.drawPlayer(grid);
		if (noOfPlayers == 3) {
			p3.drawPlayer(grid);
		} else if (noOfPlayers == 4) {
			p3.drawPlayer(grid);
			p4.drawPlayer(grid);
		}
	}

	/**
	 * Draw the current hand to the screen
	 */
	private void drawHand() {
		GridPane hand = new GridPane();
		Insets padding = new Insets(20, 0, 0, 20);
		currentHand = p1Hand;
		if (currentPlayer == 1) {
			currentHand = p1Hand;
		} else if (currentPlayer == 2) {
			currentHand = p2Hand;
		} else if (currentPlayer == 3) {
			currentHand = p3Hand;
		} else if (currentPlayer == 4) {
			currentHand = p4Hand;
		}

		for (int i = 0; i < currentHand.getHolding().size(); i++) {
			drawHandTile(hand, i, currentHand.getHolding().get(i));
			if (currentHand.getHolding().get(i) == justDrawn && !(justDrawn instanceof Floor)) {
				makeGrey(hand, i);
			}
		}
		drawRotateArrowL(currentHand.getHolding().size(), hand);
		drawRotateArrowR(currentHand.getHolding().size() + 1, hand);
		drawSkipActionButton(currentHand.getHolding().size() + 2, hand);
		hand.setVgap(10);
		hand.setPadding(padding);
		this.setCenter(hand);
	}

	/**
	 * Greys out action tiles when they are drawn and cannot be used
	 * 
	 * @param hand  Current hand
	 * @param index Index value
	 */
	private void makeGrey(GridPane hand, int index) {
		final String GREY = "images/halfGrey.png";
		Image imgToDraw = new Image(GREY);
		ImageView imgView = new ImageView(imgToDraw);
		hand.add(imgView, 0, index);
	}

	/**
	 * This draws a skip action button at the end of the hand
	 * 
	 * @param x    The index at which it should be drawn in the hand
	 * @param grid The gridpane object it is to be added to
	 */
	private void drawSkipActionButton(int x, GridPane grid) {
		final String ARROW = "images/skipAction.png";
		Button button = new Button();

		Image imgToDraw = new Image(ARROW);
		ImageView imgView = new ImageView(imgToDraw);

		button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");

		button.setOnAction(e -> {
			if (currentTurn.getTurnTime() == 2) {
				currentTurn.actionDone();
			}
			drawEverything();
		});

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);

		button.setGraphic(imgView);
		grid.add(button, 0, x);
	}

	/**
	 * Draw an arrow to rotate the current tile left
	 * 
	 * @param index The index at which to draw the arrow
	 * @param grid  The grid in which to draw the arrow
	 */
	private void drawRotateArrowL(int index, GridPane grid) {

		final String ARROW = "images/rotateL.png";
		Button button = new Button();

		Image imgToDraw = new Image(ARROW);
		ImageView imgView = new ImageView(imgToDraw);

		button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");

		button.setOnAction(e -> {

			if (currentFloor != null) {

				if (currentFRotation == 3) {
					currentFRotation = 0;
				} else {
					currentFRotation++;
				}
				currentFloor.newRotation(currentFRotation);
				drawHand();
			}
			/*
			 * System.out.println(currentFloor.getRotation());
			 * System.out.println(currentFloor.getDirections()[0]);
			 * System.out.println(currentFloor.getDirections()[1]);
			 * System.out.println(currentFloor.getDirections()[2]);
			 * System.out.println(currentFloor.getDirections()[3]);
			 */
		});

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);

		button.setGraphic(imgView);
		grid.add(button, 0, index);
	}

	/**
	 * Draw an arrow to rotate the current tile right
	 * 
	 * @param index The index at which to draw the arrow
	 * @param grid  The grid in which to draw the arrow
	 */
	private void drawRotateArrowR(int index, GridPane grid) {

		final String ARROW = "images/rotateR.png";
		Button button = new Button();

		Image imgToDraw = new Image(ARROW);
		ImageView imgView = new ImageView(imgToDraw);

		button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");

		button.setOnAction(e -> {
			if (currentFloor != null) {
				if (currentFRotation == 0) {
					currentFRotation = 3;
				} else {
					currentFRotation--;
				}
				currentFloor.newRotation(currentFRotation);
				drawHand();
			}

			/*
			 * System.out.println(currentFloor.getRotation());
			 * System.out.println(currentFloor.getDirections()[0]);
			 * System.out.println(currentFloor.getDirections()[1]);
			 * System.out.println(currentFloor.getDirections()[2]);
			 * System.out.println(currentFloor.getDirections()[3]);
			 */

		});
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		button.setGraphic(imgView);
		grid.add(button, 0, index);
	}

	/**
	 * Draws a hand tile button to the screen
	 * 
	 * @param hand  The gridPane in which to add the button
	 * @param index The index at which to draw the tile in a gridPane
	 * @param tile  The tile that is being held
	 */
	private void drawHandTile(GridPane hand, int index, Tile tile) {
		final Tile TILE = tile;
		Button button = new Button();
		Image imgToDraw = new TileImage(tile, true);
		ImageView imgView = new ImageView(imgToDraw);
		// ut.println(tile.getRotation());
		imgView.setRotate(tile.getRotation() * 90);

		if (currentPlayer == 1) {
			button.setStyle(
					"-fx-border-color: linear-gradient(to bottom, #FF0000, #4d0000);-fx-background-color: transparent;");
		} else if (currentPlayer == 2) {
			button.setStyle(
					"-fx-border-color: linear-gradient(to bottom, #3333ff, #ff66cc);-fx-background-color: transparent;");
		} else if (currentPlayer == 3) {
			button.setStyle(
					"-fx-border-color: linear-gradient(to bottom, #ffff00, #ffffcc);-fx-background-color: transparent;");
		} else if (currentPlayer == 4) {
			button.setStyle(
					"-fx-border-color: linear-gradient(to bottom, #00ff00, #ccff99);-fx-background-color: transparent;");
		}

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);

		button.setOnAction(e -> {
			if (!(tile instanceof Floor)) {
				currentAction = TILE;
			}
		});

		ColumnConstraints col = new ColumnConstraints();
		col.setHalignment(HPos.CENTER);
		hand.getColumnConstraints().add(col);

		RowConstraints row = new RowConstraints();
		row.setValignment(VPos.CENTER);
		hand.getRowConstraints().add(row);

		button.setGraphic(imgView);
		hand.add(button, 0, index);
	}

	boolean btFinish = true;

	/**
	 * Draws the tile button at the location in the grid
	 * 
	 * @param grid The grid pane in which to draw the tile
	 * @param tile The tile to be drawn
	 * @param x    The x coordinate at which to draw the tile
	 * @param y    The y coordinate at which to draw the tile
	 */
	private void drawTile(GridPane grid, Floor tile, int x, int y) {

		final String ICE = "images/frozenOverlay.png";
		final String FIRE = "images/fire.png";
		final Floor REPRESENTS = tile;
		ArrayList<Floor> effecting = new ArrayList<Floor>();
		Button button = new Button();
		Image imgToDraw = new TileImage(tile, tile.canMove());

		ImageView imgView = new ImageView(imgToDraw);

		ColumnConstraints col = new ColumnConstraints();
		col.setHalignment(HPos.CENTER);
		grid.getColumnConstraints().add(col);

		RowConstraints row = new RowConstraints();
		row.setValignment(VPos.CENTER);
		grid.getRowConstraints().add(row);

		imgView.setRotate(tile.getRotation() * 90);

		button.setStyle("-fx-border-color: b3b3b3;-fx-background-color: transparent;");
		button.setMaxWidth(80);
		button.setMaxHeight(80);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);

		button.setOnAction(e -> {
			/*
			 * System.out.println("0: " + tile.getDirections()[0]); System.out.println("1: "
			 * + tile.getDirections()[1]); System.out.println("2: " +
			 * tile.getDirections()[2]); System.out.println("3: " +
			 * tile.getDirections()[3]); System.out.println("on fire?: "+tile.isOnFire());
			 * System.out.println("player on?: "+tile.isPlayerOn());
			 * System.out.println("Rotation: "+tile.getRotation());
			 */
			if (currentTurn.getTurnTime() == 2) {
				if (currentAction != null) {
					if (currentAction != justDrawn) {
						if (currentAction instanceof Lasting) {// if it is a lasting tile
							if (currentAction instanceof Ice) {// if it is an ice tile
								for (int i = -2; i < 1; i++) {
									for (int j = -2; j < 1; j++) {
										try {
											effecting.add(board.getTileAt(x + i, y + j));
										} catch (Exception a) {

										}
										effects.addEffect((Lasting) currentAction, effecting, 1 * noOfPlayers);
									}
								}
							} else {// it is a fire tile
								effecting.add(board.getTileAt(x - 1, y - 1));
								effects.addEffect((Lasting) currentAction, effecting, 2 * noOfPlayers);
							}
						} else if (currentAction instanceof DoubleMove) {
							currentTurn.doubleMove();
						} else if (currentAction instanceof BackTrack) {
							btFinish = false;
							if (tile == p1.getTileOn() && currentPlayer != 1) {
								btFinish = btFinish || p1.backTrack();
							}
							if (tile == p2.getTileOn() && currentPlayer != 2) {
								btFinish = btFinish || p2.backTrack();
							}
							if (noOfPlayers == 3 || noOfPlayers == 4) {
								if (tile == p3.getTileOn() && currentPlayer != 3) {
									btFinish = btFinish || p3.backTrack();
								}
							}
							if (noOfPlayers == 4) {
								if (tile == p4.getTileOn() && currentPlayer != 4) {
									btFinish = btFinish || p4.backTrack();
								}
							}
						}
						if (btFinish) {
							// System.out.println("actionDone");

							currentHand.remove(currentAction);
							currentAction = null;
							currentTurn.actionDone();
							drawEverything();
						}
						btFinish = true;
					}
				}
				drawEverything();
			}
		});

		button.setGraphic(imgView);
		grid.add(button, x, y);
		if (tile.isFrozen()) {
			ImageView imgViewICE = new ImageView(ICE);
			grid.add(imgViewICE, x, y);
		}
		if (tile.isOnFire()) {
			ImageView imgViewFIRE = new ImageView(FIRE);
			grid.add(imgViewFIRE, x, y);
		}
	}

	/**
	 * Draws an arrow button on a grid at a location
	 * 
	 * @param grid     The grid at which to draw the arrow button
	 * @param rotation The rotation at which the arrow button will be
	 * @param x        The x co ord of the arrow button
	 * @param y        The y co ord of the arrow button
	 */
	private void drawArrow(GridPane grid, int rotation, int x, int y) {
		final int intRotate = rotation;
		final int intX = x;
		final int intY = y;

		final String ARROW = "images/simple_arrow.png";
		final String ARROWP2 = "images/simple arrow2.png";
		final String ARROWP3 = "images/simple arrow3.png";
		final String ARROWP4 = "images/simple arrow4.png";
		Image imgToDraw = new Image(ARROW);
		Button button = new Button();
		button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");
		if (currentPlayer == 1) {
			imgToDraw = new Image(ARROW);
		} else if (currentPlayer == 2) {
			imgToDraw = new Image(ARROWP2);
		} else if (currentPlayer == 3) {
			imgToDraw = new Image(ARROWP3);
		} else if (currentPlayer == 4) {
			imgToDraw = new Image(ARROWP4);
		}

		ImageView imgView = new ImageView(imgToDraw);
		imgView.setRotate(rotation * 90);
		button.setOnAction(e -> {
			if (currentTurn.getTurnTime() == 1) {
				if (currentFloor != null) {
					if (intRotate == 0 || intRotate == 2) {
						bag.placeTileInBag(this.board.addTile(currentFloor, intRotate, intX - 1));
					} else {
						bag.placeTileInBag(this.board.addTile(currentFloor, intRotate, intY - 1));
					}
					currentHand.remove(currentFloor);
					currentFloor = null;
					currentTurn.placeDone();

					p1.checkMoved(intRotate);
					p2.checkMoved(intRotate);
					if (noOfPlayers == 3) {
						p3.checkMoved(intRotate);
					} else if (noOfPlayers == 4) {
						p3.checkMoved(intRotate);
						p4.checkMoved(intRotate);
					}

					drawHand();
					drawBoard();
				}
			}
		});

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);

		button.setGraphic(imgView);
		grid.add(button, x, y);
	}

}

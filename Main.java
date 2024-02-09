import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * This class is the Main class for Assignment 2.
 * 
 * @author Todd Dyer
 * @author Sam Tudberry
 * @version 1.0
 */
public class Main extends Application {

	public static final double WINDOW_WIDTH = 1000;
	public static final double WINDOW_HEIGHT = 800;

	private static Stage mainStage; // Main window
	private static Stage pauseStage = new Stage(); // Pause window
	private static Stage profileSelectStage = new Stage(); // Profile select window
	private static Stage victoryStage = new Stage(); // Victory window
	private static Scene currentGameScene;

	/**
	 * Creates new game
	 * 
	 * @param board New game board
	 */
	public static void playGame(Board board) {

		currentGameScene = new Scene(new Game2(board));

		mainStage.setScene(currentGameScene);
	}

	/**
	 * Loads level select from a file
	 * 
	 * @param titleText Title
	 * @param files     List of files
	 * @param fileType  Type of file
	 */
	public static void loadLevelSelect(String titleText, ArrayList<File> files, String fileType) {
		mainStage.setScene(new Scene(new LevelSelect(titleText, files, fileType)));
	}

	/**
	 * Loads profile selection
	 * 
	 * @param currentPlayer Current player piece
	 * @param playerNum     Current player's number
	 */
	public static void loadProfileSelection(PlayerPiece currentPlayer, int playerNum) {
		mainStage.hide();
		profileSelectStage.setScene(new Scene(new ProfileSelectionMenu(currentPlayer, playerNum)));
		profileSelectStage.show();
	}

	/**
	 * Closes profile selection menu
	 */
	public static void closeProfileSelectMenu() {
		profileSelectStage.hide();
		mainStage.show();
	}

	/**
	 * Allows to load a new or saved game
	 */
	public static void loadGameOptions() {
		mainStage.setScene(new Scene(new LoadOrNew()));
	}

	/**
	 * Chooses the game to load
	 * 
	 * @param gameToLoad
	 */
	public void loadGame(Game gameToLoad) {
		currentGameScene = new Scene(gameToLoad);
	}

	/**
	 * Shows start menu
	 */
	public static void showStartMenu() {
		mainStage.setScene(new Scene(new StartMenu()));
	}

	/**
	 * Shows victory screen
	 * 
	 * @param text Victory text on screen
	 */
	public static void showVictoryScreen(String text) {
		victoryStage.setScene(new Scene(new VictoryMenu(text)));
		mainStage.hide();
		victoryStage.show();
	}

	/**
	 * Shows settings menu
	 */
	public static void showSettingsMenu() {
		mainStage.setScene(new Scene(new SettingsMenu()));
	}

	/**
	 * Shows leaderboard menu
	 */
	public static void showLeaderboardsMenu() {
		mainStage.setScene(new Scene(new LeaderboardMenu()));
	}

	/**
	 * Shows pause menu
	 */
	public static void showPauseMenu() {
		pauseStage.setScene(new Scene(new PauseMenu()));
		mainStage.hide();
		pauseStage.show();
	}

	/**
	 * Hides pause menu
	 */
	public static void hidePauseMenu() {
		pauseStage.hide();
		mainStage.show();

	}

	/**
	 * Hides victory menu
	 */
	public static void hideVictoryMenu() {
		victoryStage.hide();
		mainStage.show();
	}

	/**
	 * Saves the game
	 * 
	 * @param board   Game board
	 * @param p1      Player 1
	 * @param p2      Player 2
	 * @param p3      Player 3
	 * @param p4      Player 4
	 * @param hand1   Player 1's hand
	 * @param hand2   Player 2's hand
	 * @param hand3   Player 3's hand
	 * @param hand4   Player 4's hand
	 * @param effects Action tiles in effect
	 */
	public static void saveGame(Board board, PlayerPiece p1, PlayerPiece p2, PlayerPiece p3, PlayerPiece p4, Hand hand1,
			Hand hand2, Hand hand3, Hand hand4, InEffect effects) {
		try {

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter save name: ");
			String filename = scanner.nextLine();
			scanner.nextLine();
			scanner.close();

			File newSave = new File("S" + filename + ".txt");
			if (newSave.createNewFile()) {

				FileWriter writer = new FileWriter(newSave);
				PrintWriter printWriter = new PrintWriter(writer);
				printWriter.println(board.getGridSizeX() + "," + board.getGridSizeY());
				printWriter.println(board.fixedTileNum);
				for (int i = 0; i < board.getGridSizeX(); i++) {
					for (int j = 0; j < board.getGridSizeY(); j++) {
						if (board.getTileAt(i, j).isFixed()) {
							switch (board.getTileAt(i, j).getClass().toString()) {
							case ("class Corner"):
								printWriter.println(
										i + "," + j + "," + "CORNER" + "," + board.getTileAt(i, j).getRotation());
								break;
							case ("class T_tile"):
								printWriter.println(
										i + "," + j + "," + "TPIECE" + "," + board.getTileAt(i, j).getRotation());
								break;
							case ("class Straight"):
								printWriter.println(
										i + "," + j + "," + "STRAIGHT" + "," + board.getTileAt(i, j).getRotation());
								break;
							case ("class WinTile"):
								printWriter
										.println(i + "," + j + "," + "WIN" + "," + board.getTileAt(i, j).getRotation());
								break;
							}

						}

					}
				}

				printWriter.println(board.tileNum);
				for (int i = 0; i < board.getGridSizeX(); i++) {
					for (int j = 0; j < board.getGridSizeY(); j++) {
						if (!board.getTileAt(i, j).isFixed()) {
							switch (board.getTileAt(i, j).getClass().toString()) {
							case ("class Corner"):
								printWriter.println(
										i + "," + j + "," + "CORNER" + "," + board.getTileAt(i, j).getRotation());
								break;
							case ("class T_tile"):
								printWriter.println(
										i + "," + j + "," + "TPIECE" + "," + board.getTileAt(i, j).getRotation());
								break;
							case ("class Straight"):
								printWriter.println(
										i + "," + j + "," + "STRAIGHT" + "," + board.getTileAt(i, j).getRotation());
								break;
							case ("class WinTile"):
								printWriter
										.println(i + "," + j + "," + "WIN" + "," + board.getTileAt(i, j).getRotation());
								break;
							}

						}
					}
				}

				printWriter.println("Tiles in bag," + board.getBag().getTilesInBag().size());
				for (int i = 0; i < board.getBag().getTilesInBag().size(); i++) {
					switch (board.getBag().getTileInBag(i).getClass().toString()) {
					case ("class Corner"):
						printWriter.println("CORNER");
						break;
					case ("class T_tile"):
						printWriter.println("TPIECE");
						break;
					case ("class Straight"):
						printWriter.println("STRAIGHT");
						break;
					case ("class WinTile"):
						printWriter.println("WIN");
						break;
					case ("class Fire"):
						printWriter.println("FIRE");
						break;
					case ("class Ice"):
						printWriter.println("ICE");
						break;
					case ("class BackTrack"):
						printWriter.println("BACKTRACK");
						break;
					case ("class DoubleMove"):
						printWriter.println("DOUBLEMOVE");
						break;
					}

				}

				// Printing xpos and ypos of all players and their current hand size and
				// contents to the save file.
				printWriter.println(p1.getXpos() + "," + p1.getYpos());
				printWriter.println(hand1.getHolding().size());
				for (int i = 0; i < hand1.getHolding().size(); i++) {
					printWriter.println(hand1.getAt(i).getClass());
				}

				printWriter.println(p2.getXpos() + "," + p2.getYpos());
				printWriter.println(hand2.getHolding().size());
				for (int i = 0; i < hand2.getHolding().size(); i++) {
					printWriter.println(hand2.getAt(i).getClass());
				}

				printWriter.println(p3.getXpos() + "," + p3.getYpos());
				printWriter.println(hand3.getHolding().size());
				for (int i = 0; i < hand3.getHolding().size(); i++) {
					printWriter.println(hand3.getAt(i).getClass());
				}

				printWriter.println(p4.getXpos() + "," + p4.getYpos());
				printWriter.println(hand4.getHolding().size());
				for (int i = 0; i < hand4.getHolding().size(); i++) {
					printWriter.println(hand4.getAt(i).getClass());
				}

				printWriter.close();
				writer.close();

				System.out.println("Saving game as: " + newSave.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Shows primary stage
	 */
	public void start(Stage primaryStage) {

		mainStage = primaryStage;
		mainStage.setScene(new Scene(new StartMenu()));
		mainStage.show();

	}

	/**
	 * Main method
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		launch(args);

	}

}

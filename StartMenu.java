import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class represents the games start menu.
 * 
 * @author Todd Dyer.
 * @version 1.0
 */

public class StartMenu extends Menu {

	/**
	 * Creates StartMenu class instance
	 */
	public StartMenu() {
		super("Labyrinth");
		makePlayButton();
		makeSettingsButton();
		makeLeaderboardsButton();
		makeQuitButton();
		showMOTD();
	}

	/**
	 * Button that allows user to play.
	 */
	private void makePlayButton() {
		super.makeButton("Play").setOnAction(e -> {
			// Main.playGame();
			Main.loadGameOptions();
		});
	}

	/**
	 * Button that allows access to settings of the game.
	 */
	private void makeSettingsButton() {
		super.makeButton("Settings").setOnAction(e -> {
			Main.showSettingsMenu();
		});
	}

	/**
	 * Button that allows access to the leaderboard of the game.
	 */
	private void makeLeaderboardsButton() {
		super.makeButton("Leaderboards").setOnAction(e -> {
			Main.showLeaderboardsMenu();
		});
	}

	/**
	 * Creates a quit button
	 */
	private void makeQuitButton() {
		super.makeButton("Quit").setOnAction(e -> {
			System.exit(0);
		});
	}

	/**
	 * Displays message of the day
	 */
	private void showMOTD() {
		Label motdLabel = new Label(MOTD.getMOTD());
		super.setBottom(motdLabel);
		motdLabel.setAlignment(Pos.CENTER);
		motdLabel.setPadding(new Insets(Main.WINDOW_HEIGHT / 20));
		motdLabel.setFont(new Font(Main.WINDOW_HEIGHT / 50));
		motdLabel.setTextFill(Color.WHITE);
	}

}

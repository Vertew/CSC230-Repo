import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * The LeaderboardMenu class creates an actual menu for the leaderboard
 * 
 * @author Todd Dyer
 * @author Marta Piaszczynska
 * @version 1.0
 */
public class LeaderboardMenu extends BorderPane {

	/**
	 * Constructor for LeaderboardMenu class
	 */
	public LeaderboardMenu() {
		buildGUI();
		setMinSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		showWinsLB();
	}

	/**
	 * Outputs the wins to the screen
	 */
	private void showWinsLB() {
		Leaderboard lb = new Leaderboard("wins");
		this.setCenter(lb);
	}

	/**
	 * Outputs the losses to the screen
	 */
	private void showLossesLB() {
		Leaderboard lb = new Leaderboard("losses");
		this.setCenter(lb);
	}

	/**
	 * Outputs the games played to the screen
	 */
	private void showGamesPlayedLB() {
		Leaderboard lb = new Leaderboard("games");
		this.setCenter(lb);
	}

	/**
	 * Builds the GUI for the leaderboard and the buttons that will go on the
	 * leaderboard
	 */
	private void buildGUI() {
		// Create GUI pane look nice
		VBox buttonBox = new VBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(0, 100, 0, 0));
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		buttonBox.setSpacing(10);

		// Create return button
		Button returnButton = new CustomButton("Return", false);
		returnButton.setOnAction(e -> {
			Main.showStartMenu();
		});
		// Create button that shows the games played leaderboard
		Button showGamesPlayedButton = new CustomButton("Games Played", false);
		showGamesPlayedButton.setOnAction(e -> {
			showGamesPlayedLB();
		});
		// Create button that shows the losses leaderboard
		Button showLossesButton = new CustomButton("Losses", false);
		showLossesButton.setOnAction(e -> {
			showLossesLB();
		});
		// Create button that shows the wins leaderboard
		Button showWinsButton = new CustomButton("Wins", false);
		showWinsButton.setOnAction(e -> {
			showWinsLB();
		});

		// Add button to GUI
		buttonBox.getChildren().addAll(returnButton, showWinsButton, showLossesButton, showGamesPlayedButton);

		// Add GUI to pane
		this.setRight(buttonBox);
	}

}

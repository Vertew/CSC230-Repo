import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Leaderboard displays the top 10 scores of all the profiles saved. Each
 * leaderboard can display either wins, losses or games played. The leaderboard
 * is sorted in descending order.
 * 
 * @author Todd Dyer - 1911039
 * @version 1.1
 */
public class Leaderboard extends VBox {

	private ArrayList<Profile> profiles = new ArrayList<Profile>();
	// What type of leaderboard it is
	private String type;

	/**
	 * Constructs an instance of the leaderboard class
	 * 
	 * @param type The type of leaderboard this is
	 */
	public Leaderboard(String type) {
		setAlignment(Pos.CENTER);
		setMinSize(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT / 2);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		setSpacing(10);
		this.type = type;
		loadProfiles();
		sortPlayers();
		printScores();
	}

	/**
	 * Load all of the profiles into this leaderboard.
	 */
	private void loadProfiles() {
		File files = new File(System.getProperty("user.dir") + ((char) 92));
		for (File file : files.listFiles()) {
			if (file.getName().endsWith(".txt")) {
				if (file.getName().startsWith("P")) {
					profiles.add(Profile.loadProfile(file));
				}
			}
		}
	}

	/**
	 * Sorts all of the saved profiles by a specified type in descending order.
	 */
	private void sortPlayers() {
		for (int i = 0; i < profiles.size(); i++) {
			for (int j = 0; j < profiles.size() - 1; j++) {
				if ((type == "wins" && profiles.get(j).getWins() < profiles.get(j+1).getWins()) ||
						(type == "losses" && profiles.get(j).getWins() < profiles.get(j+1).getLosses()) ||
						(type == "games" && profiles.get(j).getWins() < profiles.get(j+1).getGamesPlayed())) {
					Profile temp = profiles.get(j+1);
					profiles.set(j+1, profiles.get(j));
					profiles.set(j, temp);
				}
			}
		}
	}

	/**
	 * Prints the top 10 scores out of all saved profiles.
	 */
	private void printScores() {
		String scoreStr = "";
		for (int i = 0; i < profiles.size() && i < 10; i++) {
			switch (type) {
			case "wins":
				scoreStr = (i + 1) + "  |  " + profiles.get(i).getName() + "  |  " + Integer.toString(profiles.get(i).getWins())
						+ " wins";
				break;
			case "losses":
				scoreStr = (i + 1) + "  |  " + profiles.get(i).getName() + "  |  "
						+ Integer.toString(profiles.get(i).getLosses()) + " losses";
				break;
			case "games":
				scoreStr = (i + 1) + "  |  " + profiles.get(i).getName() + "  |  "
						+ Integer.toString(profiles.get(i).getGamesPlayed()) + " games";
			}
			Label label = new Label(scoreStr);
			label.setFont(new Font(35));
			label.setTextFill(Color.WHITE);
			this.getChildren().add(label);
		}
	}

}
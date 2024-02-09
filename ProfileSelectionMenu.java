import java.io.File;

import javafx.scene.control.TextInputDialog;

/**
 * ProfileSelectionMenu class creates a menu to allow profile selection
 * 
 * @author Todd Dyer
 * @version 1.0
 */

public class ProfileSelectionMenu extends Menu {

	/**
	 * Creates an instance of ProfileSelectionMenu
	 * 
	 * @param currentPlayer Current player
	 * @param playerNum     Player's number
	 */
	public ProfileSelectionMenu(PlayerPiece currentPlayer, int playerNum) {
		super("Select a profile: Player " + playerNum);
		makeNewProfileButton(currentPlayer);
		makeProfileButtons(currentPlayer);
}

	/**
	 * Creates new profile button
	 */
	private void makeNewProfileButton(PlayerPiece currentPlayer) {
		super.makeButton("Make new profile").setOnAction(e -> {
			TextInputDialog inputName = new TextInputDialog();
			inputName.setContentText("Input a name for your new profile:");
			inputName.setTitle("Create new profile");
			inputName.showAndWait();
			currentPlayer.setProfile(new Profile(inputName.getEditor().getText()));
			Main.closeProfileSelectMenu();
		});
	}

	/**
	 * Creates player's button
	 * 
	 * @param currentPlayer Current player
	 */
	private void makeProfileButtons(PlayerPiece currentPlayer) {
		File files = new File(System.getProperty("user.dir") + ((char) 92));
		for (File file : files.listFiles()) {
			if (file.getName().endsWith(".txt")) {
				if (file.getName().startsWith("P")) {
					Profile p = Profile.loadProfile(file);
					super.makeButton(p.getName()).setOnAction(e -> {
						currentPlayer.setProfile(p);
						Main.closeProfileSelectMenu();
					});
				}
			}
		}
	}

}

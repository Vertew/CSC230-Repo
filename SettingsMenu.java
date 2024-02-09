/**
 * This class represents the games settings menu.
 * 
 * @author Todd Dyer.
 * @version 1.0
 */

public class SettingsMenu extends Menu {

	/**
	 * Creates an instance of SettingMenu class.
	 */
	public SettingsMenu() {
		super("Settings");
		super.makeButton("Audio");
		super.makeButton("Graphics");
		makeReturnButton();
	}

	/**
	 * Creates return button
	 */
	private void makeReturnButton() {
		super.makeButton("Return").setOnAction(e -> {
			Main.showStartMenu();
		});
	}

}

/**
 * This class represents the games pause menu.
 * 
 * @author Todd Dyer.
 * @version 1.0
 */

public class PauseMenu extends Menu {

	/**
	 * Creares an instance of PauseMenu class.
	 */
	public PauseMenu() {
		super("Pause");
		makeContinueButton();
		makeQuitButton();
	}

	/**
	 * Creates continue button
	 */
	private void makeContinueButton() {
		super.makeButton("Continue").setOnAction(e -> {
			Main.hidePauseMenu();
		});
	}

	/**
	 * Creates quit button
	 */
	private void makeQuitButton() {
		super.makeButton("Quit").setOnAction(e -> {
			Main.showStartMenu();
			Main.hidePauseMenu();
		});
	}

}

/**
 * This class represents the functions of the victory menu.
 * 
 * @authors Sam Tudberry.
 * @version 1.0
 */

public class VictoryMenu extends Menu {

	/**
	 * Constructor for victory menu.
	 * 
	 * @param text Text that is displayed on the victory screen.
	 */
	public VictoryMenu(String text) {
		super(text);
		makeQuitButton();
	}

	/**
	 * Creates a quit button.
	 */
	private void makeQuitButton() {
		super.makeButton("Quit").setOnAction(e -> {
			Main.showStartMenu();
			Main.hideVictoryMenu();
		});
	}

}

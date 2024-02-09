import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Menu class creates a template for all menus in the game
 * 
 * @author Todd Dyer
 * @version 1.0
 */

public class Menu extends BorderPane {

	protected VBox buttonBox;
	private Label title;

	/**
	 * Creates an instance of Menu class
	 * 
	 * @param titleText Button label
	 */
	public Menu(String titleText) {
		this.title = new Label(titleText);
		this.buttonBox = new VBox();
		buildGUI();
		this.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
	}

	/**
	 * Method to create graphic representation of the Menu
	 */
	private void buildGUI() {

		// Add the title
		this.setTop(title);
		title.setPadding(new Insets(Main.WINDOW_HEIGHT / 20));
		title.setFont(new Font(Main.WINDOW_HEIGHT / 10));
		title.setTextFill(Color.WHITE);
		setAlignment(title, Pos.BOTTOM_CENTER);

		// Create the container for the buttons and make it look nice
		this.setCenter(buttonBox);
		buttonBox.setAlignment(Pos.TOP_CENTER);
		buttonBox.setSpacing(Main.WINDOW_HEIGHT / 100);
		buttonBox.setPadding(new Insets(Main.WINDOW_HEIGHT / 20));

		// Make the pane look nice
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

	}

	/**
	 * Creates menu button
	 * 
	 * @param text Button label
	 */
	protected Button makeButton(String text) {
		// Create Menu button and add it to the button pane
		Button button = new CustomButton(text, true);
		buttonBox.getChildren().add(button);
		return button;
	}

}

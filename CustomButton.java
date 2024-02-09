import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class defines a new custom button
 * 
 * @author Todd Dyer
 * @version 1.0
 */
public class CustomButton extends Button {

	private static final Double MENU_BUTTON_WIDTH = Main.WINDOW_WIDTH / 2;
	private static final Double MENU_BUTTON_HEIGHT = Main.WINDOW_HEIGHT / 10;
	private static final Double GAME_BUTTON_WIDTH = 150.0;
	private static final Double GAME_BUTTON_HEIGHT = 50.0;

	/**
	 * Constructor for a new custom button
	 * 
	 * @param text         The text that is on the button
	 * @param isMenuButton True if the button is a menu button, false otherwise
	 */
	public CustomButton(String text, Boolean isMenuButton) {
		super(text);
		if (isMenuButton) {
			setMinSize(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
		} else {
			setMinSize(GAME_BUTTON_WIDTH, GAME_BUTTON_HEIGHT);
		}
		setFont(new Font(30));
		setTextFill(Color.WHITE);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));

		// Change colour when hovered over
		setOnMouseEntered(e -> {
			setTextFill(Color.BLACK);
			setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		});

		// Change colour when hovered over
		setOnMouseExited(e -> {
			setTextFill(Color.WHITE);
			setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		});
	}

	/**
	 * Get the menu button height
	 * 
	 * @return MENU_BUTTON_HEIGHT the height of the menu button
	 */
	public Double getMenuButtonHeight() {
		return MENU_BUTTON_HEIGHT;
	}

}

import java.io.File;
import java.util.ArrayList;

/**
 * The class LevelSelect extends Menu allows the user to choose what level they
 * would like for the game
 * 
 * @author Todd Dyer
 * @version 1.0
 */
public class LevelSelect extends Menu {

	private String fileType;
	private ArrayList<File> levels = new ArrayList<File>();

	/**
	 * Constructor for LevelSelect
	 * 
	 * @param titleText The text of the title
	 * @param files     The files that will be used
	 * @param fileType  The filetype used
	 */
	public LevelSelect(String titleText, ArrayList<File> files, String fileType) {
		super(titleText);
		setFiles(files);
		generateButtons();
		makeReturnButton();
		this.fileType = fileType;
	}

	/**
	 * Sets the files used for obtaining the levels
	 * 
	 * @param fileName The file that will be used
	 */
	private void setFiles(ArrayList<File> fileName) {
		levels = fileName;
	}

	/**
	 * Genarates the buttons that will be used in the menu
	 */
	private void generateButtons() {
		for (int i = 0; i < levels.size(); i++) {
			makeButton(levels.get(i), generateTitle(levels.get(i)));
		}
	}

	/**
	 * Genarates the tiles from the file inputted
	 * 
	 * @param x the file that is inputted
	 */
	private String generateTitle(File fileName) {
		return fileName.getName().substring(1).replace(".txt", "");
	}

	/**
	 * Genarates a button
	 * 
	 * @param levelFile   File with saved level
	 * @param buttonTitle Button label
	 */
	private void makeButton(File levelFile, String buttonTitle) {
		super.makeButton(buttonTitle).setOnAction(e -> {

			// This button is created if a new game is being started
			if (fileType.equals("new")) {
				Main.playGame(new Board(levelFile));
			}
			/*
			 * This button is created if a save game is being loaded, a different board
			 * constructor is called in this case.
			 */
			else if (fileType.equals("save")) {
				Main.playGame(new Board(levelFile, fileType));
			}
		});
	}

	/**
	 * Genarates a return button
	 */
	private void makeReturnButton() {
		super.makeButton("Return").setOnAction(e -> {
			Main.loadGameOptions();
		});
	}

}

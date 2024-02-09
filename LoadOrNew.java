import java.io.File;
import java.util.ArrayList;

/**
 * LoadOrNew Class is responsible for loading a saved game or starting a new
 * game
 * 
 * @author
 * @version 1.0
 */

public class LoadOrNew extends Menu {

	private String filePath;
	private ArrayList<File> saved = new ArrayList<File>();
	private ArrayList<File> levels = new ArrayList<File>();

	/**
	 * Creates LoadOrNew class instance
	 */
	public LoadOrNew() {
		super("Select a save");
		setFilePath(System.getProperty("user.dir"));
		initFiles();
		makeLoadButton();
		makeNewGameButton();
		makeReturnButton();
	}

	/**
	 * Restets file path
	 * 
	 * @param path File path
	 */
	private void setFilePath(String path) {
		this.filePath = path;
	}

	/**
	 * Sets file names for saved files (start with 'S') and level files (start with
	 * 'L')
	 */
	private void initFiles() {
		File files = new File(filePath + ((char) 92));
		for (File file : files.listFiles()) {
			if (file.getName().endsWith(".txt")) {
				if (file.getName().startsWith("S")) {
					saved.add(file);
				} else if (file.getName().startsWith("L")) {
					levels.add(file);
				}
			}
		}
	}

	/**
	 * Creates load button
	 */
	private void makeLoadButton() {
		super.makeButton("Load game").setOnAction(e -> {
			if (!saved.isEmpty()) {
				Main.loadLevelSelect("Load saved game:", saved, "save");
			} else {
				Main.loadLevelSelect("No Saved Games", saved, "save");
			}
		});
	}

	/**
	 * Creates new game button
	 */
	private void makeNewGameButton() {
		super.makeButton("New game").setOnAction(e -> {
			Main.loadLevelSelect("New game: ", levels, "new");
		});
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class represents a Player Profile.
 * 
 * @author Todd Dyer - 1911039
 * @version 1.0
 */

public class Profile {

	// Attributes
	private int wins;
	private int losses;
	private int gamesPlayed;
	private String name;

	/**
	 * Creates an instance of the Profile class. This constructor is used when
	 * creating a brand new Profile. Wins, losses and games played are set to 0 by
	 * default.
	 * 
	 * @param name Name of this Profile.
	 */
	public Profile(String name) {
		this.wins = 0;
		this.losses = 0;
		this.gamesPlayed = 0;
		this.name = name;
		saveProfile();
	}

	/**
	 * Creates a instance of the Profile class. This constructor is used when
	 * loading a Profile from a save file.
	 * 
	 * @param wins        Number of wins this Profile has.
	 * @param losses      Number of losses this Profile has.
	 * @param gamesPlayed Number of games played by this Profile.
	 * @param name        Name of this Profile.
	 */
	public Profile(int wins, int losses, int gamesPlayed, String name) {
		this.wins = wins;
		this.losses = losses;
		this.gamesPlayed = gamesPlayed;
		this.name = name;
		saveProfile();
	}

	/**
	 * @return Number of wins this Profile has.
	 */
	public int getWins() {
		return this.wins;
	}

	/**
	 * @return Number of losses this Profile has.
	 */
	public int getLosses() {
		return this.losses;
	}

	/**
	 * @return Number of games played by this Profile.
	 */
	public int getGamesPlayed() {
		return this.gamesPlayed;
	}

	/**
	 * @return Name of this Profile.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Increase the number of wins by 1.
	 */
	public void incrementWins() {
		this.wins++;
	}

	/**
	 * Increase the number of losses by 1.
	 */
	public void incrementLosses() {
		this.losses++;
	}

	/**
	 * Increase the number of games played by 1.
	 */
	public void incrementGamesPlayed() {
		this.gamesPlayed++;
	}
	
	/**
	 * @return The data stored within this Profile in the form of a string.
	 */
	public String toString() {
		return name + " " + wins + " " + losses + " " + gamesPlayed;
	}

	/**
	 * Saves this profile as a text file with the filename set to 'P' concatenated
	 * with the name of this profile. Profiles are automatically overwritten.
	 */
	public void saveProfile() {
		// ensures the default profiles aren't saved
		if(!name.startsWith("Default")) {
			try {
	        	String filename = name;
	        	
	            File newProfile = new File("P" + filename + ".txt");
					
				FileWriter writer = new FileWriter(newProfile);
				PrintWriter printWriter = new PrintWriter(writer);
				printWriter.println(name);
				printWriter.println(wins);
				printWriter.println(losses);
				printWriter.println(gamesPlayed);
				printWriter.close();
		        writer.close();
			} catch (IOException e) {
				Alert profileNotSavedMsg = new Alert(AlertType.ERROR);
				profileNotSavedMsg.setContentText("Profile not saved!");
				profileNotSavedMsg.show();
			}
		}
	}

	/**
	 * Loads a profile.
	 * 
	 * @param profileToLoad The name of the profile to load.
	 * @return The profile that has been loaded.
	 * @throws FileNotFoundException
	 */
	public static Profile loadProfile(File profileToLoad) {

		Scanner input;
		try {
			input = new Scanner(profileToLoad);
		} catch (FileNotFoundException e) {
			Alert profileNotLoadedMsg = new Alert(AlertType.ERROR);
			profileNotLoadedMsg.setContentText("Profile not loaded!");
			profileNotLoadedMsg.show();
			return null;
		}
		String nameToReturn = input.nextLine();
		int winsToReturn = Integer.parseInt(input.nextLine());
		int lossesToReturn = Integer.parseInt(input.nextLine());
		int gamesPlayedToReturn = Integer.parseInt(input.nextLine());
		input.close();

		return new Profile(winsToReturn, lossesToReturn, gamesPlayedToReturn, nameToReturn);

	}

}

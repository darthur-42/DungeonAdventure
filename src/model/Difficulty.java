/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents difficulty levels for the game.
 * 
 * @author Justin Le
 * @version 19 Mar 2025
 */
public enum Difficulty {
	EASY("Easy"), MEDIUM("Medium"), HARD("Hard");
	
	private final String myDisplayName;
	
	private Difficulty(String theName) {
		myDisplayName = theName;
	}
	
	@Override
	public String toString() {
		return myDisplayName;
	}
}

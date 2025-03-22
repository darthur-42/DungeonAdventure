/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents difficulty levels for the game.
 * 
 * @author Justin Le
 * @version 21 Mar 2025
 */
public enum Difficulty {
	EASY("Easy"), MEDIUM("Medium"), HARD("Hard");
	
	/** The type of the Difficulty. */
	private final String myType;
	
	/**
	 * Sets the type for the Difficulty.
	 *
	 * @param theType the type
	 */
	private Difficulty(String theName) {
		myType = theName;
	}
	
	@Override
	public String toString() {
		return myType;
	}
}

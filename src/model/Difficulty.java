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
	
	EASY("Easy", "Enemy stats scale at 75%."),
	MEDIUM("Medium", "Enemy stats scale at 100%."),
	HARD("Hard", "Enemy stats scale at 125%.");
	
	/** The type of the Difficulty. */
	private final String myType;
	
	/** The description of the Difficulty. */
	private final String myDescription;
	
	/**
	 * Sets the type and description for the Difficulty.
	 *
	 * @param theType the type
	 * @param theDescription the description
	 */
	private Difficulty(final String theName, final String theDescription) {
		myType = theName;
		myDescription = theDescription;
	}
	
	/**
	 * Returns the description of the Difficulty.
	 * 
	 * @return the description of the Difficulty
	 */
	public String getDescription() {
		return myDescription;
	}
	@Override
	public String toString() {
		return myType;
	}
	
}

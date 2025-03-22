/*
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the types of heroes that can be selected in the game.
 * 
 * @author Justin Le
 * @version 11 Mar 2025
 */
public enum HeroType {
	WARRIOR("Warrior"), PRIESTESS("Priestess"), THIEF("Thief"), BERSERKER("Berserker");

	/** Display name for the hero type. */
	private final String myDisplayName;

	/**
	 * Sets the display name for the hero type.
	 *
	 * @param theName the name to display
	 */
	private HeroType(String theName) {
		myDisplayName = theName;
	}

	/**
	 * Returns the display name of this hero type.
	 *
	 * @return the hero type as a formatted string
	 */
	@Override
	public String toString() {
		return myDisplayName;
	}
}

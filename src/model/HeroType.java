/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the types of Heroes that can be created.
 * 
 * @author Justin Le
 * @version 11 Mar 2025
 */
public enum HeroType {
	WARRIOR, PRIESTESS, THIEF;

	/**
	 * Returns the HeroType that matches the given name.
	 *
	 * @param theName the Hero name
	 * @return the matching HeroType
	 * @throws IllegalArgumentException if the name is invalid
	 */
	public static HeroType fromString(final String theName) {
		if (theName == null) {
			throw new IllegalArgumentException("Hero name cannot be null.");
		}
		
		return switch (theName.trim().toUpperCase()) {
			case "WARRIOR" -> WARRIOR;
			case "PRIESTESS" -> PRIESTESS;
			case "THIEF" -> THIEF;
			default -> throw new IllegalArgumentException("Invalid Hero name.");
		};
	}
}

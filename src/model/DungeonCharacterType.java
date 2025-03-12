/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the types of DungeonCharacters that can be created.
 * 
 * @author Anna Brewer, Justin Le
 * @version 11 Mar 2025
 */
public enum DungeonCharacterType {
	WARRIOR, PRIESTESS, THIEF, GREMLIN, OGRE, SKELETON;

	/**
	 * Returns the DungeonCharacterType that matches the given name.
	 *
	 * @param theName the DungeonCharacter name
	 * @return the matching DungeonCharacterType
	 * @throws IllegalArgumentException if the name is invalid
	 */
	public static DungeonCharacterType fromString(final String theName) {
		if (theName == null) {
			throw new IllegalArgumentException("DungeonCharacter name cannot be null.");
		}
		
		return switch (theName.trim().toUpperCase()) {
			case "WARRIOR" -> WARRIOR;
			case "PRIESTESS" -> PRIESTESS;
			case "THIEF" -> THIEF;
			case "GREMLIN" -> GREMLIN;
			case "OGRE" -> OGRE;
			case "SKELETON" -> SKELETON;
			default -> throw new IllegalArgumentException("Invalid DungeonCharacter name.");
		};
	}
}

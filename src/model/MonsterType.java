/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the types of Monsters that can be created.
 * 
 * @author Anna Brewer, Justin Le
 * @version 11 Mar 2025
 */
public enum MonsterType {
	GREMLIN, OGRE, SKELETON;

	/**
	 * Returns the MonsterType that matches the given name.
	 *
	 * @param theName the Monster name
	 * @return the matching MonsterType
	 * @throws IllegalArgumentException if the name is invalid
	 */
	public static MonsterType fromString(final String theName) {
		if (theName == null) {
			throw new IllegalArgumentException("Monster name cannot be null.");
		}
		
		return switch (theName.trim().toUpperCase()) {
			case "GREMLIN" -> GREMLIN;
			case "OGRE" -> OGRE;
			case "SKELETON" -> SKELETON;
			default -> throw new IllegalArgumentException("Invalid Monster name.");
		};
	}
}

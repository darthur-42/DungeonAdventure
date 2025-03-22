/*
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the types of monsters that can appear in the dungeon.
 *
 * @author Anna Brewer, Justin Le
 * @version 11 Mar 2025
 */
public enum MonsterType {
	OGRE, GREMLIN, SKELETON, DRAGON;

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
		case "OGRE" -> OGRE;
		case "GREMLIN" -> GREMLIN;
		case "SKELETON" -> SKELETON;
		case "DRAGON" -> DRAGON;
		default -> throw new IllegalArgumentException("Invalid Monster name.");
		};
	}
}

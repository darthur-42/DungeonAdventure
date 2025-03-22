/*
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the four pillars of Object-Oriented Programming (OOP).
 * 
 * @author Arthur Fornia, Justin Le
 * @version 21 Mar 2025
 */
public enum PillarOO {
	ABSTRACTION("Abstraction"), ENCAPSULATION("Encapsulation"),
	INHERITANCE("Inheritance"), POLYMORPHISM("Polymorphism");

	/** Display name for the pillar. */
	private final String myDisplayName;

	/**
	 * Sets the display name for this pillar.
	 *
	 * @param theName the name to display
	 */
	private PillarOO(String theName) {
		myDisplayName = theName;
	}

	/**
	 * Returns the display name of this pillar.
	 *
	 * @return the pillar as a formatted string
	 */
	@Override
	public String toString() {
		return myDisplayName;
	}
}

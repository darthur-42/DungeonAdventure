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
	WARRIOR("Warrior"), PRIESTESS("Priestess"), THIEF("Thief"), BERSERKER("Berserker");
	
	private final String myDisplayName;
	
	private HeroType(String theName) {
		myDisplayName = theName;
	}
	
	@Override
	public String toString() {
		return myDisplayName;
	}
}

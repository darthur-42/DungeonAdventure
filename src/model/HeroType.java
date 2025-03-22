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
	WARRIOR(
			"Warrior",
			"The Warrior is a Hero that has high health, high damage, low attack speed, high hit "
			+ "chance, and low block chance. Their special attack (Charge) has a low chance to deal "
			+ "even higher damage."
	),
	PRIESTESS(
			"Priestess",
			"The Priestess is a Hero that has low health, low damage, medium attack speed, medium "
			+ "hit chance and medium block chance. Their special attack (Heal) heals themselves."
	),
	THIEF(
			"Thief",
			"The Thief is a Hero that has low health, low damage, high attack speed, high hit "
			+ "chance, and high block chance. Their special attack (Bamboozle) has a high chance to "
			+ "do a standard attack and a moderate chance to give an additional turn."
	),
	BERSERKER(
			"Berserker",
			"The Berserker is a Hero that has high health, very high damage, medium attack speed, "
			+ "high hit chance, and zero block chance. Their special attack (Leech) does a weaker "
			+ "attack and additionally heals themselves slightly if it lands."
	);
	
	/** The type of the HeroType. */
	private final String myType;
	private final String myDescription;
	
	/**
	 * Sets the type and description for the HeroType.
	 *
	 * @param theType the type
	 * @param theDescription the description
	 */
	private HeroType(final String theType, final String theDescription) {
		myType = theType;
		myDescription = theDescription;
	}
	
	/**
	 * Returns the description of the Hero.
	 * 
	 * @return the description of the Hero
	 */
	public String getDescription() {
		return myDescription;
	}

	/**
	 * Returns a string representation of the HeroType.
	 *
	 * @return a string representation of the HeroType
	 */
	@Override
	public String toString() {
		return myType;
	}
}

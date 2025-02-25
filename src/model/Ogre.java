/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents an Ogre monster.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public class Ogre extends Monster {

	/**
	 * Constructs an Ogre with stats from the database.
	 * 
	 * @param theHealthPoints the monster's health points
	 * @param theDamageMin    the minimum damage
	 * @param theDamageMax    the maximum damage
	 * @param theAttackSpeed  the attack speed
	 * @param theHitChance    the chance to hit
	 * @param theHealMin      the minimum heal amount
	 * @param theHealMax      the maximum heal amount
	 * @param theHealChance   the chance to heal
	 * @param theRandom       the Random instance
	 */
	public Ogre(int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed, double theHitChance,
			int theHealMin, int theHealMax, double theHealChance, Random theRandom) {
		super("Ogre", theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theHealMin, theHealMax,
				theHealChance, theRandom);
	}

	/**
	 * Constructs an Ogre.
	 */
	public Ogre() {
		this(new Random());
	}

	/**
	 * Constructs an Ogre. Can pass in a random instance for testing.
	 */
	public Ogre(final Random theRandom) {
		super("Ogre", 200, 30, 60, 2, 0.6, 30, 60, 0.1, theRandom);
	}

	/**
	 * Attacks the target character and may heal.
	 * 
	 * @param otherCharacter the character being attacked
	 */
	@Override
	public void castAttackOn(final DungeonCharacter otherCharacter) {
		System.out.println(getName() + " attacks " + otherCharacter.getName() + "!");
		attack(otherCharacter);
		heal();
	}
}

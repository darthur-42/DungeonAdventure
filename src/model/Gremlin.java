/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a Gremlin monster.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public class Gremlin extends Monster {

	/**
	 * Constructs a Gremlin with stats from the database.
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
	public Gremlin(int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed, double theHitChance,
			int theHealMin, int theHealMax, double theHealChance, Random theRandom) {
		super("Gremlin", theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theHealMin,
				theHealMax, theHealChance, theRandom);
	}

	/**
	 * Constructs a Gremlin.
	 */
	public Gremlin() {
		this(new Random());
	}

	/**
	 * Constructs a Gremlin. Can pass in a random instance for testing.
	 */
	public Gremlin(final Random theRandom) {
		super("Gremlin", 70, 15, 30, 5, 0.8, 20, 40, 0.4, theRandom);
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

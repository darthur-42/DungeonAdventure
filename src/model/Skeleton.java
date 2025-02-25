/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a Skeleton monster.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public class Skeleton extends Monster {

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
	public Skeleton(int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed, double theHitChance,
			int theHealMin, int theHealMax, double theHealChance, Random theRandom) {
		super("Skeleton", theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theHealMin,
				theHealMax, theHealChance, theRandom);
	}

	/**
	 * Constructs a Skeleton.
	 */
	public Skeleton() {
		this(new Random());
	}

	/**
	 * Constructs a Skeleton. Can pass in a random instance for testing.
	 */
	public Skeleton(final Random theRandom) {
		super("Skeleton", 100, 30, 50, 3, 0.8, 30, 50, 0.3, theRandom);
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

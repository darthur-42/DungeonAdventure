/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a Gremlin monster.
 * 
 * @author Anna Brewer
 * @version 3 Mar 2025
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
	 * Attacks a character. If the attack results in lost HP, the Gremlin has a chance to heal.
	 * Healing is checked after HP is reduced.
	 *
	 * @param otherCharacter The character being attacked.
	 */
	@Override
	public void castAttackOn(final DungeonCharacter otherCharacter) {
	    int originalHealth = otherCharacter.getCurHealthPoints();
	    attack(otherCharacter);

	    if (otherCharacter.getCurHealthPoints() < originalHealth && getCurHealthPoints() > 0) {
	        heal();
	    }
	}
}
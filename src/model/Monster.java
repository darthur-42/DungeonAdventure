/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * @author Anna Brewer, Justin Le
 * @version 3 Mar 2025
 */
public abstract class Monster extends DungeonCharacter implements Healable {
	private int myHealingMin;
	private int myHealingMax;
	private double myHealingChance;

	/**
	 * Constructs a Monster. Can pass in a random instance for testing.
	 * 
	 * @param theName         the monster's name
	 * @param theHealthPoints the monster's health points
	 * @param theDamageMin    minimum damage
	 * @param theDamageMax    maximum damage
	 * @param theAttackSpeed  attack speed
	 * @param theHitChance    chance to hit
	 * @param theHealingMin      minimum heal amount
	 * @param theHealingMax      maximum heal amount
	 * @param theHealingChance   chance to heal
	 * @param theRandom       random instance
	 */
	public Monster(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
			final int theAttackSpeed, final double theHitChance, final int theHealingMin, final int theHealingMax,
			final double theHealingChance, final Random theRandom) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theRandom);
		setHealingRange(theHealingMin, theHealingMax);
		setHealingChance(theHealingChance);
	}

	@Override
	public int getHealingMin() {
		return myHealingMin;
	}

	@Override
	public int getHealingMax() {
		return myHealingMax;
	}

	@Override
	public void setHealingRange(final int newHealingMin, final int newHealingMax) {
		int healingMaxLimit = 999;
		
		if (newHealingMin <= 0) {
			throw new IllegalArgumentException("Minimum healing cannot be zero or negative.");
		}
		if (newHealingMax <= newHealingMin) {
			throw new IllegalArgumentException("Maximum healing cannot be less than minimum "
					+ "healing.");
		}
		
		myHealingMin = Math.min(newHealingMin, healingMaxLimit);
		myHealingMax = Math.min(newHealingMax, healingMaxLimit);
	}

	@Override
	public int getRandomHealing() {
		return getRandomHealing(myRandom);
	}

	@Override
	public double getHealingChance() {
		return myHealingChance;
	}

	@Override
	public void setHealingChance(final double newHealingChance) {
		if (newHealingChance <= 0.0) {
			throw new IllegalArgumentException("Heal chance cannot be zero or negative.");
		}
		
		myHealingChance = Math.min(newHealingChance, CHANCE_MAX_LIMIT);
	}

	@Override
	public void heal() {
		heal(myRandom);
//		System.out.println(getName() + " healed for " + healAmount + " HP!");
	}

	/**
	 * Attacks the target character.
	 * 
	 * @param otherCharacter the character being attacked
	 */
	public abstract void castAttackOn(final DungeonCharacter otherCharacter);

	/**
	 * Returns a string representation of the Monster.
	 *
	 * @return a string containing the Monster's name, health, damage range, speed,
	 *         hit chance, and heal range
	 */
	@Override
	public String toString() {
	    return String.format("%s [Health: %d, Damage: %d-%d, Speed: %d, Hit Chance: %.2f, Heal: %d-%d, Heal Chance: %.2f]",
	            getName(), getCurHealthPoints(), getDamageMin(), getDamageMax(), getAttackSpeed(),
	            getHitChance(), getHealingMin(), getHealingMax(), getHealingChance());
	}
}

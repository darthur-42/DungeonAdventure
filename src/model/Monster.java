/**
 * TCSS 360 Group Project
 */
package model;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * Monsters can attack heroes, take damage, and heal themselves after a successful hit.
 * Supports property change events to notify listeners of health updates.
 * 
 * @author Anna Brewer, Justin Le
 * @version 21 Mar 2025
 */
public class Monster extends DungeonCharacter implements Healable, Serializable {

	/** Unique identifier for serialization. */
	private static final long serialVersionUID = -2138794081851463063L;

	/** The minimum amount the monster can heal. */
	private int myHealingMin;

	/** The maximum amount the monster can heal. */
	private int myHealingMax;

	/** The chance for the monster to heal. */
	private double myHealingChance;

	/**
	 * Constructs a Monster with stats, healing attributes, and a Random instance for testing.
	 * 
	 * @param theName          the monster's name
	 * @param theHealthPoints  the monster's health points
	 * @param theDamageMin     minimum damage
	 * @param theDamageMax     maximum damage
	 * @param theAttackSpeed   attack speed
	 * @param theHitChance     chance to hit
	 * @param theHealingMin    minimum heal amount
	 * @param theHealingMax    maximum heal amount
	 * @param theHealingChance chance to heal
	 * @param theRandom        random instance
	 */
	public Monster(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
			final int theAttackSpeed, final double theHitChance, final int theHealingMin, final int theHealingMax,
			final double theHealingChance, final Random theRandom) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theRandom);
		setHealingRange(theHealingMin, theHealingMax);
		setHealingChance(theHealingChance);
	}

	/**
	 * Returns the monster's healing chance (0.0 to 1.0).
	 * 
	 * @return the monster's healing chance
	 */
	@Override
	public double getHealingChance() {
		return myHealingChance;
	}

	/**
	 * Sets the monster's healing chance (0.0 to 1.0).
	 * 
	 * @param theHealingChance the chance that the monster will heal
	 * @throws IllegalArgumentException if the value is zero or negative
	 */
	@Override
	public void setHealingChance(final double theHealingChance) {
		if (theHealingChance <= 0.0) {
			throw new IllegalArgumentException("Heal chance cannot be zero or negative.");
		}

		myHealingChance = Math.min(theHealingChance, CHANCE_MAX_LIMIT);
	}

	/**
	 * Returns the minimum healing amount.
	 * 
	 * @return the minimum heal value
	 */
	@Override
	public int getHealingMin() {
		return myHealingMin;
	}

	/**
	 * Returns the maximum healing amount.
	 * 
	 * @return the maximum heal value
	 */
	@Override
	public int getHealingMax() {
		return myHealingMax;
	}

	/**
	 * Sets the monster's healing range.
	 * 
	 * @param theHealingMin the minimum healing value
	 * @param theHealingMax the maximum healing value
	 * @throws IllegalArgumentException if values are invalid
	 */
	@Override
	public void setHealingRange(final int theHealingMin, final int theHealingMax) {
		int healingMaxLimit = 999;

		if (theHealingMin <= 0) {
			throw new IllegalArgumentException("Minimum healing cannot be zero or negative.");
		}
		if (theHealingMax <= theHealingMin) {
			throw new IllegalArgumentException("Maximum healing cannot be less than minimum " + "healing.");
		}

		myHealingMin = Math.min(theHealingMin, healingMaxLimit);
		myHealingMax = Math.min(theHealingMax, healingMaxLimit);
	}

	/**
	 * Returns a random healing amount within the monster's healing range.
	 * 
	 * @return a random healing value
	 */
	@Override
	public int getRandomHealing() {
		return getRandomHealing(myRandom);
	}

	/**
	 * Attempts to heal the monster if it's alive and chance succeeds.
	 * Notifies listeners of updated HP after healing.
	 */
	@Override
	public void heal() {
		if (getCurHealthPoints() > 0) {
			int oldHealth = getCurHealthPoints();
			heal(myRandom);
			myChanges.firePropertyChange("health", oldHealth, getCurHealthPoints());
		}
	}

	/**
	 * Attacks a target and attempts to heal if the attack causes damage.
	 * Healing only triggers if the monster survives and the target loses HP.
	 * 
	 * @param theOtherCharacter the character being attacked
	 */
	public void castAttackOn(final DungeonCharacter theOtherCharacter) {
		int originalHealth = theOtherCharacter.getCurHealthPoints();
		attack(theOtherCharacter);

		if (theOtherCharacter.getCurHealthPoints() < originalHealth && getCurHealthPoints() > 0) {
			heal();
		}
	}

	/**
	 * Applies damage to the monster.
	 * 
	 * @param theDamageAmount the amount of damage taken
	 */
	public void takeDamage(final int theDamageAmount) {
		receiveDamage(theDamageAmount);
	}
}

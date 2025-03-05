/**
 * TCSS 360 Group Project
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * @author Anna Brewer, Justin Le
 * @version 3 Mar 2025
 */
public abstract class Monster extends DungeonCharacter implements Healable {

	/** The minimum heal amount for the monster. */
	private int myHealingMin;

	/** The maximum heal amount for the monster. */
	private int myHealingMax;

	/** The chance for the monster to heal. */
	private double myHealingChance;

	/** Property change support for event-based updates. */
	private final PropertyChangeSupport myChanges;

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
		myChanges = new PropertyChangeSupport(this);
	}

	/**
	 * Adds a listener for property changes.
	 *
	 * @param theListener the listener to add
	 */
	public void addPropertyChangeListener(final PropertyChangeListener theListener) {
		myChanges.addPropertyChangeListener(theListener);
	}

	/**
	 * Removes a listener.
	 *
	 * @param theListener the listener to remove
	 */
	public void removePropertyChangeListener(final PropertyChangeListener theListener) {
		myChanges.removePropertyChangeListener(theListener);
	}

	/**
	 * Returns the chance that the monster will heal.
	 * 
	 * @return the heal chance (percentage as a decimal)
	 */
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

	/**
	 * Returns the minimum possible healing amount.
	 * 
	 * @return the minimum heal value
	 */
	@Override
	public int getHealingMin() {
		return myHealingMin;
	}

	/**
	 * Returns the maximum possible healing amount.
	 * 
	 * @return the maximum heal value
	 */
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

	/**
	 * Returns a random healing amount within the allowed range.
	 * 
	 * @return a random healing value
	 */
	@Override
	public int getRandomHealing() {
		return getRandomHealing(myRandom);
	}

	/**
	 * Heals the monster if it has a chance to recover health.
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
	 * Attacks the target character.
	 * 
	 * @param theOtherCharacter the character being attacked
	 */
	public void castAttackOn(DungeonCharacter theOtherCharacter) {
		if (theOtherCharacter.getCurHealthPoints() > 0) {
			attack(theOtherCharacter);

			if (getCurHealthPoints() > 0) {
				heal();
			}
		}
	}

	/**
	 * Returns a string representation of the Monster.
	 * 
	 * @return a string containing the Monster's name, health, damage range, speed,
	 *         and hit chance
	 */
	@Override
	public String toString() {
		return String.format("%s [Health: %d, Damage: %d-%d, Speed: %d, Hit Chance: %.2f, Heal Chance: %.2f]",
				getName(), getCurHealthPoints(), getDamageMin(), getDamageMax(), getAttackSpeed(), getHitChance(),
				getHealingChance());
	}
}
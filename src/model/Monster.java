/**
 * TCSS 360 Group Project
 */
package model;

import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * @author Anna Brewer, Justin Le
 * @version 19 Mar 2025
 */
public class Monster extends DungeonCharacter implements Healable, Serializable {

	/** Unique identifier for serialization. */
	private static final long serialVersionUID = 1L;

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
	 * @return the healing chance (percentage as a decimal)
	 */
	@Override
	public double getHealingChance() {
		return myHealingChance;
	}

	/**
	 * Sets the chance for the monster to heal.
	 * 
	 * @param theHealingChance the healing chance as a decimal
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

	/**
	 * Sets the healing range for the monster.
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
	 * Attacks the target character. If the attack lands, the Monster has a chance
	 * to heal.
	 * 
	 * @param theOtherCharacter the character being attacked
	 */
	public void castAttackOn(final DungeonCharacter otherCharacter) {
		int originalHealth = otherCharacter.getCurHealthPoints();
		attack(otherCharacter);

		if (otherCharacter.getCurHealthPoints() < originalHealth && getCurHealthPoints() > 0) {
			heal();
		}
	}

	/**
	 * Receive damage and update current health.
	 * 
	 * @param theDamageAmount amount of damage received
	 */
	public void takeDamage(final int theDamageAmount) {
		receiveDamage(theDamageAmount);
	}
}

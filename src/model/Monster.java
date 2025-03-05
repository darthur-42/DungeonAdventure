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
 * @author Anna Brewer
 * @version 3 Mar 2025
 */
public abstract class Monster extends DungeonCharacter implements Healable {

	/** The minimum heal amount for the monster. */
	private final int myHealMin;

	/** The maximum heal amount for the monster. */
	private final int myHealMax;

	/** The chance for the monster to heal. */
	private final double myHealChance;

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
	 * @param theHealMin      minimum heal amount
	 * @param theHealMax      maximum heal amount
	 * @param theHealChance   chance to heal
	 * @param theRandom       random instance
	 */
	public Monster(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
			final int theAttackSpeed, final double theHitChance, final int theHealMin, final int theHealMax,
			final double theHealChance, final Random theRandom) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theRandom);
		myHealMin = theHealMin;
		myHealMax = theHealMax;
		myHealChance = theHealChance;
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
	public double getHealChance() {
		return myHealChance;
	}

	/**
	 * Returns the minimum possible healing amount.
	 * 
	 * @return the minimum heal value
	 */
	@Override
	public int getHealingMin() {
		return myHealMin;
	}

	/**
	 * Returns the maximum possible healing amount.
	 * 
	 * @return the maximum heal value
	 */
	@Override
	public int getHealingMax() {
		return myHealMax;
	}

	/**
	 * Returns a random healing amount within the allowed range.
	 * 
	 * @return a random healing value
	 */
	@Override
	public int getRandomHealing() {
		return getRandomHealing(this);
	}

	/**
	 * Heals the monster if it has a chance to recover health.
	 */
	@Override
	public void heal() {
		if (getCurHealthPoints() > 0) {
			int oldHealth = getCurHealthPoints();
			heal(this);
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
				getHealChance());
	}
}
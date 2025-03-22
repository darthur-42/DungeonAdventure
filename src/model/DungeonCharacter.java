/*
 * TCSS 360 Group Project
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Random;

/**
 * Abstract superclass for heroes and monsters, representing a character in the dungeon.
 * 
 * Defines shared behavior such as health, damage, speed, hit chance, and combat.
 * Supports property change events to notify listeners of damage, healing, and health updates.
 *
 * @author Justin Le, Anna Brewer
 * @version 21 Mar 2025
 */
public abstract class DungeonCharacter implements Serializable {

	/** Unique identifier for serialization. */
	private static final long serialVersionUID = 900627586203518407L;

	/** The maximum limit of chance-based values. */
	protected static final double CHANCE_MAX_LIMIT = 1.0;

	/** Random instance used to generate random numbers. */
	protected final Random myRandom;

	/** The name of the DungeonCharacter. */
	private String myName;

	/** The current health points of the DungeonCharacter. */
	private int myCurHealthPoints;

	/** The maximum health points of the DungeonCharacter. */
	private int myMaxHealthPoints;

	/** The minimum damage of the DungeonCharacter. */
	private int myDamageMin;

	/** The maximum damage of the DungeonCharacter. */
	private int myDamageMax;

	/** The attack speed of the DungeonCharacter. */
	private int myAttackSpeed;

	/** The hit chance of the DungeonCharacter. */
	private double myHitChance;

	/** PropertyChangeSupport for handling event-based updates. */
	protected final PropertyChangeSupport myChanges = new PropertyChangeSupport(this); 

	/**
	 * Constructs a DungeonCharacter with a name, health points, a damage range, an attack speed,
	 * and a hit chance. Can pass in a random instance for testing.
	 * 
	 * @param theName the name
	 * @param theHealthPoints the health points
	 * @param theDamageMin the minimum damage
	 * @param theDamageMax the maximum damage
	 * @param theAttackSpeed the attack speed
	 * @param theHitChance the hit chance
	 * @param theRandomInstance the random instance
	 */
	public DungeonCharacter(final String theName, final int theHealthPoints, final int theDamageMin,
			final int theDamageMax, final int theAttackSpeed, final double theHitChance,
			final Random theRandomInstance) {
		myRandom = theRandomInstance;
		setName(theName);
		setHealthPoints(theHealthPoints);
		setDamageRange(theDamageMin, theDamageMax);
		setAttackSpeed(theAttackSpeed);
		setHitChance(theHitChance);
	}

	/**
	 * Adds a listener for property change events.
	 * 
	 * @param theListener the listener to add
	 */
	public void addPropertyChangeListener(final PropertyChangeListener theListener) {
		myChanges.addPropertyChangeListener(theListener);
	}

	/**
	 * Removes a listener for property change events.
	 * 
	 * @param theListener the listener to remove
	 */
	public void removePropertyChangeListener(final PropertyChangeListener theListener) {
		myChanges.removePropertyChangeListener(theListener);
	}

	/**
	 * Returns the DungeonCharacter's name.
	 * 
	 * @return the DungeonCharacter's name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Sets the DungeonCharacter's name.
	 * 
	 * @param newName new name
	 */
	public void setName(final String newName) {
		int nameMaxLength = 20;

		if (newName == null || newName.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (newName.length() > nameMaxLength) {
			throw new IllegalArgumentException("Name cannot exceed " + nameMaxLength
					+ " characters.");
		}

		myName = newName;
	}

	/**
	 * Returns the DungeonCharacter's current health points.
	 * 
	 * @return the DungeonCharacter's current health points
	 */
	public int getCurHealthPoints() {
		return myCurHealthPoints;
	}

	/**
	 * Returns the DungeonCharacter's maximum health points.
	 * 
	 * @return the DungeonCharacter's maximum health points
	 */
	public int getMaxHealthPoints() {
		return myMaxHealthPoints;
	}

	/**
	 * Returns whether the DungeonCharacter is alive or not.
	 * 
	 * @return whether the DungeonCharacter is alive or not
	 */
	public boolean isAlive() {
		return myCurHealthPoints > 0;
	}

	/**
	 * Returns whether the DungeonCharacter is at full health or not.
	 * 
	 * @return whether the DungeonCharacter is at full health or not
	 */
	public boolean isFullHealth() {
		return myCurHealthPoints == myMaxHealthPoints;
	}

	/**
	 * Sets the DungeonCharacter's current health points; should only be used during testing.
	 * 
	 * @param theHealthPoints the new health point value
	 */
	private void setHealthPoints(final int theHealthPoints) {
		int healthMaxLimit = 999;

		if (theHealthPoints <= 0) {
			throw new IllegalArgumentException("Health points cannot be zero or negative.");
		}

		myMaxHealthPoints = Math.min(theHealthPoints, healthMaxLimit);
		myCurHealthPoints = myMaxHealthPoints;
	}

	/**
	 * Sets the DungeonCharacter's current health points; should only be used during testing.
	 * 
	 * @param newHealthPoints new health point value
	 */
	public void setCurHealthPoints(final int newHealthPoints) {
		if (newHealthPoints < 0) {
			throw new IllegalArgumentException("Health points cannot be negative.");
		}

		myCurHealthPoints = Math.min(newHealthPoints, myMaxHealthPoints);
	}

	/**
	 * Updates the DungeonCharacter's current health points to a new value based on a change in
	 * health points.
	 * 
	 * @param deltaHealthPoints change in health points
	 */
	private void updateCurHealthPoints(final int deltaHealthPoints) {
		int newHealthPoints = getCurHealthPoints() + deltaHealthPoints;
		myCurHealthPoints = Math.min(Math.max(newHealthPoints, 0), getMaxHealthPoints());
	}

	/**
	 * Returns the DungeonCharacter's minimum damage.
	 * 
	 * @return the DungeonCharacter's minimum damage
	 */
	public int getDamageMin() {
		return myDamageMin;
	}

	/**
	 * Returns the DungeonCharacter's maximum damage.
	 * 
	 * @return the DungeonCharacter's maximum damage
	 */
	public int getDamageMax() {
		return myDamageMax;
	}

	/**
	 * Sets the DungeonCharacter's damage range; only used during construction.
	 * 
	 * @param theDamageMin the new minimum damage
	 * @param theDamageMax the new maximum damage
	 */
	private void setDamageRange(final int theDamageMin, final int theDamageMax) {
		int damageMaxLimit = 999;

		if (theDamageMin <= 0) {
			throw new IllegalArgumentException("Minimum damage cannot be zero or negative.");
		}
		if (theDamageMax <= theDamageMin) {
			throw new IllegalArgumentException("Maximum damage cannot be less than minimum "
					+ "damage.");
		}

		myDamageMin = Math.min(theDamageMin, damageMaxLimit);
		myDamageMax = Math.min(theDamageMax, damageMaxLimit);
	}

	/**
	 * Returns a random number in the DungeonCharacter's damage range.
	 * 
	 * @return a random number in the DungeonCharacter's damage range
	 */
	public int getRandomDamage() {
		return myRandom.nextInt(getDamageMin(), getDamageMax() + 1);
	}

	/**
	 * Returns the DungeonCharacter's attack speed.
	 * 
	 * @return the DungeonCharacter's attack speed
	 */
	public int getAttackSpeed() {
		return myAttackSpeed;
	}

	/**
	 * Sets the DungeonCharacter's attack speed; only used during construction.
	 * 
	 * @param newAttackSpeed new attack speed
	 */
	private void setAttackSpeed(final int newAttackSpeed) {
		int attackSpeedMaxLimit = 99;

		if (newAttackSpeed <= 0) {
			throw new IllegalArgumentException("Attack speed cannot be zero or negative.");
		}

		myAttackSpeed = Math.min(newAttackSpeed, attackSpeedMaxLimit);
	}

	/**
	 * Returns the DungeonCharacter's hit chance.
	 * 
	 * @return the DungeonCharacter's hit chance
	 */
	public double getHitChance() {
		return myHitChance;
	}

	/**
	 * Sets the DungeonCharacter's hit chance; only used during construction.
	 * 
	 * @param newHitChance new hit chance
	 */
	private void setHitChance(final double newHitChance) {
		if (newHitChance <= 0.0) {
			throw new IllegalArgumentException("Hit chance cannot be zero or negative.");
		}

		myHitChance = Math.min(newHitChance, CHANCE_MAX_LIMIT);
	}

	/**
	 * Receives damage and fires property change events.
	 * 
	 * @param theDamageAmount the amount of damage received
	 */
	protected void receiveDamage(final int theDamageAmount) {
		int oldHealth = getCurHealthPoints();
		updateCurHealthPoints(-theDamageAmount);
		myChanges.firePropertyChange("health", oldHealth, getCurHealthPoints());
		myChanges.firePropertyChange("damageTaken", null, theDamageAmount);
	}

	/**
	 * Receives healing and fires property change events.
	 * 
	 * @param theHealingAmount the amount of healing received
	 */
	protected void receiveHealing(final int theHealingAmount) {
		int oldHealth = getCurHealthPoints();
		updateCurHealthPoints(theHealingAmount);
		myChanges.firePropertyChange("health", oldHealth, getCurHealthPoints());
		myChanges.firePropertyChange("healingReceived", null, theHealingAmount);
	}

	/**
	 * Performs an attack on another DungeonCharacter.
	 * 
	 * @param theOtherCharacter the target DungeonCharacter
	 */
	public void attack(final DungeonCharacter theOtherCharacter) {
		double hitChance = getHitChance();
		double hitRequirement = myRandom.nextDouble(0.0, 1.0);

		if (hitChance >= hitRequirement) {
			theOtherCharacter.receiveDamage(getRandomDamage());
		}
	}

	/**
	 * Returns a string representation of the DungeonCharacter.
	 * 
	 * @return a string containing the DungeonCharacter's name, health, damage range, speed,
	 *         and hit chance
	 */
	@Override
	public String toString() {
		String result = "";

		result += getName() + "\n";
		result += "HP:  " + getCurHealthPoints() + "/" + getMaxHealthPoints() + "\n";
		result += "DMG: " + getDamageMin() + "-" + getDamageMax() + "\n";
		result += "SPD: " + getAttackSpeed() + "\n";
		result += "ACC: " + String.format("%.2f%%", getHitChance() * 100);

		return result;
	}
}

/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * This abstract class represents a generic entity within the Dungeon, with common behaviors that
 * subclasses must implement.
 * 
 * @author Justin Le
 * @version 3 Mar 2025
 */
public abstract class DungeonCharacter {
	
	/** The maximum limit of chance-based values. */
	protected static final double CHANCE_MAX_LIMIT = 1.0;
	
	/** Random instance used to generate random numbers. */
	protected Random myRandom;
	
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
	
	/** The current room the DungeonCharacter is in. */
	private Room myCurRoom;
	
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
	 * @param theRandom the random instance
	 */
	public DungeonCharacter(final String theName, final int theHealthPoints, final int theDamageMin,
			final int theDamageMax, final int theAttackSpeed, final double theHitChance,
			final Random theRandom) {
		myRandom = theRandom;
		setName(theName);
		setHealthPoints(theHealthPoints);
		setDamageRange(theDamageMin, theDamageMax);
		setAttackSpeed(theAttackSpeed);
		setHitChance(theHitChance);
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
	 * Sets the DungeonCharacter's name; only used during construction.
	 * 
	 * @param newName new name
	 */
	private void setName(final String newName) {
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
	 * Sets the DungeonCharacter's current and maximum health points; only used during construction.
	 * 
	 * @param newHealthPoints new health point value
	 */
	private void setHealthPoints(final int newHealthPoints) {
		int healthMaxLimit = 999;
		
		if (newHealthPoints <= 0) {
			throw new IllegalArgumentException("Health points cannot be zero or negative.");
		}
		
		myMaxHealthPoints = Math.min(newHealthPoints, healthMaxLimit);
		myCurHealthPoints = myMaxHealthPoints;
	}
	
	/**
	 * Sets the DungeonCharacter's current health points; should only be used during testing.
	 * 
	 * @param newHealthPoints new health point value
	 */
	public void setCurHealthPoints(final int newHealthPoints) {
		if (newHealthPoints <= 0) {
			throw new IllegalArgumentException("Health points cannot be zero or negative.");
		}
		
		myCurHealthPoints = Math.min(newHealthPoints, myMaxHealthPoints);
	}
	
	/**
	 * Updates the DungeonCharacter's current health points to a new value based on a change in
	 * health points.
	 * 
	 * @param deltaHealthPoints change in health points
	 */
	public void updateCurHealthPoints(final int deltaHealthPoints) {
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
	 * @param newDamageMin new minimum damage
	 * @param newDamageMax new maximum damage
	 */
	private void setDamageRange(final int newDamageMin, final int newDamageMax) {
		int damageMaxLimit = 999;
		
		if (newDamageMin <= 0) {
			throw new IllegalArgumentException("Minimum damage cannot be zero or negative.");
		}
		if (newDamageMax <= newDamageMin) {
			throw new IllegalArgumentException("Maximum damage cannot be less than minimum "
					+ "damage.");
		}
		
		myDamageMin = Math.min(newDamageMin, damageMaxLimit);
		myDamageMax = Math.min(newDamageMax, damageMaxLimit);
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
	 * Returns the room the character is currently in.
	 * 
	 * @return the room the character is currently in
	 */
	public Room getCurRoom() {
		return myCurRoom;
	}
	
	/**
	 * Perform an attack on another DungeonCharacter.
	 * 
	 * @param otherCharacter the other DungeonCharacter
	 */
	public void attack(final DungeonCharacter otherCharacter) {
		double hitRequirement = myRandom.nextDouble(0, 1);
		
		if (getHitChance() >= hitRequirement) {
			otherCharacter.receiveDamage(getRandomDamage());
		}
	}
	
	/**
	 * Receive an amount of damage and update current health.
	 * 
	 * @param theDamageAmount amount of damage received
	 */
	public void receiveDamage(final int theDamageAmount) {
		updateCurHealthPoints(-theDamageAmount);
	}
	
}

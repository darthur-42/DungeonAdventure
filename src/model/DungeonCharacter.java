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
 * @version 19 Feb 2025
 */
public abstract class DungeonCharacter {
	
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
	public DungeonCharacter(String theName, int theHealthPoints, int theDamageMin,
			int theDamageMax, int theAttackSpeed, double theHitChance, Random theRandom) {
		myRandom = theRandom;
		myName = theName;
		myCurHealthPoints = theHealthPoints;
		myMaxHealthPoints = theHealthPoints;
		myDamageMin = theDamageMin;
		myDamageMax = theDamageMax;
		myAttackSpeed = theAttackSpeed;
		myHitChance = theHitChance;
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
	 * Returns the DungeonCharacter's current health points.
	 * 
	 * @return the DungeonCharacter's current health points
	 */
	public int getCurHealthPoints() {
		return myCurHealthPoints;
	}
	
	/**
	 * Sets the DungeonCharacter's health points to a new value.
	 * 
	 * @param newHealthPoints new health value
	 */
	public void setCurHealthPoints(int newHealthPoints) {
		myCurHealthPoints = Math.min(Math.max(newHealthPoints, 0), getMaxHealthPoints());
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
	 * Returns the DungeonCharacter's hit chance.
	 * 
	 * @return the DungeonCharacter's hit chance
	 */
	public double getHitChance() {
		return myHitChance;
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
	public void attack(DungeonCharacter otherCharacter) {
		double hitCheck = myRandom.nextDouble(0, getHitChance());
		double hitRequirement = myRandom.nextDouble(0, 1);
		
		if (hitCheck >= hitRequirement) {
			if (otherCharacter instanceof Hero) {
				double blockCheck = myRandom.nextDouble(0, ((Hero) otherCharacter).getBlockChance());
				double blockRequirement = myRandom.nextDouble(0, 1);
				
				// When target succeeds block check, skip damage operation
				if (blockCheck >= blockRequirement) {
					return;
				}
			}
			
			int otherNewHealthPoints = otherCharacter.getCurHealthPoints() - getRandomDamage();
			
			otherCharacter.setCurHealthPoints(otherNewHealthPoints);
		}
	}
	
}

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
 * @version 11 Feb 2025
 */
public abstract class DungeonCharacter {
	
	protected Random random = new Random();
	
	private String myName;
	private int myHealthPoints;
	private int myDamageMin;
	private int myDamageMax;
	private int myAttackSpeed;
	private double myHitChance;
	
	protected DungeonCharacter(String theName, int theHealthPoints, int theDamageMin,
			int theDamageMax, int theAttackSpeed, double theHitChance) {
		myName = theName;
		myHealthPoints = theHealthPoints;
		myDamageMin = theDamageMin;
		myDamageMax = theDamageMax;
		myAttackSpeed = theAttackSpeed;
		myHitChance = theHitChance;
	}
	
	/**
	 * Returns the character's name.
	 * 
	 * @return the character's name
	 */
	protected String getName() {
		return myName;
	}
	
	/**
	 * Returns the character's health points.
	 * 
	 * @return the character's health points
	 */
	protected int getHealthPoints() {
		return myHealthPoints;
	}
	
	/**
	 * Sets the character's health points to a new value.
	 * 
	 * @param newHealthPoints New health value
	 */
	protected void setHealthPoints(int newHealthPoints) {
		myHealthPoints = newHealthPoints;
	}
	
	/**
	 * Returns the character's minimum damage.
	 * 
	 * @return the character's minimum damage
	 */
	protected int getDamageMin() {
		return myDamageMin;
	}
	
	/**
	 * Returns the character's maximum damage.
	 * 
	 * @return the character's maximum damage
	 */
	protected int getDamageMax() {
		return myDamageMax;
	}
	
	/**
	 * Returns a random number in the character's damage range.
	 * 
	 * @return a random number in the character's damage range
	 */
	protected int getRandomDamage() {
		return random.nextInt(getDamageMin(), getDamageMax() + 1);
	}
	
	/**
	 * Returns the character's attack speed.
	 * 
	 * @return Returns the character's attack speed
	 */
	protected int getAttackSpeed() {
		return myAttackSpeed;
	}
	
	/**
	 * Returns the character's hit chance.
	 * 
	 * @return the character's hit chance
	 */
	protected double getHitChance() {
		return myHitChance;
	}
	
	/**
	 * Perform an attack on another DungeonCharacter.
	 * 
	 * @param otherCharacter the other DungeonCharacter
	 */
	protected void attack(DungeonCharacter otherCharacter) {
		double thisHitChance = random.nextDouble(0, getHitChance());
		double thisHitRequirement = random.nextDouble(0, 1);
		
		if (thisHitChance >= thisHitRequirement) {
			int otherNewHealthPoints = otherCharacter.getHealthPoints() - getRandomDamage();
			
			otherCharacter.setHealthPoints(otherNewHealthPoints);
		}
	}
	
}

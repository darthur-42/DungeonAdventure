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
 * @version 18 Feb 2025
 */
public abstract class DungeonCharacter {
	
	/** Random object used to generate random numbers. */
	protected Random random = new Random();
	
	/** The name of the DungeonCharacter. */
	private String myName;
	
	/** The health points of the DungeonCharacter. */
	private int myHealthPoints;
	
	/** The minimum damage of the DungeonCharacter. */
	private int myDamageMin;
	
	/** The maximum damage of the DungeonCharacter. */
	private int myDamageMax;
	
	/** The attack speed of the DungeonCharacter. */
	private int myAttackSpeed;
	
	/** The hit chance of the DungeonCharacter. */
	private double myHitChance;
	
	/**
	 * Constructs a DungeonCharacter with a name, health points, a damage range, an attack speed,
	 * and a hit chance.
	 * 
	 * @param theName the name
	 * @param theHealthPoints the health points
	 * @param theDamageMin the minimum damage
	 * @param theDamageMax the maximum damage
	 * @param theAttackSpeed the attack speed
	 * @param theHitChance the hit chance
	 */
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
	 * @param newHealthPoints new health value
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
		double thisHitCheck = random.nextDouble(0, getHitChance());
		double thisHitRequirement = random.nextDouble(0, 1);
		
		if (thisHitCheck >= thisHitRequirement) {
			if (otherCharacter instanceof Hero) {
				double thisBlockCheck = random.nextDouble(0, ((Hero) otherCharacter).getBlockChance());
				double thisBlockRequirement = random.nextDouble(0, 1);
				
				if (thisBlockCheck < thisBlockRequirement) {  // When target fails block check
					return;
				}
			}
			
			int otherNewHealthPoints = otherCharacter.getHealthPoints() - getRandomDamage();
			
			otherCharacter.setHealthPoints(otherNewHealthPoints);
		}
	}
	
}

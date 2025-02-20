/**
 * TCSS 360 Group Project
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This abstract class represents a generic playable entity within the Dungeon, with common
 * behaviors that subclasses must implement.
 * 
 * @author Justin Le
 * @version 19 Feb 2025
 */
public abstract class Hero extends DungeonCharacter {
	
	/** The starting number of potions for the Hero. */
	private static final int STARTING_NUM_POTIONS = 3;
	
	/** The block chance of the Hero. */
	private double myBlockChance;
	
	/** The number of healing potions the Hero has. */
	private int myNumHealingPotions;
	
	/** The number of vision potions the Hero has. */
	private int myNumVisionPotions;
	
	/** The list of OO pillars the Hero has collected. */
	private List<String> myCollectedPillars;
	
	/**
	 * Constructs a Hero with a name, health points, a damage range, an attack speed, a hit chance,
	 * and a block chance. Can pass in a random instance for testing.
	 * 
	 * @param theName the name
	 * @param theHealthPoints the health points
	 * @param theDamageMin the minimum damage
	 * @param theDamageMax the maximum damage
	 * @param theAttackSpeed the attack speed
	 * @param theHitChance the hit chance
	 * @param theBlockChance the block chance
	 * @param theRandom the random instance
	 */
	public Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax,
			int theAttackSpeed, double theHitChance, double theBlockChance, Random theRandom) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
				theRandom);
		
		myBlockChance = theBlockChance;
		myNumHealingPotions = STARTING_NUM_POTIONS;
		myNumVisionPotions = STARTING_NUM_POTIONS;
		myCollectedPillars = new ArrayList<String>();
	}
	
	/**
	 * Returns the Hero's block chance.
	 * 
	 * @return the Hero's block chance
	 */
	public double getBlockChance() {
		return myBlockChance;
	}
	
	/**
	 * Returns the number of healing potions the Hero has.
	 * 
	 * @return the number of healing potions the Hero has
	 */
	public int getNumHealingPotions() {
		return myNumHealingPotions;
	}
	
	/**
	 * Use a healing potion to heal the Hero.
	 */
	public void useHealingPotion() {
		if (myNumHealingPotions > 0) {
			myNumHealingPotions--;
			setCurHealthPoints(getCurHealthPoints() + myRandom.nextInt(50, 101));
		}
	}
	
	/**
	 * Returns the number of vision potions the Hero has.
	 * 
	 * @return the number of vision potions the Hero has
	 */
	public int getNumVisionPotions() {
		return myNumVisionPotions;
	}
	
	/**
	 * Use a vision potion to gain vision of surrounding Rooms for the Hero to see.
	 */
	public void useVisionPotion() {
		if (myNumVisionPotions > 0) {
			myNumVisionPotions--;
			// Maybe do stuff here?
		}
	}
	
	/**
	 * Returns the list of collected OO pillars the Hero has.
	 * 
	 * @return the list of collected OO pillars the Hero has
	 */
	public List<String> getCollectedPillars() {
		return myCollectedPillars;
	}
	
	/**
	 * Adds an OO pillar to the list of collected OO pillars the Hero has.
	 * 
	 * @param thePillar the OO pillar
	 */
	public void collectPillar(String thePillar) {
		myCollectedPillars.add(thePillar);
	}
	
	/**
	 * Perform a special attack on another DungeonCharacter.
	 * 
	 * @param otherCharacter the other DungeonCharacter
	 */
	public abstract void specialAttack(DungeonCharacter otherCharacter);
}

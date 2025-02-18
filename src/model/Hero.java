/**
 * TCSS 360 Group Project
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class represents a generic playable entity within the Dungeon, with common behaviors
 * that subclasses must implement.
 * 
 * @author Justin Le
 * @version 18 Feb 2025
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
	
	/** The number of OO pillars the Hero has collected. */
	private List<String> myPillarsCollected;
	
	/** The current room the Hero is in. */
	private Room myCurrentRoom;
	
	/**
	 * Constructs a Hero with a name, health points, a damage range, an attack speed, a hit chance,
	 * and a block chance.
	 * 
	 * @param theName the name
	 * @param theHealthPoints the health points
	 * @param theDamageMin the minimum damage
	 * @param theDamageMax the maximum damage
	 * @param theAttackSpeed the attack speed
	 * @param theHitChance the hit chance
	 * @param theBlockChance the block chance
	 */
	protected Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax,
			int theAttackSpeed, double theHitChance, double theBlockChance) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance);
		
		myBlockChance = theBlockChance;
		myNumHealingPotions = STARTING_NUM_POTIONS;
		myNumVisionPotions = STARTING_NUM_POTIONS;
		myPillarsCollected = new ArrayList<String>();
	}
	
	/**
	 * Returns the block chance.
	 * 
	 * @return the block chance
	 */
	protected double getBlockChance() {
		return myBlockChance;
	}
	
	/**
	 * Returns the number of healing potions.
	 * 
	 * @return the number of healing potions
	 */
	protected int getNumHealingPotions() {
		return myNumHealingPotions;
	}
	
	/**
	 * Use a healing potion to heal.
	 */
	protected void useHealingPotion() {
		myNumHealingPotions--;
		setHealthPoints(getHealthPoints() + random.nextInt(50, 101));
	}
	
	/**
	 * Returns the number of vision potions.
	 * 
	 * @return the number of vision potions
	 */
	protected int getNumVisionPotions() {
		return myNumVisionPotions;
	}
	
	/**
	 * Use a vision potion to gain vision of surrounding Rooms.
	 */
	protected void useVisionPotion() {
		myNumVisionPotions--;
		// Maybe do stuff here?
	}
	
	/**
	 * Adds an OO pillar to their pillars collected.
	 * 
	 * @param thePillar the OO pillar
	 */
	protected void collectPillar(String thePillar) {
		myPillarsCollected.add(thePillar);
	}
	
	/**
	 * Perform a special attack on another DungeonCharacter.
	 * 
	 * @param otherCharacter the other DungeonCharacter
	 */
	protected abstract void specialAttack(DungeonCharacter otherCharacter);
}

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
 * @version 4 Mar 2025
 */
public abstract class Hero extends DungeonCharacter {
	
	/** The starting number of potions for the Hero. */
	private static final int STARTING_NUM_POTIONS = 3;
	
	/** The number of potions for the Hero. */
	private static final int NUM_POTIONS_MAX_LIMIT = 99;
	
	/** The minimum amount of healing a healing potion does. */
	private static final int POTION_HEALING_MIN = 50;
	
	/** The maximum amount of healing a healing potion does. */
	private static final int POTION_HEALING_MAX = 100;
	
	/** The block chance of the Hero. */
	private double myBlockChance;
	
	/** The number of healing potions the Hero has. */
	private int myNumHealingPotions;
	
	/** The number of vision potions the Hero has. */
	private int myNumVisionPotions;
	
	/** The list of OO pillars the Hero has collected. */
	private List<PillarOO> myCollectedPillars;
	
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
	 * @param theRandomInstance the random instance
	 */
	public Hero(final String theName, final int theHealthPoints, final int theDamageMin,
			final int theDamageMax, final int theAttackSpeed, final double theHitChance,
			final double theBlockChance, final Random theRandomInstance) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
				theRandomInstance);
		
		setBlockChance(theBlockChance);
		myNumHealingPotions = STARTING_NUM_POTIONS;
		myNumVisionPotions = STARTING_NUM_POTIONS;
		myCollectedPillars = new ArrayList<PillarOO>();
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
	 * Sets the Hero's block chance.
	 * 
	 * @param newBlockChance new block chance
	 */
	private void setBlockChance(final double newBlockChance) {
		if (newBlockChance < 0.0) {
			throw new IllegalArgumentException("Block chance cannot be negative.");
		}
		
		myBlockChance = Math.min(newBlockChance, CHANCE_MAX_LIMIT);
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
	 * Set the number of healing potions to a new number.
	 * 
	 * @param newNumHealingPotions new number of healing potions
	 */
	public void setNumHealingPotions(final int newNumHealingPotions) {
		if (newNumHealingPotions < 0) {
			throw new IllegalArgumentException("Number of healing potions cannot be negative.");
		}
		
		myNumHealingPotions = Math.min(newNumHealingPotions, NUM_POTIONS_MAX_LIMIT);
	}
	
	/**
	 * Collect a healing potion.
	 */
	public void collectHealingPotion() {
		myNumHealingPotions++;
	}
	
	/**
	 * Use a healing potion to heal the Hero.
	 */
	public void useHealingPotion() {
		if (myNumHealingPotions > 0) {
			myNumHealingPotions--;
			receiveHealing(myRandom.nextInt(POTION_HEALING_MIN, POTION_HEALING_MAX + 1));
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
	 * Set the number of vision potions to a new number.
	 * 
	 * @param newNumVisionPotions new number of vision potions
	 */
	public void setNumVisionPotions(final int newNumVisionPotions) {
		if (newNumVisionPotions < 0) {
			throw new IllegalArgumentException("Number of vision potions cannot be negative.");
		}
		
		myNumVisionPotions = Math.min(newNumVisionPotions, NUM_POTIONS_MAX_LIMIT);
	}
	
	/**
	 * Collect a vision potion.
	 */
	public void collectVisionPotion() {
		myNumVisionPotions++;
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
	public List<PillarOO> getCollectedPillars() {
		return myCollectedPillars;
	}
	
	public boolean getHasAllPillars() {
		return myCollectedPillars.contains(PillarOO.ABSTRACTION)
				&& myCollectedPillars.contains(PillarOO.ENCAPSULATION)
				&& myCollectedPillars.contains(PillarOO.INHERITANCE)
				&& myCollectedPillars.contains(PillarOO.POLYMORPHISM);
	}
	
	/**
	 * Adds an OO pillar to the list of collected OO pillars the Hero has.
	 * 
	 * @param thePillar the OO pillar
	 */
	public void collectPillar(final PillarOO thePillar) {
		myCollectedPillars.add(thePillar);
	}
	
	/**
	 * If about to receive damage, perform a block check. If the block check succeeds, receive no
	 * damage; otherwise, receive an amount of damage and update current health.
	 * 
	 * @param theDamageAmount amount of damage
	 */
	protected void receiveDamage(final int theDamageAmount) {
		double blockRequirement = myRandom.nextDouble(0.0, 1.0);
		
		// When target fails block check, perform damage operation
		if (getBlockChance() < blockRequirement) {
			super.receiveDamage(theDamageAmount);
		}
	}
	
	/**
	 * Perform a special attack on another DungeonCharacter.
	 * 
	 * @param otherCharacter the other DungeonCharacter
	 */
	public abstract void specialAttack(final DungeonCharacter otherCharacter);
}

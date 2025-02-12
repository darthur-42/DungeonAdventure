/**
 * TCSS 360 Group Project
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * 
 * @author Justin Le
 * @version 11 Feb 2025
 */
public abstract class Hero extends DungeonCharacter {
	private static final int STARTING_NUM_POTIONS = 3;
	
	private double myBlockChance;
	private int myNumHealingPotions;
	private int myNumVisionPotions;
	private List<String> myPillarsCollected;
	private Room myCurrentRoom;
	
	protected Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax,
			int theAttackSpeed, double theHitChance, double theBlockChance) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance);
		
		myBlockChance = theBlockChance;
		myNumHealingPotions = STARTING_NUM_POTIONS;
		myNumVisionPotions = STARTING_NUM_POTIONS;
		myPillarsCollected = new ArrayList<String>();
	}
	
	protected double getBlockChance() {
		return myBlockChance;
	}
	
	protected boolean getAttackBlocked() {
		double thisBlockChance = random.nextDouble(0, getBlockChance());
		double thisBlockRequirement = random.nextDouble(0, 1);
		
		return thisBlockChance >= thisBlockRequirement;
	}
	
	protected int getNumHealingPotions() {
		return myNumHealingPotions;
	}
	
	protected void useHealingPotion() {
		myNumHealingPotions--;
		setHealthPoints(getHealthPoints() + random.nextInt(50, 101));
	}
	
	protected int getNumVisionPotions() {
		return myNumVisionPotions;
	}
	
	protected void useVisionPotion() {
		myNumVisionPotions--;
		// Maybe do stuff here?
	}
	
	protected void collectPillar(String thePillar) {
		myPillarsCollected.add(thePillar);
	}
	
	protected abstract void specialAttack(DungeonCharacter otherCharacter);
}

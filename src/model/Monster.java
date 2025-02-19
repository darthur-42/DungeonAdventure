/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * @author Anna Brewer
 * @version 18 Feb 2025
 */
public abstract class Monster extends DungeonCharacter {
	private int myHealMin;
	private int myHealMax;
	private double myHealChance;
	private Random myRandom = new Random();

	/**
	 * Constructs a Monster using stats retrieved from the database.
	 * 
	 * @param theName         The name of the monster.
	 * @param theHealthPoints The monster's health points.
	 * @param theDamageMin    The minimum damage the monster can deal.
	 * @param theDamageMax    The maximum damage the monster can deal.
	 * @param theAttackSpeed  The monster's attack speed.
	 * @param theHitChance    The monster's hit chance.
	 * @param theHealMin      The minimum amount the monster can heal.
	 * @param theHealMax      The maximum amount the monster can heal.
	 * @param theHealChance   The chance for the monster to heal after attacking.
	 */
	protected Monster(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
			double theHitChance, int theHealMin, int theHealMax, double theHealChance) {
		super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance);
		this.myHealMin = theHealMin;
		this.myHealMax = theHealMax;
		this.myHealChance = theHealChance;
	}

	protected int getHealMin() {
		return myHealMin;
	}

	protected int getHealMax() {
		return myHealMax;
	}

	protected double getHealChance() {
		return myHealChance;
	}

	/**
	 * Heals the monster if conditions are met.
	 */
	protected void heal() {
		if (getHealthPoints() > 0 && myRandom.nextDouble() < myHealChance) {
			int healAmount = myRandom.nextInt(myHealMin, myHealMax + 1);
			setHealthPoints(getHealthPoints() + healAmount);
			System.out.println(getName() + " healed for " + healAmount + " HP!");
		}
	}

	/**
	 * Attacks the target.
	 * 
	 * @param theTarget The character being attacked.
	 */
	protected abstract void castAttackOn(DungeonCharacter theTarget);
}

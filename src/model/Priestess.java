/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * The Priestess is a Hero that has low health, low damage, medium attack speed, medium hit chance,
 * and medium block chance. Their special attack heals themselves.
 * 
 * @author Justin Le
 * @version 3 Mar 2025
 */
public class Priestess extends Hero implements Healable {
	
	/** The minimum healing of the Priestess. */
	private int myHealingMin;
	
	/** The maximum healing of the Priestess. */
	private int myHealingMax;
	
	/** The healing chance of the Priestess. */
	private double myHealingChance;
	
	/**
	 * Constructs a Warrior.
	 */
	public Priestess() {
		this(new Random());
	}
	
	/**
	 * Constructs a Priestess. Can pass in a random instance for testing.
	 */
	public Priestess(final Random theRandomInstance) {
		super("Priestess", 75, 25, 45, 5, 0.7, 0.3, theRandomInstance);
		
		setHealingRange(50, 75);
		setHealingChance(1.0);
	}

	@Override
	public int getHealingMin() {
		return myHealingMin;
	}

	@Override
	public int getHealingMax() {
		return myHealingMax;
	}
	
	@Override
	public void setHealingRange(final int newHealingMin, final int newHealingMax) {
		int healingMaxLimit = 999;
		
		if (newHealingMin <= 0) {
			throw new IllegalArgumentException("Minimum healing cannot be zero or negative.");
		}
		if (newHealingMax <= newHealingMin) {
			throw new IllegalArgumentException("Maximum healing cannot be less than minimum "
					+ "healing.");
		}
		
		myHealingMin = Math.min(newHealingMin, healingMaxLimit);
		myHealingMax = Math.min(newHealingMax, healingMaxLimit);
	}
	
	@Override
	public int getRandomHealing() {
		return getRandomHealing(myRandom);
	}
	
	@Override
	public double getHealingChance() {
		return myHealingChance;
	}
	
	@Override
	public void setHealingChance(final double newHealingChance) {
		if (newHealingChance <= 0.0) {
			throw new IllegalArgumentException("Heal chance cannot be zero or negative.");
		}
		
		myHealingChance = Math.min(newHealingChance, CHANCE_MAX_LIMIT);
	}
	
	@Override
	public void heal() {
		heal(myRandom);
	}
	
	/**
	 * {@inheritDoc} This special attack heals the caster.
	 */
	@Override
	public void specialAttack(final DungeonCharacter otherCharacter) {
		heal();
	}
	
}

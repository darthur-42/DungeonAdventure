/**
 * 
 */
package model;

import java.util.Random;

/**
 * The Berserker is a Hero that has high health, very hjgh damage, medium attack speed, high hit
 * chance,and zero block chance. Their special attack does a weaker attack and additionally heals
 * themselves slightly if it lands.
 * 
 * @author Justin Le
 * @version 11 Mar 2025
 */
public class Berserker extends Hero implements Healable {
	
	/** The minimum healing of the Berserker. */
	private int myHealingMin;
	
	/** The maximum healing of the Berserker. */
	private int myHealingMax;
	
	/** The healing chance of the Berserker. */
	private double myHealingChance;
	
	/**
	 * Constructs a Berserker.
	 */
	public Berserker() {
		this(new Random());
	}
	
	/**
	 * Constructs a Berserker. Can pass in a random instance for testing.
	 */
	public Berserker(final Random theRandomInstance) {
		super("Berserker", 125, 50, 70, 5, 0.8, 0.0, theRandomInstance);
		
		setHealingRange(25, 40);
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
	 * {@inheritDoc} This special attack does a weaker attack and, if it lands, additionally
	 * slightly heals the caster.
	 */
	@Override
	public void specialAttack(final DungeonCharacter otherCharacter) {
		double hitChance = getHitChance();
		double hitRequirement = myRandom.nextDouble(0.0, 1.0);
		
		if (hitChance >= hitRequirement) {
			int randomSpecialAttackDamage = Math.max(getRandomDamage() - 20, 0);
			
			otherCharacter.receiveDamage(randomSpecialAttackDamage);
			heal();
		}
	}

}

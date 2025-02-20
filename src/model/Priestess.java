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
 * @version 19 Feb 2025
 */
public class Priestess extends Hero {
	
	/**
	 * Constructs a Warrior.
	 */
	public Priestess() {
		this(new Random());
	}
	
	/**
	 * Constructs a Priestess. Can pass in a random instance for testing.
	 */
	public Priestess(Random theRandomInstance) {
		super("Priestess", 75, 25, 45, 5, 0.7, 0.3, theRandomInstance);
	}
	
	/**
	 * {@inheritDoc} This special attack heals the caster.
	 */
	@Override
	public void specialAttack(DungeonCharacter otherCharacter) {
		int randomSpecialSkillHealing = myRandom.nextInt(50, 76);
		int otherNewHealthPoints = getCurHealthPoints() + randomSpecialSkillHealing;
		
		setCurHealthPoints(otherNewHealthPoints);
	}
	
}

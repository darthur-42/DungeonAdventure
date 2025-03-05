/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * The Warrior is a Hero that has high health, high damage, low attack speed, high hit chance, and
 * low block chance. Their special attack has a low chance to deal even higher damage.
 * 
 * @author Justin Le
 * @version 19 Feb 2025
 */
public class Warrior extends Hero {
	
	/**
	 * Constructs a Warrior.
	 */
	public Warrior() {
		this(new Random());
	}
	
	/**
	 * Constructs a Warrior. Can pass in a random instance for testing.
	 */
	public Warrior(final Random theRandomInstance) {
		super("Warrior", 125, 35, 60, 4, 0.8, 0.2, theRandomInstance);
	}
	
	/**
	 * {@inheritDoc} This special attack has a low chance to deal even higher damage.
	 */
	@Override
	public void specialAttack(final DungeonCharacter otherCharacter) {
		double hitCheck = myRandom.nextDouble(0, 0.4);
		double hitRequirement = myRandom.nextDouble(0, 1);
		
		if (hitCheck >= hitRequirement) {
			int randomSpecialSkillDamage = myRandom.nextInt(75, 176);
			
			otherCharacter.updateCurHealthPoints(-randomSpecialSkillDamage);
		}
	}
	
}

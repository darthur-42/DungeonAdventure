/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * The Thief is a Hero that has low health, low damage, high attack speed, high hit chance, and
 * high block chance. Their special attack has a high chance to do a standard attack and a moderate
 * chance to give an additional turn.
 * 
 * @author Justin Le
 * @version 19 Feb 2025
 */
public class Thief extends Hero {
	
	/**
	 * Constructs a Thief.
	 */
	public Thief() {
		this(new Random());
	}
	
	/**
	 * Constructs a Thief. Can pass in a random instance for testing.
	 */
	public Thief(Random theRandomInstance) {
		super("Thief", 75, 20, 40, 6, 0.8, 0.4, theRandomInstance);
	}
	
	/**
	 * {@inheritDoc} This special attack has a high chance to do a normal attack, a moderate chance
	 * to give an additional turn, and a low chance to do nothing.
	 */
	@Override
	public void specialAttack(DungeonCharacter otherCharacter) {
		double hitCheck = myRandom.nextDouble(0, 1);
		
		if (hitCheck >= 0.2) {
			int otherNewHealthPoints = otherCharacter.getCurHealthPoints() - getRandomDamage();
			otherCharacter.setCurHealthPoints(otherNewHealthPoints);
			
			if (hitCheck >= 0.6) {
				// TODO Get extra turn
			}
		}
	}
	
}

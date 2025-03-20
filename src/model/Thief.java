/**
 * TCSS 360 Group Project
 */
package model;

import java.io.Serializable;
import java.util.Random;

/**
 * The Thief is a Hero that has low health, low damage, high attack speed, high hit chance, and
 * high block chance. Their special attack has a high chance to do a standard attack and a moderate
 * chance to give an additional turn.
 * 
 * @author Justin Le
 * @version 4 Mar 2025
 */
public class Thief extends Hero implements Serializable {
	
	/** Unique identifier for serialization. */
    private static final long serialVersionUID = 1L;
    
	/**
	 * Constructs a Thief.
	 */
	public Thief() {
		this(new Random());
	}
	
	/**
	 * Constructs a Thief. Can pass in a random instance for testing.
	 */
	public Thief(final Random theRandomInstance) {
		super("Thief", 75, 20, 40, 6, 0.8, 0.4, theRandomInstance);
	}
	
	/**
	 * {@inheritDoc} This special attack has a high chance to do a normal attack, a moderate chance
	 * to give an additional turn, and a low chance to do nothing.
	 */
	@Override
	public void specialAttack(final DungeonCharacter otherCharacter) {
		double hitChance = myRandom.nextDouble(0.0, 1.0);
		double hitRequirement = 0.2;
		double extraTurnRequirement = 0.6;
		
		if (hitChance >= hitRequirement) {
			otherCharacter.receiveDamage(getRandomDamage());
			
			if (hitChance >= extraTurnRequirement) {
				// TODO Get extra turn
			}
		}
	}
	
}

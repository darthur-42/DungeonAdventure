/**
 * TCSS 360 Group Project
 */
package tests.mockclasses;

import java.util.Random;

import model.DungeonCharacter;
import model.Hero;

/**
 * A mock version of Hero, designed for testing the abstract class.
 * 
 * @author Justin Le
 * @version 4 Mar 2025
 */
@SuppressWarnings("serial")
public class MockHero extends Hero {
	
	/**
	 * Constructs a MockHero.
	 * 
	 * @param theRandomInstance the random instance
	 */
	public MockHero(Random theRandomInstance) {
		super("MockHero", "Special Attack", 100, 10, 20, 5, 0.5, 0.75, theRandomInstance);
	}

	@Override
	public void specialAttack(DungeonCharacter otherCharacter,
			final int theDamageScale, final double theHitChanceScale) {
		return;
	}	
}

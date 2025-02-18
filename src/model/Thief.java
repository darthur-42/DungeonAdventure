/**
 * TCSS 360 Group Project
 */
package model;

/**
 * The Thief is a Hero that has low health, low damage, high atatck speed, high hit chance, and
 * high block chance. Their special attack has a high chance to do a standard attack and a moderate
 * chance to give an additional turn.
 * 
 * @author Justin Le
 * @version 18 Feb 2025
 */
public class Thief extends Hero {
	
	/**
	 * Constructs a Thief.
	 */
	protected Thief() {
		super("Thief", 75, 20, 40, 6, 0.8, 0.4);
	}
	
	/**
	 * {@inheritDoc} This special attack has a high chance to do a standard attack and a moderate
	 * chance to give an additional turn.
	 */
	@Override
	protected void specialAttack(DungeonCharacter otherCharacter) {
		double thisHitRoll = random.nextDouble(0, 1);
		
		if (thisHitRoll <= 0.8) {
			attack(otherCharacter);
			if (thisHitRoll <= 0.4) {
				// TODO Get extra turn
			}
		}
	}
	
}

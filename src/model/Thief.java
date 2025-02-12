/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Description
 * 
 * @author Justin Le
 * @version 11 Feb 2025
 */
public class Thief extends Hero {
	
	protected Thief() {
		super("Thief", 75, 20, 40, 6, 0.8, 0.4);
	}
	
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

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
public class Priestess extends Hero {
	
	protected Priestess() {
		super("Priestess", 75, 25, 45, 5, 0.7, 0.3);
	}
	
	@Override
	protected void specialAttack(DungeonCharacter otherCharacter) {
		// TODO Auto-generated method stub
		int randomSpecialSkillHealing = random.nextInt(50, 76);
		int otherNewHealthPoints = otherCharacter.getHealthPoints() + randomSpecialSkillHealing;
		
		otherCharacter.setHealthPoints(otherNewHealthPoints);
	}
	
}

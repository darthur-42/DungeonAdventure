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
public class Warrior extends Hero {
	
	protected Warrior() {
		super("Warrior", 125, 35, 60, 4, 0.8, 0.2);
	}

	@Override
	protected void specialAttack(DungeonCharacter otherCharacter) {
		double thisHitChance = random.nextDouble(0, 0.4);
		double thisHitRequirement = random.nextDouble(0, 1);
		
		if (thisHitChance >= thisHitRequirement) {
			int randomSpecialSkillDamage = random.nextInt(75, 176);
			int otherNewHealthPoints = otherCharacter.getHealthPoints() - randomSpecialSkillDamage;
			
			otherCharacter.setHealthPoints(otherNewHealthPoints);
		}
	}
	
}

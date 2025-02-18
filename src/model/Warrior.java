/**
 * TCSS 360 Group Project
 */
package model;

/**
 * The Warrior is a Hero that has high health, high damage, low atatck speed, high hit chance, and
 * low block chance. Their special attack has a low chance to deal even higher damage.
 * 
 * @author Justin Le
 * @version 18 Feb 2025
 */
public class Warrior extends Hero {
	
	/**
	 * Constructs a Warrior.
	 */
	protected Warrior() {
		super("Warrior", 125, 35, 60, 4, 0.8, 0.2);
	}
	
	/**
	 * {@inheritDoc} This special attack has a low chance to deal even higher damage.
	 */
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

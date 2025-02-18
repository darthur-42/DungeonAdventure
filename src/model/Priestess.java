/**
 * TCSS 360 Group Project
 */
package model;

/**
 * The Priestess is a Hero that has low health, low damage, medium atatck speed, medium hit chance,
 * and medium block chance. Their special attack heals themselves.
 * 
 * @author Justin Le
 * @version 18 Feb 2025
 */
public class Priestess extends Hero {
	
	/**
	 * Constructs a Priestess.
	 */
	protected Priestess() {
		super("Priestess", 75, 25, 45, 5, 0.7, 0.3);
	}
	
	/**
	 * {@inheritDoc} This special attack heals the caster.
	 */
	@Override
	protected void specialAttack(DungeonCharacter otherCharacter) {
		int randomSpecialSkillHealing = random.nextInt(50, 76);
		int otherNewHealthPoints = getHealthPoints() + randomSpecialSkillHealing;
		
		setHealthPoints(otherNewHealthPoints);
	}
	
}

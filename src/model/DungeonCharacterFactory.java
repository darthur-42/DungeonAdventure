/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Factory for creating DungeonCharacter objects.
 * 
 * @author Anna Brewer, Justin Le
 * @version 11 Mar 2025
 */
public class DungeonCharacterFactory {
	
	/**
	 * Returns a Hero with its stats.
	 *
	 * @param theHeroType the Hero type
	 * 
	 * @return the Hero
	 * @throws IllegalArgumentException if the HeroType is invalid
	 */
	public static DungeonCharacter createDungeonCharacter(final DungeonCharacterType theHeroType) {
		return switch (theHeroType) {
			case WARRIOR -> new Warrior();
			case PRIESTESS -> new Priestess();
			case THIEF -> new Thief();
			default -> throw new IllegalArgumentException("Invalid Hero type.");  
		};
	}
	
	/**
	 * Returns a Monster with its stats.
	 *
	 * @param theMonsterType the Monster type
	 * @param theHealthPoints the Monster health points
	 * @param theDamageMin the Monster minimum damage
	 * @param theDamageMax the Monster maximum damage
	 * @param theAttackSpeed the Monster attack speed
	 * @param theHitChance the Monster hit chance
	 * @param theHealingMin the Monster minimum healing
	 * @param theHealingMax the Monster maximum healing
	 * @param theHealingChance the Monster heal chance
	 * @param theRandom the random instance
	 * 
	 * @return the Monster
	 * @throws IllegalArgumentException if the MonsterType is invalid
	 */
	public static DungeonCharacter createDungeonCharacter(final DungeonCharacterType theMonsterType,
			final int theHealthPoints, final int theDamageMin, final int theDamageMax,
			final int theAttackSpeed, final double theHitChance, final int theHealingMin,
			final int theHealingMax, final double theHealingChance, final Random theRandom) {
		return switch (theMonsterType) {
			case GREMLIN -> new Gremlin(theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
										theHealingMin, theHealingMax, theHealingChance, theRandom);
			case OGRE -> new Ogre(theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
								  theHealingMin, theHealingMax, theHealingChance, theRandom);
			case SKELETON -> new Skeleton(theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
										  theHealingMin, theHealingMax, theHealingChance, theRandom);
			default -> throw new IllegalArgumentException("Invalid Monster type.");  
		};
	}
	
}
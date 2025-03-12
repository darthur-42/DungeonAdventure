/**
 * TCSS 360 Group Project
 */
package model;

import java.util.List;
import java.util.Random;

/**
 * Factory for creating DungeonCharacter objects.
 * 
 * @author Anna Brewer, Justin Le
 * @version 11 Mar 2025
 */
public class DungeonCharacterFactory {
	
	private MonsterDatabase myMonsterDatabase;
	private List<Object[]> myMonsterData;
	
	public DungeonCharacterFactory() {
		myMonsterDatabase = new MonsterDatabase();
		myMonsterData = myMonsterDatabase.getAllMonstersData();
	}
	
	/**
	 * Returns a Hero with its stats and special attack.
	 *
	 * @param theHeroType the Hero type
	 * 
	 * @return the Hero
	 * @throws IllegalArgumentException if the HeroType is invalid
	 */
	public DungeonCharacter createDungeonCharacter(final HeroType theHeroType) {
		return switch (theHeroType) {
			case WARRIOR -> new Warrior();
			case PRIESTESS -> new Priestess();
			case THIEF -> new Thief();
		};
	}
	
	/**
	 * Returns a Monster with its stats.
	 *
	 * @param theMonsterType the Monster type
	 * 
	 * @return the Monster
	 * @throws IllegalArgumentException if the MonsterType is invalid
	 */
	public DungeonCharacter createDungeonCharacter(final MonsterType theMonsterType) {
		Object[] curMonsterData = myMonsterData.get(theMonsterType.ordinal());
		return new Monster((String) curMonsterData[0], (int) curMonsterData[1],
				(int) curMonsterData[2], (int) curMonsterData[3], (int) curMonsterData[4],
				(double) curMonsterData[5], (int) curMonsterData[6], (int) curMonsterData[7],
				(double) curMonsterData[8], new Random());
	}
	
}
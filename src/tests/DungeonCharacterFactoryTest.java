/*
 * TCSS 360 Group Project
 */
package tests;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the DungeonCharacterFactory class.
 * 
 * @author Anna Brewer
 * @version 21 Mar 2025
 */
public class DungeonCharacterFactoryTest {

	private DungeonCharacterFactory myFactory;

	/**
	 * Sets up a DungeonCharacterFactory with a default MonsterDatabase.
	 */
	@BeforeEach
	public void setUp() {
		myFactory = new DungeonCharacterFactory();
	}

	/**
	 * Tests that a Warrior is correctly created.
	 */
	@Test
	public void testCreateWarrior() {
		DungeonCharacter hero = myFactory.createDungeonCharacter(HeroType.WARRIOR);
		assertNotNull(hero);
		assertEquals("Warrior", hero.getName());
	}

	/**
	 * Tests that a Priestess is correctly created.
	 */
	@Test
	public void testCreatePriestess() {
		DungeonCharacter hero = myFactory.createDungeonCharacter(HeroType.PRIESTESS);
		assertNotNull(hero);
		assertEquals("Priestess", hero.getName());
	}

	/**
	 * Tests that a Thief is correctly created.
	 */
	@Test
	public void testCreateThief() {
		DungeonCharacter hero = myFactory.createDungeonCharacter(HeroType.THIEF);
		assertNotNull(hero);
		assertEquals("Thief", hero.getName());
	}

	/**
	 * Tests that a Berserker is correctly created.
	 */
	@Test
	public void testCreateBerserker() {
		DungeonCharacter hero = myFactory.createDungeonCharacter(HeroType.BERSERKER);
		assertNotNull(hero);
		assertEquals("Berserker", hero.getName());
	}

	/**
	 * Tests that an invalid HeroType throws an exception.
	 */
	@Test
	public void testCreateHeroWithNullTypeThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			myFactory.createDungeonCharacter(null);
		});
	}

	/**
	 * Tests that a Monster is correctly created for each MonsterType and
	 * Difficulty.
	 */
	@Test
	public void testCreateMonsterAllTypesAndDifficulties() {
		for (MonsterType monsterType : MonsterType.values()) {
			for (Difficulty difficulty : Difficulty.values()) {
				DungeonCharacter monster = myFactory.createDungeonCharacter(monsterType, difficulty);
				assertNotNull(monster);
				assertInstanceOf(Monster.class, monster);
				assertEquals(monsterType.toString(), monster.getName().toUpperCase());
			}
		}
	}

	/**
	 * Tests that null MonsterType throws an exception.
	 */
	@Test
	public void testCreateMonsterWithNullTypeThrowsException() {
		assertThrows(NullPointerException.class, () -> {
			myFactory.createDungeonCharacter(null, Difficulty.EASY);
		});
	}

	/**
	 * Tests that null Difficulty throws an exception.
	 */
	@Test
	public void testCreateMonsterWithNullDifficultyThrowsException() {
		assertThrows(NullPointerException.class, () -> {
			myFactory.createDungeonCharacter(MonsterType.OGRE, null);
		});
	}
}
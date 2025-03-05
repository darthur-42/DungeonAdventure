/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.PillarOO;
import tests.mockclasses.MockDungeonCharacter;
import tests.mockclasses.MockHero;

/**
 * Test cases for Hero.
 * 
 * @author Justin Le
 * @version 4 Mar 2025
 */
class HeroTest {
	
	private Random myMockHeroRandom;
	private Random myMockEnemyRandom;
	private MockHero myTestHero;
	private MockDungeonCharacter myTestEnemy;
	
	/**
     * Initialize the test Hero and mock random before each test.
     */
	@BeforeEach
	void setUp() {
		myMockHeroRandom = mock(Random.class);
		myMockEnemyRandom = mock(Random.class);
		myTestHero = new MockHero(myMockHeroRandom);
		myTestEnemy = new MockDungeonCharacter(myMockEnemyRandom);
	}
	
	/**
	 * Test method for {@link model.Hero#getBlockChance()}.
	 */
	@Test
	void testGetBlockChance() {
		assertEquals(myTestHero.getBlockChance(), 0.75);
	}
	
	/**
	 * Test method for {@link model.Hero#getNumHealingPotions()}.
	 */
	@Test
	void testGetNumHealingPotions() {
		assertEquals(myTestHero.getNumHealingPotions(), 3);
	}
	
	/**
	 * Test method for {@link model.Hero#useHealingPotion()}.
	 * When the Hero's health is low.
	 */
	@Test
	void testUseHealingPotionLowHealth() {
		myTestHero.setCurHealthPoints(10);
		when(myMockHeroRandom.nextInt(50, 101)).thenReturn(50);
		
		myTestHero.useHealingPotion();
		
		assertEquals(myTestHero.getNumHealingPotions(), 2);
		assertEquals(myTestHero.getCurHealthPoints(), 60);
	}
	
	/**
	 * Test method for {@link model.Hero#useHealingPotion()}.
	 * When the Hero's health is full.
	 */
	@Test
	void testUseHealingPotionFullHealth() {
		myTestHero.useHealingPotion();
		
		assertEquals(myTestHero.getNumHealingPotions(), 2);
		assertEquals(myTestHero.getCurHealthPoints(), 100);
	}
	
	/**
	 * Test method for {@link model.Hero#getNumVisionPotions()}.
	 */
	@Test
	void testGetNumVisionPotions() {
		assertEquals(myTestHero.getNumVisionPotions(), 3);
	}
	
	/**
	 * Test method for {@link model.Hero#useVisionPotion()}.
	 */
	@Test
	void testUseVisionPotion() {
		myTestHero.useVisionPotion();
		
		assertEquals(myTestHero.getNumVisionPotions(), 2);
	}
	
	/**
	 * Test method for {@link model.Hero#getCollectedPillars()}.
	 */
	@Test
	void testGetCollectedPillars() {
		assertEquals(myTestHero.getCollectedPillars().size(), 0);
	}
	
	/**
	 * Test method for {@link model.Hero#collectPillar(java.lang.String)}.
	 */
	@Test
	void testCollectPillar() {
		myTestHero.collectPillar(PillarOO.ABSTRACTION);
		
		assertEquals(myTestHero.getCollectedPillars().getFirst(), PillarOO.ABSTRACTION);
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#attack(model.DungeonCharacter)}.
	 * When the Hero blocks an incoming attack.
	 */
	@Test
	void testAttackBlocked() {
		when(myMockHeroRandom.nextDouble(0.0, 1.0)).thenReturn(0.0);
		when(myMockEnemyRandom.nextDouble(0.0, 1.0)).thenReturn(0.0);
		
		myTestEnemy.attack(myTestHero);
		
		assertEquals(100, myTestHero.getCurHealthPoints());
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#attack(model.DungeonCharacter)}.
	 * When the Hero fails to block an incoming attack.
	 */
	@Test
	void testAttackNotBlocked() {
		when(myMockHeroRandom.nextDouble(0.0, 1.0)).thenReturn(1.0);
		when(myMockEnemyRandom.nextDouble(0.0, 1.0)).thenReturn(0.0);
		when(myMockEnemyRandom.nextInt(10, 21)).thenReturn(15);
		
		myTestEnemy.attack(myTestHero);
		
		assertEquals(85, myTestHero.getCurHealthPoints());
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
}

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
import tests.mockclasses.MockHero;

/**
 * Test cases for Hero.
 * 
 * @author Justin Le
 * @version 19 Feb 2025
 */
class HeroTest {
	
	private Random myMockRandom;
	private MockHero myTestHero;
	
	/**
     * Initialize the test Hero and mock random before each test.
     */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestHero = new MockHero(myMockRandom);
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
		when(myMockRandom.nextInt(50, 101)).thenReturn(50);
		
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
		when(myMockRandom.nextInt(50, 101)).thenReturn(100);
		
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
	
}

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

import tests.mockclasses.MockDungeonCharacter;

/**
 * Test cases for DungeonCharacter.
 * 
 * @author Justin Le
 * @version 19 Feb 2025
 */
class DungeonCharacterTest {
	
	private Random myMockRandom;
	private MockDungeonCharacter myTestDungeonCharacter;
	
	/**
     * Initialize the test DungeonCharacter and mock random before each test.
     */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestDungeonCharacter = new MockDungeonCharacter(myMockRandom);
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getName()}.
	 */
	@Test
	void testGetName() {
		assertEquals("Mock Dungeon Character", myTestDungeonCharacter.getName());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getCurHealthPoints()}.
	 */
	@Test
	void testGetCurHealthPoints() {
		assertEquals(100, myTestDungeonCharacter.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#setCurHealthPoints(int)}.
	 */
	@Test
	void testSetCurHealthPoints() {
		myTestDungeonCharacter.setCurHealthPoints(50);
		
		assertEquals(50, myTestDungeonCharacter.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getMaxHealthPoints()}.
	 */
	@Test
	void testGetMaxHealthPoints() {
		assertEquals(100, myTestDungeonCharacter.getMaxHealthPoints());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getDamageMin()}.
	 */
	@Test
	void testGetDamageMin() {
		assertEquals(10, myTestDungeonCharacter.getDamageMin());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getDamageMax()}.
	 */
	@Test
	void testGetDamageMax() {
		assertEquals(20, myTestDungeonCharacter.getDamageMax());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getRandomDamage()}.
	 */
	@Test
	void testGetRandomDamage() {
		when(myMockRandom.nextInt(10, 21)).thenReturn(15);
		
		assertEquals(15, myTestDungeonCharacter.getRandomDamage());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getAttackSpeed()}.
	 */
	@Test
	void testGetAttackSpeed() {
		assertEquals(5, myTestDungeonCharacter.getAttackSpeed());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getHitChance()}.
	 */
	@Test
	void testGetHitChance() {
		assertEquals(5, myTestDungeonCharacter.getAttackSpeed());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getCurRoom()}.
	 */
	@Test
	void testGetCurRoom() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#attack(model.DungeonCharacter)}.
	 */
	@Test
	void testAttack() {
		MockDungeonCharacter testEnemy = new MockDungeonCharacter(myMockRandom);
		when(myMockRandom.nextInt(10, 21)).thenReturn(15);
		
		myTestDungeonCharacter.attack(testEnemy);
		
		assertEquals(100, myTestDungeonCharacter.getCurHealthPoints());
		assertEquals(85, testEnemy.getCurHealthPoints());
	}
	
}

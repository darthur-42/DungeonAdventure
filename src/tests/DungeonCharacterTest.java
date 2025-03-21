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
 * @version 4 Mar 2025
 */
class DungeonCharacterTest {
	
	private Random myMockRandom;
	private MockDungeonCharacter myTestDungeonCharacter;
	private MockDungeonCharacter myTestEnemy;
	
	/**
     * Initialize the test DungeonCharacter and mock random before each test.
     */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestDungeonCharacter = new MockDungeonCharacter(myMockRandom);
		myTestEnemy = new MockDungeonCharacter(myMockRandom);
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#getName()}.
	 */
	@Test
	void testGetName() {
		assertEquals("MockDngnChar", myTestDungeonCharacter.getName());
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
	 * Test method for {@link model.DungeonCharacter#attack(model.DungeonCharacter)}.
	 * When the DungeonCharacter lands an attack.
	 */
	@Test
	void testAttackHit() {
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(0.0);
		when(myMockRandom.nextInt(10, 21)).thenReturn(15);
		
		myTestDungeonCharacter.attack(myTestEnemy);
		
		assertEquals(100, myTestDungeonCharacter.getCurHealthPoints());
		assertEquals(85, myTestEnemy.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.DungeonCharacter#attack(model.DungeonCharacter)}.
	 * When the DungeonCharacter fails to land an attack.
	 */
	@Test
	void testAttackMiss() {
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(1.0);
		
		myTestDungeonCharacter.attack(myTestEnemy);
		
		assertEquals(100, myTestDungeonCharacter.getCurHealthPoints());
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
}

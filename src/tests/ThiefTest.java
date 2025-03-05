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

import model.Thief;
import tests.mockclasses.MockDungeonCharacter;

/**
 * Test cases for Thief.
 * 
 * @author Justin Le
 * @version 4 Mar 2025
 */
class ThiefTest {
	
	private Random myMockRandom;
	private Thief myTestThief;
	private MockDungeonCharacter myTestEnemy;
	
	/**
     * Initialize the test Priestess and mock random before each test.
     */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestThief = new Thief(myMockRandom);
		myTestEnemy = new MockDungeonCharacter(myMockRandom);
	}
	
	/**
	 * Test method for {@link model.Thief#specialAttack(model.DungeonCharacter)}.
	 * When the Thief does a normal attack and gets an extra turn.
	 */
	@Test
	void testSpecialAttackGetExtraTurn() {
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(1.0);
		when(myMockRandom.nextInt(20, 41)).thenReturn(20);
		
		myTestThief.specialAttack(myTestEnemy);
		
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link model.Thief#specialAttack(model.DungeonCharacter)}.
	 * When the Thief does a normal attack but doesn't get an extra turn.
	 */
	@Test
	void testSpecialAttackDoNormalAttack() {
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(0.5);
		when(myMockRandom.nextInt(20, 41)).thenReturn(20);
		
		myTestThief.specialAttack(myTestEnemy);
		
		assertEquals(75, myTestThief.getCurHealthPoints());
		assertEquals(80, myTestEnemy.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.Thief#specialAttack(model.DungeonCharacter)}.
	 * When the Thief does nothing.
	 */
	@Test
	void testSpecialAttackDoNothing() {
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(0.0);
		
		myTestThief.specialAttack(myTestEnemy);
		
		assertEquals(75, myTestThief.getCurHealthPoints());
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
}

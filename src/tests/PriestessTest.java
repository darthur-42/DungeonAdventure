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

import model.Priestess;
import tests.mockclasses.MockDungeonCharacter;

/**
 * Test cases for Priestess.
 * 
 * @author Justin Le
 * @version 16 Mar 2025
 */
class PriestessTest {
	
	private Random myMockRandom;
	private Priestess myTestPriestess;
	private MockDungeonCharacter myTestEnemy;
	
	/**
     * Initialize the test Priestess and mock random before each test.
     */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestPriestess = new Priestess(myMockRandom);
		myTestEnemy = new MockDungeonCharacter(myMockRandom);
	}
	
	/**
	 * Test method for {@link model.Priestess#specialAttack(model.DungeonCharacter)}.
	 * When the Priestess' health is low.
	 */
	@Test
	void testSpecialAttackLowHealth() {
		myTestPriestess.setCurHealthPoints(10);
		when(myMockRandom.nextInt(50, 76)).thenReturn(55);
		
		myTestPriestess.specialAttack(myTestEnemy);
		
		assertEquals(65, myTestPriestess.getCurHealthPoints());
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.Priestess#specialAttack(model.DungeonCharacter)}.
	 * When the Priestess' health is full.
	 */
	@Test
	void testSpecialAttackFullHealth() {
		myTestPriestess.specialAttack(myTestEnemy);
		
		assertEquals(75, myTestPriestess.getCurHealthPoints());
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
}

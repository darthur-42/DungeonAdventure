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

import model.Berserker;
import tests.mockclasses.MockDungeonCharacter;

/**
 * Test cases for Berserker.
 * 
 * @author Justin Le
 * @version 16 Mar 2025
 */
class BerserkerTest {
	
	private Random myMockRandom;
	private Berserker myTestBerserker;
	private MockDungeonCharacter myTestEnemy;
	
	/**
	 * Initialize the test Berserker and mock random before each test.
	 */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestBerserker = new Berserker(myMockRandom);
		myTestEnemy = new MockDungeonCharacter(myMockRandom);
	}
	
	/**
	 * Test method for {@link model.Berserker#specialAttack(model.DungeonCharacter)}.
	 */
	@Test
	void testSpecialAttackHit() {
		myTestBerserker.setCurHealthPoints(100);
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(0.0);
		when(myMockRandom.nextInt(50, 71)).thenReturn(50);
		when(myMockRandom.nextInt(25, 41)).thenReturn(40);
		
		myTestBerserker.specialAttack(myTestEnemy);
		
		assertEquals(70, myTestEnemy.getCurHealthPoints());
		assertEquals(125, myTestBerserker.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.Berserker#specialAttack(model.DungeonCharacter)}.
	 */
	@Test
	void testSpecialAttackMiss() {
		myTestBerserker.setCurHealthPoints(100);
		when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(1.0);
		
		myTestBerserker.specialAttack(myTestEnemy);
		
		assertEquals(100, myTestEnemy.getCurHealthPoints());
		assertEquals(100, myTestBerserker.getCurHealthPoints());
	}
	
}

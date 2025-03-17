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

import model.Warrior;
import tests.mockclasses.MockDungeonCharacter;

/**
 * Test cases for Warrior.
 * 
 * @author Justin Le
 * @version 16 Mar 2025
 */
class WarriorTest {
	
	private Random myMockRandom;
	private Warrior myTestWarrior;
	private MockDungeonCharacter myTestEnemy;
	
	/**
	 * * Initialize the test Warrior and mock random before each test.
	 * */
	@BeforeEach
	void setUp() {
		myMockRandom = mock(Random.class);
		myTestWarrior = new Warrior(myMockRandom);
		myTestEnemy = new MockDungeonCharacter(myMockRandom);
	}
	
	/**
	 * Test method for {@link model.Warrior#specialAttack(model.DungeonCharacter)}.
	 * When the Warrior's special attack hits but doesn't kill.
	 */
	@Test
	void testSpecialAttackHit() {
		when(myMockRandom.nextDouble(0, 0.4)).thenReturn(1.0);
		when(myMockRandom.nextDouble(0, 1)).thenReturn(0.0);
		when(myMockRandom.nextInt(75, 176)).thenReturn(75);
		
		myTestWarrior.specialAttack(myTestEnemy);
		
		assertEquals(25, myTestEnemy.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.Warrior#specialAttack(model.DungeonCharacter)}.
	 * When the Warrior's special attack hits and kills.
	 */
	@Test
	void testSpecialAttackKill() {
		myTestEnemy.setCurHealthPoints(50);
		when(myMockRandom.nextDouble(0, 0.4)).thenReturn(1.0);
		when(myMockRandom.nextDouble(0, 1)).thenReturn(0.0);
		when(myMockRandom.nextInt(75, 176)).thenReturn(100);
		
		myTestWarrior.specialAttack(myTestEnemy);
		
		assertEquals(0, myTestEnemy.getCurHealthPoints());
	}
	
	/**
	 * Test method for {@link model.Warrior#specialAttack(model.DungeonCharacter)}.
	 * When the Warrior's special attack misses.
	 */
	@Test
	void testSpecialAttackMiss() {
		when(myMockRandom.nextDouble(0, 0.4)).thenReturn(0.0);
		when(myMockRandom.nextDouble(0, 1)).thenReturn(1.0);
		
		myTestWarrior.specialAttack(myTestEnemy);
		
		assertEquals(100, myTestEnemy.getCurHealthPoints());
	}
	
}

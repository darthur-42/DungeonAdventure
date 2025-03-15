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

import tests.mockclasses.MockDungeon;

/**
 * Test cases for Dungeon.
 * 
 * @author Arthur Fornia
 * @version 2 March 2025
 */
class DungeonTest {
	private Random myMockRandom;
	private MockDungeon myTestDungeon;
	private static final int MAP_KEY_LENGTH = 244; 
	
	/**
     * Initialize the test Dungeon and mock random before each following test.
     */
	@BeforeEach
	void setUp() {
		Random random = new Random();
		myTestDungeon = new MockDungeon(random);
		myMockRandom = mock(Random.class);
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeEntrance()}.
	 * If the dungeon creates the Entrance.
	 */
	@Test
	void testPlaceEntrance() {
		assertTrue(myTestDungeon.toString().substring(MAP_KEY_LENGTH).contains("i"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeExit()}.
	 * If the dungeon creates the Exit.
	 */
	@Test
	void testPlaceExit() {
		assertTrue(myTestDungeon.toString().substring(MAP_KEY_LENGTH).contains("O"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placePillars()}.
	 * If the dungeon creates the PillarOO's.
	 */
	@Test
	void testPlacePillars() {
		assertTrue(myTestDungeon.toString().substring(MAP_KEY_LENGTH).contains("A"));
		assertTrue(myTestDungeon.toString().substring(MAP_KEY_LENGTH).contains("E"));
		assertTrue(myTestDungeon.toString().substring(MAP_KEY_LENGTH).contains("I"));
		assertTrue(myTestDungeon.toString().substring(MAP_KEY_LENGTH).contains("P"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeOthers()}.
	 * If the dungeon creates Healing Potions.
	 */
	@Test
	void testPlaceHealing() {
		when(myMockRandom.nextInt(10)).thenReturn(0, 1, 1, 1);
		MockDungeon testDungeon = new MockDungeon(myMockRandom, myTestDungeon.myMap, 
				myTestDungeon.myActiveRooms);
		assertTrue(testDungeon.toString().substring(MAP_KEY_LENGTH).contains("H"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeOthers()}.
	 * If the dungeon creates Vision Potions.
	 */
	@Test
	void testPlaceVision() {
		when(myMockRandom.nextInt(10)).thenReturn(1, 0, 1, 1);
		MockDungeon testDungeon = new MockDungeon(myMockRandom, myTestDungeon.myMap, 
				myTestDungeon.myActiveRooms);
		assertTrue(testDungeon.toString().substring(MAP_KEY_LENGTH).contains("v"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeOthers()}.
	 * If the dungeon creates Pits.
	 */
	@Test
	void testPlacePits() {
		when(myMockRandom.nextInt(10)).thenReturn(1, 1, 0, 1);
		MockDungeon testDungeon = new MockDungeon(myMockRandom, myTestDungeon.myMap, 
				myTestDungeon.myActiveRooms);
		assertTrue(testDungeon.toString().substring(MAP_KEY_LENGTH).contains("X"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeOthers()}.
	 * If the dungeon creates Pits.
	 */
	@Test
	void testPlaceMonster() {
		when(myMockRandom.nextInt(10)).thenReturn(1, 1, 0, 1);
		MockDungeon testDungeon = new MockDungeon(myMockRandom, myTestDungeon.myMap, 
				myTestDungeon.myActiveRooms);
		assertTrue(testDungeon.toString().substring(MAP_KEY_LENGTH).contains("M"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeOthers()}.
	 * If the dungeon creates Pits.
	 */
	@Test
	void testPlaceMultiple() {
		when(myMockRandom.nextInt(10)).thenReturn(0, 0, 0, 0, 1);
		MockDungeon testDungeon = new MockDungeon(myMockRandom, myTestDungeon.myMap, 
				myTestDungeon.myActiveRooms);
		System.out.println(testDungeon.toString().substring(MAP_KEY_LENGTH));
		assertTrue(testDungeon.toString().substring(MAP_KEY_LENGTH).contains("m"));
	}
	
	/**
	 * Test method for {@link model.Dungeon#placeOthers()}.
	 * If the dungeon creates empty rooms.
	 */
	@Test
	void testPlaceNothing() {
		when(myMockRandom.nextInt(10)).thenReturn(1, 1, 1, 1);
		MockDungeon testDungeon = new MockDungeon(myMockRandom, myTestDungeon.myMap, 
				myTestDungeon.myActiveRooms);
		assertTrue(testDungeon.toString().substring(MAP_KEY_LENGTH).contains(" "));
	}
	
}

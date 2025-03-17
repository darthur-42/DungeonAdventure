/**
 * TCSS 360 Group Project
 */
package tests.mockclasses;

import java.util.Random;

import model.Dungeon;
import model.DungeonCharacterFactory;
import model.Room;

/**
 * A mock version of Dungeon, designed for testing the class.
 * 
 * @author Arthur Fornia
 * @version 4 Mar 2025
 */
public class MockDungeon extends Dungeon {
	
	/**
	 * Constructs a MockDungeon.
	 * 
	 * @param theRandomInstance the random instance
	 */
	public MockDungeon(Random theRandomInstance) {
		super(new DungeonCharacterFactory(), theRandomInstance);
	}
	
	/**
	 * Constructs a MockDungeon using a pre-existing map.
	 * 
	 * @param theRandomInstance the random instance
	 */
	public MockDungeon(Random theRandomInstance, Room[][] theMap, Room[] theActiveRooms) {
		super(theRandomInstance, theMap, theActiveRooms);
	}
	
}

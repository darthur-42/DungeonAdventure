/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;
import java.util.HashSet;

/**
 * This class represents the Dungeon in which the adventure occurs.
 * It will be implemented as a 2D array of Rooms.
 * 
 * @author Arthur Fornia
 * @version 18 Feb 2025
 */
public class Dungeon {
	
	/** An integer used in creation of the Map. It sets the max bounds of each array. */
	private final int MAP_SIZE = 10;
	
	/** An integer used in creation of the Dungeon. It sets the max number of reachable rooms. */
	private final int DUNGEON_SIZE = 20;
	
	/** 
	 * An integer used in creation of the Dungeon. It sets the chance potions and pits spawn. 
	 * Odds are calculated by the chance that a random integer is 0 from 0 to LOOT_CHANCE. 
	 */
	private final int LOOT_CHANCE = 10; 
	
	/** 
	 * An integer used in creation of the Dungeon. It sets the chance Monsters spawn. 
	 * Odds are calculated by the chance that a random integer is 0 from 0 to MONSTER_CHANCE. 
	 */
	private final int MONSTER_CHANCE = 10; 
	
	/** Random object used to generate random numbers. */
	private Random myRandom;
	
	/** An array of Rooms which are reachable Rooms, used for Random generation. */
	public Room[] myActiveRooms;
	
	/** A 2D array of Rooms which is the Map for the Dungeon. */
	public Room[][] myMap;
	
	/** 
	 * The factory responsible for creating DungeonCharacter instances, 
	 * including Heroes and Monsters. This is used to generate random 
	 * monsters from the database when populating the dungeon. 
	 */
	private DungeonCharacterFactory myFactory;
	
	/**
	 * Constructs a Dungeon which is essentially a 2D array of Rooms.
	 * Sets all of the Rooms coordinate tracking fields (roomX and roomY).
	 */
	public Dungeon(DungeonCharacterFactory theFactory) {
		this(theFactory, new Random());
	}
	
	/**
	 * Constructs a Dungeon which is essentially a 2D array of Rooms.
	 * Sets all of the Rooms coordinate tracking fields (roomX and roomY).
	 * Can pass in random instance for testing.
	 * 
	 * @param theRandomInstance the random instance
	 */
	public Dungeon(DungeonCharacterFactory theFactory, Random theRandomInstance) {
		this.myRandom = theRandomInstance;
		this.myFactory = theFactory;
		myMap = new Room[MAP_SIZE][MAP_SIZE];
		for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				myMap[x][y] = new Room(x, y);
			}
		}
		
		generateMaze();
		placeEntrance();
		placeExit();
		placePillars();
		placeOthers();
	}
	
	/**
	 * Constructs a Dungeon with a preset map. Only used in testing.
	 * 
	 * @param theRandomInstance the random instance
	 * @param theMap the map being used to make the dungeon. 
	 * @param theActiveRooms the array generated from generateMaze().
	 */
	public Dungeon(Random theRandomInstance, Room[][] theMap, Room[] theActiveRooms) {
		this.myRandom = theRandomInstance;
		this.myMap = theMap;
		this.myActiveRooms = theActiveRooms;
		placeOthers();
	}
	
	/**
	 * Makes all of the doors that connect the Rooms to each other in a maze like pattern.
	 * Adds all of the rooms that are reachable to the Array myActiveRooms.
	 */
	private void generateMaze() {
		HashSet<Room> activeRoomsHash = new HashSet<Room>(DUNGEON_SIZE);
		Room currentRoom = randomRoom(); 
		Room nextRoom = currentRoom;
		activeRoomsHash.add(currentRoom);
		while (activeRoomsHash.size() < DUNGEON_SIZE) {
			int nextDirection = myRandom.nextInt(Direction.values().length); 
			if (nextDirection == Direction.NORTH.ordinal() && (currentRoom.getRoomY() - 1) >= 0) {
				nextRoom = myMap[currentRoom.getRoomX()][currentRoom.getRoomY() - 1];
				currentRoom.setHasDoors(Direction.NORTH);
				nextRoom.setHasDoors(Direction.SOUTH);
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			} else if (nextDirection == Direction.EAST.ordinal() && (currentRoom.getRoomX() + 1) < MAP_SIZE) { 
				nextRoom = myMap[currentRoom.getRoomX() + 1][currentRoom.getRoomY()];
				currentRoom.setHasDoors(Direction.EAST);
				nextRoom.setHasDoors(Direction.WEST);
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			} else if (nextDirection == Direction.SOUTH.ordinal() && (currentRoom.getRoomY() + 1) < MAP_SIZE) { 
				nextRoom = myMap[currentRoom.getRoomX()][currentRoom.getRoomY() + 1];
				currentRoom.setHasDoors(Direction.SOUTH);
				nextRoom.setHasDoors(Direction.NORTH);
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			} else if (nextDirection == Direction.WEST.ordinal() && (currentRoom.getRoomX() - 1) >= 0) { 
				nextRoom = myMap[currentRoom.getRoomX() - 1][currentRoom.getRoomY()];
				currentRoom.setHasDoors(Direction.WEST);
				nextRoom.setHasDoors(Direction.EAST);
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			}
		}
		
		myActiveRooms = activeRoomsHash.toArray(new Room[DUNGEON_SIZE]); 
	}
	
	/**
	 * Places an entrance in one of the Rooms of the Dungeon. 
	 */
	private void placeEntrance() {
		Room room = randomActiveRoom();
		room.setHasEntrance(); 
	}
	
	/**
	 * Places an entrance in one of the Rooms of the Dungeon. 
	 */
	private void placeExit() {
		Room room = randomActiveRoom();
		while (room.getHasEntrance()) {
			room = randomActiveRoom();
		}
		
		room.setHasExit();
	}
	
	/**
	 * Places one of each of the four pillars of OOP in four different Rooms of the Dungeon.
	 * 
	 * @param theAccumulator is an integer used to track which Pillar will be next. 
	 */
	private void placePillars() {
		for (PillarOO pillar : PillarOO.values()) { 
			Room room = randomActiveRoom();
			while (room.getHasEntrance() || room.getHasExit() || room.getHasPillarOO()) {
				room = randomActiveRoom();
			}
						
			room.setHasPillarOO();
			room.setPillar(pillar);
		}
	}
	
	
	/**
	 * Goes through myActiveRooms and Rooms with nothing in them can randomly place objects.
	 * There is a 10% chance that each of the 3 objects are placed. 
	 */
	private void placeOthers() {
		for (Room currentRoom : myActiveRooms) {
			if (!currentRoom.getHasEntrance() && !currentRoom.getHasExit() && !currentRoom.getHasPillarOO()) { 
				if (myRandom.nextInt(LOOT_CHANCE) == 0) {
					currentRoom.setHasHealingPotion();
				}
				
				if (myRandom.nextInt(LOOT_CHANCE) == 0) {
					currentRoom.setHasVisionPotion();
				}
				
				if (myRandom.nextInt(LOOT_CHANCE) == 0) {
					currentRoom.setHasPit();
				}
				
				if (myRandom.nextInt(MONSTER_CHANCE) == 0) {
					currentRoom.setHasMonster(true);
					MonsterType randomMonsterType = MonsterType.values()[myRandom.nextInt(MonsterType.values().length)];
					Monster newMonster = (Monster) myFactory.createDungeonCharacter(randomMonsterType);
					currentRoom.setMonster(newMonster);
				}
			}
		}
	}
	
	/**
	 * Picks a room at random from map.
	 * 
	 * @return a random Room from map.
	 */
	private Room randomRoom() { 
		return myMap[myRandom.nextInt(0, MAP_SIZE - 1)][myRandom.nextInt(0, MAP_SIZE - 1)];
	}
	
	/**
	 * Picks a room at random from activeRooms.
	 * 
	 * @return a random Room from activeRooms.
	 */
	private Room randomActiveRoom() { 
		return myActiveRooms[myRandom.nextInt(0, myActiveRooms.length - 1)];
	}
	
	/**
	 * Creates a String that represents the Dungeon to help visualize it. 
	 * 
	 * @return a String that represents the Dungeon.
	 */
	@Override
	public String toString() {
		String output = "Map:\n" +
		"Walls = *, Doors = <^V> depending on orientation (in the order west north south east),\n" +
		"Multiple Items = m, Pit = X, Entrance = i (in), Exit = O (Out), Monster = M\n" +
		"Healing Potion = H, Vision Potion = v, Empty Room = \" \" (space), Pillars = A, E, I, or P\n";
		
		for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				output += myMap[x][y].toString() + "\t";
			}
			output += "\n"; //new line after each row
		}
		
		output += "\n";
		return output;
	}
}
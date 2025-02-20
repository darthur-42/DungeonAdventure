/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;
import java.util.ArrayList;

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
	
	/** An integer used in creation of the Dungeon. It is the number of Pillars of OOP. */
	private final int PILLARS = 4;
	
	/** 
	 * An integer used in creation of the Dungeon. It sets the chance potions and pits spawn. 
	 * Odds are calculated by the chance that a random integer is 1 from 1 to LOOT_CHANCE. 
	 */
	private final int LOOT_CHANCE = 10; // 1 - 10 is a 10% chance
	
	/** Random object used to generate random numbers. */
	private Random random = new Random();
	
	/** An array of Rooms which are reachable Rooms, used for Random generation. */
	private ArrayList<Room> activeRooms;
	
	/** A 2D array of Rooms which is the Map for the Dungeon. */
	Room[][] map;
	
	/**
	 * Constructs a Dungeon which is essentially a 2D array of Rooms.
	 * Sets all of the Rooms coordinate tracking fields (roomX and roomY).
	 */
	Dungeon() {
		map = new Room[MAP_SIZE][MAP_SIZE];
		for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				map[y][x].roomX = x;
				map[y][x].roomY = y;
			}
		}
		
		activeRooms = new ArrayList<Room>(DUNGEON_SIZE);
		generateMaze();
	}
	
	/**
	 * Places an entrance in one of the Rooms of the Dungeon. 
	 * Ensures there is nothing else in it.
	 */
	private void placeEntrance() {
		Room room = randomActiveRoom();
		room = new Room(room.roomX, room.roomY, room.hasNorth, 
				room.hasEast, room.hasSouth, room.hasWest);
		room.hasEntrance = true;
	}
	
	/**
	 * Places an entrance in one of the Rooms of the Dungeon. 
	 * Ensures there is nothing else in it.
	 */
	private void placeExit() {
		Room room = randomActiveRoom();
		while (room.hasEntrance) {
			room = randomActiveRoom();
		}
		
		room = new Room(room.roomX, room.roomY, room.hasNorth, 
				room.hasEast, room.hasSouth, room.hasWest);
		room.hasExit = true;
	}
	
	/**
	 * Places one of each of the four pillars of OOP in four different Rooms of the Dungeon.
	 * 
	 * @param theAccumulator is an integer used to track which Pillar will be next. 
	 */
	private void placePillars(int theAccumulator) {
		final char[] pillarIDs = {'A', 'E', 'I', 'P'};
		Room room = randomActiveRoom();
		while (room.hasEntrance || room.hasExit || room.hasPillarOO) {
			room = randomActiveRoom();
		}
		
		room.hasPillarOO = true;
		room.pillarID = pillarIDs[theAccumulator];
	}
	
	
	/**
	 * Goes through activeRooms and Rooms with nothing in them can randomly place objects.
	 * There is a 10% chance that each of the 3 objects are placed. 
	 */
	private void placeOthers() {
		for (int i = 0; i < activeRooms.size(); i++) {
			Room currentRoom = activeRooms.get(i);
			if (!currentRoom.hasEntrance || !currentRoom.hasExit || !currentRoom.hasPillarOO) { 
				if (random.nextInt(1, LOOT_CHANCE) == 1) {
					currentRoom.hasHealingPotion = true;
				}
				if (random.nextInt(1, LOOT_CHANCE) == 1) {
					currentRoom.hasVisionPotion = true;
				}
				if (random.nextInt(1, LOOT_CHANCE) == 1) {
					currentRoom.hasPit = true;
				}
			}
		}
	}
	
	/**
	 * Makes all of the doors that connect the Rooms to each other creating a maze like pattern.
	 * Then calls place methods to put objects into rooms that can be reached. 
	 */
	void generateMaze() {
		Room currentRoom = randomRoom(); 
		activeRooms.add(currentRoom);
		while (activeRooms.size() < DUNGEON_SIZE) {
			int nextDirection = randomNextInt(1, 4);
			//TODO need to add out of bounds checking 
			if (nextDirection == 1) { //north
				currentRoom.hasNorth = true;
				Room nextRoom = map[currentRoom.roomY + 1][currentRoom.roomX];//here
				nextRoom.hasSouth = true;
				activeRooms.add(nextRoom);
				currentRoom = nextRoom;
			} else if (nextDirection == 2) { //east
				
			} else if (nextDirection == 3) { //south
				
			} else /*(nextDirection == 4)*/ { //west
				
			}
			
			if (!activeRooms.contains(nextRoom)) {
				activeRooms.add(nextRoom);
			}
			nextRoom = randomRoom();
		}
		//TODO  WIP ABOVE Create a maze (randomly generate) by placing the doors, 
		//currently just picks random disconnected Rooms 
		placeEntrance();
		placeExit();
		for (int i = 0; i < PILLARS; i++) { 
			placePillars(i);
		}
		placeOthers();
	}
	
	/**
	 * Picks a room at random from map.
	 * 
	 * @return a random Room from map.
	 */
	private Room randomRoom() { 
		return map[random.nextInt(0, MAP_SIZE - 1)][random.nextInt(0, MAP_SIZE - 1)];
	}
	
	/**
	 * Picks a room at random from activeRooms.
	 * 
	 * @return a random Room from activeRooms.
	 */
	private Room randomActiveRoom() { 
		return activeRooms.get(random.nextInt(0, DUNGEON_SIZE - 1)); 
	}
	
	/**
	 * Creates a String that represents the Dungeon to help visualize it. 
	 * 
	 * @return a String that represents the Dungeon.
	 */
	@Override
	public String toString() {
		String output = "Map:\n";
		for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				output += map[y][x].toString();
			}
			output += "\n"; //new line after each row
		}
		
		output += "\n";
		output += "List of reachable Rooms: " + activeRooms.toString() + "\n";
		return output;
	}
}

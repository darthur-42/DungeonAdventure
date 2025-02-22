/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;
import java.util.HashSet;
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
	
	/** 
	 * An integer used in creation of the Dungeon. It sets the chance potions and pits spawn. 
	 * Odds are calculated by the chance that a random integer is 1 from 1 to LOOT_CHANCE. 
	 */
	private final int LOOT_CHANCE = 10; // 1 - 10 is a 10% chance
	
	/** Random object used to generate random numbers. */
	private Random random = new Random();
	
	/** An array of Rooms which are reachable Rooms, used for Random generation. */
	private Room[] activeRooms;
	
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
				map[x][y] = new Room(x, y);
			}
		}
		
		generateMaze();
		placeEntrance();
		placeExit();
		placePillars();
		placeOthers();
	}
	
	/**
	 * Makes all of the doors that connect the Rooms to each other in a maze like pattern.
	 * Adds all of the rooms that are reachable to the Set activeRoomsHash, which is converted
	 * into the Room array activeRooms. 
	 */
	private void generateMaze() {
		HashSet<Room> activeRoomsHash = new HashSet<Room>(DUNGEON_SIZE);
		Room currentRoom = randomRoom(); 
		Room nextRoom = currentRoom;
		activeRoomsHash.add(currentRoom);
		while (activeRoomsHash.size() < DUNGEON_SIZE) {
			int nextDirection = random.nextInt(0, 4); //4 cardinal directions
			if (nextDirection == 0 && (currentRoom.roomY - 1) >= 0) { //north
				nextRoom = map[currentRoom.roomX][currentRoom.roomY - 1];
				currentRoom.hasNorth = true;
				nextRoom.hasSouth = true;
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			} else if (nextDirection == 1 && (currentRoom.roomX + 1) < MAP_SIZE) { //east
				nextRoom = map[currentRoom.roomX + 1][currentRoom.roomY];
				currentRoom.hasEast = true;
				nextRoom.hasWest = true;
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			} else if (nextDirection == 2 && (currentRoom.roomY + 1) < MAP_SIZE) { //south
				nextRoom = map[currentRoom.roomX][currentRoom.roomY + 1];
				currentRoom.hasSouth = true;
				nextRoom.hasNorth = true;
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			} else if (nextDirection == 3 && (currentRoom.roomX - 1) >= 0) { //west
				nextRoom = map[currentRoom.roomX - 1][currentRoom.roomY];
				currentRoom.hasWest = true;
				nextRoom.hasEast = true;
				activeRoomsHash.add(nextRoom);
				currentRoom = nextRoom; 
			}
		}
		
		activeRooms = activeRoomsHash.toArray(new Room[DUNGEON_SIZE]); 
	}
	
	/**
	 * Places an entrance in one of the Rooms of the Dungeon. 
	 */
	private void placeEntrance() {
		Room room = randomActiveRoom();
		room.hasEntrance = true;
	}
	
	/**
	 * Places an entrance in one of the Rooms of the Dungeon. 
	 */
	private void placeExit() {
		Room room = randomActiveRoom();
		while (room.hasEntrance) {
			room = randomActiveRoom();
		}
		
		room.hasExit = true;
	}
	
	/**
	 * Places one of each of the four pillars of OOP in four different Rooms of the Dungeon.
	 * 
	 * @param theAccumulator is an integer used to track which Pillar will be next. 
	 */
	private void placePillars() {
		final char[] pillarIDs = {'A', 'E', 'I', 'P'};

		for (char pillarID : pillarIDs) { 
			Room room = randomActiveRoom();
			while (room.hasEntrance || room.hasExit || room.hasPillarOO) {
				room = randomActiveRoom();
			}
						
			room.hasPillarOO = true;
			room.pillarID = pillarID;
		}
	}
	
	
	/**
	 * Goes through activeRooms and Rooms with nothing in them can randomly place objects.
	 * There is a 10% chance that each of the 3 objects are placed. 
	 */
	private void placeOthers() {
		for (Room currentRoom : activeRooms) {
			if (!currentRoom.hasEntrance && !currentRoom.hasExit && !currentRoom.hasPillarOO) { 
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
		return activeRooms[random.nextInt(0, activeRooms.length - 1)];
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
				output += map[x][y].toString() + "\t";
			}
			output += "\n"; //new line after each row
		}
		
		output += "\n";
		return output;
	}
}

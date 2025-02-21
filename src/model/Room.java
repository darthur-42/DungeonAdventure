/**
 * TCSS 360 Group Project
 */
package model;

/**
 * This class represents the Rooms of the Dungeon in which the adventure occurs.
 * 
 * @author Arthur Fornia
 * @version 18 Feb 2025
 */
class Room {
	
	/** If the Room has the Entrance. */
	boolean hasEntrance;
	
	/** If the Room has the Exit. */
	boolean hasExit;
	
	/** If the Room has a Healing Potion in it. */
	boolean hasHealingPotion;
	
	/** If the Room has a Vision Potion in it. */
	boolean hasVisionPotion;
	
	/** If the Room has a Pit in it. */
	boolean hasPit;
	
	/** If the Room has a Pillar of OOP in it. */
	boolean hasPillarOO;
	
	/** If the Room has a Door to the North. */
	boolean hasNorth;
	
	/** If the Room has a Door to the East. */
	boolean hasEast;
	
	/** If the Room has a Door to the South. */
	boolean hasSouth;
	
	/** If the Room has a Door to the West. */
	boolean hasWest;
	
	/** An ID Character to Identify the Pillar of OOP in the Room, '?' if none. */
	char pillarID;
	
	/** An integer marking the Rooms horizontal position in the Map. */
	int roomX;
	
	/** An integer marking the Rooms vertical position in the Map. */
	int roomY;
	
	/**
	 * Constructs a Room which is an element of the 2D array of Dungeon. 
	 * This is an empty Room with no connecting Rooms.
	 */
	Room(final int theX, final int theY) {
		this.hasEntrance = false;
		this.hasExit = false;
		this.hasHealingPotion = false;
		this.hasVisionPotion = false;
		this.hasPit = false;
		this.hasPillarOO = false;
		this.hasNorth = false;
		this.hasEast = false;
		this.hasSouth = false;
		this.hasWest = false;
		this.pillarID = '?';
		this.roomX = theX;
		this.roomY = theY;
	}
	
	/**
	 * Creates and returns a String that represents the Room to help with testing. 
	 * 
	 * @return a String that displays all of the data stored in the Room. 
	 */
	String testString() {
		String PillarName;
		if (this.pillarID == 'A') {
			PillarName = "Abstraction";
		} else if (this.pillarID == 'E') {
			PillarName = "Encapsulation";
		} else if (this.pillarID == 'I') {
			PillarName = "Inheritance";
		} else if (this.pillarID == 'P') {
			PillarName = "Polymorphism";
		} else {
			PillarName = "No Pillar";
		}
		
		return "Room at (" + this.roomX + ", " + this.roomY + "): [Entrance: " + this.hasEntrance + "Exit: " + this.hasExit + 
				"Healing Potion: " + this.hasHealingPotion + "Vision Potion: " + 
				this.hasVisionPotion + "Pit: " + this.hasPit + "OOPillar: " + this.hasPillarOO + 
				"Pillar Name: " + PillarName + "Doors: {" + "North: " + this.hasNorth + "East: " +
				this.hasEast + "South: " + this.hasSouth + "West: " + this.hasWest + "}]";
	}
	
	/**
	 * Creates and returns a String that represents the Room to help visualize it. 
	 * Walls and corners = *
	 * Doors = <^V> depending on orientation (in the order west north south east)
	 * Multiple Items = M
	 * Pit = X
	 * Entrance = i (in)
	 * Exit = O (Out)
	 * Healing Potion = H
	 * Vision Potion = v
	 * Empty Room = " " (space)
	 * Pillars = A, E, I, or P
	 * example room with a door to the north and west, and multiple items: 
	 * <^M**
	 * 
	 * @return a String that displays all of the data stored in the Room in command line UI. 
	 */
	@Override
	public String toString() {
		String output = ""; 
		
		if (this.hasWest) { //west door
			output += "<";
		} else {
			output += "*";
		} //end west 
		
		if (this.hasNorth) { //north door
			output += "^";
		} else {
			output += "*";
		} //end north 
		
		char center = '?'; //center
		int accumulator = 0;
		if (this.hasEntrance) {
			center = 'i';
			accumulator++;
		} else if (this.hasExit) {
			center = 'O';
			accumulator++;
		} else if (this.hasHealingPotion) {
			center = 'H';
			accumulator++;
		} else if (this.hasVisionPotion) {
			center = 'v';
			accumulator++;
		} else if (this.hasPit) {
			center = 'X';
			accumulator++;
		} else if (this.hasPillarOO) {
			center = this.pillarID;
			accumulator++;
		} else if (accumulator > 1) {
			center = 'M';
		} else {
			center = ' ';
		}
		output += center; //end center
		
		if (this.hasSouth) { //south door
			output += "V";
		} else {
			output += "*";
		} //end south 
		
		if (this.hasEast) { //east door
			output += ">";
		} else {
			output += "*";
		} //end east 
		
		return output;
	}
}

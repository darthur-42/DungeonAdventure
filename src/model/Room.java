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
	private boolean myHasEntrance;
	
	/** If the Room has the Exit. */
	private boolean myHasExit;
	
	/** If the Room has a Healing Potion in it. */
	private boolean myHasHealingPotion;
	
	/** If the Room has a Vision Potion in it. */
	private boolean myHasVisionPotion;
	
	/** If the Room has a Pit in it. */
	private boolean myHasPit;
	
	/** If the Room has a Pillar of OOP in it. */
	private boolean myHasPillarOO;
	
	/** If the Room has a Monster in it. */
	private boolean myHasMonster;
	
	/** If the Room has a Door to the North. */
	private boolean myHasNorth;
	
	/** If the Room has a Door to the East. */
	private boolean myHasEast;
	
	/** If the Room has a Door to the South. */
	private boolean myHasSouth;
	
	/** If the Room has a Door to the West. */
	private boolean myHasWest;
	
	/** An ID Character to Identify the Pillar of OOP in the Room, '?' if none. */
	private char myPillarID;
	
	/** An integer marking the Rooms horizontal position in the Map. */
	private int myRoomX;
	
	/** An integer marking the Rooms vertical position in the Map. */
	private int myRoomY;
	
	/**
	 * Constructs a Room which is an element of the 2D array of Dungeon. 
	 * This is an empty Room with no connecting Rooms.
	 */
	Room(final int theX, final int theY) {
		this.myHasEntrance = false;
		this.myHasExit = false;
		this.myHasHealingPotion = false;
		this.myHasVisionPotion = false;
		this.myHasPit = false;
		this.myHasPillarOO = false;
		this.myHasNorth = false;
		this.myHasEast = false;
		this.myHasSouth = false;
		this.myHasWest = false;
		this.myPillarID = '?';
		this.myRoomX = theX;
		this.myRoomY = theY;
	}
	
	/**
	 * Returns if the Room has the Entrance. 
	 * 
	 * @return if the Room has the Entrance. 
	 */
	public boolean getHasEntrance() {
		return this.myHasEntrance;
	}
	
	/**
	 * Sets if the Room has the Entrance. 
	 */
	public void setHasEntrance() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasEntrance = true;
		}
	}
	
	/**
	 * Returns if the Room has the Exit. 
	 * 
	 * @return if the Room has the Exit. 
	 */
	public boolean getHasExit() {
		return this.myHasExit;
	}
	
	/**
	 * Sets if the Room has the Exit.
	 */
	public void setHasExit() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasExit = true;
		}
	}
	
	/**
	 * Returns if the Room has a healing potion. 
	 * 
	 * @return if the Room has a healing potion. 
	 */
	public boolean getHasHealingPotion() {
		return this.myHasHealingPotion;
	}
	
	/**
	 * Sets if the Room has a healing potion.
	 */
	public void setHasHealingPotion() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasHealingPotion = true;
		}
	}
	
	/**
	 * Returns if the Room has a vision potion. 
	 * 
	 * @return if the Room has a vision potion. 
	 */
	public boolean getHasVisionPotion() {
		return this.myHasVisionPotion;
	}
	
	/**
	 * Sets if the Room has a vision potion. 
	 */
	public void setHasVisionPotion() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasVisionPotion = true;
		}
	}
	
	/**
	 * Returns if the Room has a pit. 
	 * 
	 * @return if the Room has a pit. 
	 */
	public boolean getHasPit() {
		return this.myHasPit;
	}
	
	/**
	 * Sets if the Room has a pit. 
	 */
	public void setHasPit() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasPit = true;
		}
	}
	
	/**
	 * Returns if the Room has a Pillar of OOP. 
	 * 
	 * @return if the Room has a Pillar of OOP. 
	 */
	public boolean getHasPillarOO() {
		return this.myHasPillarOO;
	}
	
	/**
	 * Sets if the Room has a pit. 
	 */
	public void setHasPillarOO() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasPit = true;
		}
	}
	
	/**
	 * Returns if the Room has a Door to the North. 
	 * 
	 * @return if the Room has a Door to the North. 
	 */
	public boolean getHasNorth() {
		return this.myHasNorth;
	}
	
	/**
	 * Sets if the Room has a Door to the North. 
	 */
	public void setHasNorth() {
		this.myHasNorth = true;
	}
	
	/**
	 * Returns if the Room has a Door to the East. 
	 * 
	 * @return if the Room has a Door to the East. 
	 */
	public boolean getHasEast() {
		return this.myHasEast;
	}
	
	/**
	 * Sets if the Room has a Door to the East. 
	 */
	public void setHasEast() {
		this.myHasEast = true;
	}
	
	/**
	 * Returns if the Room has a Door to the South. 
	 * 
	 * @return if the Room has a Door to the South. 
	 */
	public boolean getHasSouth() {
		return this.myHasSouth;
	}
	
	/**
	 * Sets if the Room has a Door to the South. 
	 */
	public void setHasSouth() {
		this.myHasSouth = true;
	}
	
	/**
	 * Returns if the Room has a Door to the West. 
	 * 
	 * @return if the Room has a Door to the West. 
	 */
	public boolean getHasWest() {
		return this.myHasWest;
	}
	
	/**
	 * Sets if the Room has a Door to the West. 
	 */
	public void setHasWest() {
		this.myHasWest = true;
	}
	
	/**
	 * Return a character representing the Room's Pillar of OOP.  
	 * 
	 * @return a character representing the Room's Pillar of OOP. 
	 */
	public char getPillarID() {
		return this.myPillarID;
	}
	
	/**
	 * Sets if the Room has a pit. 
	 */
	public void setPillarID(char thePillarID) {
		this.myPillarID = thePillarID;
	}
	
	/**
	 * Returns an integer representing the Room's horizontal (X) position in the Dungeon.  
	 * 
	 * @return an integer representing the Room's horizontal (X) position in the Dungeon.  
	 */
	public int getRoomX() {
		return this.myRoomX;
	}
	
	/**
	 * Returns an integer representing the Room's vertical (Y) position in the Dungeon.  
	 * 
	 * @return an integer representing the Room's vertical (Y) position in the Dungeon.  
	 */
	public int getRoomY() {
		return this.myRoomY;
	}
	
	/**
	 * Creates and returns a String that represents the Room to help with testing. 
	 * 
	 * @return a String that displays all of the data stored in the Room. 
	 */
	String testString() {
		String PillarName;
		if (this.myPillarID == 'A') {
			PillarName = "Abstraction";
		} else if (this.myPillarID == 'E') {
			PillarName = "Encapsulation";
		} else if (this.myPillarID == 'I') {
			PillarName = "Inheritance";
		} else if (this.myPillarID == 'P') {
			PillarName = "Polymorphism";
		} else {
			PillarName = "No Pillar";
		}
		
		return "Room at (" + this.myRoomX + ", " + this.myRoomY + "): [Entrance: " + this.myHasEntrance + "Exit: " + this.myHasExit + 
				"Healing Potion: " + this.myHasHealingPotion + "Vision Potion: " + 
				this.myHasVisionPotion + "Pit: " + this.myHasPit + "OOPillar: " + this.myHasPillarOO + 
				"Pillar Name: " + PillarName + "Doors: {" + "North: " + this.myHasNorth + "East: " +
				this.myHasEast + "South: " + this.myHasSouth + "West: " + this.myHasWest + "}]";
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
		
		if (this.myHasWest) { //west door
			output += "<";
		} else {
			output += "*";
		} //end west 
		
		if (this.myHasNorth) { //north door
			output += "^";
		} else {
			output += "*";
		} //end north 
		
		char center = '?'; //center
		int accumulator = 0;
		if (this.myHasEntrance) {
			center = 'i';
			accumulator++;
		} else if (this.myHasExit) {
			center = 'O';
			accumulator++;
		} else if (this.myHasHealingPotion) {
			center = 'H';
			accumulator++;
		} else if (this.myHasVisionPotion) {
			center = 'v';
			accumulator++;
		} else if (this.myHasPit) {
			center = 'X';
			accumulator++;
		} else if (this.myHasPillarOO) {
			center = this.myPillarID;
			accumulator++;
		} else if (accumulator > 1) {
			center = 'M';
		} else {
			center = ' ';
		}
		output += center; //end center
		
		if (this.myHasSouth) { //south door
			output += "V";
		} else {
			output += "*";
		} //end south 
		
		if (this.myHasEast) { //east door
			output += ">";
		} else {
			output += "*";
		} //end east 
		
		return output;
	}
}

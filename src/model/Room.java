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
	
	/** If the Room has a Monster in it. */ //TODO
	private boolean myHasMonster;
	
	/** If the Room has a Door in that Direction. */
	private boolean[] myHasDoors;
	
	/** The Pillar of OOP in the Room */
	private PillarsOOP myPillar;
	
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
		this.myHasMonster = false; 
		this.myHasDoors = new boolean[Directions.values().length];
		this.myPillar = null;
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
	 * Sets if the Room has a Pillar. 
	 */
	public void setHasPillarOO() {
		if (!this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO) {
			this.myHasPit = true;
		}
	}
	
	/**
	 * Returns if the Room has a Door in each Direction. 
	 * 
	 * @return if the Room has a Door in each Direction. 
	 */
	public boolean[] getHasDoors() {
		return this.myHasDoors;
	}
	
	/**
	 * Sets if the Room has a Door in each Direction. 
	 * 
	 * @param the Direction of the Door being added. 
	 */
	public void setHasDoors(Directions theDirection) {
		this.myHasDoors[theDirection.ordinal()] = true;
	}
	
	/**
	 * Returns the Room's Pillar of OOP.  
	 * 
	 * @return the Room's Pillar of OOP. 
	 */
	public PillarsOOP getPillar() {
		return this.myPillar;
	}
	
	/**
	 * Sets the pillarsOOP of the pillar in this Room. 
	 * 
	 * @param pillarsOOP of the PillarOO being places.
	 */
	public void setPillar(PillarsOOP thePillar) {
		this.myPillar = thePillar;
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
		return "Room at (" + this.myRoomX + ", " + this.myRoomY + "): [Entrance: " +
				this.myHasEntrance + "Exit: " + this.myHasExit + "Healing Potion: " + 
				this.myHasHealingPotion + "Vision Potion: " + this.myHasVisionPotion + "Pit: " +
				this.myHasPit + "OOPillar: " + this.myHasPillarOO + "Pillar Name: " + 
				this.myPillar.toString() + "Doors: {" + "North: " + 
				this.myHasDoors[Directions.NORTH.ordinal()] + "East: " +
				this.myHasDoors[Directions.EAST.ordinal()] + "South: " + 
				this.myHasDoors[Directions.SOUTH.ordinal()] + "West: " +
				this.myHasDoors[Directions.WEST.ordinal()] + "}]";
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
		
		if (this.myHasDoors[Directions.WEST.ordinal()]) { //west door
			output += "<";
		} else {
			output += "*";
		} //end west 
		
		if (this.myHasDoors[Directions.NORTH.ordinal()]) { //north door
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
			center = this.myPillar.toString().charAt(0);
			accumulator++;
		} else if (accumulator > 1) {
			center = 'M';
		} else {
			center = ' ';
		}
		output += center; //end center
		
		if (this.myHasDoors[Directions.SOUTH.ordinal()]) { //south door
			output += "V";
		} else {
			output += "*";
		} //end south 
		
		if (this.myHasDoors[Directions.EAST.ordinal()]) { //east door
			output += ">";
		} else {
			output += "*";
		} //end east 
		
		return output;
	}
}

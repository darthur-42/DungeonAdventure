/**
i * TCSS 360 Group Project
 */
package model;

import java.io.Serializable;

/**
 * This class represents the Rooms of the Dungeon in which the adventure occurs.
 * 
 * @author Arthur Fornia, Justin Le
 * @version 20 Mar 2025
 */
public class Room implements Serializable {
	
	/** Unique identifier for serialization. */
	private static final long serialVersionUID = -8906470120784019548L;

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
	
	/** If the Room has a Door in that Direction. */
	private boolean[] myHasDoors;
	
	/** The Pillar of OOP in the Room */
	private PillarOO myPillar;
	
	/** An integer marking the Rooms horizontal position in the Map. */
	private int myRoomX;
	
	/** An integer marking the Rooms vertical position in the Map. */
	private int myRoomY;
	
	/** The Monster that is in the Room. */
	private Monster myMonster;
	
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
		this.myHasDoors = new boolean[Direction.values().length];
		this.myPillar = null;
		this.myRoomX = theX;
		this.myRoomY = theY;
		this.myMonster = null;
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
	 * Forces the Room to have the Exit; DEBUG ONLY!!!
	 */
	public void forceHasExit() {
		this.myHasExit = true;
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
	 * 
	 * @param theHasHealingPotion if the Room has a healing potion
	 */
	public void setHasHealingPotion(final boolean theHasHealingPotion) {
		if (theHasHealingPotion && !this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO
				|| !theHasHealingPotion) {
			this.myHasHealingPotion = theHasHealingPotion;
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
	 * 
	 * @param theHasHealingPotion if the Room has a vision potion
	 */
	public void setHasVisionPotion(final boolean theHasVisionPotion) {
		if (theHasVisionPotion && !this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO
				|| !theHasVisionPotion) {
			this.myHasVisionPotion = theHasVisionPotion;
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
	 * Sets if the Room has a PillarOO.
	 * 
	 * @param theHasPillarOO if the Room has a PillarOO
	 */
	public void setHasPillarOO(final boolean theHasPillarOO) {
		if (theHasPillarOO && !this.myHasEntrance && !this.myHasExit || !theHasPillarOO) {
			this.myHasPillarOO = theHasPillarOO;
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
	public void setHasDoors(final Direction theDirection) {
		this.myHasDoors[theDirection.ordinal()] = true;
	}
	
	/**
	 * Returns the Room's PillarOO.
	 * 
	 * @return the Room's PillarOO.
	 */
	public PillarOO getPillar() {
		return this.myPillar;
	}
	
	/**
	 * Sets the PillarOO in this Room.
	 * 
	 * @param thePillarOO the PillarOO.
	 */
	public void setPillarOO(final PillarOO thePillarOO) {
		this.myPillar = thePillarOO;
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
	 * Returns if the Room has a Monster.  
	 * 
	 * @return if the Room has a Monster. 
	 */
	public boolean getHasMonster() {
		return this.myHasMonster;
	}
	
	/**
	 * Sets if the Room has a Monster.
	 * 
	 * @param theHasMonster if the Room has a Monster
	 */
	public void setHasMonster(final boolean theHasMonster) {
		if (theHasMonster && !this.myHasEntrance && !this.myHasExit && !this.myHasPillarOO
				|| !theHasMonster) {
			this.myHasMonster = theHasMonster;
		}
	}
	
	/**
	 * Returns the Room's Monster.  
	 * 
	 * @return the Room's Monster. 
	 */
	public Monster getMonster() {
		return this.myMonster;
	}
	
	/**
	 * Sets the Monster of this Room. 
	 * 
	 * @param theMonster the Monster being placed.
	 */
	public void setMonster(final Monster theMonster) {
		this.myMonster = theMonster;
	}
	
	/**
	 * Creates and returns a String that represents the Room to help visualize it. 
	 * Walls and corners = *
	 * Doors = <^V> depending on orientation (in the order west north south east)
	 * Multiple Items = m
	 * Monster = M
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
		
		if (this.myHasDoors[Direction.WEST.ordinal()]) { //west door
			output += "<";
		} else {
			output += "*";
		} //end west 
		
		if (this.myHasDoors[Direction.NORTH.ordinal()]) { //north door
			output += "^";
		} else {
			output += "*";
		} //end north 
		
		char center = ' '; //center
		int accumulator = 0;
		if (this.myHasEntrance) {
			center = 'i';
			accumulator++;
		} 
		
		if (this.myHasExit) {
			center = 'O';
			accumulator++;
		} 
		
		if (this.myHasHealingPotion) {
			center = 'H';
			accumulator++;
		} 
		
		if (this.myHasVisionPotion) {
			center = 'v';
			accumulator++;
		} 
		
		if (this.myHasPit) {
			center = 'X';
			accumulator++;
		} 
		
		if (this.myHasPillarOO) {
			center = this.myPillar.toString().charAt(0);
			accumulator++;
		} 
		
		if (this.myHasMonster) {
			center = 'M';
			accumulator++;
		} 
		
		if (accumulator > 1) {
			center = 'm';
		} 
		
		output += center; //end center
		
		if (this.myHasDoors[Direction.SOUTH.ordinal()]) { //south door
			output += "V";
		} else {
			output += "*";
		} //end south 
		
		if (this.myHasDoors[Direction.EAST.ordinal()]) { //east door
			output += ">";
		} else {
			output += "*";
		} //end east 
		
		return output;
	}
	
	/**
	 * Returns the Room in a more detailed view.  
	 * 
	 * @return String representing the Room in a more detailed view. 
	 */
	public String stringUI() {
		String output = "";
		
		for (int i = 0; i < 3; i++) {
			output += "    ";
			if (myHasDoors[Direction.NORTH.ordinal()]) {
				if (i == 0) {
					output += "*~~~*";
				} else {
					output += "~   ~";
				}
			}
			output += "\n";
		}
		
		String centerTop = "";
		if (myHasDoors[Direction.WEST.ordinal()]) {
			centerTop += "*~~~*";
		} else {
			centerTop += "    *";
		}
		if (myHasDoors[Direction.NORTH.ordinal()]) {
			centerTop += "^^^";
		} else {
			centerTop += "---";
		}
		if (myHasDoors[Direction.EAST.ordinal()]) {
			centerTop += "*~~~*\n";
		} else {
			centerTop += "*\n";
		}
		
		String centerMiddle = "";
		for (int i = 0; i < 2; i++) {
			if (myHasDoors[Direction.WEST.ordinal()]) {
				centerMiddle += "~   <";
			} else {
				centerMiddle += "    |";
			}
//			centerMiddle += "   ";
			if (i == 0) {
				if (myHasEntrance) {
					centerMiddle += "Ent";
				} else if (myHasExit) {
					centerMiddle += "Ext";
				} else if (myHasPillarOO) {
					centerMiddle += "Plr";
				} else {
					if (myHasHealingPotion) {
						centerMiddle += "H";
					} else {
						centerMiddle += " ";
					}
					if (myHasMonster) {
						centerMiddle += "M";
					} else {
						centerMiddle += " ";
					}
					if (myHasPit) {
						centerMiddle += "P";
					} else {
						centerMiddle += " ";
					}
				}
			} else {
				if (myHasVisionPotion) {
					centerMiddle += "V";
				} else {
					centerMiddle += " ";
				}
				centerMiddle += "Y";
				if (myHasPit) {
					centerMiddle += "t";
				} else {
					centerMiddle += " ";
				}
			}
			if (myHasDoors[Direction.EAST.ordinal()]) {
				centerMiddle += ">   ~\n";
			} else {
				centerMiddle += "|\n";
			}
		}
		
		String centerBottom = "";
		if (myHasDoors[Direction.WEST.ordinal()]) {
			centerBottom += "*~~~*";
		} else {
			centerBottom += "    *";
		}
		if (myHasDoors[Direction.SOUTH.ordinal()]) {
			centerBottom += "vvv";
		} else {
			centerBottom += "---";
		}
		if (myHasDoors[Direction.EAST.ordinal()]) {
			centerBottom += "*~~~*\n";
		} else {
			centerBottom += "*\n";
		}
		
		output += centerTop + centerMiddle + centerBottom;
		
		for (int i = 0; i < 3; i++) {
			output += "    ";
			if (myHasDoors[Direction.SOUTH.ordinal()]) {
				if (i == 2) {
					output += "*~~~*";
				} else {
					output += "~   ~";
				}
			}
			output += "\n";
		}
		
		return output;
	}
}

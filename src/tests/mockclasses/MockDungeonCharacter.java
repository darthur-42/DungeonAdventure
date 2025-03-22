/*
 * TCSS 360 Group Project
 */
package tests.mockclasses;

import java.util.Random;

import model.DungeonCharacter;

/**
 * A mock version of DungeonCharacter, designed for testing the abstract class.
 * 
 * @author Justin Le, Anna Brewer
 * @version 21 Mar 2025
 */
@SuppressWarnings("serial")
public class MockDungeonCharacter extends DungeonCharacter {
	
	/**
	 * Constructs a MockDungeonCharacter.
	 * 
	 * @param theRandomInstance the random instance
	 */
	public MockDungeonCharacter(Random theRandomInstance) {
		super("MockDngnChar", 100, 10, 20, 5, 0.5, theRandomInstance);
	}
	
	/**
	 * Helper method to call receiveHealing() for testing.
	 */
	public void callReceiveHealing(int theAmount) {
		receiveHealing(theAmount);
	}

	/**
	 * Helper method to call receiveDamage() for testing.
	 */
	public void callReceiveDamage(int theAmount) {
		receiveDamage(theAmount);
	}
}

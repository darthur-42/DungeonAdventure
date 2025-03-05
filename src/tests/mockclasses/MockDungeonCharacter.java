/**
 * TCSS 360 Group Project
 */
package tests.mockclasses;

import java.util.Random;

import model.DungeonCharacter;

/**
 * A mock version of DungeonCharacter, designed for testing the abstract class.
 * 
 * @author Justin Le
 * @version 4 Mar 2025
 */
public class MockDungeonCharacter extends DungeonCharacter {
	
	/**
	 * Constructs a MockDungeonCharacter.
	 * 
	 * @param theRandomInstance the random instance
	 */
	public MockDungeonCharacter(Random theRandomInstance) {
		super("MockDngnChar", 100, 10, 20, 5, 0.5, theRandomInstance);
	}
	
}

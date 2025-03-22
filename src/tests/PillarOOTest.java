/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.PillarOO;

/**
 * Test cases for PillarOO.
 * 
 * @author Justin Le
 * @version 21 Mar 2025
 */
class PillarOOTest {

	/**
	 * Test method for {@link model.PillarOO#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("Abstraction", PillarOO.ABSTRACTION.toString());
		assertEquals("Encapsulation", PillarOO.ENCAPSULATION.toString());
		assertEquals("Inheritance", PillarOO.INHERITANCE.toString());
		assertEquals("Polymorphism", PillarOO.POLYMORPHISM.toString());
	}

}

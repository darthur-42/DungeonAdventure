/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.HeroType;

/**
 * Test cases for HeroType.
 * 
 * @author Justin Le
 * @version 21 Mar 2025
 */
class HeroTypeTest {

	/**
	 * Test method for {@link model.HeroType#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		assertEquals(
				"The Warrior is a Hero that has high health, high damage, low attack speed, high hit "
						+ "chance, and low block chance. Their special attack (Charge) has a low chance to deal "
						+ "even higher damage.",
				HeroType.WARRIOR.getDescription());
	}

	/**
	 * Test method for {@link model.HeroType#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("Warrior", HeroType.WARRIOR.toString());
		assertEquals("Priestess", HeroType.PRIESTESS.toString());
		assertEquals("Thief", HeroType.THIEF.toString());
		assertEquals("Berserker", HeroType.BERSERKER.toString());
	}

}

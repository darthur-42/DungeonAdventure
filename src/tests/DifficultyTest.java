/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Difficulty;

/**
 * Test cases for Difficulty.
 * 
 * @author Justin Le
 * @version 21 Mar 2025
 */
class DifficultyTest {

	/**
	 * Test method for {@link model.Difficulty#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("Easy", Difficulty.EASY.toString());
		assertEquals("Medium", Difficulty.MEDIUM.toString());
		assertEquals("Hard", Difficulty.HARD.toString());
	}

}

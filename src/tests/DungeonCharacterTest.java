/*
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tests.mockclasses.MockDungeonCharacter;

/**
 * Test cases for DungeonCharacter.
 * 
 * @author Justin Le, Anna Brewer
 * @version 21 Mar 2025
 */
class DungeonCharacterTest {
	
	/** Mock Random used for testing. */
	private Random myMockRandom;

	/** The main character being tested. */
	private MockDungeonCharacter myTestDungeonCharacter;

	/** The enemy character used for attacks. */
	private MockDungeonCharacter myTestEnemy;

	/** Mock listener to test property change events. */
	private PropertyChangeListener myMockListener;
	
	/**
     * Initialize the test DungeonCharacter and mock random before each test.
     */
	@BeforeEach
	void setUp() {
	    myMockRandom = mock(Random.class);
	    myTestDungeonCharacter = new MockDungeonCharacter(myMockRandom);
	    myTestEnemy = new MockDungeonCharacter(myMockRandom);
	    myMockListener = mock(PropertyChangeListener.class);
	    myTestDungeonCharacter.addPropertyChangeListener(myMockListener);
	}
	
	/**
     * Tests getName() returns the correct name.
     */
    @Test
    void testGetName() {
        assertEquals("MockDngnChar", myTestDungeonCharacter.getName());
    }

    /**
     * Tests getCurHealthPoints() returns the expected current HP.
     */
    @Test
    void testGetCurHealthPoints() {
        assertEquals(100, myTestDungeonCharacter.getCurHealthPoints());
    }

    /**
     * Tests setCurHealthPoints() updates the current HP correctly.
     */
    @Test
    void testSetCurHealthPoints() {
    	myTestDungeonCharacter.setCurHealthPoints(50);
        assertEquals(50, myTestDungeonCharacter.getCurHealthPoints());
    }

    /**
     * Tests getMaxHealthPoints() returns the maximum HP.
     */
    @Test
    void testGetMaxHealthPoints() {
        assertEquals(100, myTestDungeonCharacter.getMaxHealthPoints());
    }

    /**
     * Tests getDamageMin() returns the correct minimum damage.
     */
    @Test
    void testGetDamageMin() {
        assertEquals(10, myTestDungeonCharacter.getDamageMin());
    }

    /**
     * Tests getDamageMax() returns the correct maximum damage.
     */
    @Test
    void testGetDamageMax() {
        assertEquals(20, myTestDungeonCharacter.getDamageMax());
    }

    /**
     * Tests getRandomDamage() returns the correct value using the mock random.
     */
    @Test
    void testGetRandomDamage() {
        when(myMockRandom.nextInt(10, 21)).thenReturn(15);
        assertEquals(15, myTestDungeonCharacter.getRandomDamage());
    }

    /**
     * Tests getAttackSpeed() returns the correct value.
     */
    @Test
    void testGetAttackSpeed() {
        assertEquals(5, myTestDungeonCharacter.getAttackSpeed());
    }

    /**
     * Tests getHitChance() returns the correct value.
     */
    @Test
    void testGetHitChance() {
        assertEquals(0.5, myTestDungeonCharacter.getHitChance());
    }

    /**
     * Tests isAlive() returns true when HP is above zero.
     */
    @Test
    void testIsAliveTrue() {
        assertTrue(myTestDungeonCharacter.isAlive());
    }

    /**
     * Tests isAlive() returns false when HP is 0.
     */
    @Test
    void testIsAliveFalse() {
    	myTestDungeonCharacter.setCurHealthPoints(0);
        assertFalse(myTestDungeonCharacter.isAlive());
    }

    /**
     * Tests isFullHealth() returns true when HP is max.
     */
    @Test
    void testIsFullHealthTrue() {
        assertTrue(myTestDungeonCharacter.isFullHealth());
    }

    /**
     * Tests isFullHealth() returns false when HP is not max.
     */
    @Test
    void testIsFullHealthFalse() {
    	myTestDungeonCharacter.setCurHealthPoints(50);
        assertFalse(myTestDungeonCharacter.isFullHealth());
    }

    /**
     * Tests attack() applies damage when hit chance succeeds.
     */
    @Test
    void testAttackHit() {
        when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(0.1);
        when(myMockRandom.nextInt(10, 21)).thenReturn(15);

        myTestDungeonCharacter.attack(myTestEnemy);
        assertEquals(85, myTestEnemy.getCurHealthPoints());
    }

    /**
     * Tests attack() does not apply damage when hit chance fails.
     */
    @Test
    void testAttackMiss() {
        when(myMockRandom.nextDouble(0.0, 1.0)).thenReturn(0.9);

        myTestDungeonCharacter.attack(myTestEnemy);
        assertEquals(100, myTestEnemy.getCurHealthPoints());
    }
    
    /**
     * Tests that receiveDamage() fires a property change event.
     */
    @Test
    void testReceiveDamageFiresEvent() {
        myTestDungeonCharacter.callReceiveDamage(10);
        assertEquals(90, myTestDungeonCharacter.getCurHealthPoints());

        verify(myMockListener).propertyChange(argThat(e ->
            e.getPropertyName().equals("damageReceived") &&
            e.getNewValue().equals(10)
        ));
    }

    /**
     * Tests that receiveHealing() fires a property change event.
     */
    @Test
    void testReceiveHealingFiresPropertyChange() {
        myTestDungeonCharacter.setCurHealthPoints(50);
        myTestDungeonCharacter.callReceiveHealing(20);

        assertEquals(70, myTestDungeonCharacter.getCurHealthPoints());
        verify(myMockListener).propertyChange(argThat(event ->
            event.getPropertyName().equals("healingReceived") &&
            event.getNewValue().equals(20)
        ));
    }
    
    /**
     * Tests that toString() returns the correct DungeonCharacter stats.
     */
    @Test
	void testToStringOutput() {
		String result = myTestDungeonCharacter.toString();
		assertTrue(result.contains("MockDngnChar"));
		assertTrue(result.contains("HP:  100/100"));
		assertTrue(result.contains("DMG: 10-20"));
		assertTrue(result.contains("SPD: 5"));
		assertTrue(result.contains("ACC: 50.00%"));
	}
}
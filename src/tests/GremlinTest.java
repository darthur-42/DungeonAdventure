/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Gremlin;
import tests.mockclasses.MockHero;

/**
 * Test cases for Gremlin.
 * 
 * @author Anna Brewer
 * @version 3 Mar 2025
 */
class GremlinTest {

    private Random myMockRandom;
    private Gremlin myTestGremlin;
    private MockHero myTestHero;

    /**
     * Sets up test objects before each test.
     */
    @BeforeEach
    void setUp() {
        myMockRandom = mock(Random.class);
        myTestGremlin = new Gremlin(70, 15, 30, 5, 0.8, 20, 40, 0.4, myMockRandom);
        myTestHero = new MockHero(myMockRandom);
    }

    /**
     * Tests Gremlin's initial stats.
     */
    @Test
    void testInitialization() {
        assertEquals("Gremlin", myTestGremlin.getName());
        assertEquals(70, myTestGremlin.getCurHealthPoints(), "Gremlin should start with 70 HP.");
        assertEquals(15, myTestGremlin.getDamageMin(), "Gremlin minimum damage should be 15.");
        assertEquals(30, myTestGremlin.getDamageMax(), "Gremlin maximum damage should be 30.");
        assertEquals(5, myTestGremlin.getAttackSpeed(), "Gremlin attack speed should be 5.");
        assertEquals(0.8, myTestGremlin.getHitChance(), "Gremlin hit chance should be 80%.");
        assertEquals(0.4, myTestGremlin.getHealChance(), "Gremlin heal chance should be 40%.");
    }

    /**
     * Tests that Gremlin successfully attacks.
     */
    @Test
    void testAttack() {
        myTestHero.setCurHealthPoints(100);

        when(myMockRandom.nextDouble()).thenReturn(0.1, 1.0);
        when(myMockRandom.nextInt(15, 31)).thenReturn(12);

        myTestGremlin.castAttackOn(myTestHero);

        assertEquals(88, myTestHero.getCurHealthPoints(), "Hero should take 12 damage.");
    }

    /**
     * Tests Gremlin's healing.
     */
    @Test
    void testHeal() {
        myTestGremlin.setCurHealthPoints(40);

        when(myMockRandom.nextDouble()).thenReturn(0.3);
        when(myMockRandom.nextInt(20, 41)).thenReturn(6);

        myTestGremlin.heal();

        assertEquals(46, myTestGremlin.getCurHealthPoints(), "Gremlin should heal by 6 HP.");
    }

    /**
     * Tests that Gremlin does not heal past max HP.
     */
    @Test
    void testMaxHeal() {
        int maxHP = myTestGremlin.getMaxHealthPoints();
        myTestGremlin.setCurHealthPoints(maxHP - 5);

        when(myMockRandom.nextDouble()).thenReturn(0.3);
        when(myMockRandom.nextInt(20, 41)).thenReturn(10);

        myTestGremlin.heal();

        assertEquals(maxHP, myTestGremlin.getCurHealthPoints(), "Gremlin should not heal past max HP.");
    }

    /**
     * Tests that Gremlin does not heal after fainting (0 HP).
     */
    @Test
    void testFaintedGremlinDoesNotHeal() {
        myTestGremlin.setCurHealthPoints(0);

        when(myMockRandom.nextDouble()).thenReturn(0.3);
        when(myMockRandom.nextInt(20, 41)).thenReturn(10);

        myTestGremlin.heal();

        assertEquals(0, myTestGremlin.getCurHealthPoints(), "Fainted Gremlin should not heal.");
    }

    /**
     * Tests multiple healing attempts.
     */
    @Test
    void testMultipleHeals() {
        myTestGremlin.setCurHealthPoints(50);

        when(myMockRandom.nextDouble()).thenReturn(0.3, 0.3);
        when(myMockRandom.nextInt(20, 41)).thenReturn(7, 6);

        myTestGremlin.heal();
        assertEquals(57, myTestGremlin.getCurHealthPoints(), "Gremlin should heal by 7 HP.");

        myTestGremlin.heal();
        assertEquals(63, myTestGremlin.getCurHealthPoints(), "Gremlin should heal by another 6 HP.");
    }
    
    /**
     * Tests that Gremlin heals after an attack that causes HP loss.
     * Healing is checked after HP is reduced.
     * If no HP is lost or Gremlin has fainted, it should not heal.
     */
    @Test
    void testHealAfterAttack() {
        myTestHero.setCurHealthPoints(100);
        myTestGremlin.setCurHealthPoints(50); 

        when(myMockRandom.nextDouble()).thenReturn(0.1, 1.0, 0.3); 
        when(myMockRandom.nextInt(15, 31)).thenReturn(12); 
        when(myMockRandom.nextInt(20, 41)).thenReturn(7); 

        myTestGremlin.castAttackOn(myTestHero);

        assertEquals(88, myTestHero.getCurHealthPoints(), "Hero should take 12 damage.");
        assertEquals(57, myTestGremlin.getCurHealthPoints(), "Gremlin should heal by 7 HP after attack.");
    }

    /**
     * Tests that a fainted Gremlin cannot attack.
     */
    @Test
    void testFaintedGremlinCannotAttack() {
        myTestGremlin.setCurHealthPoints(0);
        myTestHero.setCurHealthPoints(100);

        myTestGremlin.castAttackOn(myTestHero);

        assertEquals(100, myTestHero.getCurHealthPoints(), "Fainted Gremlin should not attack.");
    }
}

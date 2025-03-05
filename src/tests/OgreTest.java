/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Ogre;
import tests.mockclasses.MockHero;

/**
 * Tests for Ogre.
 * 
 * @author Anna Brewer
 * @version 3 Mar 2025
 */
class OgreTest {

    private Random myMockRandom;
    private Ogre myTestOgre;
    private MockHero myTestHero;

    /**
     * Sets up test objects before each test.
     */
    @BeforeEach
    void setUp() {
        myMockRandom = mock(Random.class);
        myTestOgre = new Ogre(200, 30, 60, 2, 0.6, 30, 60, 0.1, myMockRandom);
        myTestHero = new MockHero(myMockRandom);
    }

    /**
     * Tests Ogre's initial stats.
     */
    @Test
    void testInitialization() {
        assertEquals("Ogre", myTestOgre.getName());
        assertEquals(200, myTestOgre.getCurHealthPoints());
        assertEquals(30, myTestOgre.getDamageMin());
        assertEquals(60, myTestOgre.getDamageMax());
        assertEquals(2, myTestOgre.getAttackSpeed());
        assertEquals(0.6, myTestOgre.getHitChance());
        assertEquals(0.1, myTestOgre.getHealChance());
    }

    /**
     * Tests that Ogre successfully attacks.
     */
    @Test
    void testAttack() {
        myTestHero.setCurHealthPoints(100);

        when(myMockRandom.nextDouble()).thenReturn(0.5, 1.0);
        when(myMockRandom.nextInt(30, 61)).thenReturn(40);

        myTestOgre.castAttackOn(myTestHero);

        assertEquals(60, myTestHero.getCurHealthPoints(), "Hero should take 40 damage.");
    }

    /**
     * Tests Ogre's healing.
     */
    @Test
    void testHeal() {
        myTestOgre.setCurHealthPoints(150);

        when(myMockRandom.nextDouble()).thenReturn(0.05);
        when(myMockRandom.nextInt(30, 61)).thenReturn(12);

        myTestOgre.heal();

        assertEquals(162, myTestOgre.getCurHealthPoints(), "Ogre should heal by 12 HP.");
    }

    /**
     * Tests that Ogre does not heal past max HP.
     */
    @Test
    void testMaxHeal() {
        int maxHP = myTestOgre.getMaxHealthPoints();
        myTestOgre.setCurHealthPoints(maxHP - 5);

        when(myMockRandom.nextDouble()).thenReturn(0.05);
        when(myMockRandom.nextInt(30, 61)).thenReturn(10);

        myTestOgre.heal();

        assertEquals(maxHP, myTestOgre.getCurHealthPoints(), "Ogre should not heal past max HP.");
    }

    /**
     * Tests that Ogre does not heal after fainting (0 HP).
     */
    @Test
    void testFaintedOgreDoesNotHeal() {
        myTestOgre.setCurHealthPoints(0);

        when(myMockRandom.nextDouble()).thenReturn(0.05);
        when(myMockRandom.nextInt(30, 61)).thenReturn(10);

        myTestOgre.heal();

        assertEquals(0, myTestOgre.getCurHealthPoints(), "Fainted Ogre should not heal.");
    }

    /**
     * Tests multiple healing attempts.
     */
    @Test
    void testMultipleHeals() {
        myTestOgre.setCurHealthPoints(150);

        when(myMockRandom.nextDouble()).thenReturn(0.05, 0.05);
        when(myMockRandom.nextInt(30, 61)).thenReturn(12, 8);

        myTestOgre.heal();
        assertEquals(162, myTestOgre.getCurHealthPoints(), "Ogre should heal by 12 HP.");

        myTestOgre.heal();
        assertEquals(170, myTestOgre.getCurHealthPoints(), "Ogre should heal by another 8 HP.");
    }

    /**
     * Tests that Ogre heals after an attack that causes HP loss.
     * Healing is checked after HP is reduced.
     * If no HP is lost or Ogre has fainted, it should not heal.
     */
    @Test
    void testHealAfterAttack() {
        myTestHero.setCurHealthPoints(100);
        myTestOgre.setCurHealthPoints(160); 

        when(myMockRandom.nextDouble()).thenReturn(0.5, 1.0, 0.05); 
        when(myMockRandom.nextInt(30, 61)).thenReturn(40, 12); 

        myTestOgre.castAttackOn(myTestHero);

        assertEquals(60, myTestHero.getCurHealthPoints(), "Hero should take 40 damage.");
        assertEquals(172, myTestOgre.getCurHealthPoints(), "Ogre should heal by 12 HP after attack.");
    }

    /**
     * Tests that a fainted Ogre cannot attack.
     */
    @Test
    void testFaintedOgreCannotAttack() {
        myTestOgre.setCurHealthPoints(0);
        myTestHero.setCurHealthPoints(100);

        myTestOgre.castAttackOn(myTestHero);

        assertEquals(100, myTestHero.getCurHealthPoints(), "Fainted Ogre should not attack.");
    }
}

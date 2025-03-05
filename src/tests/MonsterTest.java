/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;
import java.beans.PropertyChangeListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.mockclasses.MockMonster;
import tests.mockclasses.MockHero;

/**
 * Test cases for Monster.
 * 
 * @author Anna Brewer
 * @version 3 Mar 2025
 */
class MonsterTest {

    private Random myMockRandom;
    private MockMonster myTestMonster;
    private MockHero myTestHero;

    /**
     * Initializes the test Monster and Hero with a mock random instance before each test.
     */
    @BeforeEach
    void setUp() {
        myMockRandom = mock(Random.class);
        myTestMonster = new MockMonster("Mock Monster", 100, 10, 20, 3, 0.7, 5, 10, 0.5, myMockRandom);
        myTestHero = new MockHero(myMockRandom);
    }

    /**
     * Tests if the Monster is initialized correctly.
     */
    @Test
    void testMonsterInitialization() {
        assertEquals("Mock Monster", myTestMonster.getName());
        assertTrue(myTestMonster.getCurHealthPoints() > 0, "Monster should have positive health.");
        assertTrue(myTestMonster.getDamageMin() > 0, "Monster should have a positive minimum damage.");
    }

    /**
     * Tests if heal() increases health correctly but does not exceed max health.
     */
    @Test
    void testHealIncreasesHealth() {
        int maxHP = myTestMonster.getMaxHealthPoints();
        myTestMonster.setCurHealthPoints(maxHP - 10);

        when(myMockRandom.nextDouble()).thenReturn(0.3);
        when(myMockRandom.nextInt(myTestMonster.getHealingMin(), myTestMonster.getHealingMax() + 1)).thenReturn(10);

        myTestMonster.heal();

        assertEquals(maxHP, myTestMonster.getCurHealthPoints(), "Monster should not exceed max health.");
    }

    /**
     * Tests that heal() does not happen if the heal chance fails.
     */
    @Test
    void testHealFailsWhenChanceMisses() {
        myTestMonster.setCurHealthPoints(50);
        when(myMockRandom.nextDouble()).thenReturn(0.6);

        myTestMonster.heal();

        assertEquals(50, myTestMonster.getCurHealthPoints(), "Monster should not heal when chance fails.");
    }

    /**
     * Tests if castAttackOn() correctly reduces the target's health.
     */
    @Test
    void testCastAttackOnReducesHealth() {
        myTestHero.setCurHealthPoints(100);

        when(myMockRandom.nextDouble()).thenReturn(0.1, 1.0);
        when(myMockRandom.nextInt(myTestMonster.getDamageMin(), myTestMonster.getDamageMax() + 1)).thenReturn(15);

        myTestMonster.castAttackOn(myTestHero);

        assertEquals(85, myTestHero.getCurHealthPoints(), "Hero should take 15 damage.");
    }

    /**
     * Tests that a Monster cannot heal beyond its maximum HP.
     */
    @Test
    void testMonsterCannotHealPastMaxHP() {
        int maxHP = myTestMonster.getMaxHealthPoints();
        myTestMonster.setCurHealthPoints(maxHP - 5);

        when(myMockRandom.nextDouble()).thenReturn(0.3);
        when(myMockRandom.nextInt(myTestMonster.getHealingMin(), myTestMonster.getHealingMax() + 1)).thenReturn(10);

        myTestMonster.heal();

        assertEquals(maxHP, myTestMonster.getCurHealthPoints(), "Monster should not heal past max HP.");
    }

    /**
     * Tests that a dead Monster cannot attack.
     */
    @Test
    void testMonsterDoesNotAttackWhenDead() {
        myTestMonster.setCurHealthPoints(0);
        myTestHero.setCurHealthPoints(100);

        myTestMonster.castAttackOn(myTestHero);

        assertEquals(100, myTestHero.getCurHealthPoints(), "Monster should not attack when dead.");
    }

    /**
     * Tests that an attack fails when hit chance is too low.
     */
    @Test
    void testAttackMissesWhenHitChanceFails() {
        myTestHero.setCurHealthPoints(100);

        when(myMockRandom.nextDouble()).thenReturn(0.9);

        myTestMonster.castAttackOn(myTestHero);

        assertEquals(100, myTestHero.getCurHealthPoints(), "Hero should not take damage if attack misses.");
    }

    /**
     * Tests that a Monster can heal multiple times correctly.
     */
    @Test
    void testMonsterHealsMultipleTimesCorrectly() {
        myTestMonster.setCurHealthPoints(50);

        when(myMockRandom.nextDouble()).thenReturn(0.3, 0.3);
        when(myMockRandom.nextInt(myTestMonster.getHealingMin(), myTestMonster.getHealingMax() + 1)).thenReturn(7, 6);

        myTestMonster.heal();
        assertEquals(57, myTestMonster.getCurHealthPoints(), "Monster should heal by 7 HP.");

        myTestMonster.heal();
        assertEquals(63, myTestMonster.getCurHealthPoints(), "Monster should heal by another 6 HP.");
    }

    /**
     * Tests that heal() occurs after an attack (and the monster has lost HP).
     */
    @Test
    void testHealAfterAttack() {
        myTestMonster.setCurHealthPoints(50);
        myTestHero.setCurHealthPoints(100);

        when(myMockRandom.nextDouble()).thenReturn(0.2);
        when(myMockRandom.nextInt(myTestMonster.getHealingMin(), myTestMonster.getHealingMax() + 1)).thenReturn(7);

        myTestMonster.castAttackOn(myTestHero);

        assertEquals(57, myTestMonster.getCurHealthPoints(), "Monster should heal after attack.");
    }

    /**
     * Tests that a Monster cannot heal when it is dead (HP = 0).
     */
    @Test
    void testHealDoesNotHappenIfMonsterIsDead() {
        myTestMonster.setCurHealthPoints(0);

        when(myMockRandom.nextDouble()).thenReturn(0.2);

        myTestMonster.heal();

        assertEquals(0, myTestMonster.getCurHealthPoints(), "Monster should not heal when it's dead.");
    }

    /**
     * Tests that PropertyChangeListener fires when health changes.
     */
    @Test
    void testPropertyChangeListenerFiresOnHeal() {
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        myTestMonster.addPropertyChangeListener(mockListener);

        myTestMonster.setCurHealthPoints(50);
        when(myMockRandom.nextDouble()).thenReturn(0.3);
        when(myMockRandom.nextInt(myTestMonster.getHealingMin(), myTestMonster.getHealingMax() + 1)).thenReturn(6);

        myTestMonster.heal();

        verify(mockListener).propertyChange(argThat(event -> 
            event.getPropertyName().equals("health") &&
            event.getOldValue().equals(50) &&
            event.getNewValue().equals(56)
        ));
    }
}

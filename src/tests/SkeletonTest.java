/**
 * TCSS 360 Group Project
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Skeleton;
import tests.mockclasses.MockHero;

/**
 * Tests for Skeleton.
 * 
 * @author Anna Brewer
 * @version 3 Mar 2025
 */
class SkeletonTest {

    private Random myMockRandom;
    private Skeleton myTestSkeleton;
    private MockHero myTestHero;

    /**
     * Sets up test objects before each test.
     */
    @BeforeEach
    void setUp() {
        myMockRandom = mock(Random.class);
        myTestSkeleton = new Skeleton(100, 30, 50, 3, 0.8, 30, 50, 0.3, myMockRandom);
        myTestHero = new MockHero(myMockRandom);
    }

    /**
     * Tests Skeleton's initial stats.
     */
    @Test
    void testInitialization() {
        assertEquals("Skeleton", myTestSkeleton.getName());
        assertEquals(100, myTestSkeleton.getCurHealthPoints());
        assertEquals(30, myTestSkeleton.getDamageMin());
        assertEquals(50, myTestSkeleton.getDamageMax());
        assertEquals(3, myTestSkeleton.getAttackSpeed());
        assertEquals(0.8, myTestSkeleton.getHitChance());
        assertEquals(0.3, myTestSkeleton.getHealChance());
    }

    /**
     * Tests that Skeleton successfully attacks.
     */
    @Test
    void testAttack() {
        myTestHero.setCurHealthPoints(100);

        when(myMockRandom.nextDouble()).thenReturn(0.5, 1.0);
        when(myMockRandom.nextInt(30, 51)).thenReturn(40);

        myTestSkeleton.castAttackOn(myTestHero);

        assertEquals(60, myTestHero.getCurHealthPoints(), "Hero should take 40 damage.");
    }

    /**
     * Tests Skeleton's healing.
     */
    @Test
    void testHeal() {
        myTestSkeleton.setCurHealthPoints(60);

        when(myMockRandom.nextDouble()).thenReturn(0.05);
        when(myMockRandom.nextInt(30, 51)).thenReturn(12);

        myTestSkeleton.heal();

        assertEquals(72, myTestSkeleton.getCurHealthPoints(), "Skeleton should heal by 12 HP.");
    }

    /**
     * Tests that Skeleton does not heal past max HP.
     */
    @Test
    void testMaxHeal() {
        int maxHP = myTestSkeleton.getMaxHealthPoints();
        myTestSkeleton.setCurHealthPoints(maxHP - 5);

        when(myMockRandom.nextDouble()).thenReturn(0.05);
        when(myMockRandom.nextInt(30, 51)).thenReturn(10);

        myTestSkeleton.heal();

        assertEquals(maxHP, myTestSkeleton.getCurHealthPoints(), "Skeleton should not heal past max HP.");
    }

    /**
     * Tests that Skeleton does not heal after fainting (0 HP).
     */
    @Test
    void testFaintedSkeletonDoesNotHeal() {
        myTestSkeleton.setCurHealthPoints(0);

        when(myMockRandom.nextDouble()).thenReturn(0.05);
        when(myMockRandom.nextInt(30, 51)).thenReturn(10);

        myTestSkeleton.heal();

        assertEquals(0, myTestSkeleton.getCurHealthPoints(), "Fainted Skeleton should not heal.");
    }

    /**
     * Tests multiple healing attempts.
     */
    @Test
    void testMultipleHeals() {
        myTestSkeleton.setCurHealthPoints(50);

        when(myMockRandom.nextDouble()).thenReturn(0.05, 0.05);
        when(myMockRandom.nextInt(30, 51)).thenReturn(12, 8);

        myTestSkeleton.heal();
        assertEquals(62, myTestSkeleton.getCurHealthPoints(), "Skeleton should heal by 12 HP.");

        myTestSkeleton.heal();
        assertEquals(70, myTestSkeleton.getCurHealthPoints(), "Skeleton should heal by another 8 HP.");
    }

    /**
     * Tests that Skeleton heals after an attack that causes HP loss.
     * Healing is checked after HP is reduced.
     * If no HP is lost or Skeleton has fainted, it should not heal.
     */
    @Test
    void testHealAfterAttack() {
        myTestHero.setCurHealthPoints(100);
        myTestSkeleton.setCurHealthPoints(80);

        when(myMockRandom.nextDouble()).thenReturn(0.5, 1.0, 0.05);
        when(myMockRandom.nextInt(30, 51)).thenReturn(40, 12);

        myTestSkeleton.castAttackOn(myTestHero);

        assertEquals(60, myTestHero.getCurHealthPoints(), "Hero should take 40 damage.");
        assertEquals(92, myTestSkeleton.getCurHealthPoints(), "Skeleton should heal by 12 HP after attack.");
    }

    /**
     * Tests that a fainted Skeleton cannot attack.
     */
    @Test
    void testFaintedSkeletonCannotAttack() {
        myTestSkeleton.setCurHealthPoints(0);
        myTestHero.setCurHealthPoints(100);

        myTestSkeleton.castAttackOn(myTestHero);

        assertEquals(100, myTestHero.getCurHealthPoints(), "Fainted Skeleton should not attack.");
    }
}

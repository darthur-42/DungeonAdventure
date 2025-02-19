/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Ogre test class
 * 
 * @author Anna Brewer
 * @version 18 Feb 2025
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OgreTest {

    private MockOgre mockOgre;
    private MockMonster testMonster;

    @BeforeEach
    void setUp() {
        mockOgre = new MockOgre();
        testMonster = new MockMonster("Test Monster", 100, 10, 20, 3, 0.8, 5, 15, 0.5);
    }

    @Test
    void testMockOgreAttack() {
        int initialHealth = testMonster.getHealth();
        mockOgre.castAttackOn(testMonster);
        assertTrue(testMonster.getHealth() < initialHealth);
        assertTrue(testMonster.getHealth() >= 0);
    }

    private static class MockOgre extends Ogre {
        @Override
        protected void castAttackOn(DungeonCharacter target) {
            if (target instanceof MockMonster) {
                ((MockMonster) target).reduceHealth(15);
            }
        }
    }

    private static class MockMonster extends Monster {
        protected MockMonster(String name, int health, int damageMin, int damageMax, int attackSpeed, double hitChance, int healMin, int healMax, double healChance) {
            super(name, health, damageMin, damageMax, attackSpeed, hitChance, healMin, healMax, healChance);
        }

        public int getHealth() {
            return super.getHealthPoints();
        }

        public void reduceHealth(int damage) {
            super.setHealthPoints(getHealthPoints() - damage);
        }

        @Override
        protected void castAttackOn(DungeonCharacter target) {
            target.setHealthPoints(target.getHealthPoints() - getDamageMin());
        }
    }
}

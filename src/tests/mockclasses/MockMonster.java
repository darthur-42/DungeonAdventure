/**
 * TCSS 360 Group Project
 */
package tests.mockclasses;

import java.util.Random;
import model.DungeonCharacter;
import model.Monster;

/**
 * A mock version of Monster, designed for testing the abstract class.
 * 
 * @author Anna Brewer
 * @version 3 Mar 2025
 */
public class MockMonster extends Monster {

    /**
     * Constructs a MockMonster with custom stats for testing.
     * 
     * @param theName         the monster's name
     * @param theHealthPoints the monster's health points
     * @param theDamageMin    the minimum damage
     * @param theDamageMax    the maximum damage
     * @param theAttackSpeed  the attack speed
     * @param theHitChance    the chance to hit
     * @param theHealMin      the minimum heal amount
     * @param theHealMax      the maximum heal amount
     * @param theHealChance   the chance to heal
     * @param theRandom       the random instance
     */
    public MockMonster(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                       final int theAttackSpeed, final double theHitChance, final int theHealMin, final int theHealMax,
                       final double theHealChance, final Random theRandom) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
              theHealMin, theHealMax, theHealChance, theRandom);
    }

    /**
     * Attacks a character and heals if not at 0 HP.
     *
     * @param theOtherCharacter the character being attacked.
     */
    @Override
    public void castAttackOn(final DungeonCharacter theOtherCharacter) {
        attack(theOtherCharacter);

        if (getCurHealthPoints() > 0) {
            heal();
        }
    }
}

/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public abstract class Monster extends DungeonCharacter {
    private int myHealMin;
    private int myHealMax;
    private double myHealChance;

    /**
     * Constructs a Monster. Can pass in a random instance for testing.
     * 
     * @param theName the monster's name
     * @param theHealthPoints the monster's health points
     * @param theDamageMin minimum damage
     * @param theDamageMax maximum damage
     * @param theAttackSpeed attack speed
     * @param theHitChance chance to hit
     * @param theHealMin minimum heal amount
     * @param theHealMax maximum heal amount
     * @param theHealChance chance to heal
     * @param theRandom random instance
     */
    protected Monster(final String theName, final int theHealthPoints, final int theDamageMin,
                   final int theDamageMax, final int theAttackSpeed, final double theHitChance,
                   final int theHealMin, final int theHealMax, final double theHealChance,
                   final Random theRandom) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
                theRandom);
        myHealMin = theHealMin;
        myHealMax = theHealMax;
        myHealChance = theHealChance;
    }

    protected int getHealMin() {
        return myHealMin;
    }

    protected int getHealMax() {
        return myHealMax;
    }

    protected double getHealChance() {
        return myHealChance;
    }

    /**
     * Heals the monster if conditions are met.
     */
    protected void heal() {
        if (getCurHealthPoints() > 0 && myRandom.nextDouble() < myHealChance) {
            int healAmount = myRandom.nextInt(myHealMin, myHealMax + 1);
            setCurHealthPoints(getCurHealthPoints() + healAmount);
            System.out.println(getName() + " healed for " + healAmount + " HP!");
        }
    }

    /**
     * Attacks the target.
     * @param theTarget the character being attacked
     */
    protected abstract void castAttackOn(DungeonCharacter theTarget);
}

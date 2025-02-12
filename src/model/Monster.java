/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a monster in the dungeon.
 * 
 * @author Anna Brewer
 * @version 11 Feb 2025
 */
public abstract class Monster extends DungeonCharacter {
    private int myHealMin;
    private int myHealMax;
    private double myHealChance;
    private Random myRandom = new Random();

    /**
     * Constructs a Monster.
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
     */
    protected Monster(final String theName, final int theHealthPoints, final int theDamageMin,
                   final int theDamageMax, final double theAttackSpeed, final double theHitChance,
                   final int theHealMin, final int theHealMax, final double theHealChance) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance);
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
        if (getHealthPoints() > 0 && myRandom.nextDouble() < myHealChance) {
            int healAmount = myRandom.nextInt(myHealMin, myHealMax + 1);
            setHealthPoints(getHealthPoints() + healAmount);
            System.out.println(getName() + " healed for " + healAmount + " HP!");
        }
    }

    /**
     * Attacks the target.
     * @param theTarget the character being attacked
     */
    protected abstract void castAttackOn(DungeonCharacter theTarget);
}

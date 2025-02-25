/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Factory for creating Monster objects.
 * 
 * @author Anna Brewer
 * @version 23 Feb 2025
 */
public class MonsterFactory {

    /**
     * Creates a Monster with its characteristics.
     *
     * @param theType the monster type
     * @param theHealthPoints the monster health points
     * @param theDamageMin the monster minimum damage
     * @param theDamageMax the monster maximum damage
     * @param theAttackSpeed the monster attack speed
     * @param theHitChance the monster hit chance
     * @param theHealMin the monster minimum heal amount
     * @param theHealMax the monster maximum heal amount
     * @param theHealChance the monster heal chance
     * @param theRandom the random instance
     * @return the Monster
     * @throws IllegalArgumentException if the MonsterType is invalid
     */
    public static Monster createMonster(final MonsterType theType, final int theHealthPoints, final int theDamageMin,
                                        final int theDamageMax, final int theAttackSpeed, final double theHitChance,
                                        final int theHealMin, final int theHealMax, final double theHealChance,
                                        final Random theRandom) {
        return switch (theType) {
            case GREMLIN -> new Gremlin(theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
                                        theHealMin, theHealMax, theHealChance, theRandom);
            case OGRE -> new Ogre(theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
                                  theHealMin, theHealMax, theHealChance, theRandom);
            case SKELETON -> new Skeleton(theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theHitChance,
                                          theHealMin, theHealMax, theHealChance, theRandom);
            default -> throw new IllegalArgumentException("Invalid monster type");  
        };
    }
}
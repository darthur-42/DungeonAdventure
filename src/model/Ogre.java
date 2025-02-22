/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents an Ogre monster.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public class Ogre extends Monster {
    
    /**
     * Constructs an Ogre.
     */
	protected Ogre() {
        this(new Random());
    }
    
    /**
     * Constructs an Ogre.
     */
	protected Ogre(final Random theRandom) {
        super("Ogre", 200, 30, 60, 2, 0.6, 30, 60, 0.1, theRandom);
    }

	/**
     * Attacks the target and may heal.
     * 
     * @param theTarget the character being attacked
     */
    @Override
    protected void castAttackOn(final DungeonCharacter theTarget) {
        System.out.println(getName() + " attacks " + theTarget.getName() + "!");
        attack(theTarget);
        heal();
    }
}

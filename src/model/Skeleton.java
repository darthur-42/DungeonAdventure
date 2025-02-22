/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a Skeleton monster.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public class Skeleton extends Monster {
    
    /**
     * Constructs a Skeleton..
     */
	protected Skeleton() {
        this(new Random());
    }
    
    /**
     * Constructs a Skeleton..
     */
	protected Skeleton(final Random theRandom) {
        super("Skeleton", 100, 30, 50, 3, 0.8, 30, 50, 0.3, theRandom);
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

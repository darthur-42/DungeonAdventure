/**
 * TCSS 360 Group Project
 */
package model;

import java.util.Random;

/**
 * Represents a Gremlin monster.
 * 
 * @author Anna Brewer
 * @version 22 Feb 2025
 */
public class Gremlin extends Monster {
    
    /**
     * Constructs a Gremlin.
     */
    protected Gremlin() {
        this(new Random());
    }
    
    /**
     * Constructs a Gremlin. Can pass in a random instance for testing.
     */
    protected Gremlin(final Random theRandom) {
        super("Gremlin", 70, 15, 30, 5, 0.8, 20, 40, 0.4, theRandom);
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

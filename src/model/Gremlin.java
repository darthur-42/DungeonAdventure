/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents a Gremlin monster.
 * 
 * @author Anna Brewer
 * @version 11 Feb 2025
 */
public class Gremlin extends Monster {
    
    /**
     * Constructs a Gremlin.
     */
    protected Gremlin() {
        super("Gremlin", 70, 15, 30, 5.0, 0.8, 20, 40, 0.4);
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

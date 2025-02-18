/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents a Skeleton monster.
 * 
 * @author Anna Brewer
 * @version 11 Feb 2025
 */
public class Skeleton extends Monster {
    
    /**
     * Constructs a Skeleton..
     */
	protected Skeleton() {
        super("Skeleton", 100, 30, 50, 3.0, 0.8, 30, 50, 0.3);
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

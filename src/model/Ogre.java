/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents an Ogre monster.
 * 
 * @author Anna Brewer
 * @version 11 Feb 2025
 */
public class Ogre extends Monster {
    
    /**
     * Constructs an Ogre.
     */
	protected Ogre() {
        super("Ogre", 200, 30, 60, 2.0, 0.6, 30, 60, 0.1);
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

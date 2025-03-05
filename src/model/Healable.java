/**
 * 
 */
package model;

import java.util.Random;

/**
 * This interface represents an entity within the Dungeon that can heal itself.
 * 
 * @author Justin Le
 * @version 3 Mar 2025
 */
interface Healable {
	
	/**
	 * Returns the Healable's minimum healing.
	 * 
	 * @return the Healable's minimum healing
	 */
	int getHealingMin();
	
	/**
	 * Returns the Healable's maximum healing.
	 * 
	 * @return the Healable's maximum healing
	 */
	int getHealingMax();
	
	/**
	 * Sets the Healable's healing range; only used during construction.
	 * 
	 * @param newHealingMin new minimum healing
	 * @param newHealingMax new maximum healing
	 */
	void setHealingRange(final int newHealingMin, final int newHealingMax);
	
	/**
	 * Returns a random number in the Healable's healing range.
	 * 
	 * @return a random number in the Healable's healing range
	 */
	int getRandomHealing();
	
	/**
	 * Returns the Healable's healing chance.
	 * 
	 * @return the Healable's healing chance
	 */
	double getHealingChance();
	
	/**
	 * Sets the Healable's healing chance; only used during construction.
	 * 
	 * @param newHealingChance new healing chance
	 */
	void setHealingChance(final double newHealingChance);
	
	/**
	 * Perform a heal on the Healable.
	 */
	void heal();
	
	/**
	 * Returns a random number in the Healable's healing range.
	 * 
	 * @param theRandomInstance the random instance of this entity
	 * @return a random number in the Healable's healing range
	 */
	default int getRandomHealing(final Random theRandomInstance) {
		return theRandomInstance.nextInt(getHealingMin(), getHealingMax() + 1);
	}
	
	/**
	 * Perform a heal on the Healable.
	 * 
	 * @param theRandomInstance the random instance of the Healable
	 */
	default void heal(final Random theRandomInstance) {
		if (this instanceof DungeonCharacter theCharacter) {
			double healRequirement = theRandomInstance.nextDouble(0, 1);
			
			if (getHealingChance() >= healRequirement) {
				theCharacter.updateCurHealthPoints(getRandomHealing());
			}
		} else {
			throw new IllegalStateException("Only DungeonCharacters can implement Healable.");
		}
	}
	
}

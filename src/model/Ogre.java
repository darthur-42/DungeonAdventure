/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents an Ogre monster, loading stats from the database.
 * 
 * @author Anna Brewer
 * @version 18 Feb 2025
 */
public class Ogre extends Monster {

	/**
	 * Constructs an Ogre by retrieving its stats from the database.
	 */
	protected Ogre() {
		super("Ogre",
				MonsterDatabase.getMonster("Ogre").getHealthPoints(),
				MonsterDatabase.getMonster("Ogre").getDamageMin(),
				MonsterDatabase.getMonster("Ogre").getDamageMax(),
				MonsterDatabase.getMonster("Ogre").getAttackSpeed(),
				MonsterDatabase.getMonster("Ogre").getHitChance(),
				MonsterDatabase.getMonster("Ogre").getHealMin(),
				MonsterDatabase.getMonster("Ogre").getHealMax(),
				MonsterDatabase.getMonster("Ogre").getHealChance());
	}

	/**
	 * Attacks the target and may heal.
	 * 
	 * @param theTarget The character being attacked.
	 */
	@Override
	protected void castAttackOn(final DungeonCharacter theTarget) {
		System.out.println(getName() + " attacks " + theTarget.getName() + "!");
		attack(theTarget);
		heal();
	}
}
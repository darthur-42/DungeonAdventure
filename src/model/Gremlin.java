/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents a Gremlin monster, loading stats from the database.
 * 
 * @author Anna Brewer
 * @version 18 Feb 2025
 */
public class Gremlin extends Monster {

	/**
	 * Constructs a Gremlin by retrieving its stats from the database.
	 */
	protected Gremlin() {
		super("Gremlin",
				MonsterDatabase.getMonster("Gremlin").getHealthPoints(),
				MonsterDatabase.getMonster("Gremlin").getDamageMin(),
				MonsterDatabase.getMonster("Gremlin").getDamageMax(),
				MonsterDatabase.getMonster("Gremlin").getAttackSpeed(),
				MonsterDatabase.getMonster("Gremlin").getHitChance(),
				MonsterDatabase.getMonster("Gremlin").getHealMin(),
				MonsterDatabase.getMonster("Gremlin").getHealMax(),
				MonsterDatabase.getMonster("Gremlin").getHealChance());
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
/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents a Skeleton monster, loading stats from the database.
 * 
 * @author Anna Brewer
 * @version 18 Feb 2025
 */
public class Skeleton extends Monster {

	/**
	 * Constructs a Skeleton by retrieving its stats from the database.
	 */
	protected Skeleton() {
		super("Skeleton",
				MonsterDatabase.getMonster("Skeleton").getHealthPoints(),
				MonsterDatabase.getMonster("Skeleton").getDamageMin(),
				MonsterDatabase.getMonster("Skeleton").getDamageMax(),
				MonsterDatabase.getMonster("Skeleton").getAttackSpeed(),
				MonsterDatabase.getMonster("Skeleton").getHitChance(),
				MonsterDatabase.getMonster("Skeleton").getHealMin(),
				MonsterDatabase.getMonster("Skeleton").getHealMax(),
				MonsterDatabase.getMonster("Skeleton").getHealChance());
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
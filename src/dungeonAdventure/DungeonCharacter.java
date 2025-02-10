/**
 * 
 */
package dungeonAdventure;

import java.util.Random;

/**
 * 
 */
public abstract class DungeonCharacter {
	private Random random = new Random();
	
	private String name;
	private int healthPoints;
	private int damageMin;
	private int damageMax;
	private double attackSpeed;
	private double hitChance;
	
	public DungeonCharacter(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, double theAttackSpeed, double theHitChance) {
		name = theName;
		healthPoints = theHealthPoints;
		damageMin = theDamageMin;
		damageMax = theDamageMax;
		attackSpeed = theAttackSpeed;
		hitChance = theHitChance;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	
	public void setHealthPoints(int newHealthPoints) {
		healthPoints = newHealthPoints;
	}
	
	public int getDamageMin() {
		return damageMin;
	}
	
	public int getDamageMax() {
		return damageMax;
	}
	
	public int getRandomDamage() {
		return random.nextInt(getDamageMin(), getDamageMax() + 1);
	}
	
	public double getAttackSpeed() {
		return attackSpeed;
	}
	
	public double getHitChance() {
		return hitChance;
	}
	
	public void attack(DungeonCharacter otherCharacter) {
		double thisHitChance = random.nextDouble(0, getHitChance());
		double thisHitRequirement = random.nextDouble(0, 100);
		
		if (thisHitChance >= thisHitRequirement) {
			int otherNewHealthPoints = otherCharacter.getHealthPoints() - getRandomDamage();
			
			otherCharacter.setHealthPoints(otherNewHealthPoints);
		}
	}
}


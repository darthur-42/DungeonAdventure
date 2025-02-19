package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

/**
 * Manages the SQLite database for storing and retrieving monster data.
 *
 * @author Anna Brewer
 * @version 18 Feb 2025
 */
public class MonsterDatabase {

	private static SQLiteDataSource ds = null;

	/**
	 * Establishes a connection to the SQLite database.
	 */
	public static void initializeDatabase() {
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:monsters.db");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0); 
		}
		System.out.println("Opened database successfully");
	}

	/**
	 * Creates the monsters table if it does not exist.
	 */
	public static void createTable() {
		String query = "CREATE TABLE IF NOT EXISTS monsters ( " + "name TEXT PRIMARY KEY, " + "health INTEGER, "
				+ "damage_min INTEGER, " + "damage_max INTEGER, " + "attack_speed INTEGER, " + "hit_chance REAL, "
				+ "heal_min INTEGER, " + "heal_max INTEGER, " + "heal_chance REAL)";

		try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
			int rv = stmt.executeUpdate(query);
			System.out.println("executeUpdate() returned " + rv);
			System.out.println("Monster table created successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Inserts monsters into the database.
	 */
	public static void insertMonsters() {
		System.out.println("Attempting to insert monsters into the database");

		String[] queries = { "INSERT OR IGNORE INTO monsters VALUES ('Ogre', 200, 30, 60, 2, 0.6, 30, 60, 0.1)",
				"INSERT OR IGNORE INTO monsters VALUES ('Gremlin', 70, 15, 30, 5, 0.8, 20, 40, 0.4)",
				"INSERT OR IGNORE INTO monsters VALUES ('Skeleton', 100, 30, 50, 3, 0.8, 30, 50, 0.3)" };

		try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
			for (String query : queries) {
				int rv = stmt.executeUpdate(query);
				System.out.println("executeUpdate() returned " + rv);
			}
			System.out.println("Default monsters inserted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Retrieves a monster's stats from the database.
	 *
	 * @param name Monster name
	 * @return A Monster object with retrieved stats, or null if not found.
	 */
	public static Monster getMonster(String name) {
		String query = "SELECT * FROM monsters WHERE name = ?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Monster '" + name + "' loaded from database.");
				return new Monster(rs.getString("name"), rs.getInt("health"), rs.getInt("damage_min"),
						rs.getInt("damage_max"), rs.getInt("attack_speed"), rs.getDouble("hit_chance"),
						rs.getInt("heal_min"), rs.getInt("heal_max"), rs.getDouble("heal_chance")) {
					@Override
					protected void castAttackOn(DungeonCharacter theTarget) {
						System.out.println(getName() + " attacks " + theTarget.getName() + "!");
						attack(theTarget);
						heal();
					}
				};
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		System.out.println("Warning: Monster '" + name + "' not found in database.");
		return null;
	}

	/**
	 * Queries all monsters in the database and displays them.
	 */
	public static void displayMonsters() {
		System.out.println("Selecting all rows from monsters table");
		String query = "SELECT * FROM monsters";

		try (Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				System.out.println("Monster: " + rs.getString("name") + " | HP: " + rs.getInt("health") + " | Damage: "
						+ rs.getInt("damage_min") + "-" + rs.getInt("damage_max") + " | Speed: "
						+ rs.getInt("attack_speed") + " | Hit Chance: " + rs.getDouble("hit_chance") + " | Heal: "
						+ rs.getInt("heal_min") + "-" + rs.getInt("heal_max") + " | Heal Chance: "
						+ rs.getDouble("heal_chance"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Sets up the monster database.
	 */
	public static void main(String[] args) {
		initializeDatabase();
		createTable();
		insertMonsters();
		displayMonsters();
		System.out.println("Database setup complete.");
	}
}
/**
 * TCSS 360 Group Project
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

/**
 * Stores and retrieves Monster data in a SQLite database.
 *
 * @author Anna Brewer
 * @version 19 Mar 2025
 */
public class MonsterDatabase {

	/** Database file path for the SQLite database. */
	private static final String DB_FILE = "jdbc:sqlite:monsters.db";

	/** Connection to the database. */
	private Connection myConn;
	
	/** Statement object for executing SQL queries. */
	private Statement myStmt;

	/**
	 * Establishes a connection to the SQLite database.
	 */
	public MonsterDatabase() {
		initializeDatabase();
	}

	/**
	 * Initializes the database connection.
	 */
	private void initializeDatabase() {
		SQLiteDataSource ds = null;

		try {
			ds = new SQLiteDataSource();
			ds.setUrl(DB_FILE);

			myConn = ds.getConnection();
			myStmt = myConn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Retrieves all stored monster data from the database.
	 *
	 * @return a list of Object arrays containing monster data
	 */
	public List<Object[]> getAllMonstersData() {
		List<Object[]> monsterDataList = new ArrayList<>();

		String query = "SELECT * FROM monsters";

		try (Connection conn = myConn;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				monsterDataList.add(retrieveMonsterData(rs));
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving monsters from database: " + e.getMessage());
		}

		return monsterDataList;
	}

	/**
	 * Retrieves monster data from a ResultSet.
	 *
	 * @param theRs the ResultSet containing monster data
	 * @return an Object array with the monster's stored data
	 */
	private Object[] retrieveMonsterData(ResultSet theRs) throws SQLException {
		return new Object[] { theRs.getString("name"), theRs.getInt("health_points"), theRs.getInt("damage_min"),
				theRs.getInt("damage_max"), theRs.getInt("attack_speed"), theRs.getDouble("hit_chance"),
				theRs.getInt("heal_min"), theRs.getInt("heal_max"), theRs.getDouble("heal_chance") };
	}

	/**
	 * Closes the database connection when done.
	 */
	public void close() {
		try {
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (SQLException e) {
			System.err.println("Error closing database connection: " + e.getMessage());
		}
	}
}
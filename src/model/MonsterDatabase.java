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
import java.util.Random;

import org.sqlite.SQLiteDataSource;

/**
 * Stores and retrieves Monster data in a SQLite database.
 *
 * @author Anna Brewer
 * @version 23 Feb 2025
 */
public class MonsterDatabase {
    private static final String DB_FILE = "jdbc:sqlite:monsters.db";

    private Connection myConn;
    private Statement myStmt;
    private final Random myRandom = new Random();

    /**
     * Establishes a connection to the SQLite database.
     */
    public MonsterDatabase() {
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
        
        System.out.println("Opened database successfully");
    }

    /**
     * Retrieves all Monsters from the database.
     *
     * @return a list of Monster objects
     */
    public List<Monster> getAllMonsters() {
        List<Monster> monsters = new ArrayList<>();

        try (ResultSet rs = myStmt.executeQuery("SELECT * FROM monsters")) {
            while (rs.next()) {
                monsters.add(generateMonster(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);  
        }
        return monsters;
    }

    /**
     * Generates a Monster using data from the database.
     *
     * @param theRs the ResultSet containing monster data
     * @return the Monster
     */
    private Monster generateMonster(ResultSet theRs) throws SQLException {
        MonsterType type = MonsterType.fromString(theRs.getString("name"));

        int healthPoints = theRs.getInt("health_points");
        int damageMin = theRs.getInt("damage_min");
        int damageMax = theRs.getInt("damage_max");
        int attackSpeed = theRs.getInt("attack_speed");
        double hitChance = theRs.getDouble("hit_chance");
        int healMin = theRs.getInt("heal_min");
        int healMax = theRs.getInt("heal_max");
        double healChance = theRs.getDouble("heal_chance");

        return MonsterFactory.createMonster(type, healthPoints, damageMin, damageMax, attackSpeed,
                                            hitChance, healMin, healMax, healChance, myRandom);
    }
}

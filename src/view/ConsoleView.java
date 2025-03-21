/*
 * TCSS 360 Group Project
 */
package view;

import java.util.Scanner;

import model.Difficulty;
import model.Direction;
import model.DungeonCharacter;
import model.Hero;
import model.HeroType;
import model.Room;

/**
 * Represents the user interface for the Dungeon Adventure game.
 * 
 * This includes displaying menus, selecting heroes and difficulty levels,
 * showing the current game state and battle interfaces, and reading user input
 * from the console.
 *
 * @author Justin Le, Anna Brewer
 * @version 21 Mar 2025
 */
public class ConsoleView {

	/** Scanner for reading user input. */
	private Scanner myScanner;

	/** Constructs a ConsoleView and initializes the scanner. */
	public ConsoleView() {
		myScanner = new Scanner(System.in);
	}

	/**
	 * Displays the main menu options for the game. Includes starting a new game,
	 * loading or saving progress, enabling cheats (not yet implemented), a quick
	 * start for debugging, or exiting the game.
	 */
	public void showMainMenu() {
		clearConsole();
		System.out.println("=== Dungeon Adventure ===");
		System.out.println("1. Start a New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Save Game");
		System.out.println("4. Cheats (Not Yet Implemented)");
		System.out.println("`. Quick Start (DEBUG)");
		System.out.println("0. Exit");
		System.out.print("\nEnter your choice: ");
	}

	/**
	 * Displays the hero selection menu. 
	 * The player can choose from Warrior, Priestess, Thief, or Berserker.
	 */
	public void showHeroSelection() {
		clearConsole();
		System.out.println("Choose your hero:");
		for (int i = 0; i < HeroType.values().length; i++) {
			System.out.printf("%d. %s\n", i + 1, HeroType.values()[i]);
		}
		System.out.print("\nEnter your choice: ");
	}

	/**
	 * Displays the difficulty selection menu. 
	 * The player can choose from Easy, Medium, or Hard.
	 */
	public void showDifficultySelection() {
		clearConsole();
		System.out.println("Choose your difficulty:");
		for (int i = 0; i < Difficulty.values().length; i++) {
			System.out.printf("%d. %s\n", i + 1, Difficulty.values()[i]);
		}
		System.out.print("\nEnter your choice: ");
	}

	/**
	 * Prompts the player to enter a name for their hero. 
	 * The name must be 20 characters or fewer. 
	 * If left empty, the default name will be the selected hero type.
	 */
	public void showHeroNameInput() {
		clearConsole();
		System.out.print("\nEnter your hero's name (20 characters max, leave empty for default): ");
	}

	/**
	 * Displays the hero’s current status and room layout. 
	 * Shows hero stats, room contents, and available actions based on the current room state. 
	 * If the room contains a monster, the player is prompted before battle begins.
	 * If the hero wins or loses the game, a corresponding message is displayed.
	 *
	 * @param theHero the hero character
	 * @param theRoom the room the hero is currently in
	 */
	public void showHeroCurRoom(final DungeonCharacter theHero, final Room theRoom) {
		clearConsole();
		showNewLineMessage(theHero.toString());
		showNewLineMessage(
				((Hero) theHero).getHasAllPillars() ? "(DEBUG) Has All Pillars" : "(DEBUG) Not Have All Pillars");
		showNewLine();
		showNewLineMessage(theRoom.stringUI());

		if (theHero.isAlive() && !(theRoom.getHasExit() && ((Hero) theHero).getHasAllPillars())) {
			if (theRoom.getHasMonster()) {
				showNewLineMessage("\nEnemy encounter! [ENTER] to continue.");
				getUserInput();
			} else {
				if (theRoom.getHasDoors()[Direction.NORTH.ordinal()]) {
					showControl("W", "Up");
				}
				if (theRoom.getHasDoors()[Direction.WEST.ordinal()]) {
					showControl("A", "Left");
				}
				if (theRoom.getHasDoors()[Direction.SOUTH.ordinal()]) {
					showControl("S", "Down");
				}
				if (theRoom.getHasDoors()[Direction.EAST.ordinal()]) {
					showControl("D", "Right");
				}
				if (theRoom.getHasPillarOO()) {
					showControl("P", "Pick Up Pillar");
				}
				if (theRoom.getHasHealingPotion()) {
					showControl("H", "Pick Up Healing Potion");
				}
				if (theRoom.getHasVisionPotion()) {
					showControl("V", "Pick Up Vision Potion");
				}
				showNewLine();
				showControl("`", "Spawn Monster (DEBUG)");
				showControl("~", "Win Game (DEBUG)");
				showControl("K", "Save Game");
				showControl("M", "Return to Main Menu");
				showNewLine();

			}
		} else if (theRoom.getHasExit() && ((Hero) theHero).getHasAllPillars()) {
			showNewLineMessage("\nYou win! You found the exit and got all pillars!");
			showNewLineMessage("Going back to main menu. [ENTER] to continue.");
		} else {
			showNewLineMessage("\nGame over...");
			showNewLineMessage("Going back to main menu. [ENTER] to continue.");
		}
	}

	/**
	 * Displays the battle interface showing the hero and monster status, turn
	 * number, and available combat actions. Includes debug options for winning or
	 * losing instantly.
	 *
	 * @param theHero    the hero character
	 * @param theMonster the monster being fought
	 * @param theCurTurn the current turn in the battle
	 */
	public void showBattle(final DungeonCharacter theHero, final DungeonCharacter theMonster, final int theCurTurn) {
		clearConsole();
		showNewLineMessage(String.format("Turn #%s", theCurTurn + 1));
		showNewLineMessage("---");
		showNewLineMessage(theHero.toString());
		showNewLineMessage("---");
		showNewLineMessage(theMonster.toString());
		showNewLine();
		showControl("1", "Attack");
		showControl("2", "Special Attack");
		showControl("3", String.format("Healing Potion (%d)", ((Hero) theHero).getNumHealingPotions()));
		showControl("`", "Win Battle (DEBUG)");
		showControl("~", "Lose Battle (DEBUG)");
		showNewLine();
		if (theCurTurn % 2 == 0 && theHero.isAlive() && theMonster.isAlive()) {
			System.out.print("\nEnter your choice: ");
		}
	}

	/**
	 * Returns the user's input in uppercase.
	 *
	 * @return the user input in uppercase
	 */
	public String getUserInput() {
		return myScanner.nextLine().toUpperCase();
	}

	/**
	 * Returns the user's input with original casing.
	 *
	 * @return the user input with original casing
	 */
	public String getUserInputCaseSensitive() {
		return myScanner.nextLine();
	}

	/**
	 * Prints a message and moves to the next line.
	 *
	 * @param theMessage the message to print
	 */
	public void showNewLineMessage(final String theMessage) {
		System.out.println(theMessage);
	}

	/**
	 * Prints a message without a newline.
	 * 
	 * @param theMessage the message to print
	 */
	public void showMessage(final String theMessage) {
		System.out.print(theMessage);
	}

	/**
	 * Displays a control key with its action label.
	 *
	 * @param theButton  the control key
	 * @param theMessage the description of the action
	 */
	public void showControl(final String theButton, final String theMessage) {
		System.out.printf("[%s] %s  ", theButton, theMessage);
	}

	/** Prints a blank line for visual spacing. */
	public void showNewLine() {
		System.out.println();
	}

	/** Clears the console screen. */
	public void clearConsole() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}

		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			System.out.println("Could not clear console.");
		}
	}

	/** Closes the input scanner. */
	public void closeScanner() {
		if (myScanner != null) {
			myScanner.close();
		}
	}
}

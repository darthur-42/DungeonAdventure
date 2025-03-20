/**
 * TCSS 360 Group Project
 */
package view;

import java.util.Scanner;

import model.Direction;
import model.DungeonCharacter;
import model.Hero;
import model.Room;

/**
 * Handles console-based user interactions for the game.
 *
 * @author Justin Le, Anna Brewer
 * @version 19 Mar 2025
 */
public class ConsoleView {

	/** Scanner for reading user input. */
	private Scanner myScanner;

	/** Constructs a ConsoleView and initializes the scanner. */
	public ConsoleView() {
		myScanner = new Scanner(System.in);
	}

	/** Displays the main menu options. */
	public void showMainMenu() {
		clearConsole();
		System.out.println("=== Dungeon Adventure ===");
		System.out.println("1. Start New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Save Game");
		System.out.println("`. Quick Start (DEBUG)");
		System.out.println("0. Exit");
		System.out.print("\nEnter your choice: ");
	}

	/** Displays hero selection options. */
	public void showHeroSelection() {
		clearConsole();
		System.out.println("Choose your hero:");
		System.out.println("1. Warrior");
		System.out.println("2. Priestess");
		System.out.println("3. Thief");
		System.out.println("4. Berserker");
		System.out.print("\nEnter your choice: ");
	}

	/** Displays difficulty selection options. */
	public void showDifficultySelection() {
		clearConsole();
		System.out.println("Choose your difficulty: (Not Yet Implemented)");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		System.out.print("\nEnter your choice: ");
	}

	/** Prompts the user to enter a hero name. */
	public void showHeroNameInput() {
		clearConsole();
		System.out.print("\nEnter your hero's name (20 characters max): ");
	}

	/**
	 * Displays the current room and hero's status.
	 *
	 * @param theHero the hero character
	 * @param theRoom the current room
	 */
	public void showHeroCurRoom(final DungeonCharacter theHero, final Room theRoom) {
		clearConsole();
		showMessage(theHero.toString());
		showMessage(theRoom.getHasExit() ? "(DEBUG) Is On Exit" : "(DEBUG) Is On Not Exit");
		showMessage(((Hero) theHero).getHasAllPillars() ? "(DEBUG) Has All Pillars" : "(DEBUG) Not Have All Pillars");
		showNewLine();
		showMessage(theRoom.stringUI());

		if (theHero.isAlive() && !theRoom.getHasExit() && !((Hero) theHero).getHasAllPillars()) {
			if (theRoom.getHasMonster()) {
				showMessage("\nEnemy encounter! [ENTER] to continue.");
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
				showControl("`", "Spawn Monster (DEBUG)");
				showControl("~", "Win Game (DEBUG)");
				showControl("M", "Return to Main Menu");
				showNewLine();

			}
		} else if (theRoom.getHasExit() && ((Hero) theHero).getHasAllPillars()) {
			showMessage("\nYou win! You found the exit and got all pillars!");
			showMessage("Going back to main menu. [ENTER] to continue.");
		} else {
			showMessage("\nGame over...");
			showMessage("Going back to main menu. [ENTER] to continue.");
		}
	}

	/**
	 * Displays the battle interface.
	 *
	 * @param theHero    the hero character
	 * @param theMonster the monster opponent
	 * @param theCurTurn the current turn number
	 */
	public void showBattle(final DungeonCharacter theHero, final DungeonCharacter theMonster, final int theCurTurn) {
		clearConsole();
		showMessage(String.format("Turn #%s", theCurTurn + 1));
		showMessage("---");
		showMessage(theHero.toString());
		showMessage("---");
		showMessage(theMonster.toString());
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

	/** Reads user input and converts it to uppercase. */
	public String getUserInput() {
		return myScanner.nextLine().toUpperCase();
	}

	/** Reads user input while keeping the original case. */
	public String getUserInputCaseSensitive() {
		return myScanner.nextLine();
	}

	/**
	 * Displays a message to the console.
	 *
	 * @param theMessage the message to display
	 */
	public void showMessage(final String theMessage) {
		System.out.println(theMessage);
	}

	/**
	 * Displays a control option in the console.
	 *
	 * @param theButton  the key/button to press
	 * @param theMessage the action description
	 */
	public void showControl(final String theButton, final String theMessage) {
		System.out.printf("[%s] %s  ", theButton, theMessage);
	}

	/** Prints a blank line for spacing. */
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

	public void closeScanner() {
		if (myScanner != null) {
			myScanner.close();
		}
	}
}

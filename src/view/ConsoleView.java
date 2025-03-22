/**
 * TCSS 360 Group Project
 */
package view;

import java.util.Scanner;

import model.Difficulty;
import model.Direction;
import model.DungeonCharacter;
import model.Hero;
import model.HeroType;
import model.PillarOO;
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
	public void showMainMenu(final boolean theHasCheatsEnabled) {
		clearConsole();
		showNewLineMessage("=== Dungeon Adventure ===");
		showNewLineMessage("1. Start a New Game");
		showNewLineMessage("2. Load Game");
		showNewLineMessage(String.format("3. Toggle Cheats (%s)", theHasCheatsEnabled ? "On" : "Off"));
		showNewLineMessage("0. Exit");
		showNewLine();
	}

	/**
	 * Displays the hero selection menu. 
	 * The player can choose from Warrior, Priestess, Thief, or Berserker.
	 */
	public void showHeroSelection() {
		clearConsole();
		showNewLineMessage("Choose your hero:");
		for (int i = 0; i < HeroType.values().length; i++) {
			showNewLineMessage(String.format("%d. %s", i + 1, HeroType.values()[i]));
			showNewLineMessage(String.format("   %s", wrapText(HeroType.values()[i].getDescription(), 50)));
			showNewLine();
		}
	}

	/**
	 * Displays the difficulty selection menu. 
	 * The player can choose from Easy, Medium, or Hard.
	 */
	public void showDifficultySelection() {
		clearConsole();
		System.out.println("Choose your difficulty:");
		for (int i = 0; i < Difficulty.values().length; i++) {
			showNewLineMessage(String.format("%d. %s", i + 1, Difficulty.values()[i]));
			showNewLineMessage(String.format("   %s", wrapText(Difficulty.values()[i].getDescription(), 50)));
		}
		showNewLine();
	}

	/**
	 * Prompts the player to enter a name for their hero. 
	 * The name must be 20 characters or fewer. 
	 * If left empty, the default name will be the selected hero type.
	 */
	public void showHeroNameInput() {
		showMessage("Enter your hero's name (20 characters max, leave empty for default): ");
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
	public void showHeroCurRoom(final DungeonCharacter theHero, final Room theRoom,
			final Room[] theAdjacentRooms, final boolean theHasDrankVisionPotion,
			final boolean theHasCheatsEnabled) {
		clearConsole();
		showNewLineMessage(theHero.toString());
		showNewLine();
		showNewLineMessage(theRoom.textUI(theAdjacentRooms, theHasDrankVisionPotion));
		showNewLineMessage(String.format(
				"Find all Pillars of OOP and get to the Exit! (%d/%d Pillars)",
				((Hero) theHero).getCollectedPillars().size(),
				PillarOO.values().length
		));
		showNewLine();
		showNewLineMessage("Key:");
		showNewLineMessage("Y - You  Ent - Entrance  Ext - Exit  Plr - Pillar of OOP");
		showNewLineMessage("M - Monster  H - Healing Potion  V - Vision Potion  Pt - Pit");
		showNewLine();

		if (theHero.isAlive() && !(theRoom.getHasExit() && ((Hero) theHero).getHasAllPillars())) {
			if (theRoom.getHasMonster()) {
				showNewLineMessage("==============================");
				showNewLineMessage(">>>>>> Enemy Encounter! <<<<<<");
				showNewLineMessage("==============================");
				showMessage("[ENTER] to continue.");
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
				showNewLine();
				showControl("H", String.format("Use Healing Potion (%d)", ((Hero) theHero).getNumHealingPotions()));
				showControl("V", String.format("Use Vision Potion (%d)", ((Hero) theHero).getNumVisionPotions()));
				if (theRoom.getHasPillarOO() || theRoom.getHasHealingPotion() || theRoom.getHasVisionPotion()) {
					showNewLine();
				}
				if (theRoom.getHasPillarOO()) {
					showControl("E", "Pick Up Pillar");
				}
				if (theRoom.getHasHealingPotion()) {
					showControl("E", "Pick Up Healing Potion");
				}
				if (theRoom.getHasVisionPotion()) {
					showControl("E", "Pick Up Vision Potion");
				}
				showNewLine();
				showControl("K", "Save Game");
				showControl("M", "Return to Main Menu");
				if (theHasCheatsEnabled) {
					showNewLine();
					showMessage("Cheats: ");
					showControl("!", "Infinite Health");
					showControl("@", "Infinite Vision");
					showControl("#", "Infinite Damage");
				}
				showNewLine();
				showNewLine();
			}
		} else if (theRoom.getHasExit() && ((Hero) theHero).getHasAllPillars()) {
			showNewLineMessage("You win! You found the Exit and got all Pillars of OOP!");
			showMessage("Returning to main menu. [ENTER] to continue.");
		} else {
			showNewLineMessage("Game over...");
			showMessage("Returning to main menu. [ENTER] to continue.");
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
	public void showBattle(final DungeonCharacter theHero, final DungeonCharacter theMonster) {
		clearConsole();
		showNewLineMessage(theHero.toString());
		showNewLineMessage("---");
		showNewLineMessage(theMonster.toString());
		showNewLine();
		showControl("1", "Attack");
		showControl("2", String.format("Special Attack (%s)", ((Hero) theHero).getSpecialAttackName()));
		showControl("3", String.format("Healing Potion (%d)", ((Hero) theHero).getNumHealingPotions()));
		showNewLine();
		showNewLine();
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

	/**
	 * Prints a blank line for visual spacing.
	 */
	public void showNewLine() {
		System.out.println();
	}

	/**
	 * Wraps text based on a max width.
	 * 
	 * @param theText the text to wrap
	 * @param theMaxWidth the max width
	 * @return the text wrapped around a max width
	 */
	public static String wrapText(String theText, int theMaxWidth) {
		StringBuilder wrappedText = new StringBuilder();
		int lineLength = 0;

		for (String curWord : theText.split(" ")) {
			if (lineLength + curWord.length() > theMaxWidth) {
				wrappedText.append("\n\t");
				lineLength = 0;
			}

			if (lineLength > 0) {
				wrappedText.append(" ");
				lineLength++;
			}

			wrappedText.append(curWord);
			lineLength += curWord.length();
		}

		return wrappedText.toString();
	}

	/**
	 * Clears the console screen.
	 */
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
			// Do nothing.
		}
	}

	/**
	 * Closes the input scanner.
	 */
	public void closeScanner() {
		if (myScanner != null) {
			myScanner.close();
		}
	}
}

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
 * 
 */
public class ConsoleView {
	
	private Scanner myScanner;
	
	public ConsoleView() {
		myScanner = new Scanner(System.in);
	}
	
	public void showMainMenu() {
		clearConsole();
		System.out.println("=== Dungeon Adventure ===");
		System.out.println("1. Start New Game");
		System.out.println("2. Load Game (Not Yet Implemented)");
		System.out.println("`. Quick Start (DEBUG)");
		System.out.println("0. Exit");
		System.out.print("\nEnter your choice: ");
	}
	
	public void showHeroSelection() {
		clearConsole();
		System.out.println("Choose your hero:");
		System.out.println("1. Warrior");
		System.out.println("2. Priestess");
		System.out.println("3. Thief");
		System.out.print("\nEnter your choice: ");
	}
	
	public void showDifficultySelection() {
		clearConsole();
		System.out.println("Choose your difficulty: (Not Yet Implemented)");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		System.out.print("\nEnter your choice: ");
	}
	
	public void showHeroNameInput() {
		clearConsole();
		System.out.print("\nEnter your hero's name (20 characters max): ");
	}
	
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
				showNewLine();
				
				System.out.print("\nEnter your choice: ");
			}
		} else if (theRoom.getHasExit() && ((Hero) theHero).getHasAllPillars()) {
			showMessage("\nYou win! You found the exit and got all pillars!");
			showMessage("Going back to main menu. [ENTER] to continue.");
		} else {
			showMessage("\nGame over...");
			showMessage("Going back to main menu. [ENTER] to continue.");
		}
	}
	
	public void showBattle(final DungeonCharacter theHero, final DungeonCharacter theMonster,
			final int theCurTurn) {
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
	
	public String getUserInput() {
		return myScanner.nextLine().toUpperCase();
	}
	
	public String getUserInputCaseSensitive() {
		return myScanner.nextLine();
	}
	
	public void showMessage(final String theMessage) {
		System.out.println(theMessage);
	}
	
	public void showControl(final String theButton, final String theMessage) {
		System.out.printf("[%s] %s  ", theButton, theMessage);
	}
	
	public void showNewLine() {
		System.out.println();
	}
	
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
		myScanner.close();
	}
	
}

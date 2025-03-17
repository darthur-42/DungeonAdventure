/**
 * TCSS 360 Group Project
 */
package view;

import java.util.Scanner;

import model.Direction;
import model.Room;

/**
 * 
 */
public class ConsoleView {
	
	private Scanner scanner;
	
	public ConsoleView() {
		scanner = new Scanner(System.in);
	}
	
	public void showMainMenu() {
		clearConsole();
		System.out.println("=== Dungeon Adventure ===");
		System.out.println("1. Start New Game");
		System.out.println("2. Load Game (Not Yet Implemented)");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
	}
	
	public void showHeroSelection() {
		clearConsole();
		System.out.println("Choose your hero:");
		System.out.println("1. Warrior");
		System.out.println("2. Priestess");
		System.out.println("3. Thief");
		System.out.print("Enter your choice: ");
	}
	
	public void showDifficultySelection() {
		clearConsole();
		System.out.println("Choose your difficulty: (Not Yet Implemented)");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		System.out.print("Enter your choice: ");
	}
	
	public void showHeroNameInput() {
		clearConsole();
		System.out.print("Enter your hero's name (20 characters max): ");
	}
	
	public void showHeroCurRoom(final Room theRoom) {
		clearConsole();
		showMessage(theRoom.stringUI());
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
		System.out.print("Enter your choice: ");
	}
	
	public String getUserInput() {
		return scanner.nextLine();
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
		for (int i = 0; i < 50; i++) {
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
		scanner.close();
	}
	
}

/**
 * TCSS 360 Group Project
 */
package view;

import controller.DungeonAdventure;

/**
 * 
 */
public class Main {
	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		DungeonAdventure controller = new DungeonAdventure(view);
		controller.launchGame();
	}
}

/**
 * TCSS 360 Group Project
 */
package view;

import controller.DungeonAdventure;

/**
 * Driver for DungeonAdevnture.
 * 
 * @author Justin Le
 * @version 16 Mar 2025
 */
public class Main {
	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		DungeonAdventure controller = new DungeonAdventure(view);
		controller.startDungeonAdventure();
	}
}

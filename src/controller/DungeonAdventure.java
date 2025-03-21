/**
 * TCSS 360 Group Project
 */
package controller;

import java.io.*;
import java.util.Random;

import model.Difficulty;
import model.Direction;
import model.Dungeon;
import model.DungeonCharacter;
import model.DungeonCharacterFactory;
import model.Hero;
import model.HeroType;
import model.Monster;
import model.MonsterType;
import model.PillarOO;
import model.Room;
import view.ConsoleView;

/**
 * Controller for the DungeonAdventure game.
 * 
 * @author Justin Le, Anna Brewer
 * @version 20 Mar 2025
 */
public class DungeonAdventure {

	private Random myRandom;

	/** Factory for creating DungeonCharacters. */
	private DungeonCharacterFactory myCharFactory;

	/** The player's Hero. */
	private DungeonCharacter myHero;

	/** The loaded game's Difficulty. */
	private Difficulty myDifficulty;

	/** The game's Dungeon. */
	private Dungeon myDungeon;

	/** The Hero's current x-coordinate in the dungeon. */
	private int myHeroCurX;

	/** The Hero's current Y-coordinate in the dungeon. */
	private int myHeroCurY;

	/** The Room where the Hero is currently located. */
	private Room myHeroCurRoom;

	/** The console-based view for user interaction. */
	private ConsoleView myView;

	/**
	 * Constructs a DungeonAdventure controller.
	 * 
	 * @param theView the console view for user interaction
	 */
	public DungeonAdventure(ConsoleView theView) {
		myRandom = new Random();
		myCharFactory = new DungeonCharacterFactory();
		myView = theView;
	}

	/**
	 * Starts the DungeonAdventure game. Displays the main menu and handles user input.
	 */
	public void startDungeonAdventure() {
		while (true) {
			myView.showMainMenu();
			String mainMenuChoice = myView.getUserInput();
			switch (mainMenuChoice) {
			case "1":
				startNewGame();
				break;
			case "2":
				loadGame("dungeonadventure.dat");
				break;
			case "3":
				saveGame("dungeonadventure.dat");
				break;
			case "`":
				quickStart();
				break;
			case "0":
				myView.showNewLineMessage("Exiting game.");
				return;
			default:
				myView.showNewLineMessage("Invalid choice. Please try again. [ENTER] to continue.");
				myView.getUserInput();
			}
		}
	}

	private void quickStart() {
		myHero = myCharFactory.createDungeonCharacter(HeroType.WARRIOR);
		myDifficulty = Difficulty.MEDIUM;
		createNewDungeon();

		playGame();
	}

	/**
	 * Starts a new game by prompting the player to select a hero, a difficulty level, and a name.
	 */
	private void startNewGame() {
		selectHero();
		if (myHero == null) {
			myView.showNewLineMessage("Error: Hero was not initialized correctly. Restarting game.");
			myView.getUserInput();
			return;
		}
		selectDifficulty();
		enterHeroName();
		createNewDungeon();
		if (myDungeon == null) {
			myView.showNewLineMessage("Error: Dungeon was not initialized correctly. Restarting game.");
			myView.getUserInput();
			return;
		}
		
		playGame();
	}

	/**
	 * Prompts the player to select a Hero.
	 */
	private void selectHero() {
		String heroChoice = "";

		while (heroChoice == "") {
			myView.showHeroSelection();
			heroChoice = myView.getUserInput();
			
			try {
				int heroChoiceInt = Integer.parseInt(heroChoice) - 1;
				if (heroChoiceInt >= 0 && heroChoiceInt < HeroType.values().length) {
					myHero = myCharFactory.createDungeonCharacter(HeroType.values()[Integer.parseInt(heroChoice) - 1]);
				} else {
					myView.showNewLineMessage("Invalid choice. Please try again. [ENTER] to continue.");
					myView.getUserInput();
					heroChoice = "";
				}
			} catch (NumberFormatException e) {
				myView.showNewLineMessage("Invalid choice. Please try again. [ENTER] to continue.");
				myView.getUserInput();
				heroChoice = "";
			}
		}

		if (myHero == null) {
			myView.showNewLineMessage("Error: Hero creation failed. Restarting game.");
			myView.getUserInput();
			return;
		}

		myView.showNewLineMessage("You have chosen: " + myHero.getName() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Prompts the player to select a difficulty level.
	 */
	private void selectDifficulty() {
		String difficultyChoice = "";

		while (difficultyChoice == "") {
			myView.showDifficultySelection();
			difficultyChoice = myView.getUserInput();
			
			try {
				int difficultyChoiceInt = Integer.parseInt(difficultyChoice) - 1;
				if (difficultyChoiceInt >= 0 && difficultyChoiceInt < Difficulty.values().length) {
					myDifficulty = Difficulty.values()[Integer.parseInt(difficultyChoice) - 1];
				} else {
					myView.showNewLineMessage("Invalid choice. Please try again. [ENTER] to continue.");
					myView.getUserInput();
					difficultyChoice = "";
				}
			} catch (NumberFormatException e) {
				myView.showNewLineMessage("Invalid choice. Please try again. [ENTER] to continue.");
				myView.getUserInput();
				difficultyChoice = "";
			}
		}

		myView.showNewLineMessage("You have chosen: " + myDifficulty.toString() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Prompts the player to enter a hero name.
	 */
	private void enterHeroName() {
		String nameInput = "";

		while (nameInput == "") {
			myView.showHeroNameInput();
			nameInput = myView.getUserInputCaseSensitive();

			if (nameInput.length() > 20) {
				myView.showNewLineMessage("Name is too long. Please try again. [ENTER] to continue.");
				myView.getUserInput();
				nameInput = "";
			} else if (nameInput.isBlank()) {
				nameInput = "0";
				continue;
			} else {
				myHero.setName(nameInput);
			}
		}

		myView.showNewLineMessage("Your hero's name is: " + myHero.getName() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Creates a new dungeon for the game.
	 */
	private void createNewDungeon() {
		myDungeon = new Dungeon(myCharFactory, myDifficulty);
		if (myDungeon == null) {
			myView.showNewLineMessage("Error: Dungeon creation failed. Restarting game.");
			myView.getUserInput();
			return;
		}

		updateHeroPosition(myDungeon.getEntrance().getRoomX(), myDungeon.getEntrance().getRoomY());
	}

	/**
	 * Runs the main game loop.
	 */
	private void playGame() {
		String statusMessage = "";
		while (myHero.isAlive() && !(myHeroCurRoom.getHasExit() && ((Hero) myHero).getHasAllPillars())) {
			if (myHeroCurRoom.getHasMonster()) {
				myView.showHeroCurRoom(myHero, myHeroCurRoom);
				startBattle(myHero, myHeroCurRoom.getMonster());
			} else {
				if (myHeroCurRoom.getHasPit()) {
					dealPitDamage();
					statusMessage = "Fell into a pit! Lost some health.";
				}
				
				myView.showHeroCurRoom(myHero, myHeroCurRoom);
				myView.showNewLine();
				if (!statusMessage.isBlank()) {
					myView.showNewLineMessage(statusMessage);
				}
				statusMessage = "";
				myView.showMessage("Enter your choice: ");
				String userInput = myView.getUserInput();
				
				if (userInput.equals("M")) {
					myView.showNewLineMessage("Returning to main menu... [ENTER] to continue.");
					myView.getUserInput();
					return;
				}
				
				switch (userInput) {
					case "W":
						if (myHeroCurRoom.getHasDoors()[Direction.NORTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY - 1);
						} else {
							statusMessage = "Cannot move North!";
						}
						break;
					case "A":
						if (myHeroCurRoom.getHasDoors()[Direction.WEST.ordinal()]) {
							updateHeroPosition(myHeroCurX - 1, myHeroCurY);
						} else {
							statusMessage = "Cannot move West!";
						}
						break;
					case "S":
						if (myHeroCurRoom.getHasDoors()[Direction.SOUTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY + 1);
						} else {
							statusMessage = "Cannot move South!";
						}
						break;
					case "D":
						if (myHeroCurRoom.getHasDoors()[Direction.EAST.ordinal()]) {
							updateHeroPosition(myHeroCurX + 1, myHeroCurY);
						} else {
							statusMessage = "Cannot move East!";
						}
						break;
					case "P":
						if (myHeroCurRoom.getHasPillarOO()) {
							((Hero) myHero).collectPillar(myHeroCurRoom.getPillar());
							myHeroCurRoom.setHasPillarOO(false);
							statusMessage = "Collected a Pillar of OO.";
						} else {
							statusMessage = "Invalid choice.";
						}
						break;
					case "H":
						if (myHeroCurRoom.getHasHealingPotion()) {
							((Hero) myHero).collectHealingPotion();
							myHeroCurRoom.setHasHealingPotion(false);
							statusMessage = "Collected a Healing Potion.";
						} else {
							statusMessage = "Invalid choice.";
						}
						break;
					case "V":
						if (myHeroCurRoom.getHasVisionPotion()) {
							((Hero) myHero).collectVisionPotion();
							myHeroCurRoom.setHasVisionPotion(false);
							statusMessage = "Collected a Vision Potion.";
						} else {
							statusMessage = "Invalid choice.";
						}
						break;
					case "`":
						myHeroCurRoom.setHasMonster(true);
						MonsterType randomMonsterType = MonsterType.values()[myRandom.nextInt(MonsterType.values().length)];
						Monster newMonster = (Monster) myCharFactory.createDungeonCharacter(randomMonsterType, myDifficulty);
						myHeroCurRoom.setMonster(newMonster);
						break;
					case "~":
						for (PillarOO curPillar : PillarOO.values()) {
							((Hero) myHero).collectPillar(curPillar);
						}
						myHeroCurRoom.forceHasExit();
						break;
					default:
						statusMessage = "Invalid choice.";
				}
			}
		}
		
		if (myHeroCurRoom.getHasExit() && ((Hero) myHero).getHasAllPillars()) {
			myView.showHeroCurRoom(myHero, myHeroCurRoom);
			myView.getUserInput();
		} else if (!myHero.isAlive()) {
			myView.showHeroCurRoom(myHero, myHeroCurRoom);
			myView.getUserInput();
		}
	}

	/**
	 * Starts a battle between the hero and a monster.
	 *
	 * @param theHero    the hero character
	 * @param theMonster the monster character
	 */
	private void startBattle(final DungeonCharacter theHero, final DungeonCharacter theMonster) {
		String battleInput = "";
		int curTurn = 0;

		while (theHero.isAlive() && theMonster.isAlive()) {
			myView.showBattle(theHero, theMonster, curTurn);

			if (curTurn % 2 == 0) {
				battleInput = myView.getUserInput();

				switch (battleInput) {
				case "1":
					theHero.attack(theMonster);
					myView.showNewLineMessage(String.format("Attacked %s! [ENTER] to continue.", theMonster.getName()));
					myView.getUserInput();
					break;
				case "2":
					((Hero) theHero).specialAttack(theMonster);
					myView.showNewLineMessage("Used special attack! [ENTER] to continue.");
					myView.getUserInput();
					break;
				case "3":
					((Hero) theHero).useHealingPotion();
					myView.showNewLineMessage("Used a healing potion!");
					if (theHero.isFullHealth()) {
						myView.showNewLineMessage("Full health!");
					}
					myView.showNewLineMessage("[ENTER] to continue.");
					myView.getUserInput();
					break;
				case "`":
					theMonster.setCurHealthPoints(0);
					break;
				case "~":
					theHero.setCurHealthPoints(0);
					break;
				default:
					myView.showNewLineMessage("Invalid choice. Please try again. [ENTER] to continue.");
					myView.getUserInput();
				}
			} else {
				theMonster.attack(theHero);
				myView.showNewLineMessage(String.format("%s attacked you! [ENTER] to continue.", theMonster.getName()));
				myView.getUserInput();
			}

			curTurn++;
		}

		curTurn--;
		myView.showBattle(theHero, theMonster, curTurn);
		if (!theMonster.isAlive()) {
			myHeroCurRoom.setHasMonster(false);
			myHeroCurRoom.setMonster(null);
			myView.showNewLineMessage("You won the battle! [ENTER] to continue.");
			myView.getUserInput();
		} else if (!theHero.isAlive()) {
			myView.showNewLineMessage("You lost the battle... [ENTER] to continue.");
			myView.getUserInput();
		}
	}

	/**
	 * Updates the hero's position in the dungeon.
	 *
	 * @param theX the new X-coordinate
	 * @param theY the new Y-coordinate
	 */
	private void updateHeroPosition(final int theX, final int theY) {
		myHeroCurX = theX;
		myHeroCurY = theY;
		myHeroCurRoom = myDungeon.getRoomAt(myHeroCurX, myHeroCurY);
	}

	/**
	 * Saves the current game state to a file using serialization.
	 */
	private void saveGame(String filename) {
		if (myDungeon == null || myHero == null) {
			myView.showNewLineMessage("Error: Cannot save, missing game data. Start a new game first. [ENTER] to continue.");
			myView.getUserInput();
			return;
		}

		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

			out.writeObject(myDungeon);
			out.writeObject(myHero);
			out.writeObject(myHeroCurX);
			out.writeObject(myHeroCurY);

			myView.showNewLineMessage("Game saved successfully! [ENTER] to continue.");
			myView.getUserInput();

		} catch (IOException e) {
			myView.showNewLineMessage("Error saving the game: " + e.getMessage());
		}
	}

	/**
	 * Loads a saved game state from a file using serialization.
	 */
	private void loadGame(String filename) {
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {

			myDungeon = (Dungeon) in.readObject();
			myHero = (Hero) in.readObject();
			myHeroCurX = (int) in.readObject();
			myHeroCurY = (int) in.readObject();
			myHeroCurRoom = myDungeon.getRoomAt(myHeroCurX, myHeroCurY);

			myView.showNewLineMessage("Game loaded successfully! [ENTER] to continue.");
			myView.getUserInput();

			playGame();

		} catch (FileNotFoundException e) {
			myView.showNewLineMessage("No saved game found. Start a new game first. [ENTER] to continue.");
			myView.getUserInput();
		} catch (EOFException e) {
			myView.showNewLineMessage("Save file is empty or corrupted. [ENTER] to continue.");
			myView.getUserInput();
		} catch (IOException | ClassNotFoundException e) {
			myView.showNewLineMessage("Error loading the game: " + e.getMessage());
		}
	}
	
	private void dealPitDamage() {
		int maxPitDamage = 10;
		((Hero) myHero).receiveTrueDamage(myRandom.nextInt(1, maxPitDamage + 1));
	}

}

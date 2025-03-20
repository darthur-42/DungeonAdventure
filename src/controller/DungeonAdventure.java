/**
 * TCSS 360 Group Project
 */
package controller;

import java.io.*;
import java.util.Random;

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
 * @version 19 Mar 2025
 */
public class DungeonAdventure {

	/** Factory for creating dungeon characters. */
	private DungeonCharacterFactory myCharFactory;

	/** The player's hero. */
	private DungeonCharacter myHero;

	/** The dungeon where the game takes place. */
	private Dungeon myDungeon;

	/** The hero's current x-coordinate in the dungeon. */
	private int myHeroCurX;

	/** The hero's current Y-coordinate in the dungeon. */
	private int myHeroCurY;

	/** The room where the hero is currently located. */
	private Room myHeroCurRoom;

	/** The console-based view for user interaction. */
	private ConsoleView myView;

	/**
	 * Constructs a DungeonAdventure controller.
	 * 
	 * @param theView the console view for user interaction
	 */
	public DungeonAdventure(ConsoleView theView) {
		myCharFactory = new DungeonCharacterFactory();
		myView = theView;
	}

	/**
	 * Starts the Dungeon Adventure game. Displays the main menu and handles user
	 * input.
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
				myView.showMessage("Exiting game.");
				return;
			default:
				myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
				myView.getUserInput();
			}
		}
	}

	private void quickStart() {
		myHero = myCharFactory.createDungeonCharacter(HeroType.WARRIOR);
		createNewDungeon();

		playGame();
	}

	/**
	 * Begins a new game by selecting a hero, difficulty, and name.
	 */
	private void startNewGame() {
		selectHero();
		selectDifficulty();
		enterHeroName();
		createNewDungeon();

		playGame();
	}

	/**
	 * Prompts the player to select a hero type.
	 */
	private void selectHero() {
		String heroChoice = "";

		while (heroChoice == "") {
			myView.showHeroSelection();
			heroChoice = myView.getUserInput();

			switch (heroChoice) {
			case "1":
				myHero = myCharFactory.createDungeonCharacter(HeroType.WARRIOR);
				break;
			case "2":
				myHero = myCharFactory.createDungeonCharacter(HeroType.PRIESTESS);
				break;
			case "3":
				myHero = myCharFactory.createDungeonCharacter(HeroType.THIEF);
				break;
			case "4":
				myHero = myCharFactory.createDungeonCharacter(HeroType.BERSERKER);
				break;
			default:
				myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
				myView.getUserInput();
				heroChoice = "";
			}
		}

		myView.showMessage("You have chosen: " + myHero.getName() + ". [ENTER] to continue.");
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

			switch (difficultyChoice) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			default:
				myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
				myView.getUserInput();
				difficultyChoice = "";
			}
		}

		myView.showMessage("You have chosen: " + "Medium" + ". [ENTER] to continue.");
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
				myView.showMessage("Name is too long. Please try again. [ENTER] to continue.");
				myView.getUserInput();
				nameInput = "";
			} else if (nameInput.isBlank()) {
				myView.showMessage("Name cannot be blank. Please try again. [ENTER] to continue.");
				myView.getUserInput();
			} else {
				myHero.setName(nameInput);
			}
		}

		myView.showMessage("Your hero's name is: " + myHero.getName() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Creates a new dungeon for the game.
	 */
	private void createNewDungeon() {
		myDungeon = new Dungeon(myCharFactory);
		updateHeroPosition(myDungeon.getEntrance().getRoomX(), myDungeon.getEntrance().getRoomY());
	}

	/**
	 * Runs the main game loop.
	 */
	private void playGame() {
		while (myHero.isAlive() && !myHeroCurRoom.getHasExit() && !((Hero) myHero).getHasAllPillars()) {
			if (myHeroCurRoom.getHasMonster()) {
				myView.showHeroCurRoom(myHero, myHeroCurRoom);
				startBattle(myHero, myHeroCurRoom.getMonster());
			} else {
				boolean validInput = false;

				while (!validInput) {
					myView.showHeroCurRoom(myHero, myHeroCurRoom);

					System.out.print("\nEnter your choice: ");
					String userInput = myView.getUserInput();

					if (userInput.equals("M")) {
						myView.showMessage("Returning to main menu... [ENTER] to continue.");
						myView.getUserInput();
						return;
					}

					switch (userInput) {
					case "W":
						if (myHeroCurRoom.getHasDoors()[Direction.NORTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY - 1);
							validInput = true;
						} else {
							myView.showMessage("Cannot move North! [ENTER] to continue.");
							myView.getUserInput();
						}
						break;
					case "A":
						if (myHeroCurRoom.getHasDoors()[Direction.WEST.ordinal()]) {
							updateHeroPosition(myHeroCurX - 1, myHeroCurY);
							validInput = true;
						} else {
							myView.showMessage("Cannot move West! [ENTER] to continue.");
							myView.getUserInput();
						}
						break;
					case "S":
						if (myHeroCurRoom.getHasDoors()[Direction.SOUTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY + 1);
							validInput = true;
						} else {
							myView.showMessage("Cannot move South! [ENTER] to continue.");
							myView.getUserInput();
						}
						break;
					case "D":
						if (myHeroCurRoom.getHasDoors()[Direction.EAST.ordinal()]) {
							updateHeroPosition(myHeroCurX + 1, myHeroCurY);
							validInput = true;
						} else {
							myView.showMessage("Cannot move East! [ENTER] to continue.");
							myView.getUserInput();
						}
						break;
					case "`":
						myHeroCurRoom.setHasMonster(true);
						MonsterType randomMonsterType = MonsterType.values()[new Random()
								.nextInt(MonsterType.values().length)];
						Monster newMonster = (Monster) myCharFactory.createDungeonCharacter(randomMonsterType);
						myHeroCurRoom.setMonster(newMonster);
						validInput = true;
						break;
					case "~":
						for (PillarOO curPillar : PillarOO.values()) {
							((Hero) myHero).collectPillar(curPillar);
						}
						myHeroCurRoom.forceHasExit();
						validInput = true;
						break;
					default:
						myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
						myView.getUserInput();
					}
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
					myView.showMessage(String.format("Attacked %s! [ENTER] to continue.", theMonster.getName()));
					myView.getUserInput();
					break;
				case "2":
					((Hero) theHero).specialAttack(theMonster);
					myView.showMessage("Used special attack! [ENTER] to continue.");
					myView.getUserInput();
					break;
				case "3":
					((Hero) theHero).useHealingPotion();
					myView.showMessage("Used a healing potion!");
					if (theHero.isFullHealth()) {
						myView.showMessage("Full health!");
					}
					myView.showMessage("[ENTER] to continue.");
					myView.getUserInput();
					break;
				case "`":
					theMonster.setCurHealthPoints(0);
					break;
				case "~":
					theHero.setCurHealthPoints(0);
					break;
				default:
					myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
					myView.getUserInput();
				}
			} else {
				theMonster.attack(theHero);
				myView.showMessage(String.format("%s attacked you! [ENTER] to continue.", theMonster.getName()));
				myView.getUserInput();
			}

			curTurn++;
		}

		curTurn--;
		myView.showBattle(theHero, theMonster, curTurn);
		if (!theMonster.isAlive()) {
			myHeroCurRoom.setHasMonster(false);
			myHeroCurRoom.setMonster(null);
			myView.showMessage("You won the battle! [ENTER] to continue.");
			myView.getUserInput();
		} else if (!theHero.isAlive()) {
			myView.showMessage("You lost the battle... [ENTER] to continue.");
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
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

			out.writeObject(myDungeon);
			out.writeObject(myHero);
			out.writeObject(myHeroCurX);
			out.writeObject(myHeroCurY);

			myView.showMessage("Game saved successfully! [ENTER] to continue.");
			myView.getUserInput();

		} catch (IOException e) {
			myView.showMessage("Error saving the game: " + e.getMessage());
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

			myView.showMessage("Game loaded successfully! [ENTER] to continue.");
			myView.getUserInput();

			playGame();

		} catch (FileNotFoundException e) {
			myView.showMessage("No saved game found. Start a new game first. [ENTER] to continue.");
			myView.getUserInput();
		} catch (EOFException e) {
			myView.showMessage("Save file is empty or corrupted. [ENTER] to continue.");
			myView.getUserInput();
		} catch (IOException | ClassNotFoundException e) {
			myView.showMessage("Error loading the game: " + e.getMessage());
		}
	}
}

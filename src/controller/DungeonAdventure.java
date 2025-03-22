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
 * @version 21 Mar 2025
 */
public class DungeonAdventure {

	/** How many steps the Hero can get Vision for upon consuming a Vision Potion. */
	private static final int VISION_MAX_STEP_COUNT = 3;

	/** Random instance used to generate random numbers. */
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

	/** How many steps the Hero has Vision for. */
	private int myHeroVisionStepCount;

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
		String statusMessage = "";

		while (heroChoice == "") {
			myView.showHeroSelection();
			if (!statusMessage.isBlank()) {
				myView.showNewLineMessage(statusMessage);
			}
			statusMessage = "";
			myView.showMessage("Enter your choice: ");
			heroChoice = myView.getUserInput();
			
			try {
				int heroChoiceInt = Integer.parseInt(heroChoice) - 1;
				if (heroChoiceInt >= 0 && heroChoiceInt < HeroType.values().length) {
					myHero = myCharFactory.createDungeonCharacter(HeroType.values()[Integer.parseInt(heroChoice) - 1]);
				} else {
					statusMessage = "Invalid choice.";
					heroChoice = "";
				}
			} catch (NumberFormatException e) {
				statusMessage = "Invalid choice.";
				heroChoice = "";
			}
		}

		if (myHero == null) {
			myView.showNewLineMessage("Error: Hero creation failed. Restarting game.");
			myView.getUserInput();
			return;
		}

		myView.showMessage("You have chosen: " + myHero.getName() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Prompts the player to select a difficulty level.
	 */
	private void selectDifficulty() {
		String difficultyChoice = "";
		String statusMessage = "";

		while (difficultyChoice == "") {
			myView.showDifficultySelection();
			if (!statusMessage.isBlank()) {
				myView.showNewLineMessage(statusMessage);
			}
			statusMessage = "";
			myView.showMessage("Enter your choice: ");
			difficultyChoice = myView.getUserInput();
			
			try {
				int difficultyChoiceInt = Integer.parseInt(difficultyChoice) - 1;
				if (difficultyChoiceInt >= 0 && difficultyChoiceInt < Difficulty.values().length) {
					myDifficulty = Difficulty.values()[Integer.parseInt(difficultyChoice) - 1];
				} else {
					statusMessage = "Invalid choice.";
					difficultyChoice = "";
				}
			} catch (NumberFormatException e) {
				statusMessage = "Invalid choice.";
				difficultyChoice = "";
			}
		}

		myView.showMessage("You have chosen: " + myDifficulty.toString() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Prompts the player to enter a hero name.
	 */
	private void enterHeroName() {
		String nameInput = "";
		String statusMessage = "";

		while (nameInput == "") {
			myView.clearConsole();
			if (!statusMessage.isBlank()) {
				myView.showNewLineMessage(statusMessage);
			}
			statusMessage = "";
			myView.showHeroNameInput();
			nameInput = myView.getUserInputCaseSensitive();

			if (nameInput.length() > 20) {
				statusMessage = "Name is too long.";
				nameInput = "";
			} else if (nameInput.isBlank()) {
				nameInput = "0";
				continue;
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
		myDungeon = new Dungeon(myCharFactory, myDifficulty);
		if (myDungeon == null) {
			myView.showNewLineMessage("Error: Dungeon creation failed. Restarting game.");
			myView.getUserInput();
			return;
		}

		updateHeroPosition(myDungeon.getEntrance().getRoomX(), myDungeon.getEntrance().getRoomY());
		myHeroVisionStepCount = 0;
	}

	/**
	 * Runs the main game loop.
	 */
	private void playGame() {
		String statusMessage = "";
		Room[] adjacentRooms = new Room[4];
		
		while (myHero.isAlive() && !(myHeroCurRoom.getHasExit() && ((Hero) myHero).getHasAllPillars())) {
			adjacentRooms[Direction.NORTH.ordinal()] = myDungeon.getRoomAt(myHeroCurX, myHeroCurY - 1);
			adjacentRooms[Direction.WEST.ordinal()] = myDungeon.getRoomAt(myHeroCurX - 1, myHeroCurY);
			adjacentRooms[Direction.SOUTH.ordinal()] = myDungeon.getRoomAt(myHeroCurX, myHeroCurY + 1);
			adjacentRooms[Direction.EAST.ordinal()] = myDungeon.getRoomAt(myHeroCurX + 1, myHeroCurY);
			
			if (myHeroCurRoom.getHasPit()) {
				dealPitDamage();
				statusMessage += "Fell into a pit! Lost some health.";
			}
			
			myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0);
			if (!statusMessage.isBlank()) {
				myView.showNewLineMessage(statusMessage);
			}
			statusMessage = "";
			
			if (myHeroCurRoom.getHasMonster()) {
//				myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0);
//				if (!statusMessage.isBlank()) {
//					myView.showNewLineMessage(statusMessage);
//				}
//				statusMessage = "";
				startBattle(myHero, myHeroCurRoom.getMonster());
			} else {
//				myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0);
//				if (!statusMessage.isBlank()) {
//					myView.showNewLineMessage(statusMessage);
//				}
//				statusMessage = "";
				myView.showMessage("Enter your choice: ");
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
							statusMessage = updateHeroVisionStepCount();
						} else {
							statusMessage = "Cannot move North!";
						}
						break;
					case "A":
						if (myHeroCurRoom.getHasDoors()[Direction.WEST.ordinal()]) {
							updateHeroPosition(myHeroCurX - 1, myHeroCurY);
							statusMessage = updateHeroVisionStepCount();
						} else {
							statusMessage = "Cannot move West!";
						}
						break;
					case "S":
						if (myHeroCurRoom.getHasDoors()[Direction.SOUTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY + 1);
							statusMessage = updateHeroVisionStepCount();
						} else {
							statusMessage = "Cannot move South!";
						}
						break;
					case "D":
						if (myHeroCurRoom.getHasDoors()[Direction.EAST.ordinal()]) {
							updateHeroPosition(myHeroCurX + 1, myHeroCurY);
							statusMessage = updateHeroVisionStepCount();
						} else {
							statusMessage = "Cannot move East!";
						}
						break;
					case "E":
						if (myHeroCurRoom.getHasPillarOO()) {
							((Hero) myHero).collectPillar(myHeroCurRoom.getPillar());
							myHeroCurRoom.setHasPillarOO(false);
							statusMessage = "Collected a Pillar of OOP: "
									+ myHeroCurRoom.getPillar().toString() + ".";
						} else if (myHeroCurRoom.getHasHealingPotion() || myHeroCurRoom.getHasVisionPotion()) {
							if (myHeroCurRoom.getHasHealingPotion()) {
								((Hero) myHero).collectHealingPotion();
								myHeroCurRoom.setHasHealingPotion(false);
								statusMessage += "Collected a Healing Potion.";
							}
							
							if (myHeroCurRoom.getHasVisionPotion()) {
								((Hero) myHero).collectVisionPotion();
								myHeroCurRoom.setHasVisionPotion(false);
								if (!statusMessage.isBlank()) {
									statusMessage += " ";
								}
								statusMessage += "Collected a Vision Potion.";
							}
						} else {
							statusMessage = "Invalid choice.";
						}
						break;
					case "H":
						if (((Hero) myHero).getHasHealingPotions()) {
							((Hero) myHero).useHealingPotion();
							statusMessage = "Used a Healing Potion!";
							if (myHero.isFullHealth()) {
								statusMessage += " Full health!";
							}
						} else {
							statusMessage = "You do not have any Healing Potions.";
						}
						break;
					case "V":
						if (((Hero) myHero).getHasVisionPotions()) {
							if (myHeroVisionStepCount <= 0) {
								((Hero) myHero).useVisionPotion();
								myHeroVisionStepCount = VISION_MAX_STEP_COUNT;
								statusMessage = "Used a Vision Potion! Gained Vision for 3 steps.";
							} else {
								statusMessage = "You still have Vision.";
							}
						} else {
							statusMessage = "You do not have any Vision Potions.";
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
			myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0);
			myView.getUserInput();
		} else if (!myHero.isAlive()) {
			myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0);
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
		String statusMessage = "";
		int curTurn = 0;
		
		while (theHero.isAlive() && theMonster.isAlive()) {
			if (curTurn % 2 == 0) {
				while (battleInput == "") {
					myView.showBattle(theHero, theMonster);
					if (!statusMessage.isBlank()) {
						myView.showNewLineMessage(statusMessage);
					}
					statusMessage = "";
					myView.showMessage("Enter your choice: ");
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
							if (((Hero) theHero).getHasHealingPotions()) {
								((Hero) theHero).useHealingPotion();
								myView.showMessage("Used a Healing Potion! ");
								if (theHero.isFullHealth()) {
									myView.showMessage("Full health! ");
								}
								myView.showMessage("[ENTER] to continue.");
								myView.getUserInput();
							} else {
								statusMessage = "You don't have any healing potions.";
								battleInput = "";
							}
							break;
						case "`":
							theMonster.setCurHealthPoints(0);
							break;
						case "~":
							theHero.setCurHealthPoints(0);
							break;
						default:
							statusMessage = "Invalid choice.";
							battleInput = "";
					}
				}
				
				battleInput = "";
			} else {
				myView.showBattle(theHero, theMonster);
				
				theMonster.attack(theHero);
				myView.showMessage(String.format("%s attacked you! [ENTER] to continue.", theMonster.getName()));
				myView.getUserInput();
			}
			
			curTurn++;
		}
		
		myView.showBattle(theHero, theMonster);
		if (!theMonster.isAlive()) {
			myHeroCurRoom.setHasMonster(false);
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
		if (myDungeon == null || myHero == null) {
			myView.showMessage("Error: Cannot save, missing game data. Start a new game first. [ENTER] to continue.");
			myView.getUserInput();
			return;
		}

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

	/**
	 * Deals non-lethal damage to the Hero when they're over a Pit.
	 */
	private void dealPitDamage() {
		int maxPitDamage = 10;
		int maxPossibleDamage = Math.min(maxPitDamage, myHero.getCurHealthPoints() - 1);
		((Hero) myHero).receiveTrueDamage(myRandom.nextInt(1, maxPossibleDamage + 1));
	}
	
	private String updateHeroVisionStepCount() {
		String result = "";
		
		myHeroVisionStepCount--;
		if (myHeroVisionStepCount == 0) {
			result = "Vision has run out!";
		}
		
		return result;
	}

}

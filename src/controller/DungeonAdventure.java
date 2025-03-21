/**
 * TCSS 360 Group Project
 */
package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Random;

import model.Difficulty;
import model.Direction;
import model.Dungeon;
import model.DungeonCharacter;
import model.DungeonCharacterFactory;
import model.Hero;
import model.HeroType;
import model.Room;
import view.ConsoleView;

/**
 * Controller for the Dungeon Adventure game.
 * 
 * Manages the game flow, user input, and interactions between the player and the dungeon.
 * This includes hero selection, starting and loading games, running battles, and saving or loading progress.
 * 
 * @author Justin Le, Anna Brewer
 * @version 21 Mar 2025
 */
public class DungeonAdventure implements PropertyChangeListener {

	/** How many steps the Hero can get Vision for upon consuming a Vision Potion. */
	private static final int VISION_MAX_STEP_COUNT = 3;

	/** Random instance used to generate random numbers. */
	private Random myRandom = new Random();

	/** Factory for creating DungeonCharacters. */
	private DungeonCharacterFactory myCharFactory = new DungeonCharacterFactory();

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

	/** Whether or not the Hero is in a battle. */
	private boolean myHeroIsInBattle;

	/** The console-based view for user interaction. */
	private ConsoleView myView;

	/** Flag to track if the Hero has an extra turn. */
	private boolean myHeroHasExtraTurn = false;

	/** Whether or not the player has cheats enabled. */
	private boolean myHasCheatsEnabled = false;

	/** Whether or not the player has infinite health enabled. */
	private boolean myHasInfiniteHealth = false;

	/** Whether or not the player has infinite damage enabled. */
	private boolean myHasInfiniteDamage = false;

	/**
	 * Constructs a DungeonAdventure controller.
	 * 
	 * @param theView the ConsoleView used for user interaction
	 */
	public DungeonAdventure(ConsoleView theView) {
		myView = theView;
	}

	/**
	 * Starts the Dungeon Adventure by displaying the main menu and responding to the player's selection.
	 * Options include starting a new game, loading or saving progress, initiating a quick start (for debugging),
	 * or exiting the game. Invalid inputs prompt the player to try again.
	 */
	public void startDungeonAdventure() {
		boolean isRunning = true;
		String mainMenuChoice = "";
		String statusMessage = "";
		
		while (isRunning) {
			myView.showMainMenu(myHasCheatsEnabled);
			if (!statusMessage.isBlank()) {
				myView.showNewLineMessage(statusMessage);
			}
			statusMessage = "";
			myView.showMessage("Enter your choice: ");
			mainMenuChoice = myView.getUserInput();
			
			switch (mainMenuChoice) {
				case "1":
					startNewGame();
					break;
				case "2":
					loadGame("dungeonadventure.dat");
					break;
				case "3":
					myHasCheatsEnabled = !myHasCheatsEnabled;
					statusMessage = String.format("Cheats %s.",
							myHasCheatsEnabled ? "enabled" : "disabled");
					break;
				case "0":
					myView.showMessage("Exiting game.");
					isRunning = false;
					break;
				default:
					statusMessage = "Invalid choice.";
			}
		}
	}

	/**
	 * Starts a new game by prompting the player to choose a hero type, select a difficulty level,
	 * enter a name, and generate a new dungeon. 
	 * Once setup is complete, the game begins.
	 */
	private void startNewGame() {
		selectHero();
		selectDifficulty();
		enterHeroName();
		createNewDungeon();
		playGame();
	}

	/**
	 * Prompts the player to choose a hero type: Warrior, Priestess, Thief, or Berserker.
	 * Creates and displays the selected hero when a valid input is provided.
	 * Prompts the player again for invalid input.
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
					myHero.addPropertyChangeListener(this);
				} else {
					statusMessage = "Invalid choice.";
					heroChoice = "";
				}
			} catch (NumberFormatException e) {
				statusMessage = "Invalid choice.";
				heroChoice = "";
			}
		}

		myView.showNewLineMessage("You have chosen: " + myHero.getName() + ". [ENTER] to continue.");
		myView.getUserInput();
	}

	/**
	 * Prompts the player to choose a difficulty level: Easy, Medium, or Hard.
	 * Applies the selected level when valid, or prompts the player again if not.
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
	 * Prompts the player to enter a hero name, up to 20 characters long.
	 * Uses a default name based on the selected hero type if left empty, or prompts again if the input is too long.
	 * Assigns the entered name to the hero once valid.
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
	 * Creates a new dungeon using the selected difficulty level and places the hero at the dungeon entrance.
	 */
	private void createNewDungeon() {
		myDungeon = new Dungeon(myCharFactory, myDifficulty);
		updateHeroPosition(myDungeon.getEntrance().getRoomX(), myDungeon.getEntrance().getRoomY());
		myHeroVisionStepCount = 0;
	}

	/**
	 * Runs the main game loop and updates the game state based on the hero's actions.
	 * While navigating the dungeon, the player can move between rooms, collect items, fall into pit traps, 
	 * trigger debug features, or encounter a monster. If a monster is present in the current room, a battle begins. 
	 * The player can save the game or return to the main menu as long as they are not in combat.
	 */
	private void playGame() {
		String statusMessage = "";
		Room[] adjacentRooms = new Room[4];
		boolean hasTakenPitDamage = false;

		while (myHero.isAlive() && !(myHeroCurRoom.getHasExit() && ((Hero) myHero).getHasAllPillars())) {
			adjacentRooms[Direction.NORTH.ordinal()] = myDungeon.getRoomAt(myHeroCurX, myHeroCurY - 1);
			adjacentRooms[Direction.WEST.ordinal()] = myDungeon.getRoomAt(myHeroCurX - 1, myHeroCurY);
			adjacentRooms[Direction.SOUTH.ordinal()] = myDungeon.getRoomAt(myHeroCurX, myHeroCurY + 1);
			adjacentRooms[Direction.EAST.ordinal()] = myDungeon.getRoomAt(myHeroCurX + 1, myHeroCurY);

			if (!hasTakenPitDamage && myHeroCurRoom.getHasPit()) {
				if (!statusMessage.isBlank()) {
					statusMessage += " ";
				}
				statusMessage += "Fell into a pit! Lost " + dealPitDamage() +" HP!";
				hasTakenPitDamage = true;
			}

			if (myHasInfiniteHealth) {
				myHero.setCurHealthPoints(myHero.getMaxHealthPoints());
			}

			myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0, myHasCheatsEnabled);
			if (!statusMessage.isBlank()) {
				myView.showNewLineMessage(statusMessage);
			}
			statusMessage = "";

			if (myHeroCurRoom.getHasMonster()) {
				startBattle(myHero, myHeroCurRoom.getMonster());
			} else {
				myView.showMessage("Enter your choice: ");
				String userInput = myView.getUserInput();

				if (userInput.equals("K")) {
					saveGame("dungeonadventure.dat");
					statusMessage = "Game saved!";
					continue;
				}

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
							hasTakenPitDamage = false;
						} else {
							statusMessage = "Cannot move North!";
						}
						break;
					case "A":
						if (myHeroCurRoom.getHasDoors()[Direction.WEST.ordinal()]) {
							updateHeroPosition(myHeroCurX - 1, myHeroCurY);
							statusMessage = updateHeroVisionStepCount();
							hasTakenPitDamage = false;
						} else {
							statusMessage = "Cannot move West!";
						}
						break;
					case "S":
						if (myHeroCurRoom.getHasDoors()[Direction.SOUTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY + 1);
							statusMessage = updateHeroVisionStepCount();
							hasTakenPitDamage = false;
						} else {
							statusMessage = "Cannot move South!";
						}
						break;
					case "D":
						if (myHeroCurRoom.getHasDoors()[Direction.EAST.ordinal()]) {
							updateHeroPosition(myHeroCurX + 1, myHeroCurY);
							statusMessage = updateHeroVisionStepCount();
							hasTakenPitDamage = false;
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
					case "!":
						if (myHasCheatsEnabled) {
							myHasInfiniteHealth = !myHasInfiniteHealth;
							statusMessage = String.format("%s infinite health.",
									myHasInfiniteHealth ? "Enabled" : "Disabled"
							);
						} else {
							statusMessage = "Invalid choice.";
						}
						break;
					case "@":
						if (myHasCheatsEnabled) {
							if (myHeroVisionStepCount < 3) {
								myHeroVisionStepCount = Integer.MAX_VALUE;
								statusMessage = "Enabled infinite vision.";
							} else {
								myHeroVisionStepCount = 0;
								statusMessage = "Disabled infinite vision.";
							}
						} else {
							statusMessage = "Invalid choice.";
						}
						break;
					case "#":
						myHasInfiniteDamage = !myHasInfiniteDamage;
						statusMessage = String.format("%s infinite damage.",
								myHasInfiniteDamage ? "Enabled" : "Disabled"
						);
						break;
					default:
						statusMessage = "Invalid choice.";
					}
			}
		}

		if (myHeroCurRoom.getHasExit() && ((Hero) myHero).getHasAllPillars()) {
			myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0, myHasCheatsEnabled);
			myView.getUserInput();
		} else if (!myHero.isAlive()) {
			myView.showHeroCurRoom(myHero, myHeroCurRoom, adjacentRooms, myHeroVisionStepCount > 0, myHasCheatsEnabled);
			myView.getUserInput();
		}
	}

	/**
	 * Starts a battle between the hero and a monster. 
	 * The battle alternates between the hero's and the monster's turns. 
	 * During the hero's turn, the player can choose to attack, use a special attack, or heal with a potion. 
	 * The player inputs their choice each round. 
	 * Once the battle ends, the player is notified whether the hero won or lost.
	 *
	 * @param theHero    the hero character
	 * @param theMonster the monster character
	 */
	private void startBattle(final DungeonCharacter theHero, final DungeonCharacter theMonster) {
		myHeroIsInBattle = true;
		theMonster.addPropertyChangeListener(this);
		String statusMessage = "";
		int curTurn = 0;
		
		while (theHero.isAlive() && theMonster.isAlive()) {
			if (myHasInfiniteHealth) {
				myHero.setCurHealthPoints(myHero.getMaxHealthPoints());
			}
			
			if (curTurn % 2 == 0) {
				String battleInput = "";
				
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
							myView.showMessage(String.format("Attacked %s! [ENTER] to continue.", theMonster.getName()));
							myView.getUserInput();
							if (myHasInfiniteDamage) {
								theHero.attack(theMonster, 999, 1);
							} else {
								theHero.attack(theMonster);
							}
							break;
						case "2":
							myView.showMessage("Used special attack! [ENTER] to continue.");
							myView.getUserInput();
							if (myHasInfiniteDamage) {
								((Hero) theHero).specialAttack(theMonster, 999, 1);
							} else {
								((Hero) theHero).specialAttack(theMonster);
							}
							break;
						case "3":
							if (((Hero) theHero).getHasHealingPotions()) {
								myView.showMessage("Used a Healing Potion! [ENTER] to continue.");
								myView.getUserInput();
								((Hero) theHero).useHealingPotion();
							} else {
								statusMessage = "You don't have any Healing Potions.";
								battleInput = "";
							}
							break;
						default:
							statusMessage = "Invalid choice.";
							battleInput = "";
					}
				}

				if (myHeroHasExtraTurn) {
					myHeroHasExtraTurn = false; 
				} else {
					curTurn++;
				}
			} else {
				myView.showBattle(theHero, theMonster);
				
				myView.showMessage(String.format("%s attacked! [ENTER] to continue.", theMonster.getName()));
				myView.getUserInput();
				if (myHasInfiniteHealth) {
					theMonster.attack(theHero, 0, 1);
				} else {
					theMonster.attack(theHero);
				}
				curTurn++;
			}
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
		
		myHeroIsInBattle = false;
	}

	/**
	 * Updates the hero's position in the dungeon by setting their new coordinates.
	 * The hero's current room is also updated based on the new position.
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
	 * Displays an error message if the dungeon or hero is missing,
	 * and a confirmation message if saved successfully.
	 * 
	 * @param filename the file to save the current game state to
	 */
	private void saveGame(String filename) {
		if (myDungeon == null || myHero == null) {
			myView.showNewLineMessage("Error: Cannot save, missing game data. "
					+ "Start a new game first. [ENTER] to continue.");
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
	 * Displays an error if the file is missing, empty, or corrupted,
	 * and a confirmation message if the game loads successfully.
	 *
	 * @param filename the file to load the saved game state from
	 */
	private void loadGame(String filename) {
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {

			myDungeon = (Dungeon) in.readObject();
			myHero = (Hero) in.readObject();
			myHero.addPropertyChangeListener(this);
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
	 * Deals random non-lethal damage to the Hero when they're over a Pit.
	 * 
	 * @return a string message of the random non-lethal damage
	 */
	private String dealPitDamage() {
		int result = 0;
		
		int maxPitDamage = 10;
		int maxPossibleDamage = Math.min(maxPitDamage, myHero.getCurHealthPoints() - 1);
		if (!myHasInfiniteHealth) {
			result = myRandom.nextInt(1, maxPossibleDamage + 1);
		}
		((Hero) myHero).receiveTrueDamage(result);
		
		return Integer.toString(result);
	}

	/**
	 * Updates the step counter for the Hero's Vision effect.
	 * 
	 * @return a string message if the Vision effect runs out
	 */
	private String updateHeroVisionStepCount() {
		String result = "";
		
		myHeroVisionStepCount--;
		if (myHeroVisionStepCount == 0) {
			result = "Vision has run out!";
		}
		
		return result;
	}

	/**
	 * Responds to character status updates during gameplay.
	 * 
	 * Displays messages in response to property change events triggered by the hero or a monster, 
	 * including damage taken, healing received, health updates, and extra turns. 
	 * Called automatically when registered characters fire events.
	 * 
	 * @param theEvent the property change event triggered by a character
	 */
	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		String property = theEvent.getPropertyName();
		DungeonCharacter character = (DungeonCharacter) theEvent.getSource();

		if (myHeroIsInBattle) {
			switch (property) {
				case "damageReceived":
					myView.showMessage(character.getName() + " received "
							+ theEvent.getNewValue() + " damage!");
					break;
				case "healingReceived":
					myView.showMessage(character.getName() + " received "
							+ theEvent.getNewValue() + " healing!");
					break;
				case "damageBlocked":
					myView.showMessage(character.getName() + " blocked the attack!");
					break;
				case "extraTurnReceived":
					myView.showMessage(character.getName() + " received an extra turn!");
					myHeroHasExtraTurn = true;
					break;
				case "specialDidNothing":
					myView.showMessage(character.getName() + "'s special attack did nothing!");
					break;
				default:
					break;
			}
			
			myView.showMessage(" [ENTER] to continue.");
			myView.getUserInput();
		}
	}
}

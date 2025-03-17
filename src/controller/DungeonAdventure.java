/**
 * TCSS 360 Group Project
 */
package controller;

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
 * @author Justin Le
 * @version 16 Mar 2025
 */
public class DungeonAdventure {
	
	private DungeonCharacterFactory myCharFactory;
	private DungeonCharacter myHero;
	private Dungeon myDungeon;
	private int myHeroCurX;
	private int myHeroCurY;
	private Room myHeroCurRoom;
	private ConsoleView myView;
	
	public DungeonAdventure(ConsoleView theView) {
		myCharFactory = new DungeonCharacterFactory();
		myView = theView;
	}
	
	public void startDungeonAdventure() {
		while (true) {
			myView.showMainMenu();
			String mainMenuChoice = myView.getUserInput();
			switch (mainMenuChoice) {
				case "1":
					startNewGame();
					break;
				case "2":
					myView.showMessage("Load game is not implemented yet. [ENTER] to continue.");
					myView.getUserInput();
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
	
	private void startNewGame() {
		selectHero();
		selectDifficulty();
		enterHeroName();
		createNewDungeon();
		
		playGame();
	}
	
	private void selectHero() {
		String heroChoice = "";
		
		while (heroChoice == "") {
			myView.showHeroSelection();
			heroChoice = myView.getUserInput();
			
			switch (heroChoice) {
				case "1": myHero = myCharFactory.createDungeonCharacter(HeroType.WARRIOR); break;
				case "2": myHero = myCharFactory.createDungeonCharacter(HeroType.PRIESTESS); break;
				case "3": myHero = myCharFactory.createDungeonCharacter(HeroType.THIEF); break;
				default:
					myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
					myView.getUserInput();
					heroChoice = "";
			}
		}
		
		myView.showMessage("You have chosen: " + myHero.getName() + ". [ENTER] to continue.");
		myView.getUserInput();
	}
	
	private void selectDifficulty() {
		String difficultyChoice = "";
		
		while (difficultyChoice == "") {
			myView.showDifficultySelection();
			difficultyChoice = myView.getUserInput();
			
			switch (difficultyChoice) {
				case "1": break;
				case "2": break;
				case "3": break;
				default:
					myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
					myView.getUserInput();
					difficultyChoice = "";
			}
		}
		
		myView.showMessage("You have chosen: " + "Medium" + ". [ENTER] to continue.");
		myView.getUserInput();
	}
	
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
	
	private void createNewDungeon() {
		myDungeon = new Dungeon(myCharFactory);
		updateHeroPosition(myDungeon.getEntrance().getRoomX(), myDungeon.getEntrance().getRoomY());
	}
	
	private void playGame() {
		while (myHero.isAlive() && !myHeroCurRoom.getHasExit()
				&& !((Hero) myHero).getHasAllPillars()) {
			if (myHeroCurRoom.getHasMonster()) {
				myView.showHeroCurRoom(myHero, myHeroCurRoom);
				startBattle(myHero, myHeroCurRoom.getMonster());
			} else {
				String userInput = "";
				
				while (userInput == "") {
					myView.showHeroCurRoom(myHero, myHeroCurRoom);
					userInput = myView.getUserInput();
					
					switch (userInput) {
					case "W":
						if (myHeroCurRoom.getHasDoors()[Direction.NORTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY - 1);
						}
						break;
					case "A":
						if (myHeroCurRoom.getHasDoors()[Direction.WEST.ordinal()]) {
							updateHeroPosition(myHeroCurX - 1, myHeroCurY);
						}
						break;
					case "S":
						if (myHeroCurRoom.getHasDoors()[Direction.SOUTH.ordinal()]) {
							updateHeroPosition(myHeroCurX, myHeroCurY + 1);
						}
						break;
					case "D":
						if (myHeroCurRoom.getHasDoors()[Direction.EAST.ordinal()]) {
							updateHeroPosition(myHeroCurX + 1, myHeroCurY);
						}
						break;
					case "`":
						myHeroCurRoom.setHasMonster(true);
						MonsterType randomMonsterType = MonsterType.values()[new Random().nextInt(MonsterType.values().length)];
						Monster newMonster = (Monster) myCharFactory.createDungeonCharacter(randomMonsterType);
						myHeroCurRoom.setMonster(newMonster);
						break;
					case "~":
						for (PillarOO curPillar : PillarOO.values()) {
							((Hero) myHero).collectPillar(curPillar);
						}
						myHeroCurRoom.forceHasExit();
						break;
					default:
						myView.showMessage("Invalid choice. Please try again. [ENTER] to continue.");
						myView.getUserInput();
						userInput = "";
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
	
	private void updateHeroPosition(final int theX, final int theY) {
		myHeroCurX = theX;
		myHeroCurY = theY;
		myHeroCurRoom = myDungeon.getRoomAt(myHeroCurX, myHeroCurY);
	}
	
}

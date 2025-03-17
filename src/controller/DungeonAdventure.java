/**
 * TCSS 360 Group Project
 */
package controller;

import model.Direction;
import model.Dungeon;
import model.DungeonCharacter;
import model.DungeonCharacterFactory;
import model.HeroType;
import model.Room;
import view.ConsoleView;

/**
 * 
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
					myView.showMessage("Load game is not implemented yet.");
					break;
				case "0":
					myView.showMessage("Exiting game.");
					return;
				default:
					myView.showMessage("Invalid choice. Please try again.");
			}
		}
	}
	
	private void startNewGame() {
		selectHero();
		selectDifficulty();
		enterHeroName();
		createNewDungeon();
		
		playGame();
	}
	
	private void selectHero() {
		myView.showHeroSelection();
		String heroChoice = "";
		
		while (heroChoice == "") {
			heroChoice = myView.getUserInput();
			
			switch (heroChoice) {
				case "1": myHero = myCharFactory.createDungeonCharacter(HeroType.WARRIOR); break;
				case "2": myHero = myCharFactory.createDungeonCharacter(HeroType.PRIESTESS); break;
				case "3": myHero = myCharFactory.createDungeonCharacter(HeroType.THIEF); break;
				default:
					myView.showMessage("Invalid choice. Please try again.");
					heroChoice = "";
			}
		}
		
		myView.showMessage("You have chosen: " + myHero.getName());
	}
	
	private void selectDifficulty() {
		myView.showDifficultySelection();
		String difficultyChoice = "";
		
		while (difficultyChoice == "") {
			difficultyChoice = myView.getUserInput();
			
			switch (difficultyChoice) {
				case "1": break;
				case "2": break;
				case "3": break;
				default:
					myView.showMessage("Invalid choice. Please try again.");
					difficultyChoice = "";
			}
		}
		
		myView.showMessage("You have chosen: " + "Medium");
	}
	
	private void enterHeroName() {
		myView.showHeroNameInput();
		String nameInput = "";
		
		while (nameInput == "") {
			nameInput = myView.getUserInput();
			
			if (nameInput.length() > 20) {
				myView.showMessage("Name is too long. Please try again.");
				nameInput = "";
			} else if (nameInput.isBlank()) {
				myView.showMessage("Name cannot be blank. Please try again.");
			} else {
				myHero.setName(nameInput);
			}
		}
		
		myView.showMessage("Your hero's name is: " + myHero.getName());
	}
	
	private void createNewDungeon() {
		myDungeon = new Dungeon(myCharFactory);
		updateHeroPosition(myDungeon.getEntrance().getRoomX(), myDungeon.getEntrance().getRoomY());
	}
	
	private void playGame() {
		while (true) {
			myView.showHeroCurRoom(myHeroCurRoom);
			String userInput = "";
			
			while (userInput == "") {
				userInput = myView.getUserInput().toUpperCase();
				
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
					default:
						myView.showMessage("Invalid choice. Please try again.");
						userInput = "";
 				}
			}
//			myView.showMessage("Reached end of program.");
//			myView.getUserInput();
		}
	}
	
	private void updateHeroPosition(final int theX, final int theY) {
		myHeroCurX = theX;
		myHeroCurY = theY;
		myHeroCurRoom = myDungeon.getRoomAt(myHeroCurX, myHeroCurY);
	}
	
}

/**
 * TCSS 360 Group Project
 */
package controller;

import model.Dungeon;
import model.DungeonCharacter;
import model.DungeonCharacterFactory;
import model.HeroType;
import view.ConsoleView;

/**
 * 
 */
public class DungeonAdventure {
	
	private DungeonCharacterFactory myCharFactory;
	private DungeonCharacter myHero;
	private Dungeon myDungeon;
	private ConsoleView myView;
//	private DungeonModel model; // Replace with your actual model class
	
//	public GameController(GameView view, DungeonModel model) {
	public DungeonAdventure(ConsoleView theView) {
		myCharFactory = new DungeonCharacterFactory();
		myView = theView;
//		model = model;
	}
	
	public void launchGame() {
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
		
		while (true) {
//			myView.showMessage(myDungeon.toString());
			myView.showMessage("Reached end of program.");
			myView.getUserInput();
		}
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
	}
	
}

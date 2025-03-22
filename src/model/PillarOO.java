/**
 * TCSS 360 Group Project
 */
package model;

/**
 * Represents the different Pillars of OOP.
 * 
 * @author Arthur Fornia, Justin Le
 * @version 21 Mar 2025
 */
public enum PillarOO {
	ABSTRACTION("Abstraction"), ENCAPSULATION("Encapsulation"),
	INHERITANCE("Inheritance"), POLYMORPHISM("Polymorphism");
	
	private final String myDisplayName;
	
	private PillarOO(String theName) {
		myDisplayName = theName;
	}
	
	@Override
	public String toString() {
		return myDisplayName;
	}
}

package it.polimi.ingsw.ps06.view.GUI;

import java.util.Observable;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;

public class MenuCLI extends Observable implements Menu {
	
	private Scanner input;
	
	public MenuCLI(Scanner input) {
		
		this.input = input;
		
		show();
	}
	
	public Scanner getScanner() {
		return input;
	}
	
	public void addNewControllerObserver(MenuController controller) {
		addObserver(controller);
	}
	
	public void show() {
		
		System.out.println();
		System.out.println(" --- Lorenzo il Magnifio --- ");
		System.out.println();
		System.out.println(" 1) Nuova Partita");
		System.out.println(" 2) Esci");
		System.out.println();
		System.out.print(" > ");
		
		while(true) readInput();
	}
	
	public void readInput() {
		int i = Integer.parseInt(input.nextLine());
		
		setChanged();
		notifyObservers(i);
	}
	
	public void showErrorMessage(String messageError) {
		System.out.println();
		System.out.print(messageError);
	}
}

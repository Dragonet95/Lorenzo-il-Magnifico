package it.polimi.ingsw.ps06;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.ps06.controller.MenuController;
import it.polimi.ingsw.ps06.view.GUI.MenuCLI;
import it.polimi.ingsw.ps06.view.GUI.MenuGUI;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Lorenzo il magnifico!");
        
        setup();
    }
    
    public static void setup() throws IOException {
    	Scanner s = new Scanner(System.in);
    	
    	System.out.print("Press 1 for CLI or 2 for GUI > ");
    	
    	MenuController controller = ( Integer.parseInt(s.nextLine()) == 1 ) ? new MenuController(new MenuCLI(s)) : new MenuController(new MenuGUI());

    }
}

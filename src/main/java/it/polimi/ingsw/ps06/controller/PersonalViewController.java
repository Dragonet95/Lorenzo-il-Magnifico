package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.events.Event;
import it.polimi.ingsw.ps06.model.events.EventParser;
import it.polimi.ingsw.ps06.model.events.StoryBoard;
import it.polimi.ingsw.ps06.networking.Client;
import it.polimi.ingsw.ps06.networking.messages.Client2Server;
import it.polimi.ingsw.ps06.networking.messages.EventMessage;
import it.polimi.ingsw.ps06.networking.messages.Message;
import it.polimi.ingsw.ps06.networking.messages.MessageParser;
import it.polimi.ingsw.ps06.networking.messages.Server2Client;
import it.polimi.ingsw.ps06.view.PersonalView;

public class PersonalViewController extends Observable implements Observer{

	private PersonalView theView;
	private Client theModel;
	
	private BoardController thePreviousController;


	public PersonalViewController(Client model, PersonalView view, BoardController thePreviousController) {
		this.theView = view;
		this.theModel = model;
		this.thePreviousController = thePreviousController;
		
		System.out.println("PERSONAL VIEW IS THE BOSS");
	}
	
	public BoardController getThePreviousController() {
		return thePreviousController;
	}

	public void setThePreviousController(BoardController thePreviousController) {
		this.thePreviousController = thePreviousController;
	}

	public void addNewObserver(Observer o) {
		addObserver(o);
	}

	public void notifyChangement(Message m) {
		setChanged();
		notifyObservers(m);
	} 

	@Override
	public void update(Observable o, Object arg) {
		/*
		if (!( arg instanceof Message))
		return;
		 */

		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theModel) ) {
			handleMessage( (Message) arg );
			return;
		}

		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {

			if (arg instanceof Message) {
				handleMessage( (Message) arg );
				return;
			}

			if (arg instanceof Event) {
				handleEvent( (Event) arg );
				return;
			}
		}
	}

	private void handleMessage(Message m) {


		if (m instanceof Server2Client) {
			//This is a normal SERVER -> CLIENT message (time to parse it)
			((Server2Client) m).accept(new MessageParser(theView));
			return;
		}	

		if (m instanceof Client2Server) {
			//This is a normal CLIENT -> SERVER message (let someone else handle it)
			notifyChangement(m);
		}	
	}

	private void handleEvent(Event e) {

		if ( e instanceof StoryBoard) 
		{
			//Let the controller handle this, it's just a StoryBoard Event (new View)
			EventParser parser = new EventParser(this);
			((StoryBoard) e).accept(parser);
		} 
		else 
		{
			//Event to let the model handle
			EventMessage m = new EventMessage(e);
			notifyChangement(m);
		}
	}

}

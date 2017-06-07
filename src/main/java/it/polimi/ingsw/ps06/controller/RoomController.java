package it.polimi.ingsw.ps06.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.Client;
import it.polimi.ingsw.ps06.model.messages.Message;
import it.polimi.ingsw.ps06.model.messages.MessageConnection;
import it.polimi.ingsw.ps06.model.messages.MessageConnectionStart;
import it.polimi.ingsw.ps06.model.messages.MessageParser;
import it.polimi.ingsw.ps06.model.messages.Server2Client;
import it.polimi.ingsw.ps06.model.messages.StoryBoard;
import it.polimi.ingsw.ps06.view.Room;

public class RoomController extends Observable implements Observer {
	
	private Room theView;
	private Client theModel;
	
	private ArrayList<String> players;
	
	public RoomController(Client model, Room view) {
		this.theView = view;
		this.theModel = model;
		
		// ---> START THE CLIENT
		MessageConnectionStart cs = new MessageConnectionStart();
		MessageParser parser = new MessageParser();
		cs.accept(parser);
	}
	
	public void addNewObserver(Observer o) {
		addObserver(o);
	}
	
	public void notifyChangement(Object o) {
		setChanged();
		notifyObservers(o);
	} 

	@Override
	public void update(Observable o, Object arg) {
		if (!( arg instanceof Message))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theModel) ) {
			
			if (arg instanceof MessageConnection) {
				
				if (arg instanceof Server2Client) {
					//This is a normal CLIENT -> SERVER message
					
					Client.getInstance().asyncSend((Server2Client) arg);
				}
				else
				{
					//This is a normal SERVER -> CLIENT message
					MessageParser parser = new MessageParser();
					players = ((MessageConnection) arg).accept(parser);
					
					theView.clearPlayers();
					for (String s : players) {
						theView.setPlayer(s, players.indexOf(s));
					}
				}
			}
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			
			if ( arg instanceof StoryBoard) 
			{
				//Let the controller handle this, it's just a StoryBoard Event (new View)
				MessageParser parser = new MessageParser();
				((StoryBoard) arg).accept(parser);
			} 
			else 
			{
				//Event to let the model handle
				notifyChangement(arg);
			}
		}
	}
}
package it.polimi.ingsw.ps06.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Event;
import it.polimi.ingsw.ps06.model.EventParser;
import it.polimi.ingsw.ps06.view.GUI.Room;

public class RoomController implements Observer {
	
	private Room theView;
	private Client theClient;
	
	public RoomController(Client client, Room roomView) {
		this.theView = roomView;
		this.theClient = client;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(arg instanceof Event))
			return;
		
		//Smista le comunicazioni provenienti dal Client
		if ( o.getClass().isInstance(theClient) ) {
	
		}
		
		//Smista le comunicazioni provenienti dalla View
		if ( o.getClass().isInstance(theView) ) {
			
			EventParser parser = new EventParser(theClient);
			((Event) arg).accept(parser);
		}
	}
}

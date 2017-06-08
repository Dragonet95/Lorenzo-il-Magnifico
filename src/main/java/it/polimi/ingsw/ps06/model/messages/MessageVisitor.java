package it.polimi.ingsw.ps06.model.messages;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.model.events.StoryBoard2PersonalView;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Room;

public interface MessageVisitor {
	
	/*
	public void visit(EventClose eventClose);
	
	public void visit(StoryBoard2Room storyboard);
	public void visit(StoryBoard2Board storyboard);
	public void visit(StoryBoard2PersonalView storyboard);
	
	public void visit(EventMemberPlaced memberPlaced);
	public void visit(EventLeaderDiscarded leaderDiscarded);
	public void visit(EventLeaderActivated leaderActivated);
	public void visit(EventLeaderPlayed leaderPlayed);
	*/
	
	public void visit(MessageUser userMessage);
	public void visit(MessageWaitingRoomConnections waitingConnections);
	
	public void visit(MessageGameStart gameStart);
	public void visit(MessageGameHasStarted hasStarted);
	public void visit(MessageConnectionStart startConnection);
	
	public void visit(MessageBoardSetupDice boardSetupDice);
}
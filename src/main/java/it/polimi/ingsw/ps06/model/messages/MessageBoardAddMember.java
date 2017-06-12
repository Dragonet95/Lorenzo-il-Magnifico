package it.polimi.ingsw.ps06.model.messages;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.ColorPalette;

public class MessageBoardAddMember extends Server2Client {

	private Action actionExecuted;
	private ColorPalette color;
	private int playerIndex;
	
	public MessageBoardAddMember(Action actionExecuted, ColorPalette color, int playerIndex) {
		this.actionExecuted = actionExecuted;
		this.color = color;
		this.playerIndex = playerIndex;
	}
	
	public Action getActionExecuted() {
		return actionExecuted;
	}


	public void setActionExecuted(Action actionExecuted) {
		this.actionExecuted = actionExecuted;
	}


	public ColorPalette getColor() {
		return color;
	}


	public void setColor(ColorPalette color) {
		this.color = color;
	}


	public int getPlayerIndex() {
		return playerIndex;
	}


	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}


	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}

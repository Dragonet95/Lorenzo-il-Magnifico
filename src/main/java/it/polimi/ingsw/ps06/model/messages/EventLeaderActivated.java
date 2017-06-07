package it.polimi.ingsw.ps06.model.messages;

public class EventLeaderActivated extends EventLeader {
	
	public EventLeaderActivated(int leaderCode) {
		super(leaderCode);
	}

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}

}

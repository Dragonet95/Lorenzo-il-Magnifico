package it.polimi.ingsw.ps06.networking.messages;

public class MessageUserLoginHasFailed extends Server2Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public void accept(MessageVisitor visitor) {
		visitor.visit(this);
	}
}
package it.polimi.ingsw.ps06.model;

public class LeaderOnTableFaceUp implements LeaderState {

	private Leader leader;
	
	public LeaderOnTableFaceUp(Leader leader) {
		this.leader = leader;
	}
	
	@Override
	public String playLeader() {
		return "La carta Leader " + leader.title + "è già stata giocata";
	}

	@Override
	public String activateLeader() {
		
		if (! leader.isOncePerRoundEffect())
			return "La carta Leader " + leader.title + "non ha un effetto \"una volta per turno\"";
		
		leader.setLeaderState( leader.getActivatedLeaderState() );
		return "La carta Leader " + leader.title + "è posta faccia in giù e l'effetto è stato risolto";
	}

	@Override
	public String deactivateLeader() {
		return "La carta Leader " + leader.title + "è già a faccia in su";
	}

	@Override
	public String discardLeader() {
		return "La carta Leader " + leader.title + "è già stata giocata e non può più essere scartata";
	}
}

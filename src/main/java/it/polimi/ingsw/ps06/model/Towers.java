package it.polimi.ingsw.ps06.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps06.model.Types.Action;
import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;
import it.polimi.ingsw.ps06.model.messages.MessageBoardMemberHasBeenPlaced;
import it.polimi.ingsw.ps06.model.messages.MessageModel2ViewNotification;

/**
* Classe per la gestione delle torri
*
* @author  ps06
* @version 1.2
* @since   2017-05-10
*/

public class Towers extends Observable implements PlaceSpace {
	private ArrayList<Territory> territories;
	private ArrayList<Building> buildings;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	
	private ArrayList<FamilyMember> memberSpaces;
	private ArrayList<DevelopementCard> deck;
	private CardAcquisition acquire;
	
	private static final int CARTE_TORRE=16;
	private EffectsActive attivi; // da modificare in listener
	private int deckIndex=0;
	private int tower=0;
	private int range1=0, range2=0;
	private int errorCode=0;
	
	/**
	* Metodo per il piazzamento di un familiare su di una delle torri, include controlli di posizionamento
	*
	* @param 	member			Familiare che si vuole piazzare
	* @param 	chosenAction 	Codice dell'azione da eseguire
	* @return 					Nothing
	*/
	
	@Override
	public void placeMember(FamilyMember member, Action chosenAction, int servants) {
		int towerIndex = Action.valueOf("TOWER_GREEN_1").ordinal();
		int actionIndex = chosenAction.ordinal();
		int relativeIndex = actionIndex - towerIndex;
		int arrayIndex;
		boolean value=false;
		boolean colorRule; // Regola del colore
		boolean extraCost; // Familiare avversario presente, +3 oro
		
		//int bonus = EffectsActive.valueBonus(p);
		//memberValue = memberValue + bonus;
		
		//chosenAction = Action.TOWER_GREEN_4;
		
		int memberValue = member.getValue() + servants;	
		
		// Si può piazzare solo se il valore del membro supera quello richiesto
		if( memberValue < checkRequirement(chosenAction) ) {
			handle(1, member);
			return;
		}
		
		
		DevelopementCard card = deck.get(deckIndex + relativeIndex);
		
		// Si può piazzare solo se la carta è ancora presente, se c'è sono sicuro che non c'è un familiare
		if(  card == null ) {
			handle(2, member);
			return;
		}
		
		acquire = new CardAcquisition(card, member.getPlayer());

		checkWhichTower(chosenAction);
		arrayIndex=range1;

		colorRule=true;
		extraCost=false;

		while(arrayIndex<range2){

			// Se è presente un familiare avversario si paga un costo extra
			if(member.getFakePlayer() != null) extraCost=true; 

			// Se è presente un familiare proprio, non è possibile metterne un altro
			if(member.getFakePlayer() == memberSpaces.get(arrayIndex).getFakePlayer()) colorRule = false;
		}

		// Caso in cui il familiare sia neutro
		if(member.getFakePlayer() == null) colorRule=true;

		// Caso in cui il familiare può essere piazzato
		if(!colorRule) 
			handle(errorCode, member);
		else {
			boolean satisfied1 = acquire.checkCosts(chosenAction, extraCost);
			boolean satisfied2 = acquire.checkRequirements(chosenAction);

			if(satisfied1 && satisfied2){
				giveBonus(chosenAction, member);
				memberSpaces.add(relativeIndex, member); 

				acquire.activate();
				MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), (member.getPlayer()).getID() );
				notifyChangement(newMember);

				deck.add(deckIndex + relativeIndex, null);
			}
		}
	
	}
	
	/**
	* Costruttore delle torri. Si occupa di disporre le carte
	*
	* @param 	deck		Mazzo di carte mischiato
	* @return 	Nothing
	*/
	public Towers(ArrayList<DevelopementCard> deck) {
		
		memberSpaces = new ArrayList<FamilyMember>();
		
		this.deck=deck;
	}

	public void initializeArrayLists(){
			
			for(int j=0; j<60; j++){
					memberSpaces.add(null);
				}
			
		}
	
	private void checkWhichTower(Action chosenAction){
		
		switch(chosenAction){
			case TOWER_GREEN_1:
			case TOWER_GREEN_2:
			case TOWER_GREEN_3:
			case TOWER_GREEN_4:
				tower=1;
				range1=0;
				range2=3;
				break;
				
			case TOWER_BLUE_1:
			case TOWER_BLUE_2:
			case TOWER_BLUE_3:
			case TOWER_BLUE_4:
				tower=2;
				range1=4;
				range2=7;
				break;
				
			case TOWER_YELLOW_1:
			case TOWER_YELLOW_2:
			case TOWER_YELLOW_3:
			case TOWER_YELLOW_4:
				tower=3;
				range1=8;
				range2=11;
				break;
				
			case TOWER_PURPLE_1:
			case TOWER_PURPLE_2:
			case TOWER_PURPLE_3:
			case TOWER_PURPLE_4:
				tower=4;
				range1=12;
				range2=15;
				break;
				
			default:
				throw new IllegalArgumentException();
		}
			
	}
	
	private int checkRequirement(Action chosenAction){
		
		switch(chosenAction) {
			case TOWER_GREEN_1:
			case TOWER_BLUE_1:
			case TOWER_YELLOW_1:
			case TOWER_PURPLE_1:
				return 1;
				
			case TOWER_GREEN_2:
			case TOWER_BLUE_2:
			case TOWER_YELLOW_2:
			case TOWER_PURPLE_2:
				return 3;
				
			case TOWER_GREEN_3:
			case TOWER_BLUE_3:
			case TOWER_YELLOW_3:
			case TOWER_PURPLE_3:
				return 5;
				
			case TOWER_GREEN_4:
			case TOWER_BLUE_4:
			case TOWER_YELLOW_4:
			case TOWER_PURPLE_4:
				return 7;
				
			default:
				throw new IllegalArgumentException();
		}
			
	}
	
	private void giveBonus(Action chosenAction, FamilyMember member){
		
		EffectsResources er = null;
		Player player = member.getPlayer();
		
		if(chosenAction==Action.TOWER_GREEN_4) er = new EffectsResources(new Resources(MaterialsKind.WOOD,2));
		if(chosenAction==Action.TOWER_GREEN_3) er = new EffectsResources(new Resources(MaterialsKind.WOOD,1));
		
		if(chosenAction==Action.TOWER_BLUE_4) er = new EffectsResources(new Resources(MaterialsKind.STONE,2));
		if(chosenAction==Action.TOWER_BLUE_3) er = new EffectsResources(new Resources(MaterialsKind.STONE,1));
		
		if(chosenAction==Action.TOWER_YELLOW_4) er = new EffectsResources(new Resources(PointsKind.MILITARY_POINTS,2));
		if(chosenAction==Action.TOWER_YELLOW_3) er = new EffectsResources(new Resources(PointsKind.MILITARY_POINTS,1));
		
		if(chosenAction==Action.TOWER_PURPLE_4) er = new EffectsResources(new Resources(MaterialsKind.COIN,2));
		if(chosenAction==Action.TOWER_PURPLE_3) er = new EffectsResources(new Resources(MaterialsKind.COIN,1));
		
		er.activate(player);
		
		MessageBoardMemberHasBeenPlaced newMember = new MessageBoardMemberHasBeenPlaced(chosenAction, member.getColor(), player.getID() );
		notifyChangement(newMember);
	}
	
	/**
	* Gestisci errori di posizionamento familiare
	*
	* @param	code		codice errore
	* @return 	Nothing
	*/
	private void handle(int code, FamilyMember member){
		
		String notification = "Il giocatore " + member.getPlayer().getColorAssociatedToID() + " ha piazzato un familiare ";
		
		switch(code) {
		case 1: notification += " di valore non sufficiente per l'azione";
			break;
		case 2: notification += ", ma la carta sviluppo non c'era";
			break;
		default: notification = "UNKNOWN ERROR ON TOWERS";
		}
		
		MessageModel2ViewNotification m = new MessageModel2ViewNotification(notification);
		notifyChangement(m);
	}
	
	/**
	* Metodo per attribuire la carta al giocatore che l'ha scelta con successo
	*
	* @param 	player			Giocatore a cui dare la carta
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 	 
	*/
	public void giveCard(Player player, Action chosenAction){
		
	}
	
	/**
	* Metodo per togliere le carte rimaste sulla torre e i familiari utilizzati
	*
	* @param 	Unused
	* @return 	Nothing
	*/
	public void cleanTowers(){
		deckIndex = deckIndex + CARTE_TORRE;
		memberSpaces.clear();
	}
	
	/**
	* Metodo per ritornare l'arraylist di territori
	*
	* @return 	territories	ArrayList territori
	*/
	public ArrayList<Territory> getTerritories(){
		
		for(int i=0; i<4; i++){
			territories.add((Territory)deck.get(i));
		}
		return territories;
	}
	
	/**
	* Metodo per ritornare l'arraylist di familiari
	*
	* @return 	memberSpaces	ArrayList familiari
	*/
	public ArrayList<FamilyMember> getFamilyList(){
		return memberSpaces;
	}
	
	/**
	* Metodo per ritornare l'arraylist di personaggi
	*
	* @return 	characters	ArrayList personaggi
	*/
	public ArrayList<Character> getCharacters(){
		for(int i=4; i<8; i++){
			characters.add((Character)deck.get(i));
		}
		return characters;
	}
	
	/**
	* Metodo per ritornare l'arraylist di edifici
	*
	* @return 	buildings	ArrayList edifici
	*/
	public ArrayList<Building> getBuildings(){
		for(int i=8; i<12; i++){
			buildings.add((Building)deck.get(i));
		}
		return buildings;
	}
	
	/**
	* Metodo per ritornare l'arraylist di carte impresa
	*
	* @return 	territories	ArrayList imprese
	*/
	public ArrayList<Venture> getVentures(){
		for(int i=12; i<16; i++){
			ventures.add((Venture)deck.get(i));
		}
		return ventures;
	}
	
	public void notifyChangement(Object o) {
		try {
		setChanged();
		notifyObservers(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addNewObserver(Observer obs) {
		addObserver(obs);
	}
	
	public void deleteAnObserver(Observer obs) {
		deleteObserver(obs);
	}
}

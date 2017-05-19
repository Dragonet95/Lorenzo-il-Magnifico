package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.Action;
import java.util.Observable;
import java.util.Observer;

/**
* Classe per la gestione di effetti che rimangono attivi nel tempo ed effetti senza una categorizzazione precisa
*
* @author  ps06
* @version 1.0
* @since   2017-05-11
*/
public class EffectsActive extends Effect implements Observer {
	
	private int diceBonus;
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void activate(Player p) {
		
		//TODO add Resources to Player Warehouse ==> Need some setter method
	}
	
	/**
	* Basic Constructor.
	* 
	*/
	public EffectsActive(){
		
	}
	
	/**
	* Constructor.
	* 
	* @param modifier 	 Contatore di modifica del valore di un familiare
	* @param set		 Valore a cui impostare uno specifico dado, prima di modificarlo
	*/
	public EffectsActive(int modifier, int set){

	}
	
	/**
	* Metodo per identificare se l'azione ha necessità di correzioni sul suo valore
	* 
	* @param 	player			Giocatore da verificare
	* @param 	chosenAction	Codice dell'azione da eseguire	
	* @return 					Nothing
	*/
	public void checkAction(Player player, Action chosenAction){
	
	}
	
	/**
	* Metodo per modificare il valore di un azione a seconda dei vari effetti attivi al momento
	* 
	* @param 	player			Giocatore interessato
	* @param 	value			Valore dell'azione eseguita
	* @return 					Nothing 
	*/
	public void setValue(Player player, int value){
		
		
	}
	
	/**
	* Metodo per controllare l'effetto di un leader che permette il posizionamento di un familiari indipendentemente dal fatto che ce ne sia già 
	* un altro posizionato
	* 
	* @param 	Unused
	* @return 	boolean 
	*/
	public boolean handleLeaderMulti(boolean multi){
		this.multi=multi;
		return multi;
	}

	
	/**
	* Metodo per la gestione dell'effetto Leader che permette la copia dell'effetto di un secondo Leader (Lorenzo de' Medici)
	*
	* @param 	Unused
	* @return 	Nothing 
	*/
	public void handleLeaderCopy(){

	}
	
	public boolean getMulti(){
		return multi;
	}
}

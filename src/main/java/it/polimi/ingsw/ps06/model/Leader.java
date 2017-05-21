package it.polimi.ingsw.ps06.model;
import it.polimi.ingsw.ps06.model.Effect;

/**
 * classe per la definizione e gestione delle carte leader
 * 
 * @author ps06
 * @version 1.1
 * @since 13-05-2017
 */

public class Leader extends Card {
		
	//private Requirement leaderInGame;
	private Effect effect;
	private boolean used=false;
	private boolean active=false;
	
	/**
	* costruttore delle carte leader
	* 
	* @param 	none
	* 
	* @return 	Nothing
	*/
	
	public Leader(){
		//da implementare il costruttore
	}
	
	 
	/**
	* metodo per verificare se il leader è stato già utilizzato o meno in questo turno
	* 
	* @param 	none
	* 
	* @return	used	valore boolean che mi dice se è stata utilizzata o no
	*/
	
	public Boolean checkUsed(){
		return used;
	}
	
	/**
	* metodo per resettare la carta leader e renderla disponibile all'utilizzo all'inizio di un nuovo turno
	* 
	* @param 	none
	* 
	* @return 	Nothing
	*/
	
	 public void resetUsed(){
		 if(used)
			 used=false;
	 }
	 
	/**
	* metodo per attivare la carta leader
	* 
	* @param	giocatore che utilizza la carta 
	* 
	* @return 	Nothing
	*/
	
	public void activateLeader(){
		//verifica i requistiti da definire
		active=true;
	}
	
	/**
	* metodo per verificare se il leader è stato già utilizzato o meno in questo turno
	* 
	* @param 	none
	* 
	* @return	used	valore boolean che mi dice se è stata utilizzata o no
	*/
	
	public Boolean checkActive(){
		return active;
	}

	/**
	* metodo per attivare l'effetto della carta leader
	* 
	* @param 	player	giocatore che utilizza la carta 
	* 
	* @return 	Nothing
	*/
	
	public void activateEffect(Player player){
		effect.activate(player);
		return;
	}
	
	
}

package it.polimi.ingsw.ps06.model;

import java.util.Observable;

/**
* Classe per la gestione delle azioni
*
* @author  ps06
* @version 1.0
* @since   2017-05-16
*/
public abstract class Actions extends Observable {
	
	protected int servants;
	
	public Actions(int servants) {
		this.servants = servants;
	}

	/**
	* Metodo di attivazione dell'azione
	*
	* @return 	
	*/
	public abstract void activate();
}

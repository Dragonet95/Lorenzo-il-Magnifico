package it.polimi.ingsw.ps06.model;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Classe per la gestione di effetti che comportano una modifica alle risorse di un player
* in relazione al numero di Carte di una tipo accumulati.
*
* @author  ps06
* @version 1.0
* @since   2017-05-25
*/
public class EffectsResourcesByCard extends EffectsResources {

	private ColorPalette cardColor;
	
	public EffectsResourcesByCard(Resources bonus, ColorPalette cardColor) {
		super(bonus);
		this.cardColor = cardColor;
	}
	
	/**
	* Implementazione del metodo astratto activate().
	* Dato un Player su cui attivare l'effetto, il metodo quanitifica il numero di carte di un certo tipo
	* accumulata per il quale poi verrà consegnato un corrispettivo bonus ripetuto. 
	* 
	* @param	p	Player sul quale attivare l'effetto
	* 
	*/
	@Override
	public void activate(Player p) {
		
		
		/*
		 *  QUALCOSA DI SIMILE, ADATTO ALLE CARTE
		 * 
		int pointsAmountOwned = p.getPersonalBoard().getPointsCount(pointsKind);
		
		for (int i=0; i < pointsAmountOwned; i++) {
			p.getPersonalBoard().increaseResources(bonus);
		}
		*/
	}

}

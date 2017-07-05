package it.polimi.ingsw.ps06.model.effects;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.Resources;
import it.polimi.ingsw.ps06.model.Types;
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

	/**
	 * Costruttore della classe Effetto per le Risorse by Card
	 * 
	 * @param	bonus		risorsa bonus che l'effetto procura al Player quando attivato
	 * @param	cardColor	tipo di carta da quantificare per assegnare il bonus
	 */
	public EffectsResourcesByCard(Resources bonus, ColorPalette cardColor) {
		super(bonus);
		this.cardColor = cardColor;
	}

	/**
	 * <p>Implementazione del metodo astratto activate()</p>
	 * <p>Dato un Player su cui attivare l'effetto, il metodo quanitifica il numero di carte di un certo tipo
	 * accumulata per il quale poi verrà consegnato un corrispettivo bonus ripetuto.</p>
	 * 
	 * @param	p	Player sul quale attivare l'effetto
	 */
	@Override
	public void activate(Player p) {

		int cardsAmountOwned = 0; 
		switch (cardColor) {
		case CARD_GREEN:
			cardsAmountOwned = p.getPersonalBoard().getTerritories().size();
			break;
		case CARD_YELLOW:
			cardsAmountOwned = p.getPersonalBoard().getBuildings().size();
			break;
		case CARD_BLUE:
			cardsAmountOwned = p.getPersonalBoard().getCharacters().size();
			break;
		case CARD_PURPLE:
			cardsAmountOwned = p.getPersonalBoard().getVentures().size();
			break;
		default : cardsAmountOwned = 0;
		}

		for (int i=0; i < cardsAmountOwned; i++) {
			p.getPersonalBoard().addResource(bonus);
		}
	}

}

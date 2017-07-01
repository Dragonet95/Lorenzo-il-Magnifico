package it.polimi.ingsw.ps06.model.cards;

import java.util.ArrayList;
import java.util.Observable;

import it.polimi.ingsw.ps06.model.Player;
import it.polimi.ingsw.ps06.model.effects.Effect;
/**
 * classe astratta per definire le carte
 * 
 * @author ps06
 * @version 1.1
 * @since 13-05-2017
 */

	public abstract class Card extends Observable {
	
		protected int code;
		protected String title;
		protected ArrayList<Effect> effect;
		
		public Card() {
			effect = new ArrayList<Effect>();
		}
	
		/**
		 * Metodo per settare il codice della carta
		 * 
		 * @param	code	codice da inserire
		 * @return	nothing
		 */
		public void setCode(int code){
			this.code=code;
			return;
		}
	  
		/**Metodo per settare il nome della carta
		 * 
		 * @param	titolo	nome della carta
		 * @return	nothing
		 */
		public void setTitle(String title){
			this.title=title;
			return;
		}
		
		/**Metodo per attivare l'effetto della carta
		 * 
		 * @param	player	giocatore su cui attivare l'effetto
		 * @return	nothing
		 */
		public void activateEffect(Player player){
			for(Effect i : effect){
			i.activate(player);
			}
			return;
		}
		
		/**Metodo che restituisce il nome della carta
		 * 
		 * @param	none
		 * @return	title	nome della carta
		 */
		public String getTitle(){
			return title;
		}
		
		/**Metodo per settare il periodo della carta
		 * 
		 * @param	arrayList	effetto da aggiungere
		 * @return	nothing
		 */
		public void setPermEffect(ArrayList<Effect> arrayList) {
			this.effect.addAll(arrayList);
		}
		
		/**Metodo che restituisce il codice della carta
		 * 
		 * @param	none
		 * @return	code	codice della carta
		 */
		public int getCode(){
			return code;
		}
}

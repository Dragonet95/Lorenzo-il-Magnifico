package it.polimi.ingsw.ps06.model;

import static it.polimi.ingsw.ps06.model.Types.ColorPalette;

/**
* Class representing the four Family Member each player has (three dice colored and one neutral)
*
* @author  ps06
* @version 1.0
* @since   2017-05-10 
*/
public class FamilyMember {
	
	private ColorPalette rappresentativeDiceColor;
	private int value;
	
	private Player ownerPlayer;
	
	/**
	* This is the default constructor of the class. The Family Member instanced is by default neutral
	* @return 	Nothing.
	*/
	public FamilyMember(Player p)
	{
		this.ownerPlayer = p;
		this.rappresentativeDiceColor = ColorPalette.UNCOLORED;
		this.value = 0;
	}
	
	/**
	   * This is the constructor of the class
	   * @param 	rappresentativeDice 	During the instance of a Family Member it has to be associated to a particular dice.
	   * 									Values accepted comes from the ColorPalette interface.
	   * @return 	Nothing.
	   */
	public FamilyMember(Player p, ColorPalette rappresentativeDice)
	{
		this(p);
		this.rappresentativeDiceColor = rappresentativeDice;
	}
	
	/**
	   * Setter for the value attribute
	   * @param 	value		New value associated
	   * @return 	Nothing.
	   */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	   * Getter for the value attribute
	   * @param 	Nothing.
	   * @return 	Current value associated
	   */
	public int getValue() {
		return value;
	}
	
	/**
	   * Setter for the Dice Color attribute
	   * @param 	rappresentativeDiceColor	New color associated
	   * @return 	Nothing.
	   */
	public void setColor(ColorPalette rappresentativeDiceColor) {
		this.rappresentativeDiceColor = rappresentativeDiceColor;
	}
	
	/**
	   * Getter for the Dice Color attribute
	   * @param 	Nothing.
	   * @return 	Current color associated
	   */
	public ColorPalette getColor() {
		return rappresentativeDiceColor;
	}
	
	public Player getPlayer() {
		return ownerPlayer;
	}
}

package entity.fieldclasses;
import java.awt.Color;

import entity.Player;

/**
 * Ownable er en underklasse til Fieldklassen,
 * som er superklasse til alle felter der kan ejes
 * Ownable indeholder således alle de metoder, den arver fra Field
 * og de metoder som kun er generelle,
 * for de felter der kan ejes.
 */
public abstract class Ownable extends Field {
	protected int price;
	Player owner;

	/** Constructor til Ownable felter:
	 * @param fieldNumber
	 * @param color
	 * @param price
	 */
	public Ownable(int fieldNumber, Color color, int price) {
		super(fieldNumber, color);

		this.price = price;
		this.owner = null;
	}

	/** Bruges til at få ejer af felt elementet.
	 * @return Felt ejer
	 */
	public Player getOwner() {
		return owner;
	}
	/** Bruges til at sætte ejeren af et felt.
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/** Bruges af spiller til at købe et felt element. 
	 * @param player
	 */
	public void buyField(Player player) {
		player.Transaction(-price);
		setOwner(player);
	
	}

}

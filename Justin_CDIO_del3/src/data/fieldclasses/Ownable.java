package data.fieldclasses;
import java.awt.Color;

import data.PlayerDTO;

/**
 * Ownable er en underklasse til Fieldklassen,
 * som er superklasse til alle felter der kan ejes
 * Ownable indeholder således alle de metoder, den arver fra Field
 * og de metoder som kun er generelle,
 * for de felter der kan ejes.
 */
public abstract class Ownable extends FieldDTO {
	protected int price;
	PlayerDTO owner;

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
	public PlayerDTO getOwner() {
		return owner;
	}
	/** Bruges til at sætte ejeren af et felt.
	 * @param owner
	 */
	public void setOwner(PlayerDTO owner) {
		this.owner = owner;
	}
	
	/** Bruges af spiller til at købe et felt element. 
	 * @param player
	 */
	public void buyField(PlayerDTO player) {
		player.Transaction(-price);
		setOwner(player);
	
	}

}

package entity.fieldclasses;
import java.awt.Color;

import entity.GameBoardDTO;
import entity.PlayerDTO;

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
	protected int NumberOwned = 0;
	/** Constructor til Ownable felter:
	 * @param fieldNumber
	 * @param color
	 * @param price
	 */
	public Ownable(int fieldNumber, String name, Color color, int price) {
		super(fieldNumber, name, color);

		this.price = price;
		this.owner = null;
	}
	public abstract int landOnField(PlayerDTO player, GameBoardDTO gb);	
	public int getNumberOwned (PlayerDTO p, GameBoardDTO gb) {
		return 0;
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

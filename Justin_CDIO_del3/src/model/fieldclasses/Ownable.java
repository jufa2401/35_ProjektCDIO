/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * Ownable er en underklasse til Fieldklassen, som er superklasse til alle
 * felter der kan ejes Ownable indeholder således alle de metoder, den arver fra
 * Field og de metoder som kun er generelle, for de felter der kan ejes.
 */
public abstract class Ownable extends FieldDTO {
	
	/** The price. */
	protected int price;
	
	/** The owner. */
	PlayerDTO owner;
	
	/** The Number owned. */
	protected int NumberOwned = 0;

	/**
	 * Constructor til Ownable felter:.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 * @param price the price
	 */
	public Ownable(int fieldNumber, String name, Color color, int price) {
		super(fieldNumber, name, color);

		this.price = price;
		this.owner = null;
	}

	/**
	 * Bruges af spiller til at købe et felt element.
	 *
	 * @param player the player
	 */
	public void buyField(PlayerDTO player) {
		player.Transaction(-price);
		setOwner(player);

	}

	/**
	 * Gets the number owned.
	 *
	 * @param p the p
	 * @param gb the gb
	 * @return the number owned
	 */
	public int getNumberOwned(PlayerDTO p, GameBoardDTO gb) {
		return 0;
	}

	/**
	 * Bruges til at få ejer af felt elementet.
	 * 
	 * @return Felt ejer
	 */
	public PlayerDTO getOwner() {
		return owner;
	}
	/**
	 * Bruges til at sætte ejeren af et felt.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(PlayerDTO owner) {
		this.owner = owner;
	}

}

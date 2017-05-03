/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * Denne klase indeholder egenskaber og metoder som vurderes generelle for alle
 * felter og er derfor abstract,.
 *
 * @author Justin
 */
public abstract class FieldDTO {
	
	/** The id. */
	private final int ID;
	
	/** The COLOR. */
	private final Color COLOR;
	
	/** The NAME. */
	private final String NAME;

	/**
	 * Constructor som laver et felt.
	 *
	 * @param fieldNumber the field number
	 * @param NAME the NAME
	 * @param COLOR the COLOR
	 */

	public FieldDTO(int fieldNumber, String NAME, Color COLOR) {
		ID = fieldNumber;
		this.COLOR = COLOR;
		this.NAME = NAME;
	}

	/**
	 * Gets the COLOR.
	 *
	 * @return the COLOR
	 */
	public Color getColor() {
		return COLOR;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the NAME.
	 *
	 * @return the NAME
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Defineres til at hente rent og price fra Fieldklassen. Det bliver smart
	 * når vi skal lave referencer i vores GUI-array Alle underklasserne har
	 * denne metode med, selvom de ikke bruger dem De vil returnere med 0, hvis
	 * de ikke skal bruge dem.
	 *
	 * @return the rent
	 */

	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Returnerer 0, så man kan skelne de øvrige felter fra dét ene taxfelt der
	 * har en taxRate, dette bruges til at bestemme hvornår spilleren skal
	 * spørges om hvorvidt de vil betale den eller den anden type skat.
	 *
	 * @return the tax amount
	 */
	public int getTaxAmount() {
		return 0;
	}

	/**
	 * Gets the tax rate.
	 *
	 * @return the tax rate
	 */
	public int getTaxRate() {
		return 0;
	}

	/**
	 * Abstract metode, som nedarves til underklasser, som hver især skal
	 * returnere en unik id.
	 *
	 * @return the type
	 */
	public abstract int getType();

	/**
	 * LandOnField til felter som kun har player som parameter.
	 *
	 * @param player the player
	 * @return the int
	 */
	public int landOnField(PlayerDTO player) {
		return 0;
	}

	/**
	 * Land on field.
	 *
	 * @param player the player
	 * @param gb the gb
	 * @return the int
	 */
	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * LandOnField Specifik til tax.
	 *
	 * @param player the player
	 * @param taxRate the tax rate
	 * @return the int
	 */
	public int landOnField(PlayerDTO player, int taxRate) {
		return 0;
	}

}

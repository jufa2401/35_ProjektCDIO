/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * Man kan ikke eje tax og dens superklasse er derfor Field. Når man lander på
 * dette felt, skal man betale en pris. Prisen er den mindste af enten den faste
 * betalingssum, eller skatteprocenten.
 */
public class TaxDTO extends FieldDTO {
	
	/** The tax rate. */
	private final int TAXAMOUNT, TAXRATE;

	/**
	 * Instantiates a new tax DTO.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 * @param TAXAMOUNT the tax amount
	 * @param TAXRATE the tax rate
	 */
	public TaxDTO(int fieldNumber, String name, Color color, int taxAmount, int taxRate) {
		super(fieldNumber, name, color);
		this.TAXAMOUNT = taxAmount;
		this.TAXRATE = taxRate;
	}

	/**
	 * ikke relevant idet feltet ikke er ownable.
	 *
	 * @return the price
	 */
	@Override
	public int getPrice() {
		return 0;
	}

	/**
	 * ikke relevant idet feltet ikke er ownable.
	 *
	 * @return the rent
	 */
	@Override
	public int getRent() {
		return 0;
	}

	/**
	 * Metoden implementeres her, da det er i dette felt de skal bruges.
	 *
	 * @return the tax amount
	 */
	@Override
	public int getTaxAmount() {
		return TAXAMOUNT;
	}

	/**
	 * Metoden implementeres her, da det er i dette felt de skal bruges.
	 *
	 * @return the tax rate
	 */
	@Override
	public int getTaxRate() {
		return TAXRATE;
	}

	/**
	 * Returnerer unik id, som identificerer denne klasse som tax.
	 *
	 * @return the type
	 */
	@Override
	public int getType() {
		return 4; // Tax
	}

	/**
	 * Hvis man lander på tax skal man i denne metode betale et fast beløb.
	 *
	 * @param player the player
	 * @return the int
	 */
	@Override
	public int landOnField(PlayerDTO player) {
		final int payment = TAXAMOUNT;
		player.Transaction(-payment);
		return payment;
	}

	// Overload metode var den eneste måde jeg kunne finde på at lave det her,
	// uden at bryde GRASP principper.
	/**
	 * Alternativ udgave af landOnField, der anvendes når skatten skal beregnes
	 * som en procentsats af saldobalancen.
	 *
	 * @param player the player
	 * @param rate the rate
	 * @return the int
	 */
	@Override
	public int landOnField(PlayerDTO player, int rate) {
		final int balance = player.getBalance();
		// Vi beregner assets som resterende penge på kontoen, det kunne have
		// inkluderet værdien af ejendomme
		// Dette kan evt. implementeres senere
		final int payment = rate * balance / 100;
		player.Transaction(-payment);
		return payment;
	}

}

package entity.fieldclasses;
import java.awt.Color;

import entity.PlayerDTO;

/**
 * Man kan ikke eje tax og dens superklasse er derfor Field.	
 * Når man lander på dette felt,
 * skal man betale en pris.
 * Prisen er den mindste af enten
 * den faste betalingssum, eller skatteprocenten.
 */
public class TaxDTO extends FieldDTO{
	private int taxAmount, taxRate;	
	public TaxDTO(int fieldNumber,String name, Color color, int taxAmount, int taxRate) {
		super(fieldNumber, name, color);
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
	}
	/**
	 *  Hvis man lander på tax skal man i denne metode
	 *  betale et fast beløb
	 */
	@Override
	public int landOnField(PlayerDTO player) {
		int payment = this.taxAmount;
		player.Transaction(-payment);
		return payment;
	}
// Overload metode var den eneste måde jeg kunne finde på at lave det her, uden at bryde GRASP principper.
	/**
	 *  Alternativ udgave af landOnField, der anvendes når skatten skal beregnes 
	 *  som en procentsats af saldobalancen
	 */
	@Override
	public int landOnField(PlayerDTO player, int rate) {
		int balance = player.getBalance();
		//			Vi beregner assets som resterende penge på kontoen, det kunne have inkluderet værdien af ejendomme
		//			Dette kan evt. implementeres senere
		int payment = rate*balance/100;
		player.Transaction(-payment);
		return payment;
	}

	/**
	 *  ikke relevant idet feltet ikke er ownable
	 */
	@Override
	public int getRent() {
		return 0;
	}
	/**
	 *  ikke relevant idet feltet ikke er ownable
	 */
	@Override
	public int getPrice() {
		return 0;
	}

	/**
	 * Returnerer unik id,  som identificerer denne klasse som tax
	 */
	@Override
	public int getType() {
		return 4;	// Tax
	}
	
	/**
	 *  Metoden implementeres her, da det er i dette felt de skal bruges.
	 */
	@Override
	public int getTaxAmount() {
		return taxAmount;
	}

	/**
	 *  Metoden implementeres her, da det er i dette felt de skal bruges.
	 */
	@Override
	public int getTaxRate() {
		return taxRate;
	}

}

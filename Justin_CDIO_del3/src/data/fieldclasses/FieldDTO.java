package data.fieldclasses;
import java.awt.Color;

import data.PlayerDTO;

public abstract class FieldDTO {
	private int ID;
	private Color color;
	/**
	 * Constructor som laver et felt.
	 * @param fieldNumber
	 * @param color
	 */
	public FieldDTO(int fieldNumber, Color color) {
		this.ID = fieldNumber;
		this.color = color;
	}

	/**
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Defineres til at hente rent og price fra Fieldklassen. 
	 * Det bliver smart når vi skal lave referencer i vores GUI-array
	 * Alle underklasserne har denne metode med, selvom de ikke bruger dem
	 * De vil returnere med 0, hvis de ikke skal bruge dem.
	 */
	
	/**
	 * Abstract metode, som nedarves til underklasser,
	 * som hver især skal returnere en unik id
	 */
	public abstract int getType();


	/**
	 * @param player
	 * @return
	 */
	public abstract int landOnField(PlayerDTO player);

	/**
	 * @param player
	 * @param taxRate
	 * @return
	 */
	public int landOnField(PlayerDTO player, int taxRate) {
		return 0;
	}
	
	/**
	 * Returnerer 0, så man kan skelne  de øvrige felter fra 
	 * dét ene taxfelt der har en taxRate, dette bruges til 
	 * at bestemme hvornår spilleren skal spørges om hvorvidt
	 * de vil betale den eller den anden type skat
	 * 
	 * @return
	 */
	public int getTaxAmount() {
		return 0;
	}
	
	public int getTaxRate() {
		return 0;
	}

	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}





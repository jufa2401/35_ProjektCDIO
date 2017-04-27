/*
 * Dette er en klasse som skal beskrive felter hvor man lander og der ikke sker noget,
 * og de felter hvor handlingen på feltet er en simpel overførsel af penge.
 * Dvs. klassen dækker over: Besøg fængsel, start, og parkering
 */

package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class StartDTO.
 */
public class StartDTO extends FieldDTO {
	
	/** The bonus. */
	private int bonus;

	/**
	 * Constructor til StartDTO.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 * @param bonus the bonus
	 */
	public StartDTO(int fieldNumber, String name, Color color, int bonus) {
		super(fieldNumber, name, color);
		this.bonus = bonus;
	}

	/**
	 * Gets the bonus.
	 *
	 * @return the bonus
	 */
	public int getBonus() {
		return bonus;
	}

	// /**
	// * ikke relevant idet feltet ikke er ownable
	// */
	// @Override
	// public int getRent() {
	// return bonus;
	// }

	// /**
	// * ikke relevant idet feltet ikke er ownable
	// */
	// @Override
	// public int getPrice() {
	// return 0;
	// }

	/**
	 * Returnerer unik id, som identificerer denne klasse som Refuge.
	 *
	 * @return the type
	 */
	@Override
	public int getType() {
		return 3;
	}

	/**
	 * Hvis man lander på en Refuge får man udbetalt en bonus.
	 *
	 * @param player the player
	 * @return the int
	 */
	@Override
	public int landOnField(PlayerDTO player) {
		// player.Transaction(this.bonus);
		// betalte beløb er positivt, derfor er det udbetalte beløb negativt
		// return -this.bonus;
		return 0;
	}

}

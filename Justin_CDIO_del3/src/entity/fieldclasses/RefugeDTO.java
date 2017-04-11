/*
 * Dette er en klasse som skal beskrive felter hvor man lander og der ikke sker noget,
 * og de felter hvor handlingen på feltet er en simpel overførsel af penge.
 * Dvs. klassen dækker over: Besøg fængsel, start, og parkering
 */

package entity.fieldclasses;
import java.awt.Color;

import entity.PlayerDTO;
//TODO: Meddelelse om modtagelse af penge LAVE BONUS OM
public class RefugeDTO extends FieldDTO {
private int bonus;

	/** Constructor til Misc felter.
	 * @param fieldNumber
	 * @param color
	 */
	public RefugeDTO(int fieldNumber, String name, Color color, int bonus) {
		super(fieldNumber, name,  color);
		this.bonus = bonus;
	}

	/**
	 *  Hvis man lander på en Refuge
	 *  får man udbetalt en bonus
	 */
	@Override
	public int landOnField(PlayerDTO player) {
	//	player.Transaction(this.bonus);
//	betalte beløb er positivt, derfor er det udbetalte beløb negativt
//		return -this.bonus;
		return 0;
	}

//	/**
//	 *  ikke relevant idet feltet ikke er ownable
//	 */
//	@Override
//	public int getRent() {
//		return bonus;
//	}

//	/**
//	 *  ikke relevant idet feltet ikke er ownable
//	 */
//	@Override
//	public int getPrice() {
//		return 0;
//	}
	
	
	public int getBonus() {
		return bonus;
	}

	/**
	 * Returnerer unik id, 
	 * som identificerer denne klasse som Refuge
	 */
	@Override
	public int getType() {
		return 3;	// Refuge
	}
	
}



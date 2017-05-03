/*
 * 
 */
package controller.fieldcontrollers;

import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.language.LanguageHandler;
import view.GUIHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class TaxController.
 */
public class TaxController {
	
	/**
	 * Tax rules.
	 *
	 * @param GUIh the GU ih
	 * @param language the language
	 * @param fieldNumber the field number
	 * @param field the field
	 * @param player the player
	 */
	public static void TaxRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field,
			PlayerDTO player) {
		final int TAXAMOUNT = field.getTaxAmount();
		final int TAXRATE = field.getTaxRate();
		int paid;
		if (TAXAMOUNT > 0 && TAXRATE > 0) {
			// Tax-felt med valg af beløb håndteres specielt, spilleren spørges
			// Teksten på knapperne er ikke tekst, men tal og behøver derfor
			// ikke oversættes
			// Teksten på knapperne er ikke tekst, men tal og behøver derfor
			// ikke oversættes
			if (GUIh.getYesNo(language.askPayTax(), TAXAMOUNT + "", TAXRATE + "%")) {
				// Fast beløb valgt
				paid = field.landOnField(player);
				GUIh.getButtonPressed(language.playerTax(player.getName(), paid), language.Ok());
			} else {
				// Rate valgt, beløb afhængig af saldobalance
				paid = field.landOnField(player, TAXRATE);
				GUIh.getButtonPressed(language.playerTax(player.getName(), paid), language.Ok());
			}

		} else {
			// Normal fast Tax / bonus, betales uden der spørges
			paid = field.landOnField(player);
			if (paid > 0) {
				GUIh.getButtonPressed(language.playerTax(player.getName(), paid), language.Ok());
			} else {
				GUIh.getButtonPressed(language.playerBonus(player.getName(), -paid), language.Ok());
			}
		}
	}

}

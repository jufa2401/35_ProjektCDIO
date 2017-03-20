package controller;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import controller.fieldcontrollers.StreetController;
import entity.Player;
import entity.fieldclasses.Field;

public class GameLogic{

	public static void FieldRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, Field field, Player player) {
		if (field.getPrice() > 0) {
			StreetController.StreetRules(GUIh, language, fieldNumber, field, player);
		} else if (field.getType() == 4) {
			//			hvis feltet ikke kan ejes
			int taxAmount = field.getTaxAmount();
			int taxRate   = field.getTaxRate();
			int paid;
			// Tax-felt med valg af beløb håndteres specielt, spilleren spørges
			if (taxAmount > 0 && taxRate > 0) {
				//			Teksten på knapperne er ikke tekst, men tal og behøver derfor ikke oversættes
				if (GUIh.getYesNo(language.askPayTax(), taxAmount+"", taxRate+"%")) {
					// Fast beløb valgt
					paid = field.landOnField(player);
				} else {
					// Rate valgt, beløb afhængig af saldobalance
					paid = field.landOnField(player, taxRate);
				}
			} else {
				// Normal fast Tax / bonus, betales uden der spørges
				paid = field.landOnField(player);
			}
			// Der gives forskellig besked afhængig af om der betales skat, eller udbetales bonus
			if (paid > 0) {
				GUIh.getButtonPressed(language.playerTax(player.getName(), paid), language.Ok());
			} else {
				GUIh.getButtonPressed(language.playerBonus(player.getName(), -paid), language.Ok());
			}
		}
		// GUI opdateres med den nye balance for spilleren
		GUIh.setBalance(player.getName(), player.getBalance());

	}

}


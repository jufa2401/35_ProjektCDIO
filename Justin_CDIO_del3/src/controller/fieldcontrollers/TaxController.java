package controller.fieldcontrollers;


import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;

public class TaxController {
	public static void TaxRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player) 
	{
		//			hvis feltet ikke kan ejes
		int taxAmount = field.getTaxAmount();
		int taxRate   = field.getTaxRate();
		int paid;
		if (taxAmount > 0 && taxRate > 0) {
			// Tax-felt med valg af beløb håndteres specielt, spilleren spørges
			//			Teksten på knapperne er ikke tekst, men tal og behøver derfor ikke oversættes
			//		Teksten på knapperne er ikke tekst, men tal og behøver derfor ikke oversættes
			if (GUIh.getYesNo(language.askPayTax(), taxAmount+"", taxRate+"%")) {
				// Fast beløb valgt
				paid = field.landOnField(player);
				GUIh.getButtonPressed(language.playerTax(player.getName(), paid), language.Ok());
			} else {
				// Rate valgt, beløb afhængig af saldobalance
				paid = field.landOnField(player, taxRate);
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






package controller;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.Player;
import entity.fieldclasses.Field;
import entity.fieldclasses.Ownable;

public class GameLogic{

	public static void FieldRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, Field field, Player player) {
		if (field.getPrice() > 0) {
			Ownable ofield = (Ownable) field;
			if (ofield.getPrice() > 0) {
				//			hvis feltet kan ejes

				if (ofield.getOwner() != null) {
					// Hvis der er en ejer af feltet
					int paid = ofield.landOnField(player);

					// Giv besked om betalt leje
					GUIh.getButtonPressed(language.playerPayTo(player.getName(), ofield.getOwner().getName(), paid), language.Ok());
					GUIh.setBalance(ofield.getOwner().getName(), ofield.getOwner().getBalance());
				} else {
					// Feltet ejes ikke af nogen, hvis spilleren har penge nok spørges der om feltet skal købes
					if (player.getBalance() > ofield.getPrice()) {
						if (GUIh.getYesNo(language.askBuyField(), language.yes(), language.no())) {
							ofield.buyField(player);
							GUIh.setOwner(fieldNumber, player.getName());
						}
					}
				}
			} else {
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
}
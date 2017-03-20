package controller.fieldcontrollers;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.Player;
import entity.fieldclasses.Field;
import entity.fieldclasses.Ownable;

public class BreweryController {
	public static void BreweryRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, Field field, Player player) {
		if (field.getType() == 2) {
			//			hvis feltet kan ejes
			Ownable ofield = (Ownable) field; 
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
		}

	}
}

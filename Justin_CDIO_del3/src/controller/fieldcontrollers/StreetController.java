package controller.fieldcontrollers;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;

public class StreetController {
	public static void StreetRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player) {
//		if (field.getPrice() > 0) {
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


package controller.fieldcontrollers;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;

public class ShippingCompanyController {
	public static void ShippingCompanyRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player, GameBoardDTO gb) {
		if (field.getType() == 1) {
			//			hvis feltet kan ejes
			Ownable ofield = (Ownable) field; 
			if (ofield.getOwner() != null) {
				// Hvis der er en ejer af feltet
				int paid = ofield.landOnField(player,gb);

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

package controller.fieldcontrollers;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.BreweryDTO;
import entity.fieldclasses.FieldDTO;

public class BreweryController {
	public static void StreetRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player, GameBoardDTO gb) {
//		Ownable ofield = (Ownable) field; 
		BreweryDTO bfield = (BreweryDTO) field; //?? noget skal sek
			if (bfield.getOwner() != null) {
				// Hvis der er en ejer af feltet
				int paid = bfield.landOnField(player);

				// Giv besked om betalt leje
				GUIh.getButtonPressed(language.playerPayTo(player.getName(), bfield.getOwner().getName(), paid), language.Ok());
				GUIh.setBalance(bfield.getOwner().getName(), bfield.getOwner().getBalance());
			} else {
				// Feltet ejes ikke af nogen, hvis spilleren har penge nok spørges der om feltet skal købes
				if (player.getBalance() > bfield.getPrice()) {
					if (GUIh.getYesNo(language.askBuyField(), language.yes(), language.no())) {
						bfield.buyField(player);
						GUIh.setOwner(fieldNumber, player.getName());
					}
				}
			}
		}

	}


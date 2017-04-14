package controller.fieldcontrollers;
import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;
import entity.fieldclasses.StreetDTO;

public class StreetController {
	public static void StreetRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player) {
//		Ownable ofield = (Ownable) field; 
		StreetDTO sfield = (StreetDTO) field; //?? noget skal sek
			if (sfield.getOwner() != null) {
				// Hvis der er en ejer af feltet
				int paid = sfield.landOnField(player);

				// Giv besked om betalt leje
				GUIh.getButtonPressed(language.playerPayTo(player.getName(), sfield.getOwner().getName(), paid), language.Ok());
				GUIh.setBalance(sfield.getOwner().getName(), sfield.getOwner().getBalance());
			} else {
				// Feltet ejes ikke af nogen, hvis spilleren har penge nok spørges der om feltet skal købes
				if (player.getBalance() > sfield.getPrice()) {
					if (GUIh.getYesNo(language.askBuyField(), language.yes(), language.no())) {
						sfield.buyField(player);
						GUIh.setOwner(fieldNumber, player.getName());
					}
				}
			}
		}

	}


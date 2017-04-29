/*
 * 
 */
package controller.fieldcontrollers;

import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.StreetDTO;
import model.language.LanguageHandler;
import view.GUIHandler;
import view.Sound;

// TODO: Auto-generated Javadoc
/**
 * The Class StreetController.
 */
public class StreetController {
	
	/**
	 * Buy house.
	 *
	 * @param GUIh the GU ih
	 * @param language the language
	 * @param player the player
	 * @param gb the gb
	 */
	public static void BuyHouse(GUIHandler GUIh, LanguageHandler language, PlayerDTO player, GameBoardDTO gb) {
		while (!GUIh.getYesNo(language.askBuyHouse(), language.move(), language.buy())) {
			int streetnumber = GUIh.buyHouse(gb, language.promptGroup(), player);
			if (streetnumber > 0) {
				GUIh.setBalance(player.getName(), player.getBalance());
				StreetDTO sfield = (StreetDTO) gb.getField(streetnumber);
				GUIh.setHouses(streetnumber, sfield.getHouses());
			} else {
				GUIh.getButtonPressed(language.failedHousePurchase(), language.Ok());

			}

		}
	}

	/**
	 * Street rules.
	 *
	 * @param GUIh the GU ih
	 * @param language the language
	 * @param fieldNumber the field number
	 * @param field the field
	 * @param player the player
	 * @param gb the gb
	 */
	public static void StreetRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field,
			PlayerDTO player, GameBoardDTO gb) {
		Sound Sound = new Sound();
		// Ownable ofield = (Ownable) field;
		StreetDTO sfield = (StreetDTO) field; // ?? noget skal sek
		if (sfield.getOwner() != null) {
			// Hvis der er en ejer af feltet
			int paid = sfield.landOnField(player);
			// Giv besked om betalt leje
			GUIh.getButtonPressed(language.playerPayTo(player.getName(), sfield.getOwner().getName(), paid),
					language.Ok());
			GUIh.setBalance(sfield.getOwner().getName(), sfield.getOwner().getBalance());
		} else {
			// Feltet ejes ikke af nogen, hvis spilleren har penge nok spørges
			// der om feltet skal købes
			if (player.getBalance() > sfield.getPrice()) {
				if (GUIh.getYesNo(language.askBuyField(), language.yes(), language.no())) {
					sfield.buyField(player);
					Sound.playPaySoundThread();
					GUIh.setOwner(fieldNumber, player.getName());
				}
			}
		}
	}

}

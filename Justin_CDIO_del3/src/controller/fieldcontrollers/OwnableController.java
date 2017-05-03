/*
 * 
 */
package controller.fieldcontrollers;

import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.Ownable;
import model.language.LanguageHandler;
import view.GUIHandler;
import view.Sound;

// TODO: Auto-generated Javadoc
/**
 * The Class OwnableController.
 */
public class OwnableController {

	/**
	 * Ownable rules.
	 *
	 * @param GUIh the GU ih
	 * @param language the language
	 * @param fieldNumber the field number
	 * @param field the field
	 * @param player the player
	 * @param gb the gb
	 */
	public static void OwnableRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field,
			PlayerDTO player, GameBoardDTO gb) {
		Sound Sound = new Sound ();
		Ownable ofield = (Ownable) field; 
		if (ofield.getOwner() != null) {
			// Hvis der er en ejer af feltet
			int paid = ofield.landOnField(player, gb);

			// Giv besked om betalt leje
			GUIh.getButtonPressed(language.playerPayTo(player.getName(), ofield.getOwner().getName(), paid),
					language.Ok());
			GUIh.setBalance(ofield.getOwner().getName(), ofield.getOwner().getBalance());
		} else {
			// Feltet ejes ikke af nogen, hvis spilleren har penge nok spørges
			// der om feltet skal købes
			if (player.getBalance() > ofield.getPrice()) {
				if (GUIh.getYesNo(language.askBuyField(), language.yes(), language.no())) {
					ofield.buyField(player);
					Sound.playPaySoundThread();
					GUIh.setOwner(fieldNumber, player.getName());
				}
			}
		}
	}

}

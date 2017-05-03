/*
 * 
 */

package controller.fieldcontrollers.unused;

import model.DiceCup;
import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.JailDTO;
import model.language.LanguageHandler;
import view.GUIHandler;
import view.Sound;

// TODO: Auto-generated Javadoc
/**
 * DEPRECATED: Replaced with Enum
 * Here to show how it could be done differently
 */

public class JailController  {

	/**
	 * Jail rules.
	 *
	 * @param GUIh the GU ih
	 * @param language the language
	 * @param fieldNumber the field number
	 * @param field the field
	 * @param player the player
	 * @param game the game
	 * @param d the d
	 */
	
	public static void JailRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field,
			PlayerDTO player, GameBoardDTO game, DiceCup d) {
		final JailDTO jfield = (JailDTO) field;
		final Sound Sound = new Sound();

		// er du i fængsel?
		if (player.getRoundsLeftJail() > 0) {
			// betal dig ud
			if ((player.getBalance() > 1000) && GUIh.getYesNo(language.askPayJail(), language.yes(), language.no())) {
				player.Transaction(-1000);
				player.setRoundsLeftJail(0);
				GUIh.getButtonPressed(language.playerOutOfJail(player.getName()), language.Ok());
			} else {
				// slå 2 ens for at komme ud
				d.rollDiceCup();
				final int[] dicevalue = d.getDiceValue();
				GUIh.showDice(dicevalue[0], dicevalue[1]);
				if (dicevalue[0] == dicevalue[1]) {
					player.setRoundsLeftJail(0);
					GUIh.getButtonPressed(language.playerOutOfJail(player.getName()), language.Ok());
				}
				// hvis du gør hverken
				else {
					player.setRoundsLeftJail(player.getRoundsLeftJail() - 1);
					GUIh.getButtonPressed(language.jailCountdown(player.getName()), language.Ok());
				}
			}
			// er kun relevant hvis du ikke er i fængsel.
		} else {
			final int currentfield = player.getCurrentField();
			final int newfield = jfield.landOnField(player, game);
			if (currentfield != newfield) {
				Sound.playJailSoundThread();
				GUIh.getButtonPressed(language.playerGoToJail(player.getName()), language.Ok());
			}
		}

	}

}


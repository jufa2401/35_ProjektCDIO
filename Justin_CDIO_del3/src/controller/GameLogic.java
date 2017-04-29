/*
 * 
 */
package controller;

import controller.fieldcontrollers.JailController;
import controller.fieldcontrollers.OwnableController;
import controller.fieldcontrollers.StreetController;
import controller.fieldcontrollers.TaxController;
import model.DiceCup;
import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.language.LanguageHandler;
import view.GUIHandler;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse styrer de "specifikke" spilforløb som den henter fra hver
 * feltcontroller.
 *
 * @author Justin
 */
public class GameLogic {

	/**
	 * Field rules.
	 *
	 * @param GUIh the GU ih
	 * @param language the language
	 * @param game the game
	 * @param fieldNumber the field number
	 * @param field the field
	 * @param player the player
	 * @param dice the dice
	 */
	public static void FieldRules(GUIHandler GUIh, LanguageHandler language, GameBoardDTO game, int fieldNumber,FieldDTO field, PlayerDTO player, DiceCup dice)
	{	
		int type = field.getType();

		switch (type) {
		case 1:
		case 2:
			OwnableController.OwnableRules(GUIh, language, fieldNumber, field, player, game);
			break;
		case 4:
			TaxController.TaxRules(GUIh, language, fieldNumber, field, player);
			break;
		case 5:
			StreetController.StreetRules(GUIh, language, fieldNumber, field, player, game);
			break;
		case 6:
			JailController.JailRules(GUIh, language, fieldNumber, field, player, game, dice);
			break;

		default:

		}
		// GUI opdateres med den nye balance for spilleren
		GUIh.setBalance(player.getName(), player.getBalance());

	}

}

package controller;
import boundary.GUIHandler; 
import boundary.language.LanguageHandler;
import controller.fieldcontrollers.JailController;
import controller.fieldcontrollers.OwnableController;
import controller.fieldcontrollers.StreetController;
import controller.fieldcontrollers.TaxController;
import entity.DiceCup;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;

public class GameLogic{

	public static void FieldRules(GUIHandler GUIh, LanguageHandler language, GameBoardDTO game, int fieldNumber, FieldDTO field, PlayerDTO player, DiceCup dice) {
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
		//		// GUI opdateres med den nye balance for spilleren
		GUIh.setBalance(player.getName(), player.getBalance());

	}

}


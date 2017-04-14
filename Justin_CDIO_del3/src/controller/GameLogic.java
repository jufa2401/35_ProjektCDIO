package controller;
import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import controller.fieldcontrollers.OwnableController;
import controller.fieldcontrollers.StreetController;
import controller.fieldcontrollers.TaxController;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;

public class GameLogic{

	public static void FieldRules(GUIHandler GUIh, LanguageHandler language, GameBoardDTO game, int fieldNumber, FieldDTO field, PlayerDTO player) {
		int type = field.getType();
		
		switch (type) {
		case 1:
			OwnableController.OwnableRules(GUIh, language, fieldNumber, field, player);
			break;
		case 2:
			OwnableController.OwnableRules(GUIh, language, fieldNumber, field, player);
			break;

		case 5: 
			StreetController.StreetRules(GUIh, language, fieldNumber, field, player);
			break;

		case 4:
			TaxController.TaxRules(GUIh, language, fieldNumber, field, player);
			break;
		default:

		}





		//		if (field.getPrice() > 0) {
		//			StreetController.StreetRules(GUIh, language, fieldNumber, field, player);
		//		
		//		} else if (field.getTaxAmount() != 0 || field.getTaxRate() != 0) {
		//				TaxController.TaxRules(GUIh, language, fieldNumber, field, player);
		//			
		//			// Der gives forskellig besked afhængig af om der betales skat, eller udbetales bonus
		//		} else {
		////			Du må ikke havne her
		//		}
		//		// GUI opdateres med den nye balance for spilleren
		GUIh.setBalance(player.getName(), player.getBalance());

	}

}


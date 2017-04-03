package functionality;
//Refuge not working
import functionality.fieldcontrollers.StreetController;
import functionality.fieldcontrollers.TaxController;
import data.PlayerDTO;
import data.fieldclasses.FieldDTO;
import presentation.GUIHandler;
import presentation.language.LanguageHandler;

public class GameLogic{

	public static void FieldRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player) {
		if (field.getPrice() > 0) {
			StreetController.StreetRules(GUIh, language, fieldNumber, field, player);
	
		
		} else if (field.getTaxAmount() != 0 || field.getTaxRate() != 0) {
				TaxController.TaxRules(GUIh, language, fieldNumber, field, player);
			
			// Der gives forskellig besked afhængig af om der betales skat, eller udbetales bonus
		} else {
//			Du må ikke havne her
		}
		// GUI opdateres med den nye balance for spilleren
		GUIh.setBalance(player.getName(), player.getBalance());

	}

}


package controller.fieldcontrollers;

import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.DiceCup;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.JailDTO;

//
public class JailController {

	public static void JailRules(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field, PlayerDTO player, GameBoardDTO game, DiceCup d) {
		JailDTO jfield = (JailDTO) field;
		
		// er du i fængsel?
		if (player.getRoundsLeftJail() > 0) {
			//betal dig ud
			if ((player.getBalance() > 1000) && GUIh.getYesNo(language.askPayJail(), language.yes(), language.no())) {
				player.Transaction(-1000);
				player.setRoundsLeftJail(0);
				GUIh.getButtonPressed(language.playerOutOfJail(player.getName()), language.Ok());
			}
			else {
			// slå 2 ens for at komme ud
				d.rollDiceCup();
				int [] dicevalue = d.getDiceValue();
				GUIh.showDice(dicevalue[0], dicevalue[1]);
				if(dicevalue[0] == dicevalue[1]) {
					player.setRoundsLeftJail(0);
					GUIh.getButtonPressed(language.playerOutOfJail(player.getName()), language.Ok());
				}
				// hvis du gør hverken
				else {
					player.setRoundsLeftJail(player.getRoundsLeftJail()-1); 
				}
			}
//			er kun relevant hvis du ikke er i fængsel.
		} else {
			int currentfield = player.getCurrentField();
			int newfield = jfield.landOnField(player, game);
			if(currentfield != newfield) {
				GUIh.getButtonPressed(language.playerGoToJail(player.getName()), language.Ok());	
			}
		}

	}

}

package controller.fieldcontrollers;

import model.DiceCup;
import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.JailDTO;
import model.language.LanguageHandler;
import view.GUIHandler;
import view.Sound;

public enum JailStateController {
	ENTER, GO_TO_JAIL, SENT_TO_JAIL,
	WILL_HE_PAY, PAY, ROLL, COUNTDOWN, ROLLED_DOUBLE, EXIT;

	public static int JailRulesState(GUIHandler GUIh, LanguageHandler language, int fieldNumber, FieldDTO field,
		PlayerDTO player, GameBoardDTO game, DiceCup d) {
		JailDTO jfield = (JailDTO) field;
		Sound Sound = new Sound();
		JailStateController state;

		state = ENTER;
		while (state != EXIT) {
			switch (state) {
			case ENTER:
				state = GO_TO_JAIL;
				if (player.getRoundsLeftJail() > 0)
					state = WILL_HE_PAY;
				break;
			case GO_TO_JAIL:
				state = EXIT;
				int currentfield = player.getCurrentField();
				int newfield = jfield.landOnField(player, game);
				if (currentfield != newfield) 
					state = SENT_TO_JAIL;
				break;
			case SENT_TO_JAIL:
				state = EXIT;
				Sound.playJailSoundThread();
				GUIh.getButtonPressed(language.playerGoToJail(player.getName()), language.Ok());
				break;
			case WILL_HE_PAY:
				state = ROLL;
				if ((player.getBalance() > 1000) && GUIh.getYesNo(language.askPayJail(), language.yes(), language.no()))
					state = PAY;
				break;
			case PAY:
				state = EXIT;
				player.Transaction(-1000);
				player.setRoundsLeftJail(0);
				GUIh.getButtonPressed(language.playerOutOfJail(player.getName()), language.Ok());
				break;
			case ROLL:
				state = COUNTDOWN;
				// slå 2 ens for at komme ud
				d.rollDiceCup();
				int[] dicevalue = d.getDiceValue();
				GUIh.showDice(dicevalue[0], dicevalue[1]);
				if (dicevalue[0] == dicevalue[1])
					state = ROLLED_DOUBLE;
				break;
			case COUNTDOWN:
				state = EXIT;
				player.setRoundsLeftJail(player.getRoundsLeftJail() - 1);
				GUIh.getButtonPressed(language.jailCountdown(player.getName()), language.Ok());
				if(player.getRoundsLeftJail() == 0)
					GUIh.getButtonPressed(language.playerOutOfJail(player.getName()), language.Ok());
				break;
			case ROLLED_DOUBLE:
				state = EXIT;
				player.setRoundsLeftJail(0);
				GUIh.getButtonPressed(language.playerOutOfJailDoubles(player.getName()), language.Ok());
				fieldNumber = player.moveToField(d.getDiceSum(), game);
				break;
			default:
				break;
			}

		}
		return fieldNumber;
	}
}

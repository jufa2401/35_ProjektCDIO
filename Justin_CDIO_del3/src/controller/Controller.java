package controller;
import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import entity.DiceCup;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.PlayerList;
import entity.fieldclasses.FieldDTO;

public class Controller {
	DiceCup dice;
	GameBoardDTO game;
	GUIHandler GUIh;
	LanguageHandler language;
	PlayerList playerList;
	public Controller (GUIHandler GUI, LanguageHandler language, GameBoardDTO gb, PlayerList playerList) {
		dice = new DiceCup();
		this.game = gb;
		this.GUIh = GUI;
		this.language = language;
		this.playerList = playerList;
	}

	public void launchGame() {
		int nPlayers = playerList.getNumberofPlayer();
		// Spillerne rykker efter tur, indtil der findes en vinder
		while(playerList.isWinner() == false) {
			for(int i = 0; i < nPlayers; i++) {
				PlayerDTO player = playerList.getPlayer(i);
				//				Checker om der er en vinder, og spiller 'i' stadig er med i spillet
				if(playerList.isWinner() == false && player.hasLost() == false)
					gameTurn(player);
			}
		}
		// Spillet er slut, og der gives besked om hvem der har vundet
		String winner = playerList.getWinner();
		GUIh.getButtonPressed(language.GameOver(winner), language.Ok());
	}

	public void gameTurn(PlayerDTO player) {
		// vent indtil spilleren er klar til at rykke
		GUIh.getButtonPressed(language.GetOkMove(player.getName()), language.Ok());
		dice.rollDiceCup();
		int[] d = dice.getDiceValue();
		GUIh.showDice(d[0], d[1]);
		// Fjerner brik fra feltet
		GUIh.removeCar(player.getCurrentField(), player.getName());
		// Husker terningekast, i tilfælde af at det skal bruges til at beregne leje
		player.SaveDiceRoll(dice);
		// spilleren rykker
		int fieldNumber = player.moveToField(dice.getDiceSum(), game);
		// spillerens brik vises på det nye felt
		GUIh.setCar(fieldNumber, player.getName());
		// vi henter feltet på basis af dets index
		FieldDTO field = game.getField(fieldNumber);
		// vi gemmer spillerens tabt-status
		boolean hasLost = player.hasLost();
		// Vi kalder GameLogic som indeholder feltreglerne
		GameLogic.FieldRules(GUIh, language, fieldNumber, field, player);	
		/*
		 *  Vi bruger den tidligere gemte tabt-status, til at kontrollere om
		 *  spilleren har tabt i den netop gennemførte træk. Hvis han har tabt,
		 *  frigives alle ejendomme og spillerens brik fjernes fra feltet
		 */
		if (!hasLost && player.hasLost() == true) {
			for (int n = 0; n <  game.getNumberOfFields(); n++) {
				if (game.removeFieldOwner(n, player) == true)
					GUIh.removeOwner(n);
			}
			GUIh.removeCar(fieldNumber, player.getName());
		}

	}
}

package controller;
import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import controller.fieldcontrollers.StreetController;
import daoimplementation.MySQLGameStateDAO;
import entity.DiceCup;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.PlayerList;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.StreetDTO;

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
		MySQLGameStateDAO gamestate = new MySQLGameStateDAO();
		int nPlayers = playerList.getNumberofPlayer();
		// Spillerne rykker efter tur, indtil der findes en vinder
		while(playerList.isWinner() == false) {
			for(int i = 0; i < nPlayers; i++) {
				PlayerDTO player = playerList.getPlayer(i);
				//				Checker om der er en vinder, og spiller 'i' stadig er med i spillet
				if(playerList.isWinner() == false && player.hasLost() == false)
					gameTurn(player);
			}
			//			Laver nye tråde når der gemmes
			new Thread(() -> 
			gamestate.saveFieldStatus(game))
			.start();
			new Thread (() ->
			gamestate.savePlayerStatus(playerList)) 
			.start();
			//			Savegamestate()
			//			save owned by state, player state
		}
		// Spillet er slut, og der gives besked om hvem der har vundet
		String winner = playerList.getWinner();
		GUIh.getButtonPressed(language.GameOver(winner), language.Ok());
	}

	public void gameTurn(PlayerDTO player) {
		int fieldNumber = player.getCurrentField();
		// vent indtil spilleren er klar til at rykke


		if (player.getRoundsLeftJail() == 0){
			if (player.canBuyHouses(game)){
				StreetController.BuyHouse(GUIh, language, player, game);
			}	else {
				GUIh.getButtonPressed(language.GetOkMove(player.getName()), language.Ok());
			}
			dice.rollDiceCup();
			int[] d = dice.getDiceValue();
			GUIh.showDice(d[0], d[1]);
			// Fjerner brik fra feltet
			GUIh.removeCar(fieldNumber, player.getName());
			// Husker terningekast, i tilfælde af at det skal bruges til at beregne leje
			player.SaveDiceRoll(dice);
			//Besked om startbonus
			if(fieldNumber + dice.getDiceSum() >= game.getNumberOfFields()){
				GUIh.getButtonPressed(language.GetOkStart(player.getName()), language.Ok());
			}
			// spilleren rykker
			fieldNumber = player.moveToField(dice.getDiceSum(), game);

			// spillerens brik vises på det nye felt
			GUIh.setCar(fieldNumber, player.getName());
			//	nedenstående setbalance er fordi guien ikke blev opdateret i tide, når man passerede start
			GUIh.setBalance(player.getName(), player.getBalance());

		}

		// vi henter feltet på basis af dets index		
		FieldDTO field = game.getField(fieldNumber);
		// vi gemmer spillerens tabt-status
		boolean hasLost = player.hasLost();
		// Vi kalder GameLogic som indeholder feltreglerne
		GameLogic.FieldRules(GUIh, language, game, fieldNumber, field, player, dice);	
		// Fjerner brik fra feltet
		GUIh.removeCar(fieldNumber, player.getName());
		//setcar for at være sikker på at guien opdatererer
		GUIh.setCar(player.getCurrentField(), player.getName());
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

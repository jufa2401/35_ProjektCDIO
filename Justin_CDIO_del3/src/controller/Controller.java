/*
 * 
 */
package controller;

import controller.fieldcontrollers.StreetController;
import model.DiceCup;
import model.PlayerDTO;
import model.PlayerList;
import model.daoboundary.GameStateDAO;
import model.daoimplementation.MySQLGameStateDAO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.language.LanguageHandler;
import view.GUIHandler;
import view.Sound;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse styrer det generelle spilforløb.
 *
 * @author Justin
 */
public class Controller {

	/** The dice. */
	DiceCup dice;

	/** The game. */
	GameBoardDTO game;

	/** The GU ih. */
	GUIHandler GUIh;

	/** The language. */
	LanguageHandler language;

	/** The player list. */
	PlayerList playerList;

	/**
	 * Instantiates a new controller.
	 *
	 * @param GUI the gui
	 * @param language the language
	 * @param gb the gb
	 * @param playerList the player list
	 */
	public Controller(GUIHandler GUI, LanguageHandler language, GameBoardDTO gb, PlayerList playerList) {
		dice = new DiceCup();
		this.game = gb;
		this.GUIh = GUI;
		this.language = language;
		this.playerList = playerList;
	}
	Sound Sound = new Sound();
	/**
	 * Styrer spilturen. Spilturen ændrer forløb afhængigt af spillerens status.
	 *
	 * @param player the player
	 */
	public void gameTurn(PlayerDTO player) {
		int fieldNumber = player.getCurrentField();
		// vent indtil spilleren er klar til at rykke

		if (player.getRoundsLeftJail() == 0) {
			if (player.canBuyHouses(game)) {
				StreetController.BuyHouse(GUIh, language, player, game);
			} else {
				GUIh.getButtonPressed(language.GetOkMove(player.getName()), language.Ok());
			}
			dice.rollDiceCup();
			int[] d = dice.getDiceValue();
			GUIh.showDice(d[0], d[1]);
			//			Sound.playSoundThread("dicethrow.wav");
			// Fjerner brik fra feltet
			GUIh.removeCar(fieldNumber, player.getName());
			// Husker terningekast, i tilfælde af at det skal bruges til at
			// beregne leje
			player.SaveDiceRoll(dice);
			// Besked om startbonus
			if (fieldNumber + dice.getDiceSum() >= game.getNumberOfFields()) {
				GUIh.getButtonPressed(language.GetOkStart(player.getName()), language.Ok());
			}
			// spilleren rykker
			fieldNumber = player.moveToField(dice.getDiceSum(), game);


			// spillerens brik vises på det nye felt
			GUIh.setCar(fieldNumber, player.getName());
			// nedenstående setbalance er fordi guien ikke blev opdateret i
			// tide, når man passerede start
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
		// setcar for at være sikker på at guien opdatererer
		GUIh.setCar(player.getCurrentField(), player.getName());
		/*
		 * Vi bruger den tidligere gemte tabt-status, til at kontrollere om
		 * spilleren har tabt i den netop gennemførte træk. Hvis han har tabt,
		 * frigives alle ejendomme og spillerens brik fjernes fra feltet
		 */
		if (!hasLost && player.hasLost() == true) {
			for (int n = 0; n < game.getNumberOfFields(); n++) {
				if (game.removeFieldOwner(n, player) == true)
					GUIh.removeOwner(n);
			}
			GUIh.removeCar(fieldNumber, player.getName());
		}

	}

	/**
	 * Denne metoder sætter "spilforløbet" igang og kalder metoder som gemmer
	 * spilstatus undervejs.
	 */
	public void launchGame() {
		GameStateDAO gamestate = new MySQLGameStateDAO();
		int nPlayers = playerList.getNumberofPlayer();
		// Spillerne rykker efter tur, indtil der findes en vinder
		while (playerList.isWinner() == false) {
			for (int i = 0; i < nPlayers; i++) {
				PlayerDTO player = playerList.getPlayer(i);
				// Checker om der er en vinder, og spiller 'i' stadig er med i
				// spillet
				if (playerList.isWinner() == false && player.hasLost() == false)
					gameTurn(player);
			}
			// Laver nye tråde når der gemmes
			new Thread(() -> gamestate.saveFieldStatus(game)).start();
			new Thread(() -> gamestate.savePlayerStatus(playerList)).start();
			// Savegamestate()
			// save owned by state, player state
		}
		// Spillet er slut, og der gives besked om hvem der har vundet
		String winner = playerList.getWinner();
		GUIh.getButtonPressed(language.GameOver(winner), language.Ok());

		Sound.playGameOverSound();
		if(GUIh.getYesNo(language.exitOrNew(),language.endgame() , language.newgame())){
			System.exit(0);
		}

	}
}


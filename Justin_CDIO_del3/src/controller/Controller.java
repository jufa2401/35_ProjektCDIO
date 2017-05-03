/*
 * 
 */
package controller;

import controller.fieldcontrollers.BuyHouseController;
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

	/** The DICE. */
	private final DiceCup DICE;

	/** The GAMEBOARD. */
	private final GameBoardDTO GAMEBOARD;

	/** The GUI. */
	private final GUIHandler GUIHANDLER;

	/** The LANGUAGE. */
	private final LanguageHandler LANGUAGE;

	/** The player list. */
	private final PlayerList PLAYERLIST;

	/** The SOUND */
	private final Sound SOUND;

	/** The fieldrules */
	private final GameLogic RULES;
	/**
	 * Instantiates a new controller.
	 *
	 * @param GUI the gui
	 * @param LANGUAGE the LANGUAGE
	 * @param gb the gb
	 * @param PLAYERLIST the player list
	 */
	public Controller(GUIHandler GUI, LanguageHandler language, GameBoardDTO gb, PlayerList PLAYERLIST) {
		GAMEBOARD = gb;
		GUIHANDLER = GUI;
		this.LANGUAGE = language;
		this.PLAYERLIST = PLAYERLIST;	
		DICE = new DiceCup();
		SOUND = new Sound();
		RULES = new GameLogic();
	}

	/**
	 * Styrer spilturen. Spilturen ændrer forløb afhængigt af spillerens status.
	 *
	 * @param player the player
	 */
	public void gameTurn(PlayerDTO player) {
		boolean hasLost = false;
		int fieldNumber = player.getCurrentField();
		// vent indtil spilleren er klar til at rykke

		if (player.getRoundsLeftJail() == 0) {
			if (player.canBuyHouses(GAMEBOARD)) {
				BuyHouseController.BuyHouseState(GUIHANDLER, LANGUAGE, player, GAMEBOARD);
			}
			int rolledDouble = 0;
			do {
				final String msg = (rolledDouble > 0) ? LANGUAGE.extraRoll(player.getName()) : LANGUAGE.GetOkMove(player.getName());  
				GUIHANDLER.getButtonPressed(msg, LANGUAGE.Ok());
				DICE.rollDiceCup();
				final int[] d = DICE.getDiceValue();
				GUIHANDLER.showDice(d[0], d[1]);
				if (d[0] == d[1])
					++rolledDouble;
				else 
					rolledDouble = 0;
				// max 2 extra ture
				if (rolledDouble >= 2)
					rolledDouble = 0;
				// Fjerner brik fra feltet
				GUIHANDLER.removeCar(fieldNumber, player.getName());
				// Husker terningekast, i tilfælde af at det skal bruges til at
				// beregne leje
				player.SaveDiceRoll(DICE);
				// Besked om startbonus
				if (fieldNumber + DICE.getDiceSum() >= GAMEBOARD.getNumberOfFields()) {
					GUIHANDLER.getButtonPressed(LANGUAGE.GetOkStart(player.getName()), LANGUAGE.Ok());
				}
				// spilleren rykker
				fieldNumber = player.moveToField(DICE.getDiceSum(), GAMEBOARD);
				// spillerens brik vises på det nye felt
				GUIHANDLER.setCar(fieldNumber, player.getName());
				// nedenstående setbalance er fordi guien ikke blev opdateret i
				// tide, når man passerede start
				GUIHANDLER.setBalance(player.getName(), player.getBalance());
				// vi henter feltet på basis af dets index
				final FieldDTO field = GAMEBOARD.getField(fieldNumber);
				// vi gemmer spillerens tabt-status
				hasLost = player.hasLost();
				// Vi kalder GameLogic som indeholder feltreglerne
				RULES.FieldRules(GUIHANDLER, LANGUAGE, GAMEBOARD, fieldNumber, field, player, DICE);
				if (player.getRoundsLeftJail() > 0)
					rolledDouble = 0;
			} while (rolledDouble > 0 && !player.hasLost());
		} else {
			// Hvis spilleren er røget i fængsel, er der ingen dobbelt-tur
			// vi henter feltet på basis af dets index
			final FieldDTO field = GAMEBOARD.getField(fieldNumber);
			// Vi kalder GameLogic som indeholder feltreglerne
			final int newFieldNumber = RULES.FieldRules(GUIHANDLER, LANGUAGE, GAMEBOARD, fieldNumber, field, player, DICE);
			if (newFieldNumber != fieldNumber) {
				// Kom ud af fængsel (slog to ens), udfør feltregler på det felt man er havnet på
				RULES.FieldRules(GUIHANDLER, LANGUAGE, GAMEBOARD, newFieldNumber, field, player, DICE);
			}
		}

		// Fjerner brik fra feltet
		GUIHANDLER.removeCar(fieldNumber, player.getName());
		// setcar for at være sikker på at guien opdatererer
		GUIHANDLER.setCar(player.getCurrentField(), player.getName());
		/*
		 * Vi bruger den tidligere gemte tabt-status, til at kontrollere om
		 * spilleren har tabt i den netop gennemførte træk. Hvis han har tabt,
		 * frigives alle ejendomme og spillerens brik fjernes fra feltet
		 */
		if (!hasLost && player.hasLost() == true) {
			for (int n = 0; n < GAMEBOARD.getNumberOfFields(); n++) {
				if (GAMEBOARD.removeFieldOwner(n, player) == true)
					GUIHANDLER.removeOwner(n);
			}
			GUIHANDLER.removeCar(fieldNumber, player.getName());
		}

	}

	/**
	 * Denne metoder sætter "spilforløbet" igang og kalder metoder som gemmer
	 * spilstatus undervejs.
	 */
	public void launchGame() {
		final GameStateDAO gamestate = new MySQLGameStateDAO();
		final int nPlayers = PLAYERLIST.getNumberofPlayer();
		// Spillerne rykker efter tur, indtil der findes en vinder
		while (PLAYERLIST.isWinner() == false) {
			for (int i = 0; i < nPlayers; i++) {
				final PlayerDTO player = PLAYERLIST.getPlayer(i);
				// Checker om der er en vinder, og spiller 'i' stadig er med i
				// spillet
				if (PLAYERLIST.isWinner() == false && player.hasLost() == false)
					gameTurn(player);
			}
			// Laver nye tråde når der gemmes, strengt taget unødvendigt, da opslagene går så hurtigt.
			gamestate.saveFieldStatusThread(GAMEBOARD);
			gamestate.savePlayerStatusThread(PLAYERLIST);
			// Savegamestate()
			// save owned by state, player state
		}
		// Spillet er slut, og der gives besked om hvem der har vundet
		final String winner = PLAYERLIST.getWinner();
		GUIHANDLER.getButtonPressed(LANGUAGE.GameOver(winner), LANGUAGE.Ok());

		SOUND.playGameOverSound();
		if(GUIHANDLER.getYesNo(LANGUAGE.exitOrNew(),LANGUAGE.endgame() , LANGUAGE.newgame())){
			System.exit(0);
		}

	}
}


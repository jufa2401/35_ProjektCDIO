/*
 * 
 */
package controller;

import model.PlayerList;
import model.daoboundary.GameStateDAO;
import model.daoboundary.PlayerDAO;
import model.daoimplementation.MySQLGameStateDAO;
import model.daoimplementation.MySQLPlayerDAO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.Ownable;
import model.fieldclasses.StreetDTO;
import model.language.LanguageHandler;
import view.GUIHandler;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse initialiserer spillet, og sætter det igang.
 *
 * @author Justin
 */
public class SetupController {

	/**
	 * Instantiates a new setup controller.
	 */
	public SetupController() {

	}

	/**
	 * Setup.
	 *
	 * @param GUIh the GUIhandler
	 * @param game the game
	 * @param language the language
	 * @return the player list
	 */
	public PlayerList setup(GUIHandler GUIh, GameBoardDTO game, LanguageHandler language) {

		GameStateDAO gamestate = new MySQLGameStateDAO();
		PlayerDAO playerdao = new MySQLPlayerDAO();
		// Opretter spilleplade i GUI på basis af feltrækken i GameBoard
		GUIh.createGameBoard(game, language); 
		// Vis spillets regler til spillerne
		GUIh.getGameRules(language.gameRules());

		int playerCount = 0;
		PlayerList playerList;
		// Spørg om genoptaget spil
		boolean isSavedGame = gamestate.checkFieldStatus();
		if (isSavedGame && GUIh.getYesNo(language.AskLoadGame(), language.yes(), language.no())) {
			playerCount = playerdao.getPlayerCount();
			playerList = playerdao.getPlayers();
			for (int i = 0; i < playerCount; i++) {
				String name = playerList.getPlayer(i).getName();
				int balance = playerList.getPlayer(i).getBalance();
				int currentField = playerList.getPlayer(i).getCurrentField();
				GUIh.addPlayer(name, currentField, balance);
				GUIh.setCar(currentField, name);
			}
			gamestate.loadFieldStatus(playerList, game); // load fieldstatus
			for (int i = 0; i < game.getNumberOfFields(); i++) {
				FieldDTO field = game.getField(i);
				int type = field.getType();
				switch (type) {
				case 1:
				case 2:
				case 5:
					Ownable ofield = (Ownable) field;
					if (ofield.getOwner() != null) {
						String name = ofield.getOwner().getName();
						GUIh.setOwner(ofield.getID(), name);
						if (type == 5) {
							StreetDTO sfield = (StreetDTO) ofield;
							//Den udleverede GUI indeholder ikke en metoder som kan opdatere dent, hvilket der behov for når der kommer flere huse
							GUIh.setRent(i, language.getFieldRent(sfield.getRent())); 
							GUIh.setHouses(i, sfield.getHouses());
						}
					}
					break;
				default:
					break;
				}
			}
		} else {
			// Spørger om antal spillere
			playerCount = GUIh.getInteger(language.AskHowManyPlayers(), 2, 6);

			// Databasen skal tømmes her for det gamle spil (ownable og player)
			gamestate.emptyDataEntries();
			playerList = new PlayerList(playerCount);
			// beder om spillernavne, og kontrollerer de ikke er i brug
			for (int i = 0; i < playerCount; i++) {
				String name;
				do {
					name = GUIh.getName(language.askForPlayerName(), language.badName());
				} while (playerList.isNameUsed(i, name));
				playerList.addPlayer(i, name, 0, 30000, 0);
				GUIh.addPlayer(playerList.getPlayer(i).getName(), 0, playerList.getPlayer(i).getBalance());
			}
		}
		return playerList;
	}

}
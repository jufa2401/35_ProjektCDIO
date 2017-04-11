package rungame;
import boundary.GUIHandler;
import boundary.dao.PlayerDAO;
import boundary.language.LanguageHandler;
import controller.Controller;
import daoimplementation.MySQLPlayerDAO;
import entity.GameBoardDTO;
import entity.PlayerList;
public class Main {
	/* 
	 * Min main klasse starter initialiserer GameBoard,
	 * GUIHandler, sætter sprog, henter reglerne
	 */
	public static void main (String[]args) {
		GameBoardDTO game = new GameBoardDTO();
		GUIHandler GUIh = new GUIHandler();
		PlayerDAO playerdao = new MySQLPlayerDAO();
		//		Sætter sproget til dansk, flere sprog kan udvikles i language pakken, ved at implementere language definitions
		LanguageHandler language = new LanguageHandler("Dansk");
		// Opretter spilleplade i GUI på basis af feltrækken i GameBoard
		GUIh.createGameBoard(game, language);
		// Vis spillets regler til spillerne
		GUIh.getGameRules(language.gameRules());
		//		Spørg om genoptaget spil
		int playerCount = 0;
		PlayerList playerList;
		if (GUIh.getYesNo(language.AskLoadGame(), language.yes(), language.no())) {
			playerCount = playerdao.getPlayerCount();
			playerList = playerdao.getPlayers();
			for (int i = 0; i < playerCount; i++) {				
				String name = playerList.getPlayer(i).getName(); 
				int balance = playerList.getPlayer(i).getBalance();
				int currentField = playerList.getPlayer(i).getCurrentField();
				GUIh.addPlayer(name, currentField, balance);
				GUIh.setCar(currentField, name);
			}	
		}
		else {
			// Spørger om antal spillere
			playerCount = GUIh.getInteger(language.AskHowManyPlayers(), 2, 6);		

			//			Databasen skal tømmes her for det gamle spil (ownable og player)
			playerList = new PlayerList(playerCount);
			// beder om spillernavne, og kontrollerer de ikke er i brug
			for (int i = 0; i < playerCount; i++)
			{
				String name;
				do {
					name = GUIh.getString(language.askForPlayerName());
				} while (playerList.isNameUsed(i,name));
				playerList.addPlayer(i, name, 0, 30000, 0 , 0);
				GUIh.addPlayer(playerList.getPlayer(i).getName(), 0, playerList.getPlayer(i).getBalance());
			}	
		}
		//Start spillet
		Controller GameController = new Controller(GUIh, language, game, playerList);
		GameController.launchGame();
	}
}



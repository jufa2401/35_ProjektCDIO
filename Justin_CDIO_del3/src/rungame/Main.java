package rungame;
import boundary.GUIHandler;
import boundary.language.LanguageHandler;
import controller.Controller;
import entity.GameBoardDTO;
import entity.PlayerList;
public class Main {
/* 
 * Min main klasse starter initialiserer GameBoard,
 * GUIHandler, sætter sprog, henter reglerne
 * 
 */
	public static void main (String[]args) {
		GameBoardDTO game = new GameBoardDTO();
		GUIHandler GUIh = new GUIHandler();
//		Sætter sproget til dansk, flere sprog kan udvikles i language pakken, ved at implementere language definitions
		LanguageHandler language = new LanguageHandler("Dansk");
		// Opretter spilleplade i GUI på basis af feltrækken i GameBoard
		GUIh.createGameBoard(game, language);
		// Vis spillets regler til spillerne
		GUIh.getGameRules(language.gameRules());
		// Spørger om antal spillere
		int playerCount = GUIh.getInteger(language.AskHowManyPlayers(), 2, 6);		
		PlayerList playerList = new PlayerList(playerCount);
		// beder om spillernavne, og kontrollerer de ikke er i brug
		for (int i = 0; i < playerCount; i++)
		{
			String name;
			do {
				name = GUIh.getString(language.askForPlayerName());
			} while (playerList.isNameUsed(i,name));
			playerList.addPlayer(i, name);
			GUIh.addPlayer(playerList.getPlayer(i).getName(), playerList.getPlayer(i).getBalance());
		}
		//Start spillet
		Controller GameController = new Controller(GUIh, language, game, playerList);
		GameController.launchGame();
	}
}



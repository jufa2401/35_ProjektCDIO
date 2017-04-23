package rungame;
import boundary.GUIHandler;
import boundary.dao.PlayerDAO;
import boundary.language.LanguageHandler;
import controller.Controller;
import daoimplementation.MySQLGameStateDAO;
import daoimplementation.MySQLPlayerDAO;
import entity.GameBoardDTO;
import entity.PlayerList;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;
import entity.fieldclasses.StreetDTO;
public class Main {
	/* 
	 * Min main klasse starter initialiserer GameBoard,
	 * GUIHandler, sætter sprog, henter reglerne
	 */
	public static void main (String[]args) {
		
		GameBoardDTO game = new GameBoardDTO();
		GUIHandler GUIh = new GUIHandler();

		MySQLGameStateDAO gamestate = new MySQLGameStateDAO();
		PlayerDAO playerdao = new MySQLPlayerDAO();
		//		Sætter sproget til dansk, flere sprog kan udvikles i language pakken, ved at implementere language definitions
		LanguageHandler language = new LanguageHandler("Dansk");
		// Opretter spilleplade i GUI på basis af feltrækken i GameBoard
		GUIh.createGameBoard(game, language);		//GameBoardet var forsøgt at starte i sin egen tråd, men GUIen tager allerede højde for dette.
		// Vis spillets regler til spillerne
		GUIh.getGameRules(language.gameRules());
		//		Spørg om genoptaget spil
		int playerCount = 0;
		PlayerList playerList;
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
			gamestate.loadFieldStatus(playerList, game); //load fieldstatus
			for (int i = 0; i < game.getNumberOfFields(); i++) {
				FieldDTO field = game.getField(i);
				int type = field.getType();
				switch (type) {
				case 1:
				case 2:
				case 5:
					Ownable ofield = (Ownable) field;
					if (ofield.getOwner() != null){
						String name = ofield.getOwner().getName();
						GUIh.setOwner(ofield.getID(), name);
						if(type == 5) {
							StreetDTO sfield = (StreetDTO) ofield;
						GUIh.setRent(i, language.getFieldRent(sfield.getRent())); // Vrker ikke pga. GUI ikke opdaterer rent, den skal opdateres pr hus.
						GUIh.setHouses(i, sfield.getHouses());
						}
					}
					break;
				default: break;
				}
			}			
		}
		else {
			// Spørger om antal spillere
			playerCount = GUIh.getInteger(language.AskHowManyPlayers(), 2, 6);		

			//			Databasen skal tømmes her for det gamle spil (ownable og player)
			gamestate.emptyDataEntries();
			playerList = new PlayerList(playerCount);
			// beder om spillernavne, og kontrollerer de ikke er i brug
			for (int i = 0; i < playerCount; i++)
			{
				String name;
				do {
					name = GUIh.getName(language.askForPlayerName());
				} while (playerList.isNameUsed(i,name));
				playerList.addPlayer(i, name, 0, 30000, 0);
				GUIh.addPlayer(playerList.getPlayer(i).getName(), 0, playerList.getPlayer(i).getBalance());
			}	
		}
		//Start spillet
		Controller GameController = new Controller(GUIh, language, game, playerList);
		GameController.launchGame();
	}
}



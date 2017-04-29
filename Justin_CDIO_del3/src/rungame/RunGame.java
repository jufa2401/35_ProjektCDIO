package rungame;

import controller.Controller;
import controller.SetupController;
import model.PlayerList;
import model.fieldclasses.GameBoardDTO;
import model.language.LanguageHandler;
import view.GUIHandler;
/**
 * Min RunGame klasse starter og initialiserer GameBoard, GUIHandler, sætter
 * sprog, henter reglerne starter spillet
 *
 * @param args the arguments
 */
public class RunGame {
	public void run() {		
		GameBoardDTO game = new GameBoardDTO();
		GUIHandler GUIh = new GUIHandler();
		SetupController setup = new SetupController();
		// Sætter sproget til dansk, flere sprog kan udvikles i language pakken,
		// ved at implementere language definitions
		LanguageHandler language = new LanguageHandler("Dansk");
		PlayerList playerList = setup.setup(GUIh, game, language);
		

		// Start spillet
		Controller GameController = new Controller(GUIh, language, game, playerList);
		GameController.launchGame();
	}
}

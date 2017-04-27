package rungame;

import controller.Controller;
import controller.SetupController;
import model.PlayerList;
import model.fieldclasses.GameBoardDTO;
import model.language.LanguageHandler;
import view.GUIHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/*
	 * Min main klasse starter initialiserer GameBoard, GUIHandler, sætter
	 * sprog, henter reglerne
	 */
	public static void main(String[] args) {

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

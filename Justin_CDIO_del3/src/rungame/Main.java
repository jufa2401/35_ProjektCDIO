package rungame;
import controller.SetupController;

/**
 * The Class Main.
 */
public class Main {

	public static void main(String[] args) {		
		final SetupController SETUP = new SetupController();
		/*
		 *Dette virker ikke rigtigt, da GUIen ikke har funktioner til at fjerne spillere. 
		 *Dette virker delvist, hvis man starter kun 2 mands spil. Men de døde spillere bliver stadig vist, dog ikke rykket
		 */
		while(true){
			//Starter spillet
			SETUP.runGame();
		}
	}
}

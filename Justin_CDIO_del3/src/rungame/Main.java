package rungame;
import view.GUIHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
		
	public static void main(String[] args) {		
		RunGame run = new RunGame();
		GUIHandler GUIh = new GUIHandler();
//		Starter spillet
		run.run();
//		Når vi når enden af Controller skal GUIen lukkes, så felterne kan tegnes igen
		GUIh.shutdown();
//		Spillet starter 
		run.run();
				
		
	}
}

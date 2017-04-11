package boundary;
import java.awt.Color;

import boundary.language.LanguageHandler;
import desktop_codebehind.Car;
import desktop_codebehind.Car.Builder;
import desktop_fields.Field;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.GameBoardDTO;
/**		
 * Det er forsøgt at begrænse koblingen mellem boundary og entities,
 * men har endt med at beholde GameBoard, da  det for mig resulterede i 
 * meget simplere kode. 
 */
public class GUIHandler {
	int length = 0;
	static int pid = 0;
	/**
	 * Spillebrættet oprettes i GUIen
	 * I stedet for at inddele vores felter i de forskellige builder felter,
	 * har jeg valgt at lave et array some deler alle felterne ind i 2 grupper
	 * Ownable og ikke-ownable
	 * for at man kan kende forskel på felttyperne har jeg så inddelt mine 
	 * felte ind i forskelligt farvede objekter, som er hentet fra GameBoards array.
	 * 
	 */
	public void createGameBoard(GameBoardDTO gb, LanguageHandler language) {
		length = gb.getNumberOfFields();
		Field[] fields = new Field[length];
		for (int index = 0; index < length; index++) {
			int price = gb.getFieldPrice(index);
			int type  = gb.getFieldType(index);
			int id    = gb.getField(index).getID();
			//hvis feltet er ownable erklærer vi en 'street'
			if (price > 0) {
				int rent  = gb.getFieldRent(index);
				fields[id] = new Street.Builder()
						.setTitle(gb.getFieldName(id))
						.setSubText(language.getFieldPrice(price))
						.setDescription(language.getFieldDescription(type))
						.setBgColor(gb.getFieldColor(index))
						.setRent(language.getFieldRent(rent))
						.build();
			} else {
				fields[id] = new Street.Builder()
						.setTitle(gb.getFieldName(id))
						.setSubText(language.getFieldPrice(price))
						.setDescription(language.getFieldDescription(type))
						.setBgColor(gb.getFieldColor(index))
						.build();
			}
		}
		GUI.create(fields);
	}
	
	/**
	 * prompter spilleren for et tal mellem min og max
	 * @param message
	 * @param min
	 * @param max
	 * @return
	 */
	public int getInteger(String message, int min, int max) {
		return GUI.getUserInteger(message, min, max);
	}
	/**
	 * Prompter spilleren for indtast af tekststreng
	 * @param message
	 * @return
	 */
	public String getString(String message) {
		return GUI.getUserString(message);
	}
	/**
	 * opretter én spiller i GUIen, der kan maks være 6 spillere,
	 * derfor er der oprettet 6 forskellige biler.
	 * Variablen pid er statisk, og tælles op hver gang addPlayer kaldes.
	 * Derved sikrer man at hver gang der tilføjes en spiller, bliver der tilføjet
	 * forskellig brik
	 * @param player
	 * @param balance
	 */
	public void addPlayer(String player, int currentField, int balance) {
		Builder carBuilder = new Car.Builder();
		switch (pid) {
		case 0:
			carBuilder
			.typeRacecar()
			.patternHorizontalGradiant()
			.primaryColor(Color.MAGENTA)
			.secondaryColor(Color.DARK_GRAY);
			break;
		case 1:
			carBuilder
			.typeTractor()
			.patternDiagonalDualColor()
			.primaryColor(Color.GREEN)
			.secondaryColor(Color.ORANGE);
			break;
		case 2:
			carBuilder
			.typeRacecar()
			.patternDotted()
			.primaryColor(Color.BLUE)
			.secondaryColor(Color.CYAN);
			break;
		case 3:
			carBuilder
			.typeUfo()
			.patternZebra()
			.primaryColor(Color.YELLOW)
			.secondaryColor(Color.MAGENTA);
			break;
		case 4:
			carBuilder
			.typeTractor()
			.patternCheckered()
			.primaryColor(Color.RED)
			.secondaryColor(Color.BLACK);
			break;
		case 5:
			carBuilder
			.typeUfo()
			.patternHorizontalDualColor()
			.primaryColor(Color.WHITE)
			.secondaryColor(Color.GREEN);
			break;
		default:
			break;
		}
		Car car = carBuilder.build();
		GUI.addPlayer(player, balance, car);
		setCar(currentField, player);
		pid++;
	}
/**
 * viser en specifik spillers brik på et given felt
 * @param currentField
 * @param name
 */
	public void setCar(int currentField, String name) {
		GUI.setCar(currentField+1, name);						//+1
	}

	public void showDice(int d1, int d2) {
		GUI.setDice(d1, d2);
	}
	/**
	 * fjerner en specifik spillers brikke fra et givet felt
	 * @param currentField
	 * @param name
	 */
	public void removeCar(int currentField, String name) {
		GUI.removeCar(currentField+1, name);					//+1

	}
	/**
	 * Prompter spilleren for ja/nej valg
	 * @param message
	 * @param Yes
	 * @param No
	 * @return
	 */
	public boolean getYesNo(String message, String Yes, String No) {
		String response = GUI.getUserButtonPressed(message, Yes, No);
		if (response.equals(Yes))
			return true;
		else return false;
	}
	/**
	 * opdaterer en spillers viste balance i GUIen
	 * @param name
	 * @param newBalance
	 */
	public void setBalance(String name, int newBalance) {
		GUI.setBalance(name, newBalance);
		
	}
	/**
	 * Opdaterer et givet felt til at vise hvilken spiller ejer det.
	 * @param currentField
	 * @param player
	 */
	public void setOwner(int currentField, String player) {
		GUI.setOwner(currentField+1, player);	//+1
	}
	/**
	 * Opdaterer et felt til ikke at have nogen ejer
	 * @param i
	 */
	public void removeOwner(int i) {
		GUI.removeOwner(i+1);	//+1
	}
	/**
	 * viser besked, venter på at spilleren trykker ok
	 * @param getOkMove
	 * @param ok
	 */
	public void getButtonPressed(String getOkMove, String ok) {
		GUI.getUserButtonPressed(getOkMove, ok);
	}
	/**
	 * viser spillets regler
	 * @param gameRules
	 */
	public void getGameRules(String gameRules) {
		GUI.showMessage(gameRules);
	}

	
}




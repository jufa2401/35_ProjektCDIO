package boundary.language;

public class LanguageHandler {

	private static LanguageDefinitions selectedLanguage;
	public String getFieldDescription(int type) {
		return selectedLanguage.getFieldDescription(type);
	}
	

	public LanguageHandler(String language) {
		setLanguage(language);
	}

	/**
	 * Metode til at skifte sprog, skulle det blive nødvendigt ved viderudvikling
	 * Engelsk er med som eksempel, men er kommenteret ud
	 * @param language
	 */
	public void setLanguage(String language) {
		switch(language) {
		case "Dansk": selectedLanguage = new Dansk(); break;
//		case "English": selectedLanguage = new English(); break;
		default: selectedLanguage = new Dansk();
		}
	}
	
	public String gameRules(){
		return selectedLanguage.gameRules();
	}

	public String getFieldName(int index) {
		return selectedLanguage.getFieldName(index);
	}

	public String getFieldPrice(int price) {
		return selectedLanguage.getFieldPrice(price);
	}
	
	public String getFieldRent(int rent) {
		return selectedLanguage.getFieldRent(rent);
	}

	public String AskHowManyPlayers() {
		return selectedLanguage.AskHowManyPlayer();
	}

	public String askForPlayerName() {
		return selectedLanguage.AskForPlayerName();
	}

	public String askBuyField() {
		return selectedLanguage.askBuyField();
	}


	public String no() {
		return selectedLanguage.no();
	}


	public String yes() {
		return selectedLanguage.yes();
	}


	public String GetOkMove(String s) {
		return selectedLanguage.getOkMove(s);
	}


	public String Ok() {
		return selectedLanguage.Ok();
	}


	public String GameOver(String winner) {
		return selectedLanguage.GameOver(winner);
	}


	public String playerPayTo(String payer, String recipient, int paid) {
		return selectedLanguage.playerPayTo(payer, recipient, paid);
	}
	
	public String playerTax(String name, int paid) {
		return selectedLanguage.playerTax(name, paid);
	}


	public String playerBonus(String name, int bonus) {
		return selectedLanguage.playerBonus(name, bonus);
	}


	public String askPayTax() {
		return selectedLanguage.askPayTax();
	}

}
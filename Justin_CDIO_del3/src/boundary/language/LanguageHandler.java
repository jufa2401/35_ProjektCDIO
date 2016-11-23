package boundary.language;

public class LanguageHandler {

	private static LanguageDefinitions selectedLanguage;
	public String getFieldDescription(int type) {
		return selectedLanguage.getFieldDescription(type);
	}
	

	public LanguageHandler(String language) {
		setLanguage(language);
	}

	public void setLanguage(String language) {
		switch(language) {
		case "Dansk": selectedLanguage = new Dansk(); break;
//		case "English": selectedLanguage = new English(); break;
		default: selectedLanguage = new Dansk();
		}
	}

	/**
	 * Notifies of language change
	 * @return String
	 */
	public String notifyLangChange(){
		return selectedLanguage.notifyLangChange();
	}

	public String getFieldName(int id) {
		return selectedLanguage.getFieldName(id);
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
	
	public String gameRules(){
		return selectedLanguage.gameRules();
	}

}
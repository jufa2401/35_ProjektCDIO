/*
 * 
 */
package model.language;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse returnerer den valgte implementation af vores sprogdefinitioner.
 *
 * @author Justin
 */
public class LanguageHandler {

	/** The selected language. */
	private static LanguageDefinitions selectedLanguage;

	/**
	 * Bad name.
	 *
	 * @return the string
	 */
	public String badName() {
		return selectedLanguage.badName();
	}

	/**
	 * Instantiates a new language handler.
	 *
	 * @param language the language
	 */
	public LanguageHandler(String language) {
		setLanguage(language);
	}

	/**
	 * Ask buy field.
	 *
	 * @return the string
	 */
	public String askBuyField() {
		return selectedLanguage.askBuyField();
	}

	/**
	 * Ask buy house.
	 *
	 * @return the string
	 */
	public String askBuyHouse() {
		return selectedLanguage.askBuyHouse();
	}

	// public String getFieldName(int index) {
	// return selectedLanguage.getFieldName(index);
	// }

	/**
	 * Ask for player name.
	 *
	 * @return the string
	 */
	public String askForPlayerName() {
		return selectedLanguage.AskForPlayerName();
	}

	/**
	 * Ask how many players.
	 *
	 * @return the string
	 */
	public String AskHowManyPlayers() {
		return selectedLanguage.AskHowManyPlayer();
	}

	/**
	 * Ask load game.
	 *
	 * @return the string
	 */
	public String AskLoadGame() {
		return selectedLanguage.AskLoadGame();
	}

	/**
	 * Ask pay jail.
	 *
	 * @return the string
	 */
	public String askPayJail() {
		return selectedLanguage.askPayJail();
	}

	/**
	 * Ask pay tax.
	 *
	 * @return the string
	 */
	public String askPayTax() {
		return selectedLanguage.askPayTax();
	}

	/**
	 * Buy.
	 *
	 * @return the string
	 */
	public String buy() {
		return selectedLanguage.buy();
	}

	/**
	 * Buy house group.
	 *
	 * @return the string
	 */
	public String buyHouseGroup() {
		return selectedLanguage.buyHouseGroup();
	}

	/**
	 * Failed house purchase.
	 *
	 * @return the string
	 */
	public String failedHousePurchase() {
		return selectedLanguage.failedHousePurchase();
	}

	/**
	 * Game over.
	 *
	 * @param winner the winner
	 * @return the string
	 */
	public String GameOver(String winner) {
		return selectedLanguage.GameOver(winner);
	}

	/**
	 * Game rules.
	 *
	 * @return the string
	 */
	public String gameRules() {
		return selectedLanguage.gameRules();
	}

	/**
	 * Gets the field description.
	 *
	 * @param type the type
	 * @return the field description
	 */
	public String getFieldDescription(int type) {
		return selectedLanguage.getFieldDescription(type);
	}

	/**
	 * Gets the field price.
	 *
	 * @param price the price
	 * @return the field price
	 */
	public String getFieldPrice(int price) {
		return selectedLanguage.getFieldPrice(price);
	}

	/**
	 * Gets the field rent.
	 *
	 * @param rent the rent
	 * @return the field rent
	 */
	public String getFieldRent(int rent) {
		return selectedLanguage.getFieldRent(rent);
	}

	/**
	 * Gets the ok move.
	 *
	 * @param s the s
	 * @return the string
	 */
	public String GetOkMove(String s) {
		return selectedLanguage.getOkMove(s);
	}

	/**
	 * Gets the ok start.
	 *
	 * @param name the name
	 * @return the string
	 */
	public String GetOkStart(String name) {
		return selectedLanguage.GetOkStart(name);
	}

	/**
	 * House price.
	 *
	 * @return the string
	 */
	public String housePrice() {
		return selectedLanguage.housePrice();
	}

	/**
	 * Move.
	 *
	 * @return the string
	 */
	public String move() {
		return selectedLanguage.move();
	}

	/**
	 * No.
	 *
	 * @return the string
	 */
	public String no() {
		return selectedLanguage.no();
	}

	/**
	 * Ok.
	 *
	 * @return the string
	 */
	public String Ok() {
		return selectedLanguage.Ok();
	}

	/**
	 * Player bonus.
	 *
	 * @param name the name
	 * @param bonus the bonus
	 * @return the string
	 */
	public String playerBonus(String name, int bonus) {
		return selectedLanguage.playerBonus(name, bonus);
	}

	/**
	 * Player go to jail.
	 *
	 * @param name the name
	 * @return the string
	 */
	public String playerGoToJail(String name) {
		return selectedLanguage.playerGoTOJail(name);
	}

	/**
	 * Player out of jail.
	 *
	 * @param name the name
	 * @return the string
	 */
	public String playerOutOfJail(String name) {
		return selectedLanguage.playerOutOfJail(name);
	}

	/**
	 * Player pay to.
	 *
	 * @param payer the payer
	 * @param recipient the recipient
	 * @param paid the paid
	 * @return the string
	 */
	public String playerPayTo(String payer, String recipient, int paid) {
		return selectedLanguage.playerPayTo(payer, recipient, paid);
	}

	/**
	 * Player tax.
	 *
	 * @param name the name
	 * @param paid the paid
	 * @return the string
	 */
	public String playerTax(String name, int paid) {
		return selectedLanguage.playerTax(name, paid);
	}

	/**
	 * Prompt group.
	 *
	 * @return the string
	 */
	public String promptGroup() {
		return selectedLanguage.promptGroup();
	}

	/**
	 * Metode til at skifte sprog, skulle det blive nødvendigt ved
	 * viderudvikling Engelsk er med som eksempel, men er kommenteret ud.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		switch (language) {
		case "Dansk":
			selectedLanguage = new Dansk();
			break;
		// case "English": selectedLanguage = new English(); break;
		default:
			selectedLanguage = new Dansk();
		}
	}

	/**
	 * Yes.
	 *
	 * @return the string
	 */
	public String yes() {
		return selectedLanguage.yes();
	}

	public String timedExit() {
		return selectedLanguage.timedExit();
		
	}

	public String newgame() {
		return selectedLanguage.newgame();
	}

	public String endgame() {
		return selectedLanguage.endgame();
	}

	public String exitOrNew() {
		return selectedLanguage.exitOrNew();
	}

}
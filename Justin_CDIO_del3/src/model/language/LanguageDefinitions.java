/*
 * 
 */
package model.language;

// TODO: Auto-generated Javadoc
/**
 * The Interface LanguageDefinitions.
 */
interface LanguageDefinitions {

	/**
	 * Ask buy field.
	 *
	 * @return the string
	 */
	String askBuyField();

	/**
	 * Ask buy house.
	 *
	 * @return the string
	 */
	String askBuyHouse(String name);

	/**
	 * Ask for player name.
	 *
	 * @return the string
	 */
	String AskForPlayerName();

	/**
	 * Ask how many player.
	 *
	 * @return the string
	 */
	String AskHowManyPlayer();

	/**
	 * Ask load game.
	 *
	 * @return the string
	 */
	String AskLoadGame();

	/**
	 * Ask pay jail.
	 *
	 * @return the string
	 */
	String askPayJail();

	/**
	 * Ask pay tax.
	 *
	 * @return the string
	 */
	String askPayTax();

	/**
	 * Bad name.
	 *
	 * @return the string
	 */
	String badName();

	/**
	 * Buy.
	 *
	 * @return the string
	 */
	String buy();

	/**
	 * Buy house group.
	 *
	 * @return the string
	 */
	String buyHouseGroup();

	/**
	 * Failed house purchase.
	 *
	 * @return the string
	 */
	String failedHousePurchase();

	/**
	 * Game over.
	 *
	 * @param winner the winner
	 * @return the string
	 */
	String GameOver(String winner);

	/**
	 * Game rules.
	 *
	 * @return the string
	 */
	String gameRules();

	/**
	 * Gets the field description.
	 *
	 * @param type the type
	 * @return the field description
	 */
	String getFieldDescription(int type);

	/**
	 * Gets the field price.
	 *
	 * @param price the price
	 * @return the field price
	 */
	String getFieldPrice(int price);

	/**
	 * Gets the field rent.
	 *
	 * @param rent the rent
	 * @return the field rent
	 */
	String getFieldRent(int rent);

	/**
	 * Gets the ok move.
	 *
	 * @param s the s
	 * @return the ok move
	 */
	String getOkMove(String s);

	/**
	 * Gets the ok start.
	 *
	 * @param name the name
	 * @return the string
	 */
	String GetOkStart(String name);

	/**
	 * House price.
	 *
	 * @return the string
	 */
	String housePrice();

	/**
	 * Move.
	 *
	 * @return the string
	 */
	String move();

	/**
	 * No.
	 *
	 * @return the string
	 */
	String no();

	/**
	 * Ok.
	 *
	 * @return the string
	 */
	String Ok();

	/**
	 * Player bonus.
	 *
	 * @param name the name
	 * @param bonus the bonus
	 * @return the string
	 */
	String playerBonus(String name, int bonus);

	/**
	 * Player go TO jail.
	 *
	 * @param name the name
	 * @return the string
	 */
	String playerGoToJail(String name);

	/**
	 * Player out of jail.
	 *
	 * @param name the name
	 * @return the string
	 */
	String playerOutOfJailDoubles(String name);

	/**
	 * Player pay to.
	 *
	 * @param payer the payer
	 * @param recipient the recipient
	 * @param paid the paid
	 * @return the string
	 */
	String playerPayTo(String payer, String recipient, int paid);

	/**
	 * Player tax.
	 *
	 * @param name the name
	 * @param paid the paid
	 * @return the string
	 */
	String playerTax(String name, int paid);

	/**
	 * Prompt group.
	 *
	 * @return the string
	 */
	String promptGroup();

	/**
	 * Yes.
	 *
	 * @return the string
	 */
	String yes();
	/**
	 * Unused: intended for use to inform the player of shutdown game after a timeout
	 * @return the string
	 */
	String timedExit();
	/**
	 *  asks player if he wants to exit the game or start a new one
	 * @return
	 */
	String exitOrNew();
	/**
	 * end the game
	 * @return the string
	 */
	String endgame();
	/**
	 * start a new game
	 * @return the string
	 */
	String newgame();
	/**
	 * count down rounds left in jail
	 * @param name of player in prison
	 * @return the string
	 */
	String jailCountdown(String name);
	/**
	 * failed house purchase, you cannot afford it.
	 * @return the string
	 */

	String failedHouseNoMoney();
	/**
	 * Failed house purchase, no more houses left
	 * @return the string
	 */
	String failedHouseNoHouses();
	/**
	 * Succesful house purchase, house built
	 * @return the string
	 */
	String SuccessfulHousePurchase(int newrent, String fieldname);
	/**
	 * Player rolled a double, extra roll is rewarded
	 * @param name
	 * @return
	 */
	String extraRoll(String name);

	/**
	 * player released from jail
	 * @param name
	 * @return
	 */
	String playerOutOfJail(String name);

}
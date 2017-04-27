/*
 * 
 */
package model.language;

// TODO: Auto-generated Javadoc
/**
 * The Class Dansk.
 */
//sss
public class Dansk implements LanguageDefinitions {

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#askBuyField()
	 */
	@Override
	public String askBuyField() {
		return "Vil du købe dette felt?";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#askBuyHouse()
	 */
	@Override
	public String askBuyHouse() {
		return "Vil du rykke eller købe hus?";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#AskForPlayerName()
	 */
	@Override
	public String AskForPlayerName() {
		return "Indtast spillernavn: ";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#AskHowManyPlayer()
	 */
	@Override
	public String AskHowManyPlayer() {
		return "Hvor mange spillere? (mellem 2 og 6) ";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#AskLoadGame()
	 */
	@Override
	public String AskLoadGame() {
		return "Vil du genoptage et gemt spil?";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#askPayJail()
	 */
	@Override
	public String askPayJail() {
		return "Vil du betale 1000 for at komme ud af fængsel?";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#askPayTax()
	 */
	@Override
	public String askPayTax() {
		return "Hvilket beløb skal der betales i skat?";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#badName()
	 */
	@Override
	public String badName() {
		return "Ulovligt navn, indtast nyt spillernavn \nPrøv med  danske bogstaver og et kortere navn";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#buy()
	 */
	@Override
	public String buy() {
		return "Køb hus";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#buyHouseGroup()
	 */
	@Override
	public String buyHouseGroup() {
		return "Vil du rykke eller købe hus?";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#failedHousePurchase()
	 */
	@Override
	public String failedHousePurchase() {
		return "Du kunne ikke købe hus, enten har du ikke penge nok, eller for mange huse.";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#GameOver(java.lang.String)
	 */
	@Override
	public String GameOver(String winner) {
		return "Spillet er slut. Vinderen er: " + winner + ". Tillykke!";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#gameRules()
	 */
	@Override
	public String gameRules() {
		return "\t Dette spil kan spilles af 2-6 spillere. \n"
				+ "\t Spillet tabes ved at gå bankerot, den sidste spiller med likvide midler vinder spillet. \n"
				+ "\t Alle spillere starter med en balance på 30.000. \n"
				+ "\t Visse felter kan købes, andre kan medføre en betaling. \n"
				+ "\t Hvis en spiller lander på et ejet felt, betales en sum til ejeren. \n ";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#getFieldDescription(int)
	 */
	@Override
	public String getFieldDescription(int type) {
		String s;
		switch (type) {
		case 1:
			s = "Rederi";
			break;
		case 2:
			s = "Bryggeri";
			break;
		case 3:
			s = "Start";
			break;
		case 4:
			s = "Skat";
			break;
		case 5:
			s = "Gade";
			break;
		case 6:
			s = "Fængsel";
			break;
		case 7:
			s = "Prøv lykken";
			break;
		case 8:
			s = "Gratis parkering";
			break;
		default:
			s = "ingen type sat";
			break;
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#getFieldPrice(int)
	 */
	@Override
	public String getFieldPrice(int price) {
		String s;
		if (price > 0) {
			s = "Pris " + price;
		} else {
			// hvis feltet ikke er ownable (price = 0), så returneres en tom
			// streng.
			s = "";
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#getFieldRent(int)
	 */
	@Override
	public String getFieldRent(int rent) {
		String s;
		if (rent > 0) {
			s = "Lejen er: " + rent;
		} else {
			// hvis feltet ikke er ownable (rent = 0), så returneres en tom
			// streng.
			s = "";
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#getOkMove(java.lang.String)
	 */
	@Override
	public String getOkMove(String playerName) {
		return "Det er " + playerName + "'s tur, Tryk OK for at kaste terningerne og rykke";

	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#GetOkStart(java.lang.String)
	 */
	@Override
	public String GetOkStart(String name) {
		return "Spilleren " + name + " passerer start og modtager bonus";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#housePrice()
	 */
	@Override
	public String housePrice() {
		return "\t Huspris: ";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#move()
	 */
	@Override
	public String move() {
		return "Ryk";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#no()
	 */
	@Override
	public String no() {
		return "Nej";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#Ok()
	 */
	@Override
	public String Ok() {
		return "OK";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#playerBonus(java.lang.String, int)
	 */
	@Override
	public String playerBonus(String name, int bonus) {
		return "Spiller '" + name + "' modtager " + bonus + " i bonus.";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#playerGoTOJail(java.lang.String)
	 */
	@Override
	public String playerGoTOJail(String name) {
		return "Spilleren " + name + " smides i fængsel";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#playerOutOfJail(java.lang.String)
	 */
	@Override
	public String playerOutOfJail(String name) {

		return "Spilleren " + name + " er kommet ud af fængsel!";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#playerPayTo(java.lang.String, java.lang.String, int)
	 */
	@Override
	public String playerPayTo(String payer, String recipient, int paid) {
		return "Spiller '" + payer + "' betaler " + paid + " til '" + recipient + "'.";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#playerTax(java.lang.String, int)
	 */
	@Override
	public String playerTax(String name, int paid) {
		return "Spiller '" + name + "' betaler " + paid + " i skat.";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#promptGroup()
	 */
	@Override
	public String promptGroup() {
		return "Vælg hvilken gadegruppe du vil købe hus på:";
	}

	/* (non-Javadoc)
	 * @see boundary.language.LanguageDefinitions#yes()
	 */
	@Override
	public String yes() {
		return "Ja";
	}

}

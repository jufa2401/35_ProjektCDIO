package boundary.language;
//sss
public class Dansk implements LanguageDefinitions {

	@Override
	public String gameRules() {
		return 	  "\t Dette spil kan spilles af 2-6 spillere. \n"
				+ "\t Spillet tabes ved at gå bankerot, den sidste spiller med likvide midler vinder spillet. \n"
				+ "\t Alle spillere starter med en balance på 30.000. \n"
				+ "\t Visse felter kan købes, andre kan medføre en betaling. \n"
				+ "\t Hvis en spiller lander på et ejet felt, betales en sum til ejeren. \n " ;
	}
	@Override
	public String getFieldRent(int rent) {
		String s;
		if (rent > 0) {
			s = "Lejen er: " + rent;
		} else {
			//hvis feltet ikke er ownable (rent = 0), så returneres en tom streng.
			s = "";
		}
		return s;
	}

	@Override
	public String getFieldPrice(int price) {
		String s;
		if (price > 0) {
			s = "Pris " + price;
		} else {
			//hvis feltet ikke er ownable (price = 0), så returneres en tom streng.
			s = "";
		}
		return s;
	}

	@Override
	public String AskHowManyPlayer() {
		return "Hvor mange spillere? (mellem 2 og 6) ";
	}
	@Override
	public String AskForPlayerName() {
		return "Indtast spillernavn: ";
	}	
	@Override
	public String badName() {
		return "Ulovligt navn, indtast nyt spillernavn \nPrøv med  danske bogstaver og et kortere navn";
	}
	@Override
	public String askBuyField() {
		return "Vil du købe dette felt?";
	}
	@Override
	public String getFieldDescription(int type) {
		String s;
		switch (type) {
		case 1: 	s = "Rederi";				break;
		case 2: 	s = "Bryggeri";				break;
		case 3: 	s = "Start";				break;
		case 4: 	s = "Skat";					break;
		case 5: 	s = "Gade";					break;
		case 6: 	s = "Fængsel";				break;
		case 7: 	s = "Prøv lykken";			break;
		case 8: 	s = "Gratis parkering";		break;
		default:	s = "ingen type sat";		break;
		}
		return s;
	}
	@Override
	public String no() {
		return "Nej";
	}
	@Override
	public String yes() {
		return "Ja";
	}
	@Override
	public String getOkMove(String playerName) {
		return "Det er " + playerName+ "'s tur, Tryk OK for at kaste terningerne og rykke";
				
	}
	@Override
	public String Ok() {
		return "OK";
	}
	@Override
	public String GameOver(String winner) {
		return "Spillet er slut. Vinderen er: " + winner + ". Tillykke!";
	}
	@Override
	public String playerPayTo(String payer, String recipient, int paid) {
		return "Spiller '" + payer + "' betaler "+ paid + " til '" + recipient + "'.";
	}

	@Override
	public String playerTax(String name, int paid) {
		return "Spiller '" + name + "' betaler "+ paid + " i skat.";
	}
	@Override
	public String playerBonus(String name, int bonus) {
		return "Spiller '" + name + "' modtager "+ bonus+ " i bonus.";
	}
	@Override
	public String askPayTax() {
		return "Hvilket beløb skal der betales i skat?";
	}
	@Override
	public String AskLoadGame() {
		return "Vil du genoptage et gemt spil?";
	}
	@Override
	public String askPayJail() {
		return "Vil du betale 1000 for at komme ud af fængsel?";
	}
	@Override
	public String playerOutOfJail(String name) {
	
		return "Spilleren " + name +" er kommet ud af fængsel!";
	}
	@Override
	public String playerGoTOJail(String name) {
		return "Spilleren " +name + " smides i fængsel";
	}
	@Override
	public String GetOkStart(String name) {
		return "Spilleren " +name + " passerer start og modtager bonus";
	}
	@Override
	public String askBuyHouse() {
		return "Vil du rykke eller købe hus?";
	}
	@Override
	public String move() {
		return "Ryk";
	}
	@Override
	public String buy() {
		return "Køb hus";
	}
	@Override
	public String buyHouseGroup() {
		return "Vil du rykke eller købe hus?";
	}
	@Override
	public String promptGroup() {
		return "Vælg hvilken gadegruppe du vil købe hus på:";
	}


}

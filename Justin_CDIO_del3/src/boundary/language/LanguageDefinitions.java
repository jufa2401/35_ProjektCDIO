  package boundary.language;

	 interface LanguageDefinitions {
	
	 String gameRules();
		
	 String getFieldRent(int rent);
	
	 String getFieldPrice(int price);

	 String getFieldDescription(int type);
	 	
	 String AskHowManyPlayer();

	 String AskForPlayerName();
	
	 String askBuyField();

	 String no();

	 String yes();

	 String getOkMove(String s);

	 String Ok();

	 String GameOver(String winner);

	 String playerPayTo(String payer, String recipient, int paid);
	 
	 String AskLoadGame();
	
	 String playerTax(String name, int paid);

	 String playerBonus(String name, int bonus);

	 String askPayTax();

	String askPayJail();

	String playerOutOfJail(String name);

	String playerGoTOJail(String name);

	String GetOkStart(String name);

	String badName();

	String askBuyHouse();

	String move();

	String buy();

	String buyHouseGroup();

	String promptGroup();
	
}
  package boundary.language;

	 interface LanguageDefinitions {
	
	 String gameRules();
	
	 String getFieldName(int index);
	
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
	
	

	 String playerTax(String name, int paid);

	 String playerBonus(String name, int bonus);

	 String askPayTax();
	
}
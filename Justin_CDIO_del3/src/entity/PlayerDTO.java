package entity;

import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.StreetDTO;

public class PlayerDTO {
	private String name;
	private int balance, currentField, d1, d2, Identifier, rounds_left_jail;
	private boolean hasLost;
	//private static int AvailableIdentifer = 0;
	/**
	 * Den private int anvendes til at give hver spiller et unikt id
	 * @param playerid 
	 * 
	 * @param name
	 * @param balance
	 * @param balance2 
	 */
	public PlayerDTO (int playerid, String name, int position, int balance, int rounds_left_jail){
		this.name = name;
		this.balance = balance;
		this.rounds_left_jail = rounds_left_jail;

		//	Disse variabler skal vel hentes fra databasen?

		hasLost = false;
		currentField = position;
		//		Identifier = AvailableIdentifer++;
		Identifier = playerid;
	}

	public PlayerDTO() {
		// TODO Auto-generated constructor stub
	}


	//getters and setters for navn og balance 
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 	 metode der tjekker om en spiller har tabt
	 * @return
	 */
	public boolean hasLost() {
		if (balance <= 0) {
			hasLost = true;
		}
		return hasLost;
	}
	// nedenstående metode bliver ikke brugt, og kan fjernes
	public void setHasLost(boolean hasLost) {
		this.hasLost = hasLost;
	}
	public int getBalance() {
		return balance;
	}

	/**
	 *  Vi laver metode til at gemme terningsummen, denne metode er lavet specifikt 
	 *  med hensyn til felter hvor aktionen er bestemt af terningsummen.
	 */
	public void SaveDiceRoll(DiceCup dice){
		int[] d = dice.getDiceValue();
		this.d1 = d[0];
		this.d2 = d[1];
	}
	public int getDiceSum() {
		return d1 + d2;
	}

	/**
	 * Metode til overførsel af penge
	 * Hvis balancen ryger under nul bliver balancen sat til 0
	 * Denne metode burde kunne laves med SQL procedure.
	 * @param amount
	 * @return
	 */


	public int Transaction(int amount){
		balance = balance + amount;
		if(balance < 0){
			balance = 0;
		}
		return balance;
	}	

	/**
	 * 	Giver muligheden for at betale til andre spillere, afslutter med at returnere den ændrede balance
	 *  Hvis en spiller lander på sit eget felt, trækker man i princippet fra sin egen konto, men de bliver tilført igen med det samme.
	 * @param recipient
	 * @param amount
	 * @return
	 */
	//	DATABASE
	public int payTo (PlayerDTO recipient, int amount) {
		recipient.Transaction(amount);
		Transaction(-amount);
		return balance;
	}

	//	Getters and setters for fieldnumber - DATABASE
	public int getCurrentField() {
		return currentField;
	}
	/**
	 * 	Når vi rykker os udover arraylængden ruller vi rundt ved at trække arraylængden fra.
	 * @param roll
	 * @param gb
	 * @return
	 */

	public int moveToField(int roll, GameBoardDTO gb) {
		int length = gb.getNumberOfFields();
		this.currentField += roll;
		while(this.currentField >= length) {
			this.currentField -= length;
			//			this.balance += gb.getStartBonus();
			Transaction(gb.getStartBonus());
		}
		return this.currentField;
	}
	//	Ny kode skal rettes
	public int moveToJail(int jailindex, GameBoardDTO gb) {
		this.currentField = jailindex;
		setRoundsLeftJail(3);
		return this.currentField;
	}

	public int getPlayerID() {
		return this.Identifier;
	}

	public int getRoundsLeftJail() {
		return rounds_left_jail;
	}
	public int setRoundsLeftJail(int n) {
		rounds_left_jail = n;
		return rounds_left_jail;
	}

	public boolean canBuyHouses(GameBoardDTO gb) {
		//	ineffektiv kode men programmet er ikke begrænset af hastighed
		int nfields = gb.getNumberOfFields();

		for (int i = 0; i<nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 5){
				StreetDTO sfield = (StreetDTO) field;
				if (sfield.checkStreetGroupOwned (this, gb)) {
					return true;
				}
			}
		}
		return false;
	}

	public int buyHouse(GameBoardDTO gb, String group) {
		int nfields = gb.getNumberOfFields();
		int fieldindex = -1;
		int housesonlastfield = 5;
		int housesonthisfield;
		for (int i = 0; i<nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 5){
				StreetDTO sfield = (StreetDTO) field;
				if(sfield.getGroup().equals(group)) {
					housesonthisfield = sfield.getHouses();
					if (housesonthisfield <= housesonlastfield) {
						fieldindex = i;
						housesonlastfield = housesonthisfield;
					}
				}
			}
		}
		if(fieldindex > 0) {
			StreetDTO sfield = (StreetDTO) gb.getField(fieldindex);
			int houseprice = sfield.getHousePrice();
			int nhouses = sfield.getHouses();
			if((balance > houseprice) && (nhouses < 4)) {
				Transaction(-houseprice);
				sfield.setHouses(nhouses+1);
			} else {
				fieldindex = -1;
			}
		}
		return fieldindex;
	}		


}







/*
 * 
 */
package model;

import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.StreetDTO;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse indeholder Spilleren, hans egenskaber, samt de metoder som viser
 * og ændrer spillerens værdier.
 *
 * @author Justin
 */
public class PlayerDTO {
	
	/** The name. */
	private String name;
	
	/** The balance, The position of player, dice1, dice2The rounds left jail. */
	private int balance, currentField, d1, d2, Identifier, rounds_left_jail;
	
	/** The has lost. */
	private boolean hasLost;

	/**
	 * Instantiates a new player DTO.
	 */
	public PlayerDTO() {
		// TODO Auto-generated constructor stub
	}

	// private static int AvailableIdentifer = 0;
	/**
	 * Den private int anvendes til at give hver spiller et unikt id.
	 *
	 * @param playerid the playerid
	 * @param name the name
	 * @param position the position
	 * @param balance the balance
	 * @param rounds_left_jail the rounds left jail
	 */
	public PlayerDTO(int playerid, String name, int position, int balance, int rounds_left_jail) {
		this.name = name;
		this.balance = balance;
		this.rounds_left_jail = rounds_left_jail;

		// Disse variabler skal vel hentes fra databasen?

		hasLost = false;
		currentField = position;
		// Identifier = AvailableIdentifer++;
		Identifier = playerid;
	}

	/**
	 * Buy house.
	 *
	 * @param gb the gb
	 * @param group the group
	 * @return the int
	 */
	public int buyHouse(GameBoardDTO gb, String group) {
		int nfields = gb.getNumberOfFields();
		int fieldindex = -1;
		int housesonlastfield = 5;
		int housesonthisfield;
		for (int i = 0; i < nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 5) {
				StreetDTO sfield = (StreetDTO) field;
				if (sfield.getGroup().equals(group)) {
					housesonthisfield = sfield.getHouses();
					if (housesonthisfield <= housesonlastfield) {
						fieldindex = i;
						housesonlastfield = housesonthisfield;
					}
				}
			}
		}
		if (fieldindex > 0) {
			StreetDTO sfield = (StreetDTO) gb.getField(fieldindex);
			int houseprice = sfield.getHousePrice();
			int nhouses = sfield.getHouses();
			if ((balance > houseprice) && (nhouses < 4)) {
				Transaction(-houseprice);
				sfield.setHouses(nhouses + 1);
			} else {
				fieldindex = -1;
			}
		}
		return fieldindex;
	}

	/**
	 * Can buy houses.
	 *
	 * @param gb the gb
	 * @return true, if successful
	 */
	public boolean canBuyHouses(GameBoardDTO gb) {
		// ineffektiv kode men programmet er ikke begrænset af hastighed
		int nfields = gb.getNumberOfFields();

		for (int i = 0; i < nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 5) {
				StreetDTO sfield = (StreetDTO) field;
				if (sfield.checkStreetGroupOwned(this, gb)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Gets the current field.
	 *
	 * @return the current field
	 */
	// Getters and setters for fieldnumber - DATABASE
	public int getCurrentField() {
		return currentField;
	}

	/**
	 * Gets the dice sum from player.
	 *
	 * @return the dice sum
	 */
	public int getDiceSum() {
		return d1 + d2;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// getters and setters for navn og balance
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the player ID.
	 *
	 * @return the player ID
	 */
	public int getPlayerID() {
		return this.Identifier;
	}

	/**
	 * Gets the rounds left jail.
	 *
	 * @return the rounds left jail
	 */
	public int getRoundsLeftJail() {
		return rounds_left_jail;
	}

	/**
	 * metode der tjekker om en spiller har tabt.
	 *
	 * @return true, if successful
	 */
	public boolean hasLost() {
		if (balance <= 0) {
			hasLost = true;
		}
		return hasLost;
	}

	/**
	 * Når vi rykker os udover arraylængden ruller vi rundt ved at trække
	 * arraylængden fra.
	 *
	 * @param roll the roll
	 * @param gb the gb
	 * @return the int
	 */

	public int moveToField(int roll, GameBoardDTO gb) {
		int length = gb.getNumberOfFields();
		this.currentField += roll;
		while (this.currentField >= length) {
			this.currentField -= length;
			// this.balance += gb.getStartBonus();
			Transaction(gb.getStartBonus());
		}
		return this.currentField;
	}

	/**
	 * Move to jail.
	 *
	 * @param jailindex the jailindex
	 * @param gb the gb
	 * @return the int
	 */
	// Ny kode skal rettes
	public int moveToJail(int jailindex, GameBoardDTO gb) {
		this.currentField = jailindex;
		setRoundsLeftJail(3);
		return this.currentField;
	}

	/**
	 * Giver muligheden for at betale til andre spillere, afslutter med at
	 * returnere den ændrede balance Hvis en spiller lander på sit eget felt,
	 * trækker man i princippet fra sin egen konto, men de bliver tilført igen
	 * med det samme.
	 *
	 * @param recipient the recipient
	 * @param amount the amount
	 * @return the int
	 */
	// DATABASE
	public int payTo(PlayerDTO recipient, int amount) {
		recipient.Transaction(amount);
		Transaction(-amount);
		return balance;
	}

	/**
	 * Vi laver metode til at gemme terningsummen, denne metode er lavet
	 * specifikt med hensyn til felter hvor aktionen er bestemt af
	 * terningsummen.
	 *
	 * @param dice the dice
	 */
	public void SaveDiceRoll(DiceCup dice) {
		int[] d = dice.getDiceValue();
		this.d1 = d[0];
		this.d2 = d[1];
	}

	/**
	 * Sets the checks for lost.
	 *
	 * @param hasLost the new checks for lost
	 */
	// nedenstående metode bliver ikke brugt, og kan fjernes
	public void setHasLost(boolean hasLost) {
		this.hasLost = hasLost;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the rounds left jail.
	 *
	 * @param n the n
	 * @return the int
	 */
	public int setRoundsLeftJail(int n) {
		rounds_left_jail = n;
		return rounds_left_jail;
	}

	/**
	 * Metode til overførsel af penge Hvis balancen ryger under nul bliver
	 * balancen sat til 0 Denne metode burde kunne laves med SQL procedure.
	 *
	 * @param amount the amount
	 * @return the int
	 */

	public int Transaction(int amount) {
		balance = balance + amount;
		if (balance < 0) {
			balance = 0;
		}
		return balance;
	}

}

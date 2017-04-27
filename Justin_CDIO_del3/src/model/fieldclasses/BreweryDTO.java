/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class BreweryDTO.
 */
public class BreweryDTO extends Ownable {
	
	/** The rent 2. */
	int rent_1, rent_2;

	/**
	 * Instantiates a new brewery DTO.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 * @param price the price
	 * @param rent_1 the rent 1
	 * @param rent_2 the rent 2
	 */
	public BreweryDTO(int fieldNumber, String name, Color color, int price, int rent_1, int rent_2) {
		super(fieldNumber, name, color, price);
		this.rent_1 = rent_1;
		this.rent_2 = rent_2;
	}

	/**
	 * Superklassens metode til at købe feltet genbruges derudover registreres
	 * det at denne spiller har købt endnu en LaborCamps, da dette skal bruges
	 * til at beregne leje.
	 *
	 * @param player the player
	 */
	@Override
	public void buyField(PlayerDTO player) {
		super.buyField(player);
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.Ownable#getNumberOwned(entity.PlayerDTO, entity.GameBoardDTO)
	 */
	@Override
	public int getNumberOwned(PlayerDTO p, GameBoardDTO gb) {
		int nfields = gb.getNumberOfFields();
		int BreweriesOwned = 0;
		for (int i = 0; i < nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 2) {
				BreweryDTO bfield = (BreweryDTO) field;
				if (bfield.getOwner() == p) {
					++BreweriesOwned;
				}
			}
		}
		return BreweriesOwned;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getPrice()
	 */
	@Override
	public int getPrice() {
		return this.price;
	}

	/**
	 * Returnerer unik id, som identificerer denne klasse som Labor Camp.
	 *
	 * @return the type
	 */
	@Override
	public int getType() {
		return 2; // Labor Camp
	}

	/**
	 * Hvis man lander på en LaborCamp som er ejet, skal man betale en variabel
	 * leje til ejeren. Lejen er bestemt efter hvor mange af denne type ejendom
	 * man ejer dvs. at du kan opkræve højere leje, jo flere 'labor camps' du
	 * ejer og spillerens terningkast Når det er bestemt bliver det betalt
	 * metoden returnerer det betalte beløb, som bruges til at tælle balancen
	 * ned i GUIen
	 *
	 * @param player the player
	 * @param gb the gb
	 * @return the int
	 */
	@Override
	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		int rent = 0;
		if (this.owner != null) {
			int BreweriesOwned = getNumberOwned(this.owner, gb);
			rent = (BreweriesOwned > 1) ? rent_2 * player.getDiceSum() : rent_1 * player.getDiceSum();
			player.payTo(this.owner, rent);
		}
		return rent;

	}
}

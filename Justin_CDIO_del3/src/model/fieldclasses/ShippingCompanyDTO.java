/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/* Denne type felt har en pris,
 * som kan betales for ejerskab.
 */

/**
 * The Class ShippingCompanyDTO.
 *
 * @author Justin
 */

public class ShippingCompanyDTO extends Ownable {

	/** The Shipping companys owned. */
	int ShippingCompanysOwned;

	/** The rent. */
	int[] rent = new int[5];

	/**
	 * Constructor til ShippingCompany felt objekter/instanser.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 * @param price the price
	 * @param rent_1 the rent 1
	 * @param rent_2 the rent 2
	 * @param rent_3 the rent 3
	 * @param rent_4 the rent 4
	 */

	public ShippingCompanyDTO(int fieldNumber, String name, Color color, int price, int rent_1, int rent_2, int rent_3,
			int rent_4) {
		super(fieldNumber, name, color, price);
		rent[0] = 0;
		rent[1] = rent_1;
		rent[2] = rent_2;
		rent[3] = rent_3;
		rent[4] = rent_4;

	}

	/**
	 * Superklassens metode til at købe feltet genbruges derudover registreres
	 * det at denne spiller har købt endnu en ShippingCompany, da dette skal
	 * bruges til at beregne leje.
	 *
	 * @param player the player
	 */
	@Override
	public void buyField(PlayerDTO player) {
		super.buyField(player);
		// player.setShippingCompanysOwned(1+player.getShippingCompanysOwned());
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.Ownable#getNumberOwned(entity.PlayerDTO, entity.GameBoardDTO)
	 */
	@Override
	public int getNumberOwned(PlayerDTO p, GameBoardDTO gb) {
		int nfields = gb.getNumberOfFields();
		int ShippingCompanysOwnedByOwner = 0;

		for (int i = 0; i < nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 1) {
				ShippingCompanyDTO sfield = (ShippingCompanyDTO) field;
				if (sfield.getOwner() == p) {
					++ShippingCompanysOwnedByOwner;
				}
			}
		}

		return ShippingCompanysOwnedByOwner;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getPrice()
	 */
	@Override
	public int getPrice() {
		return this.price;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getRent()
	 */
	@Override
	public int getRent() {
		return 0;
	}

	/**
	 * Returnerer unik id, som identificerer denne klasse som ShippingCompany.
	 *
	 * @return the type
	 */
	@Override
	public int getType() {
		return 1; // ShippingCompany
	}

	/**
	 * Hvis man lander på en ShippingCompany som er ejet, skal man betale en
	 * variabel leje til ejeren. Lejen er bestemt efter hvor mange af denne type
	 * ejendom man eer dvs. at du kan opkræve højere leje, jo flere
	 * 'ShippingCompanys' du ejer Når det er bestemt bliver det betalt metoden
	 * returnerer det betalte beløb, som bruges til at tælle balancen ned i
	 * GUIen
	 *
	 * @param player the player
	 * @param gb the gb
	 * @return the int
	 */

	@Override
	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		int paid = 0;
		if (this.owner != null) {
			ShippingCompanysOwned = getNumberOwned(this.owner, gb);
			int r = rent[ShippingCompanysOwned];
			player.payTo(this.owner, r);
			paid = r;
		}
		return paid;
	}

}

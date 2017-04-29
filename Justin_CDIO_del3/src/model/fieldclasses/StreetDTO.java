/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * Denne type felt har en pris, som kan betales for ejerskab. Hvis man lander på
 * en street som er ejet, skal man betale en fast leje til ejeren.
 * 
 */
public class StreetDTO extends Ownable {
	
	/** The rent. */
	private int[] rent = new int[6];

	/** The houseprice. */
	private int houses, hotels, houseprice;
	
	/** The group. */
	String group;

	/**
	 * Constructor til street felter.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param streetgroup the streetgroup
	 * @param color the color
	 * @param price the price
	 * @param rent the rent
	 * @param rent_1 the rent 1
	 * @param rent_2 the rent 2
	 * @param rent_3 the rent 3
	 * @param rent_4 the rent 4
	 * @param houseprice the houseprice
	 */
	public StreetDTO(int fieldNumber, String name, String streetgroup, Color color, int price, int rent, int rent_1,
			int rent_2, int rent_3, int rent_4, int houseprice) {
		super(fieldNumber, name, color, price);
		this.rent[0] = rent;
		this.rent[1] = rent_1;
		this.rent[2] = rent_2;
		this.rent[3] = rent_3;
		this.rent[4] = rent_4;
		this.rent[5] = 0;
		this.houseprice = houseprice;
		this.group = streetgroup;
	}

	public StreetDTO(int id, String name, String streetgroup, Color color, int price, int[] rent, int houseprice) {
		super(id, name, color, price);
		this.rent[0] = rent[0];
		this.rent[1] = rent[1];
		this.rent[2] = rent[2];
		this.rent[3] = rent[3];
		this.rent[4] = rent[4];
		this.rent[5] = 0;
		this.houseprice = houseprice;
		this.group = streetgroup;
	}

	/**
	 * Check street group owned.
	 *
	 * @param p the p
	 * @param gb the gb
	 * @return true, if successful
	 */
	// Bliver aldrig kaldt, er til at checke om man ejer en gadegruppe
	public boolean checkStreetGroupOwned(PlayerDTO p, GameBoardDTO gb) {
		int nfields = gb.getNumberOfFields();
		int ngroup = 0;
		int nowned = 0;

		for (int i = 0; i < nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 5) {
				StreetDTO sfield = (StreetDTO) field;
				if (sfield.getGroup().equals(group)) {
					++ngroup;
					if (sfield.getOwner() == p) {
						++nowned;
					}
				}
			}
		}
		return (ngroup == nowned) ? true : false;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Gets the hotels.
	 *
	 * @return the hotels
	 */
	public int getHotels() {
		return hotels;
	}

	/**
	 * Gets the house price.
	 *
	 * @return the house price
	 */
	public int getHousePrice() {
		return houseprice;
	}

	/**
	 * Gets the houses.
	 *
	 * @return the houses
	 */
	public int getHouses() {
		return houses;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getPrice()
	 */
	@Override
	public int getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getRent()
	 */
	@Override
	public int getRent() {
		return rent[houses];
	}

	/**
	 * Returnerer unik id, som identificerer denne klasse som territorry.
	 *
	 * @return the type
	 */
	@Override
	public int getType() {
		return 5;
	}

	/**
	 * Method landOnField checker om der er ejer på et felt, hvis der er ejer på
	 * et felt, betales der til ejeren.
	 *
	 * @param player the player
	 * @param gb the gb
	 * @return Beløb betalt til felt ejer
	 */
	@Override
	public int landOnField(PlayerDTO player) {
		int paid = 0;
		if (this.owner != null) {
			player.payTo(this.owner, rent[houses]);
			paid = rent[houses];
			return paid;
		} else {
		}
		return paid;
	}

	/**
	 * Sets the hotels.
	 *
	 * @param hotels the new hotels
	 */
	public void setHotels(int hotels) {
		this.hotels = hotels;
	}

	/**
	 * Sets the houses.
	 *
	 * @param houses the new houses
	 */
	public void setHouses(int houses) {
		this.houses = houses;
	}

	@Override
	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		// TODO Auto-generated method stub
		return 0;
	}

}

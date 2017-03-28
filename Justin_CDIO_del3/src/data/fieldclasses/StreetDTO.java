package data.fieldclasses;
//ss
import java.awt.Color;

import data.PlayerDTO;
/**
 * Denne type felt har en pris,
 * som kan betales for ejerskab.
 * Hvis man lander på en LaborCamp som er ejet,
 * skal man betale en fast leje til ejeren.
 * 
 */
public class StreetDTO extends Ownable{
//	Streetsowned skal bruges!
	int rent, StreetsOwned;
	/** Constructor til Territory felter
	 * @param fieldNumber
	 * @param color
	 * @param price
	 * @param rent
	 */
	public StreetDTO(int fieldNumber, Color color, int price, int rent) {
		super(fieldNumber, color, price);
		this.rent = rent;	
	}
	public int getPrice() {
		return this.price;
	}

	public int getRent() {
		return rent;
	}
	/**
	 * Method landOnField checker om der er ejer på et felt,
	 * hvis der er ejer på et felt, betales der til ejeren. 
	 * @return Belæøb betalt til felt ejer 
	 */
	@Override
	public int landOnField(PlayerDTO player) {
		int paid = 0;
		if (this.owner != null){
			player.payTo(this.owner, rent);
			paid = rent;
		}
		return paid;
	}
/**
 * Returnerer unik id, 
 * som identificerer denne klasse som territorry
 */
	@Override
	public int getType() {
		return 5; 
	}
}

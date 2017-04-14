package entity.fieldclasses;
//ss
import java.awt.Color;

import entity.GameBoardDTO;
import entity.PlayerDTO;
/**
 * Denne type felt har en pris,
 * som kan betales for ejerskab.
 * Hvis man lander på en street som er ejet,
 * skal man betale en fast leje til ejeren.
 * 
 */
public class StreetDTO extends Ownable{

	int rent, houses, hotels, group;
	/** Constructor til Territory felter
	 * @param fieldNumber
	 * @param color
	 * @param price
	 * @param rent
	 * @param streetgroup 
	 */
	public StreetDTO(int fieldNumber, String name, Color color, int price, int rent, int streetgroup) {
		super(fieldNumber, name, color, price);
		this.rent = rent;
		this.group = streetgroup;
	}
	public int getPrice() {
		return this.price;
	}
	public int getRent() {
		return rent;
	}
	public int getHouses() {
		return houses;
	}
	public int getHotels() {
		return hotels;
	}
	/**
	 * Method landOnField checker om der er ejer på et felt,
	 * hvis der er ejer på et felt, betales der til ejeren. 
	 * @return Beløb betalt til felt ejer 
	 */
	@Override
	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		int paid = 0;
		if (this.owner != null){
			player.payTo(this.owner, rent);
			paid = rent;
			return paid;
		}
		else {
		}
		return paid;
	}	
	public boolean checkStreetGroupOwned (PlayerDTO p, GameBoardDTO gb) {
		int nfields = gb.getNumberOfFields();
		int ngroup = 0;
		int nowned = 0;

		for (int i = 0; i<nfields; i++) {
			FieldDTO field = gb.getField(i);
			if (field.getType() == 5){
				StreetDTO sfield = (StreetDTO) field;
				if (sfield.getGroup() == group) {
					++ngroup;
					if (sfield.getOwner() == p) {
						++nowned;
					}
				}
			}
		}
		return (ngroup == nowned)?true:false;
	}


	/**
	 * Returnerer unik id, 
	 * som identificerer denne klasse som territorry
	 */
	@Override
	public int getType() {
		return 5; 
	}
	public int getGroup() {
		return group;
	}
	public void setHouses(int houses2) {
		// TODO Auto-generated method stub
		
	}

}

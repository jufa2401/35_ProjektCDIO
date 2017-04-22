package entity.fieldclasses;
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
	private int [] rent = new int [6];
	
	private int houses, hotels, houseprice;
	String group;
	/** Constructor til street felter
	 * @param fieldNumber
	 * @param color
	 * @param price
	 * @param rent
	 * @param streetgroup 
	 */
	public StreetDTO(int fieldNumber, String name, String streetgroup, Color color, int price, int rent, int rent_1, int rent_2, int houseprice) {
		super(fieldNumber, name, color, price);
		this.rent[0] = rent;
		this.rent[1] = rent_1;
		this.rent[2] = rent_2;
		this.rent[3] = 0;
		this.rent[4] = 0;
		this.rent[5] = 0;
		this.houseprice = houseprice;
		this.group = streetgroup;
	}
	public int getPrice() {
		return price;
	}
	public int getRent(int i) {
		return rent[i];
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
//			TODO: Skal udvides til at chekke forskellige lejer
			player.payTo(this.owner, rent[0]);
			paid = rent[0];
			return paid;
		}
		else {
		}
		return paid;
	}	
//	Bliver aldrig kaldt, er til at checke om man ejer en gadegruppe
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
	public String getGroup() {
		return group;
	}
	public void setHouses(int houses) {
	
	}
	public void setHotels(int hotels) {
		
	}
	public int getHousePrice() {
		return houseprice;
	}

}

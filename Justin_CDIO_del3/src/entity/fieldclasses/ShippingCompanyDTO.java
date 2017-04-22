package entity.fieldclasses;
import java.awt.Color;

import entity.GameBoardDTO;
import entity.PlayerDTO;

/* Denne type felt har en pris,
 * som kan betales for ejerskab.
 */

/**
 * @author Justin
 *
 */

public class ShippingCompanyDTO extends Ownable {

	int ShippingCompanysOwned;
	
	int[] rent = new int[5];

	/**
	 * Constructor til ShippingCompany felt objekter/instanser. 
	 * @param fieldNumber
	 * @param color
	 * @param price
	 */

	public ShippingCompanyDTO(int fieldNumber, String name,  Color color, int price, int rent_1, int rent_2, int rent_3, int rent_4) {
		super(fieldNumber,name, color, price);
		rent[0] = 0;
		rent[1] = rent_1;
		rent[2] = rent_2;
		rent[3] = rent_3;
		rent[4] = rent_4;
		
	}
	
	public int getNumberOwned(PlayerDTO p, GameBoardDTO gb) {
		int nfields = gb.getNumberOfFields();
		int ShippingCompanysOwnedByOwner = 0;
		
		for (int i = 0; i<nfields; i++) {
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
	

	
/**
 *  Hvis man lander på en ShippingCompany som er ejet,
 * skal man betale en variabel leje til ejeren.
 * Lejen er bestemt efter hvor mange af denne type ejendom man eer
 * dvs. at du kan opkræve højere leje, jo flere 'ShippingCompanys' du ejer
 * Når det er bestemt bliver det betalt
 * metoden returnerer det betalte beløb, som bruges til at tælle
 * balancen ned i GUIen
 */
	
	
	@Override
	public int landOnField(PlayerDTO player, GameBoardDTO gb)  {
		int paid = 0;
		if (this.owner != null){
			ShippingCompanysOwned = getNumberOwned(this.owner, gb);
			int r = rent[ShippingCompanysOwned];
			player.payTo(this.owner, r);
			paid = r;
		}
		return paid;		
	}
/**
 * Superklassens metode til at købe feltet genbruges
 * derudover registreres det at denne spiller har købt 
 * endnu en ShippingCompany, da dette skal bruges til at beregne leje
 */
	@Override
	public void buyField(PlayerDTO player) {
		super.buyField(player);
//		player.setShippingCompanysOwned(1+player.getShippingCompanysOwned());
	}


	@Override
	public int getRent() {
		return 0;
	}

	@Override
	public int getPrice() {
		return this.price;
	}
	/**
	 * Returnerer unik id, 
	 * som identificerer denne klasse som ShippingCompany
	 */
	@Override
	public int getType() {
		return 1;	// ShippingCompany
	}

}

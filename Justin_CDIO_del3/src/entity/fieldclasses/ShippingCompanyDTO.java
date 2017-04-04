package entity.fieldclasses;
import java.awt.Color;

import entity.PlayerDTO;

/* Denne type felt har en pris,
 * som kan betales for ejerskab.
 */

/**
 * @author Justin
 *
 */

public class ShippingCompanyDTO extends Ownable {

	int rent, ShippingCompanysOwned;

	/**
	 * Constructor til ShippingCompany felt objekter/instanser. 
	 * @param fieldNumber
	 * @param color
	 * @param price
	 */

	public ShippingCompanyDTO(int fieldNumber, Color color, int price) {
		super(fieldNumber, color, price);
	}
/**
 *  Hvis man lander på en ShippingCompany som er ejet,
 * skal man betale en variabel leje til ejeren.
 * Lejen er bestemt efter hvor mange af denne type ejendom man ejer
 * dvs. at du kan opkræve højere leje, jo flere 'ShippingCompanys' du ejer
 * Når det er bestemt bliver det betalt
 * metoden returnerer det betalte beløb, som bruges til at tælle
 * balancen ned i GUIen
 */
	@Override
	public int landOnField(PlayerDTO player)  {
		int paid = 0;
		if (this.owner != null){
			ShippingCompanysOwned = this.owner.getShippingCompanysOwned();
			switch (ShippingCompanysOwned) {
			case 1: rent = 500;		break;
			case 2: rent = 1000;	break;
			case 3: rent = 2000;	break;
			case 4: rent = 4000;	break;
			default: break;
			}
			player.payTo(this.owner, rent);
			paid = rent;

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
		player.setShippingCompanysOwned(1+player.getShippingCompanysOwned());
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

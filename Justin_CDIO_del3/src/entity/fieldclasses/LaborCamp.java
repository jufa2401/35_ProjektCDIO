package entity.fieldclasses;
import java.awt.Color;

/* Denne type felt har en pris,
 * som kan betales for ejerskab.

 */
import entity.Player;
public class LaborCamp extends Ownable {
	int rent, LaborCampsOwned;

	/**Constructor til LaborCamp felt.
	 * @param fieldNumber
	 * @param color
	 * @param price
	 */
	public LaborCamp(int fieldNumber, Color color, int price) {
		super(fieldNumber, color, price);
	}

	/**
	 *  Hvis man lander på en LaborCamp som er ejet,
	 * skal man betale en variabel leje til ejeren.
	 * Lejen er bestemt efter hvor mange af denne type ejendom man ejer
	 * dvs. at du kan opkræve højere leje, jo flere 'labor camps' du ejer
	 * og spillerens terningkast
	 * Når det er bestemt bliver det betalt
	 * metoden returnerer det betalte beløb, som bruges til at tælle
	 * balancen ned i GUIen
	 */
	@Override
	public int landOnField(Player player) {
		int rent = 0;
		if (this.owner != null){
			LaborCampsOwned = this.owner.getLaborCampsOwned(); 
			rent = LaborCampsOwned * 100*player.getDiceSum();			
			}
		player.payTo(this.owner, rent); 
		return rent;		
	}
	
	/**
	 * Superklassens metode til at købe feltet genbruges
	 * derudover registreres det at denne spiller har købt 
	 * endnu en LaborCamps, da dette skal bruges til at beregne leje
	 */
	@Override
	public void buyField(Player player) {
		super.buyField(player);
		player.setLaborCampsOwned(1+player.getLaborCampsOwned());
	}


	
	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public int getRent() {
		// Kan evt. udvides til at returnere 100 gange terningkast
		return 0;
	}

	/**
	 * Returnerer unik id, 
	 * som identificerer denne klasse som Labor Camp
	 */
	@Override
	public int getType() {
		return 2;	// Labor Camp
	}
}

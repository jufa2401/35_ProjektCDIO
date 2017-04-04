package entity;
import java.awt.Color;
import java.lang.reflect.Array;

import entity.fieldclasses.BreweryDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;
import entity.fieldclasses.RefugeDTO;
import entity.fieldclasses.ShippingCompanyDTO;
import entity.fieldclasses.StreetDTO;
import entity.fieldclasses.Tax;
;
public class GameBoardDTO {
	private static FieldDTO[] field;


	public GameBoardDTO() {
		GameBoardDTO.field = new FieldDTO[21];

//		Territory parametre: id, feltfarve, feltpris, leje
		field[0] = new StreetDTO(0, Color.RED, 1000, 100);
		field[1] = new StreetDTO(1, Color.RED, 1500, 300);
//		Refuge parametre: id, feltfarve, bonus 
		field[2] = new RefugeDTO(2, Color.CYAN, 5000);
//		ShippingCompany parametre: id, pris
		field[3] = new ShippingCompanyDTO(3, Color.BLUE, 4000);
		field[4] = new StreetDTO(4, Color.RED, 2000, 500);
		field[5] = new StreetDTO(5, Color.RED, 3000, 700);
		field[6] = new BreweryDTO(6, Color.GREEN, 2500);
//		Tax parametre: id, feltfarve, fast skattebeløb, skatte pct.
		field[7] = new Tax(7, Color.YELLOW, 2000, 0);
		field[8] = new StreetDTO(8, Color.RED, 4000, 1000);
		field[9] = new StreetDTO(9, Color.RED, 4300, 1300);
		field[10] = new ShippingCompanyDTO(10, Color.BLUE, 4000);
		field[11] = new BreweryDTO(11, Color.GREEN, 2500);
		field[12] = new StreetDTO(12, Color.RED, 4750, 1600);
		field[13] = new StreetDTO(13, Color.RED, 5000, 2000);
		field[14] = new RefugeDTO(14, Color.CYAN, 500);
		field[15] = new ShippingCompanyDTO(15, Color.BLUE, 4000);
		field[16] = new StreetDTO(16, Color.RED, 5500, 2600);
		field[17] = new StreetDTO(17, Color.RED, 6000, 3200);
		field[18] = new Tax(18, Color.YELLOW, 4000, 10);
		field[19] = new StreetDTO(19, Color.RED, 8000, 4000);	
		field[20] = new ShippingCompanyDTO(20, Color.BLUE, 4000);
	}	
	public int getNumberOfFields () {
		return Array.getLength(field);
	}
	public Color getFieldColor(int index) {
		return field[index].getColor();
	}
	public int getFieldRent(int index) {
		return field[index].getRent();
	}
	public int getFieldPrice(int index) {
		return field[index].getPrice();
	}
	public int getFieldType(int index) {
		return field[index].getType();
	}

	public FieldDTO getField(int i ) {
		return field[i];
	}
/**
 *  Metode til at finde et felts index ved at bruge felt id, det bliver aldrig brugt.
 * @param f
 * @return
 */
	public int getFieldNumber(FieldDTO f) {
		int i;
		for (i = 0; i< getNumberOfFields(); i++){
			if (f.getID() == field[i].getID())
				break;
		}
		return i;
	}

/**
 * 	Metode til at fjerne ejerskab af ejendomme, når spiller dør
 * @param n
 * @param player
 * @return
 */
	public boolean removeFieldOwner(int n, PlayerDTO player) {
		boolean freed = false;
		if (field[n].getPrice() > 0) {	// hvis feltet kan ejes
			Ownable ofield = (Ownable) field[n]; 
			PlayerDTO owner = ofield.getOwner();
			if (owner == player) {	
				ofield.setOwner(null);
				freed = true;
			}
		}
		return freed;
	}
}











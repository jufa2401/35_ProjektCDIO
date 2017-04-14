package entity;
import java.awt.Color;
import java.lang.reflect.Array;

import boundary.dao.FieldDAO;
import daoimplementation.MySQLFieldDAO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;
import entity.fieldclasses.StartDTO;
public class GameBoardDTO {
	private static FieldDTO[] field;
	FieldDAO fielddao = new MySQLFieldDAO();
	//	StreetDAO dao = new MySQLStreetDAO();
	//	ArrayList<StreetDTO> streetlist= dao.getStreet();
	public GameBoardDTO() {
		int fieldcount = fielddao.getFieldCount();

		GameBoardDTO.field = new FieldDTO[fieldcount];

		for(int index = 0; index < fieldcount; index++) {

			field[index] = fielddao.getField(index);
		}
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

	public FieldDTO getField(int index ) {
		return field[index];
	}
	public String getFieldName(int index) {
		// TODO Auto-generated method stub
		return field[index].getName();
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
	public int getStartBonus() {
		StartDTO rfield = (StartDTO) field[0];
		return rfield.getBonus();
	}
	
}











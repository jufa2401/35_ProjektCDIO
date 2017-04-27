/*
 * 
 */
package model.fieldclasses;

import java.awt.Color; 
import java.lang.reflect.Array;
import java.util.ArrayList;

import model.PlayerDTO;
import model.daoboundary.FieldDAO;
import model.daoimplementation.MySQLFieldDAO;

// TODO: Auto-generated Javadoc
/**
 * Spillebrætsklassen indeholder især metoder som løber igennem felter.
 *
 * @author Justin
 */
public class GameBoardDTO {
	
	/** The field. */
	private static FieldDTO[] field;
	
	/** The fielddao. */
	FieldDAO fielddao = new MySQLFieldDAO();

	/**
	 * Instantiates a new GameBoardDTO.
	 */
	public GameBoardDTO() {
		int fieldcount = fielddao.getFieldCount();

		GameBoardDTO.field = new FieldDTO[fieldcount];

		for (int index = 0; index < fieldcount; index++) {

			field[index] = fielddao.getField(index);
		}
	}

	/**
	 * Gets the field.
	 *
	 * @param index the index
	 * @return the field
	 */
	public FieldDTO getField(int index) {
		return field[index];
	}

	/**
	 * Gets the field color.
	 *
	 * @param index the index
	 * @return the field color
	 */
	public Color getFieldColor(int index) {
		return field[index].getColor();
	}

	/**
	 * Gets the field name.
	 *
	 * @param index the index
	 * @return the field name
	 */
	public String getFieldName(int index) {
		// TODO Auto-generated method stub
		return field[index].getName();
	}

	/**
	 * Metode til at finde et felts index ved at bruge felt id, det bliver
	 * aldrig brugt.
	 *
	 * @param f the f
	 * @return the field number
	 */
	public int getFieldNumber(FieldDTO f) {
		int i;
		for (i = 0; i < getNumberOfFields(); i++) {
			if (f.getID() == field[i].getID())
				break;
		}
		return i;
	}

	/**
	 * Gets the field price.
	 *
	 * @param index the index
	 * @return the field price
	 */
	public int getFieldPrice(int index) {
		return field[index].getPrice();
	}

	/**
	 * Gets the field rent.
	 *
	 * @param index the index
	 * @return the field rent
	 */
	public int getFieldRent(int index) {
		return field[index].getRent();
	}

	/**
	 * Gets the field type.
	 *
	 * @param index the index
	 * @return the field type
	 */
	public int getFieldType(int index) {
		return field[index].getType();
	}

	/**
	 * Gets the groups.
	 *
	 * @return the groups
	 */
	public ArrayList<String> getGroups() {
		int nfields = getNumberOfFields();
		int ngroup = 0;
		ArrayList<String> groups = new ArrayList<String>();
		String lastgroup = "";
		for (int i = 0; i < nfields; i++) {
			FieldDTO field = getField(i);
			if (field.getType() == 5) {
				StreetDTO sfield = (StreetDTO) field;
				String group = sfield.getGroup();
				if (!lastgroup.equals(group)) {
					groups.add(ngroup, group);
					++ngroup;
					lastgroup = group;
				}
			}

		}
		return groups;

	}

	/**
	 * Gets the groups owned by.
	 *
	 * @param p the p
	 * @return the groups owned by
	 */
	public ArrayList<String> getGroupsOwnedBy(PlayerDTO p) {
		int nfields = getNumberOfFields();
		int ngroup = 0;
		ArrayList<String> groups = new ArrayList<String>();
		String lastgroup = "";
		for (int i = 0; i < nfields; i++) {
			FieldDTO field = getField(i);
			if (field.getType() == 5) {
				StreetDTO sfield = (StreetDTO) field;
				if (sfield.getOwner() == p) {
					String group = sfield.getGroup();
					if (sfield.checkStreetGroupOwned(p, this)) {
						if (!lastgroup.equals(group)) {
							groups.add(ngroup, group + " (" + sfield.getHousePrice() + ")");
							++ngroup;
							lastgroup = group;
						}
					}
				}
			}

		}
		return groups;
	}

	/**
	 * Gets the number of fields.
	 *
	 * @return the number of fields
	 */
	public int getNumberOfFields() {
		return Array.getLength(field);
	}

	/**
	 * Gets the start bonus.
	 *
	 * @return the start bonus
	 */
	public int getStartBonus() {
		StartDTO rfield = (StartDTO) field[0];
		return rfield.getBonus();
	}

	/**
	 * Metode til at fjerne ejerskab af ejendomme, når spiller dør.
	 *
	 * @param n the n
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean removeFieldOwner(int n, PlayerDTO player) {
		boolean freed = false;
		if (field[n].getPrice() > 0) { // hvis feltet kan ejes
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

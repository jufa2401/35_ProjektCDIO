/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class JailDTO.
 */
public class JailDTO extends FieldDTO {

	/** The jailindex. */
	private static int jailindex = -1;

	/**
	 * Instantiates a new jail DTO.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 */
	public JailDTO(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);
		if (jailindex < 0) {
			jailindex = fieldNumber;
		}

	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 6;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#landOnField(entity.PlayerDTO, entity.GameBoardDTO)
	 */
	@Override
	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		int currentfield = this.getID();
		// hvis det er go to jail, bliver du smidt i fængsel
		if (currentfield != jailindex) {
			currentfield = player.moveToJail(jailindex, gb);
		}
		return currentfield;
	}

}

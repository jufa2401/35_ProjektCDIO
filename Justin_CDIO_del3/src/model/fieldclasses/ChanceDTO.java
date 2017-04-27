/*
 * 
 */
package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ChanceDTO.
 */
public class ChanceDTO extends FieldDTO {

	/**
	 * Instantiates a new chance DTO.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 */
	public ChanceDTO(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 7;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#landOnField(entity.PlayerDTO)
	 */
	@Override
	public int landOnField(PlayerDTO player) {
		// TODO Auto-generated method stub
		return 0;
	}

}

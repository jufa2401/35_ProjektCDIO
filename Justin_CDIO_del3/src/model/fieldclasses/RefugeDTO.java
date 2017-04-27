package model.fieldclasses;

import java.awt.Color;

import model.PlayerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class RefugeDTO.
 */
public class RefugeDTO extends FieldDTO {

	/**
	 * Instantiates a new refuge DTO.
	 *
	 * @param fieldNumber the field number
	 * @param name the name
	 * @param color the color
	 */
	public RefugeDTO(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);

	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 8;
	}

	/* (non-Javadoc)
	 * @see entity.fieldclasses.FieldDTO#landOnField(entity.PlayerDTO)
	 */
	@Override
	public int landOnField(PlayerDTO player) {

		return 0;
	}

}

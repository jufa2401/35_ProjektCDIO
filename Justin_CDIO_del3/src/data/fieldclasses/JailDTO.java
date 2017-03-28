package data.fieldclasses;

import java.awt.Color;

import data.PlayerDTO;

public class JailDTO extends FieldDTO {

	public JailDTO(int fieldNumber, Color color) {
		super(fieldNumber, color);
		
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public int landOnField(PlayerDTO player) {
		
		return 0;
	}

}

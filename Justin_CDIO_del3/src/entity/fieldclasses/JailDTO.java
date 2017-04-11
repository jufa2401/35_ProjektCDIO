package entity.fieldclasses;

import java.awt.Color;

import entity.PlayerDTO;

public class JailDTO extends FieldDTO {

	public JailDTO(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);
		
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

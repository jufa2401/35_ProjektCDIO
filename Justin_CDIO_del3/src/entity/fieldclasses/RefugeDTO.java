package entity.fieldclasses;

import java.awt.Color;

import entity.PlayerDTO;

public class RefugeDTO extends FieldDTO {

	public RefugeDTO(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);
		
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public int landOnField(PlayerDTO player) {
		
		return 0;
	}

}

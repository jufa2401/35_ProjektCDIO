package entity.fieldclasses;

import java.awt.Color;

import entity.PlayerDTO;

public class GoToJail extends FieldDTO {

	public GoToJail(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int landOnField(PlayerDTO player) {
		// TODO Auto-generated method stub
		return 0;
	}

}

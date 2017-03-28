package data.fieldclasses;

import java.awt.Color;

import data.PlayerDTO;

public class GoToJail extends FieldDTO {

	public GoToJail(int fieldNumber, Color color) {
		super(fieldNumber, color);
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

package entity.fieldclasses;

import java.awt.Color;

import entity.Player;

public class Jail extends Field {

	public Jail(int fieldNumber, Color color) {
		super(fieldNumber, color);
		
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public int landOnField(Player player) {
		
		return 0;
	}

}

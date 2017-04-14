package entity.fieldclasses;

import java.awt.Color;

import entity.GameBoardDTO;
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

	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		int currentfield = player.moveToJail(10, gb);
		return currentfield;
	}
	

}

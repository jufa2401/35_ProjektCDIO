package entity.fieldclasses;

import java.awt.Color;

import entity.GameBoardDTO;
import entity.PlayerDTO;

public class JailDTO extends FieldDTO {

	private static int jailindex = -1;

	public JailDTO(int fieldNumber, String name, Color color) {
		super(fieldNumber, name, color);
		if (jailindex  < 0)  {
			jailindex = fieldNumber;
		}

	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 6;
	}

	public int landOnField(PlayerDTO player, GameBoardDTO gb) {
		int currentfield = this.getID();
		// hvis det er go to jail, bliver du smidt i fængsel	
		if(currentfield != jailindex) {
			currentfield = player.moveToJail(jailindex, gb);
		}
		return currentfield;	
	}


}

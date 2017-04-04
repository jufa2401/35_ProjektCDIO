package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.ChanceDTO;
public interface ChanceDAO {
	public ArrayList<ChanceDTO> getChance() throws RuntimeException;
}

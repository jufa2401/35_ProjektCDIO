package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.StartDTO;
public interface RefugeDAO {
	public ArrayList<StartDTO> getRefuge() throws RuntimeException;

}

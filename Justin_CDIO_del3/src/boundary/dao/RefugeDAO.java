package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.RefugeDTO;
public interface RefugeDAO {
	public ArrayList<RefugeDTO> getRefuge() throws RuntimeException;

}

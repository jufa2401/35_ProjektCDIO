package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.StreetDTO;
public interface StreetDAO {
	public ArrayList<StreetDTO> getStreet() throws RuntimeException;

}

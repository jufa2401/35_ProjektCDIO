package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.BreweryDTO;
public interface BreweryDAO {
	public ArrayList<BreweryDTO> getBrewery() throws RuntimeException;

}

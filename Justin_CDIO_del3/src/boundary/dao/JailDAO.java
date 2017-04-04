package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.JailDTO;
public interface JailDAO {
	public ArrayList<JailDTO> getJail() throws RuntimeException;

}

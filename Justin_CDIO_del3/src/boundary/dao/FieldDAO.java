package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.FieldDTO;
public interface FieldDAO {
	public ArrayList<FieldDTO> getField() throws RuntimeException;
}

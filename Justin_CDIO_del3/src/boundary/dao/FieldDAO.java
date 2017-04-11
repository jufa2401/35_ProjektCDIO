package boundary.dao;
import java.util.ArrayList;

import entity.fieldclasses.FieldDTO;
public interface FieldDAO {
	public int getFieldCount() throws RuntimeException;
	public FieldDTO getField(int id) throws RuntimeException;
}

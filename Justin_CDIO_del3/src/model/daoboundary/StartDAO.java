package model.daoboundary;

import model.fieldclasses.StartDTO;

public interface StartDAO {

	StartDTO getStart(int id, String name) throws RuntimeException;

}

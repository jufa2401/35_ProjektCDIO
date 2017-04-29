package model.daoboundary;

import model.fieldclasses.StreetDTO;

public interface StreetDAO {

	StreetDTO getStreet(int id, String name, int price, int[] rent) throws RuntimeException;

}

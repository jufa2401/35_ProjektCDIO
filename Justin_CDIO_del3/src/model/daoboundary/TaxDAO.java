package model.daoboundary;

import model.fieldclasses.TaxDTO;

public interface TaxDAO {

	TaxDTO getTax(int id, String name) throws RuntimeException;

}

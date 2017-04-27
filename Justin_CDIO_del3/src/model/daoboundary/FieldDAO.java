/*
 * 
 */
package model.daoboundary;

import model.fieldclasses.FieldDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface FieldDAO.
 */
public interface FieldDAO {
	
	/**
	 * Gets the field.
	 *
	 * @param id the id
	 * @return the field
	 * @throws RuntimeException the runtime exception
	 */
	public FieldDTO getField(int id) throws RuntimeException;

	/**
	 * Gets the field count.
	 *
	 * @return the field count
	 * @throws RuntimeException the runtime exception
	 */
	public int getFieldCount() throws RuntimeException;

}

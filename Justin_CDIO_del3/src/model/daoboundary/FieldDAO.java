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
	public FieldDTO getFieldOld(int id) throws RuntimeException;

	/**
	 * Gets the field count.
	 *
	 * @return the field count
	 * @throws RuntimeException the runtime exception
	 */
	public int getFieldCount() throws RuntimeException;

	public FieldDTO getField2(int id) throws RuntimeException;

}

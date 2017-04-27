/*
 * 
 */
package model.daoboundary;

import model.ChanceCard;

// TODO: Auto-generated Javadoc
/**
 * The Interface ChanceDAO.
 */
public interface ChanceDAO {
	
	/**
	 * Gets the chance.
	 *
	 * @return the chance
	 * @throws RuntimeException the runtime exception
	 */
	public ChanceCard getChance() throws RuntimeException;

	/**
	 * Gets the chance card count.
	 *
	 * @return the chance card count
	 * @throws RuntimeException the runtime exception
	 */
	public int getChanceCardCount() throws RuntimeException;

}

/*
 * 
 */
package model.daoimplementation;

// TODO: Auto-generated Javadoc
/**
 * The Class DALException.
 */
public class DALException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5490114628089339322L;

	/**
	 * Instantiates a new DAL exception.
	 *
	 * @param e the e
	 */
	public DALException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new DAL exception.
	 *
	 * @param message the message
	 */
	public DALException(String message) {
		super(message);
	}
}

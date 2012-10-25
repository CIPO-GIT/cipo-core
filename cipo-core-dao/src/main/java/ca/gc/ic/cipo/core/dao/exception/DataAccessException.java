package ca.gc.ic.cipo.core.dao.exception;

/**
 * DataAccessException is an exception class which represents low level 
 * data access errors. This exception should be used at the data access layer.
 *
 * @author J.Denis
 * @version 1.0
 */

public class DataAccessException extends RuntimeException {

	/** Unique serial ID. */
	private static final long serialVersionUID = 8940285813286262195L;

	/**
	 * Constructor.
	 * @param message A brief description of the error
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * @param cause the root cause of the problem
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}	
}

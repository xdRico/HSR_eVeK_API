package de.ehealth.evek.api.exception;

/**
 * IllegalProcessException
 * <p>
 * Exception class thrown, when a process can not be performed due to unmatched requirements, i.E. IllegalArgumentException, UserNotProvidedException...
 * 
 * @extends Exception
 */
public class IllegalProcessException extends Exception {

	private static final long serialVersionUID = 2045452695104868904L;

	/**
	 * IllegalProcessException
	 * <p>
	 * To throw, when a process can not be performed due to unmatched requirements.
	 * <p>
	 * Constructor requiring the causing throwable.
	 * 
	 * @param cause - throwable that was originally thrown
	 */
	public IllegalProcessException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * IllegalProcessException
	 * <p>
	 * To throw, when a process can not be performed due to unmatched requirements.
	 * <p>
	 * Constructor requiring information as String.
	 * 
	 * @param info - information about the exception or cause
	 */
	public IllegalProcessException(String info) {
		super(info);
	}
	
	/**
	 * IllegalProcessException
	 * <p>
	 * To throw, when a process can not be performed due to unmatched requirements.
	 * <p>
	 * Constructor requiring information as String and the causing throwable.
	 * 
	 * @param info - information about the exception or cause
	 * @param cause - throwable that was originally thrown
	 */
	public IllegalProcessException(String info, Throwable cause) {
		super(info, cause);
	}
	
	/**
	 * protected IllegalProcessException
	 * <p>
	 * To throw, when a process can not be performed due to unmatched requirements.
	 * 
	 */
	protected IllegalProcessException() {
		super();
	}
}

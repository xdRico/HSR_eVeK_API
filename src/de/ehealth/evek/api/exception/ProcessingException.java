package de.ehealth.evek.api.exception;

/**
 * ProcessingException
 * <p>
 * Exception class thrown, when a process can not be performed due to technical exceptions, i.E. IOException, SQLException...
 * 
 * @extends Exception
 */
public class ProcessingException extends Exception {

	private static final long serialVersionUID = 8399822683142648041L;
	
	/**
	 * ProcessingException
	 * <p>
	 * To throw, when a process can not be performed due to technical exceptions, i.E. IOException, SQLException...
	 * <p>
	 * Constructor requiring additional information.
	 * 
	 * @param info - additional information about exception or cause
	 */
	public ProcessingException(String info) {
		super(info);
	}
	
	/**
	 * ProcessingException
	 * <p>
	 * To throw, when a process can not be performed due to technical exceptions, i.E. IOException, SQLException...
	 * <p>
	 * Constructor requiring the causing throwable.
	 * 
	 * @param cause - throwable that was originally thrown
	 */
	public ProcessingException(Throwable cause) {
		super(cause);
	}
	
}

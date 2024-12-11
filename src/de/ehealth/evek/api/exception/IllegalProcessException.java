package de.ehealth.evek.api.exception;

public class IllegalProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2045452695104868904L;

	public IllegalProcessException(Throwable cause) {
		super(cause);
	}
	
	public IllegalProcessException(String message) {
		super(message);
	}
}

package de.ehealth.evek.api.exception;

public class ProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8399822683142648041L;

	public ProcessingException(String message) {
		super(message);
	}
	
	public ProcessingException(Throwable cause) {
		super(cause);
	}
	
}

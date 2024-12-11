package de.ehealth.evek.api.exception;

public class UserNotProvidedException extends IllegalAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8477853536220092349L;

	private String message;
	
	public UserNotProvidedException(String message) {
		this.message = message;
	}
	
	public UserNotProvidedException() {
		message = "Processing user not provided!";
	}
	
	public String getMessage() {
		return message;
	}
	
}

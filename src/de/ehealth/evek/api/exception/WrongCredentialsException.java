package de.ehealth.evek.api.exception;

public class WrongCredentialsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -759410424357382732L;

	private Exception cause = null;
	
	public WrongCredentialsException() {
	}
	
	public WrongCredentialsException(Exception cause) {
		this.cause = cause;
	}
	
	public Exception getCause(){
		return cause;
	}
	
	@Override
	public String toString() {
		return String.format("Username and password not matching!");
	}
}

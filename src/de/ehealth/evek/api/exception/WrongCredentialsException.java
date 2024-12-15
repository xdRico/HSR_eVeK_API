package de.ehealth.evek.api.exception;

/**
 * WrongCredentialsException
 * <p>
 * Exception class thrown, when a user is tried to be logged in, but the credentials don't match an user.
 * 
 * @extends IllegalProcessException
 */
public class WrongCredentialsException extends IllegalProcessException {

	private static final long serialVersionUID = -759410424357382732L;
	
	/**
	 * WrongCredentialsException
	 * <p>
	 * To throw, when a user is tried to be created, but the username is already used. 
	 * <p>
	 * Constructor requiring the Id of the user that was tried to be created.
	 * 
	 * @param user - Id of the user that was already used
	 */
	public WrongCredentialsException() {
	}
	
	/**
	 * WrongCredentialsException
	 * <p>
	 * To throw, when a user is tried to be created, but the username is already used. 
	 * <p>
	 * Constructor requiring the throwable that was causing this exception.
	 * 
	 * @param cause - throwable that was originally thrown
	 */
	public WrongCredentialsException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * WrongCredentialsException
	 * <p>
	 * To throw, when a user is tried to be created, but the username is already used. 
	 * <p>
	 * Constructor requiring additional information.
	 * 
	 * @param info - addional information about exception or cause
	 */
	public WrongCredentialsException(String info) {
		super(info);
	}
	
	@Override
	public String toString() {
		return String.format("Username and password not matching!");
	}
}

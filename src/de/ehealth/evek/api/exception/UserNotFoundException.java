package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.type.Id;

/**
 * UserNotFoundException
 * <p>
 * Exception class thrown, when a user is tried ro access, but the username is not existing.
 * 
 * @extends IllegalProcessException
 */
public class UserNotFoundException extends IllegalProcessException  {

	private static final long serialVersionUID = -8275336912813657500L;

	private final Id<User> userName;
	
	/**
	 * UserNotFoundException
	 * <p>
	 * To throw, when a user is tried ro access, but the username is not existing.
	 * <p>
	 * Constructor requiring the Id of the user that was tried to be accessed.
	 * 
	 * @param userName - Id of the user that is not existing
	 */
	public UserNotFoundException(Id<User> userName) {
		this(userName, String.format("User %s could not be found!", 
						userName.value().toString()));
	}
	
	/**
	 * UserNotFoundException
	 * <p>
	 * To throw, when a user is tried ro access, but the username is not existing.
	 * <p>
	 * Constructor requiring the Id of the user that was tried to be accessed and additional information.
	 * 
	 * @param info - additional information about exception or cause
	 * @param userName - Id of the user that is not existing
	 */
	public UserNotFoundException(Id<User> userName, String info) {
		super(info);
		this.userName = userName;
	}
	
	/**
	 * method getUserID
	 * <p>
	 * Method to get the user-id, that was not existing
	 * 
	 * @return Id<User> - Id of the user that was is existing
	 */
	public Id<User> getUserID(){
		return userName;
	}
}

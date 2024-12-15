package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.type.Id;

/**
 * UserNameAlreadyUsedException
 * <p>
 * Exception class thrown, when a user is tried to be created, but the username is already used.
 * 
 * @extends IllegalProcessException
 */
public class UserNameAlreadyUsedException extends IllegalProcessException {

	private static final long serialVersionUID = 8170077488603253932L;

	private String userName;
	
	/**
	 * UserNameAlreadyUsedException
	 * <p>
	 * To throw, when a user is tried to be created, but the username is already used. 
	 * <p>
	 * Constructor requiring the Id of the user that was tried to be created.
	 * 
	 * @param user - Id of the user that was already used
	 */
	public UserNameAlreadyUsedException(Id<User> user) {
		this(user.value().toString());
	}
	
	/**
	 * UserNameAlreadyUsedException
	 * <p>
	 * To throw, when a user is tried to be created, but the username is already used. 
	 * <p>
	 * Constructor requiring the username of the user that was tried to be created.
	 * 
	 * @param userName - username of the user that was already used
	 */
	public UserNameAlreadyUsedException(String userName) {
		super(userName);
		this.userName = userName;
	}
	
	/**
	 * method getUserName
	 * <p>
	 * Method to get the username that was already used.
	 * 
	 * @return String - the username of the user to create
	 */
	public String getUserName() {
		return userName;
	}
}

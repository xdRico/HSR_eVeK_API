package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.type.Id;

public class UserNotFoundException extends IllegalAccessException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8275336912813657500L;

	private final Id<User> user;
	private String message;
	
	public UserNotFoundException(Id<User> user) {
		this(String.format("User %s could not be found!", 
				user.value().toString()), user);
	}
	
	public UserNotFoundException(String message, Id<User> user) {
		this.user = user;
		this.message = message;
	}

	public String message() {
			return message;
	}
	
	public Id<User> getProcessingUser(){
		return user;
	}
}

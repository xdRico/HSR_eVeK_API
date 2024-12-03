package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.type.Id;

public class UserNameAlreadyUsedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8170077488603253932L;

	private String userName;
	
	public UserNameAlreadyUsedException(Id<User> user) {
		this.userName = user.value().toString();
	}
	
	public UserNameAlreadyUsedException(String user) {
		this.userName = user;
	}
	
	public String getUserName() {
		return userName;
	}
	
}

package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.UserRole;

/**
 * UserNotAllowedException
 * <p>
 * Exception class thrown, when a user is trying to perform an action he is not allowed to.
 * 
 * @extends IllegalProcessException
 */
public class UserNotAllowedException extends IllegalProcessException  {

	private static final long serialVersionUID = -8275336912813657500L;

	private final Id<User> user;

	private final UserRole userRole;
	
	/**
	 * UserNotAllowedException
	 * <p>
	 * To throw, when a user is trying to perform an action he is not allowed to.
	 * <p>
	 * Constructor requiring the user that was performing the action.
	 * 
	 * @param user - User that tried to perform the action
	 */
	public UserNotAllowedException(User user) {
		this(user.id(), user.role());
	}
	
	/**
	 * UserNotAllowedException
	 * <p>
	 * To throw, when a user is trying to perform an action he is not allowed to.
	 * <p>
	 * Constructor requiring the Id of the user that was performing the action and his role.
	 * 
	 * @param user - Id of the user that tried to perform the action
	 * @param userRole - UserRole of the user that tried to perform the action
	 */
	public UserNotAllowedException(Id<User> user, UserRole userRole) {
		this(String.format("User %s as %s is not allowed to perform this Command!", 
				user.value().toString(), userRole.toString()),
					user, userRole);
	}
	
	/**
	 * UserNotAllowedException
	 * <p>
	 * To throw, when a user is trying to perform an action he is not allowed to.
	 * <p>
	 * Constructor requiring additional information, the Id of the user that was performing the action and his role.
	 * 
	 * @param info - additional information about the exception or cause
	 * @param user - Id of the user that tried to perform the action
	 * @param userRole - UserRole of the user that tried to perform the action
	 */
	public UserNotAllowedException(String info, Id<User> user, UserRole userRole) {
		super(info);
		this.user = user;
		this.userRole = userRole;
	}

	/**
	 * method getProcessingUser
	 * <p>
	 * Method to get the Id of the user that tried to perform the action.
	 * 
	 * @return Id<User> - the Id of the user that tried to perform the action
	 */
	public Id<User> getProcessingUser(){
		return user;
	}
	
	/**
	 * method getProcessingUserRole
	 * <p>
	 * Method to get the UserRole of the user that tried to perform the action.
	 * 
	 * @return UserRole - the UserRole of the user that tried to perform the action
	 */
	public UserRole getProcessingUserRole() {
		return userRole;
	}
}

package de.ehealth.evek.api.exception;

/**
 * UserNotProvidedException
 * <p>
 * Exception class thrown, when an action is tried to be performed but there is not processing user set. 
 * 
 * @extends IllegalProcessException
 */
public class UserNotProvidedException extends IllegalProcessException {

	private static final long serialVersionUID = -8477853536220092349L;

	/**
	 * UserNotProvidedException
	 * <p>
	 * To throw, when an action is tried to be performed but there is not processing user set.
	 * <p>
	 * Constructor requiring additional information.
	 * 
	 * @param info - additional information about exception or cause
	 */
	public UserNotProvidedException(String info) {
		super(info);
	}
	
	/**
	 * UserNotProvidedException
	 * <p>
	 * To throw, when an action is tried to be performed but there is not processing user set.
	 */
	public UserNotProvidedException() {
		this("Processing user not provided!");
	}
}

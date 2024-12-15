package de.ehealth.evek.api.exception;

/**
 * ProviderNotFoundException
 * <p>
 * Exception class thrown, when a Provider class for an Provider-Interface could not be found.
 * 
 * @extends ClassNotFoundException
 */
public class ProviderNotFoundException extends ClassNotFoundException{

	private static final long serialVersionUID = 5101669316701672426L;

	/**
	 * ProviderNotFoundException
	 * <p>
 	 * To throw, when a Provider class for an Provider-Interface could not be found.
	 * <p>
	 * Constructor requiring the provider that was not found as String.
	 * 
	 * @param provider - the provider that could not be found
	 */
	public ProviderNotFoundException(String provider) {
		super(String.format("Class of type %s could not be provided!", provider));
	}
	
}

package de.ehealth.evek.api.exception;

import java.io.IOException;

/**
 * EncryptionException
 * <p>
 * Exception class thrown, when an exception occurs while encryption / decryption process or initializing.
 * 
 * @extends IOException
 */
public class EncryptionException extends IOException {

	private static final long serialVersionUID = 1372676241291095654L;

	private Object object = null;

	/**
	 * EncryptionException
	 * <p>
	 * To throw, when an exception occurs while encryption / decryption process or initializing.
	 * <p>
	 * Constructor requiring the Object to encrypt / decrypt and an info as String.
	 * 
	 * @param object - the object that was handled, when the exception occured
	 * @param info - additional information about the exception or cause
	 */
	public EncryptionException(Object object, String info) {
		super(info);
		this.object = object;
	}
	
	/**
	 * EncryptionException
	 * <p>
	 * To throw, when an exception occurs while encryption / decryption process or initializing.
	 * <p>
	 * Constructor requiring the Object to encrypt / decrypt and a throwable as cause, when another exception / throwable was thrown in the process.
	 * 
	 * @param object - the object that was handled, when the exception occured
	 * @param cause - throwable that was thrown in the process that caused failing encryption / decryption
	 */
	public EncryptionException(Object object, Throwable cause) {
		super(cause);
		this.object = object;
	}
	
	/**
	 * EncryptionException
	 * <p>
	 * To throw, when an exception occurs while encryption / decryption process or initializing.
	 * <p>
	 * Constructor requiring an info as String.
	 * 
	 * @param info - additional information about the exception or cause
	 */
	public EncryptionException(String info) {
		super(info);
	}
	
	/**
	 * EncryptionException
	 * <p>
	 * To throw, when an exception occurs while encryption / decryption process or initializing.
	 * <p>
	 * Constructor requiring a throwable as cause, when another exception / throwable was thrown in the process.
	 * 
	 * @param cause - throwable that was thrown in the process that caused failing encryption / decryption
	 */
	public EncryptionException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * method getObject
	 * <p>
	 * used to get the Object that was provided for the exception.
	 * 
	 * @return Object - the object OR NULL that was provided to the exception
	 */
	public Object getObject() {
		return this.object;
	}
	
}

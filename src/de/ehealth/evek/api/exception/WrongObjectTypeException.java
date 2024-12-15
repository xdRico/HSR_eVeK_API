package de.ehealth.evek.api.exception;

import java.lang.reflect.Type;

/**
 * WrongObjectTypeException
 * <p>
 * Exception class thrown, when an object of a specified type was expected, but a different type was received.
 * 
 * @extends IllegalProcessException
 */
public class WrongObjectTypeException extends IllegalProcessException{

	private static final long serialVersionUID = 2003340380324984292L;
	
	private final Object receivedObject;
	
	private final Type expectedType;
	
	/**
	 * WrongObjectTypeException
	 * <p>
	 * To throw, when an object of a specified type was expected, but a different type was received.
	 * <p>
	 * Constructor requiring additional information, the expected object type and the received object.
	 * 
	 * @param info - additional information about exception or cause
	 * @param expectedType - the expected Type to be received
	 * @param receivedObject - the object that was received
	 */
	public WrongObjectTypeException(String info, Type expectedType, Object receivedObject) {
		super(info);
		this.receivedObject = receivedObject;
		this.expectedType = expectedType;
	}
	
	/**
	 * WrongObjectTypeException
	 * <p>
	 * To throw, when an object of a specified type was expected, but a different type was received.
	 * <p>
	 * Constructor requiring the expected object type and the received object.
	 * 
	 * @param expectedType - the expected Type to be received
	 * @param receivedObject - the object that was received
	 */
	public WrongObjectTypeException(Type expectedType, Object receivedObject) {
		this(String.format("%s does not match the type %s", receivedObject.toString(), expectedType.toString()),
				expectedType, receivedObject);
	}
	
	/**
	 * method getExpectedType
	 * <p>
	 * Method to get the expected type to receive.
	 * 
	 * @return Type - the expected type to receive
	 */
	public Type getExpectedType() {
		return expectedType;
	}
	
	/**
	 * method getObject
	 * <p>
	 * Method to get the received Object.
	 * 
	 * @return Object - the object that was received
	 */
	public Object getObject() {
		return receivedObject;
	}
}

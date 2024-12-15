package de.ehealth.evek.api.network;

import java.io.Serializable;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.exception.UserNotProvidedException;
import de.ehealth.evek.api.util.Log;

/**
 * interface IComServerReceiver
 * <p>
 * Interface for receiving objects on Server side
 * 
 * @extends IComReceiver
 */
public interface IComServerReceiver extends IComReceiver {
	
	/**
	 * method receiveObject
	 * <p>
	 * Method called to receive an Object.
	 * 
	 * @return boolean - true, if the object could have been handled
	 * 
	 * @throws EncryptionException - thrown when the decryption process fails
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	default boolean receiveObject(Object input) 
			throws EncryptionException, IllegalProcessException, ProcessingException {
		if(!(input instanceof Serializable))
			throw new IllegalProcessException(
					new IllegalArgumentException("Object is not a serializable!"));
		Serializable inputObject = (Serializable) input;
		inputObject = handleInputEncryption(inputObject);
		
		if(inputObject instanceof ComEncryptionKey) {
			process((ComEncryptionKey) inputObject);
			return true;
		}
		
		if(customHandleInput(inputObject))
			return true;
		
		if(inputObject instanceof User.LoginUser) 
			return setProcessingUser((User.LoginUser) inputObject);
		if(inputObject instanceof User.CreateFull) {
			process((User.CreateFull) inputObject);
			return true;
		}
		
		if(!hasProcessingUser())
			throw new IllegalProcessException(new UserNotProvidedException());
		
		if(inputObject instanceof Address.Command)
			process((Address.Command) inputObject);
		else if(inputObject instanceof Insurance.Command)
			process((Insurance.Command) inputObject);
		else if(inputObject instanceof InsuranceData.Command)
			process((InsuranceData.Command) inputObject);
//		else if(inputObject instanceof Invoice.Command)
//			process((Invoice.Command) inputObject);
		else if(inputObject instanceof Patient.Command)
			process((Patient.Command) inputObject);
//		else if(inputObject instanceof Protocol.Command)
//			process((Protocol.Command) inputObject);
		else if(inputObject instanceof ServiceProvider.Command)
			process((ServiceProvider.Command) inputObject);
		else if(inputObject instanceof TransportDetails.Command)
			process((TransportDetails.Command) inputObject);
		else if(inputObject instanceof TransportDocument.Command)
			process((TransportDocument.Command) inputObject);
		else if(inputObject instanceof User.Command)
			process((User.Command) inputObject);
		else return false;	
		
		return true;	
	}
	
	/**
	 * method setProcessingUser
	 * <p>
	 * Method called to set the specified user as processing user for permission handling
	 * 
	 * @return boolean - true, if the object could have been handled
	 * 
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	boolean setProcessingUser(User.LoginUser user) throws ProcessingException;
	
	/**
	 * method hasProcessingUser
	 * <p>
	 * Method called to get if a processing user has already been set
	 * 
	 * @return boolean - if the processing user has been set
	 */
	boolean hasProcessingUser();
	
	/**
	 * method handleInputEncryption
	 * <p>
	 * Method called to handle the decryption process of the received object.
	 * 
	 * @return Serializable - the decrypted object (serializable)
	 * 
	 * @throws EncryptionException - thrown when the decryption process fails
	 */
	Serializable handleInputEncryption(Serializable inputObject) throws EncryptionException;
	
	/**
	 * method customHandleInput
	 * <p>
	 * Method called to handle custom object receiving.
	 * 
	 * @return boolean - true, if the object could have been handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	default boolean customHandleInput(Serializable inputObject) 
			throws IllegalProcessException, ProcessingException {
		Log.sendMessage(String.format("	Object of Type %s has been recieved!", inputObject.getClass()));
		return false;
	}
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(Address.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(Insurance.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(InsuranceData.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
//	void process(Invoice.Command cmd) 
//			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(Patient.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
//	void process(Protocol.Command cmd) 
//			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(ServiceProvider.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(TransportDetails.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(TransportDocument.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received command.
	 * 
	 * @param cmd - the received command to be handled
	 * 
	 * @throws IllegalProcessException - thrown when a process can not be performed due to unmatched requirements
	 * @throws ProcessingException - when a process can not be performed due to technical exceptions
	 */
	void process(User.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	/**
	 * method process
	 * <p>
	 * Method called to process a received ComEncryptionKey.
	 * 
	 * @param key - the ComEncryptionKey to be handled
	 * 
	 * @throws EncryptionException - thrown when the decryption process fails
	 */
	void process(ComEncryptionKey key) 
			throws EncryptionException;
}

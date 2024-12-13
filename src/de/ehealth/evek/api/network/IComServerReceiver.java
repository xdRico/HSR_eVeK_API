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

public interface IComServerReceiver extends IComReceiver {
	
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
	
	boolean setProcessingUser(User.LoginUser user) throws ProcessingException;
	
	boolean hasProcessingUser();
	
	Serializable handleInputEncryption(Serializable inputObject) throws EncryptionException;
	
	default boolean customHandleInput(Serializable inputObject) 
			throws IllegalProcessException, ProcessingException {
		Log.sendMessage(String.format("	Object of Type %s has been recieved!", inputObject.getClass()));
		return false;
	}
	
	void process(Address.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	void process(Insurance.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	void process(InsuranceData.Command cmd) 
			throws IllegalProcessException, ProcessingException;
//	void process(Invoice.Command cmd) 
//			throws IllegalProcessException, ProcessingException;
	void process(Patient.Command cmd) 
			throws IllegalProcessException, ProcessingException;
//	void process(Protocol.Command cmd) 
//			throws IllegalProcessException, ProcessingException;
	void process(ServiceProvider.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	void process(TransportDetails.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	void process(TransportDocument.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	void process(User.Command cmd) 
			throws IllegalProcessException, ProcessingException;
	
	void process(ComEncryptionKey key) 
			throws EncryptionException;
	
}

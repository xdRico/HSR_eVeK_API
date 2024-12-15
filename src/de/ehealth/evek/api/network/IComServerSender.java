package de.ehealth.evek.api.network;

import java.io.IOException;
import java.util.ArrayList;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;

/**
 * interface IComServerSender
 * <p>
 * Interface for sending objects on Server side.
 * 
 * @extends IComSender
 */
public interface IComServerSender extends IComSender {
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(Address object) throws IOException;
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(Insurance object) throws IOException;
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(InsuranceData object) throws IOException;
	
//	void send(Invoice object) throws IOException;
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(Patient object) throws IOException;
	
//	void send(Protocol object) throws IOException;
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(ServiceProvider object) throws IOException;
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(TransportDetails object) throws IOException;

	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(TransportDocument object) throws IOException;

	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(User object) throws IOException;

	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(Throwable e) throws IOException;
	
	/**
	 * method send
	 * <p>
	 * Method called to send an object.
	 * 
	 * @param object - the object to send
	 * @throws IOException - when an error occurs on transmission
	 */
	void send(ArrayList<?> list) throws IOException;
}

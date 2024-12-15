package de.ehealth.evek.api.network;

import java.io.IOException;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;

/**
 * interface IComClientSender
 * <p>
 * Interface for sending objects on Client side.
 * 
 * @extends IComSender
 */
public interface IComClientSender extends IComSender {
	
	/**
	 * method sendAddress
	 * <p>
	 * Method called to send an address.
	 * 
	 * @param cmd - the address command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendAddress(Address.Command cmd) throws IOException {
		sendAsObject(cmd); 
	}
	
	/**
	 * method sendInsurance
	 * <p>
	 * Method called to send an insurance.
	 * 
	 * @param cmd - the insurance command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendInsurance(Insurance.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	/**
	 * method sendInsuranceData
	 * <p>
	 * Method called to send insurance data.
	 * 
	 * @param cmd - the insurance data command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendInsuranceData(InsuranceData.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
//	default void sendInvoice(Invoice.Command cmd) throws IOException {
//		sendAsObject(cmd);
//	}
	
	/**
	 * method sendPatient
	 * <p>
	 * Method called to send a patient.
	 * 
	 * @param cmd - the patient command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendPatient(Patient.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
//	default void sendProtocol(Protocol.Command cmd) throws IOException {
//		sendAsObject(cmd);
//	}
	
	/**
	 * method sendServiceProvider
	 * <p>
	 * Method called to send a service provider.
	 * 
	 * @param cmd - the service provider command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendServiceProvider(ServiceProvider.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	/**
	 * method sendTransportDetails
	 * <p>
	 * Method called to send transport details.
	 * 
	 * @param cmd - the transport details command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendTransportDetails(TransportDetails.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	/**
	 * method sendTransportDocument
	 * <p>
	 * Method called to send a transport document.
	 * 
	 * @param cmd - the transport document command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendTransportDocument(TransportDocument.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	/**
	 * method sendUser
	 * <p>
	 * Method called to send a user.
	 * 
	 * @param cmd - the user command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendUser(User.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	/**
	 * method loginUser
	 * <p>
	 * Method called to send a login user.
	 * 
	 * @param cmd - the login user command to send
	 * @throws IOException - when an error occurs on transmission
	 */
	default void loginUser(String username, String password) throws IOException {
		sendUser(new User.LoginUser(username, password));
	}
}

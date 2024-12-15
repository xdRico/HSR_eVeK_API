package de.ehealth.evek.api.network;

import java.util.List;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;

/**
 * interface IComClientReceiver
 * <p>
 * Interface for receiving objects on Client side
 * 
 * @extends IComReceiver
 */
public interface IComClientReceiver extends IComReceiver{
	
	/**
	 * method receiveAddress
	 * <p>
	 * Method called to receive an address.
	 * 
	 * @return Address - the received address
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public Address receiveAddress() throws Exception;
	
	/**
	 * method receiveInsurance
	 * <p>
	 * Method called to receive an insurance.
	 * 
	 * @return Insurance - the received insurance
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public Insurance receiveInsurance() throws Exception;
	
	/**
	 * method receiveInsuranceData
	 * <p>
	 * Method called to receive insurance data.
	 * 
	 * @return InsuranceData - the received insurance data
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public InsuranceData receiveInsuranceData() throws Exception;
	
//	public Invoice receiveInvoice() throws Exception;
	
	/**
	 * method receivePatient
	 * <p>
	 * Method called to receive patient.
	 * 
	 * @return Patient - the received patient
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public Patient receivePatient() throws Exception;
	
//	public Protocol receiveProtocol() throws Exception;
	
	/**
	 * method receiveServiceProvider
	 * <p>
	 * Method called to receive a service provider.
	 * 
	 * @return ServiceProvider - the received service provider
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public ServiceProvider receiveServiceProvider() throws Exception;
	
	/**
	 * method receiveTransportDetails
	 * <p>
	 * Method called to receive transport details.
	 * 
	 * @return TransportDetails - the received transport details
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public TransportDetails receiveTransportDetails() throws Exception;
	
	/**
	 * method receiveTransportDocument
	 * <p>
	 * Method called to receive a transport document.
	 * 
	 * @return Patient - the received patient
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public TransportDocument receiveTransportDocument() throws Exception;
	
	/**
	 * method receiveUser
	 * <p>
	 * Method called to receive an user.
	 * 
	 * @return User - the received user
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public User receiveUser() throws Exception;
	
	/**
	 * method receiveException
	 * <p>
	 * Method called to receive an exception or a throwable.
	 * 
	 * @return Throwable - the received throwable
	 *
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public Throwable receiveException() throws Exception;
	
	/**
	 * method receiveList
	 * <p>
	 * Method called to receive lists.
	 * 
	 * @return List - the received list
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public List<?> receiveList() throws Exception;
	
	/**
	 * method receivePublicKey
	 * <p>
	 * Method called to receive a public key as ComEncryptionKey.
	 * 
	 * @return ComEncryptionKey - the received encryption public key
	 * 
	 * @throws Exception - when an error occurs on server, encryption or transmission
	 */
	public ComEncryptionKey receivePublicKey() throws Exception ;
}

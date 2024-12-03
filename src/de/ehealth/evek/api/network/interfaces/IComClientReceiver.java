package de.ehealth.evek.api.network.interfaces;

import java.util.List;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;

public interface IComClientReceiver {
	
	public Address receiveAddress() throws Exception;
	
	public Insurance receiveInsurance() throws Exception;
	
	public InsuranceData receiveInsuranceData() throws Exception;
	
//	public Invoice receiveInvoice() throws Exception;
	
	public Patient receivePatient() throws Exception;
	
//	public Protocol receiveProtocol() throws Exception;
	
	public ServiceProvider receiveServiceProvider() throws Exception;
	
	public TransportDetails receiveTransportDetails() throws Exception;
	
	public TransportDocument receiveTransportDocument() throws Exception;
	
	public User receiveUser() throws Exception;
	
	public Throwable receiveException() throws Exception;
	
	public List<?> receiveList() throws Exception;
	
}

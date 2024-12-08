package de.ehealth.evek.api.network.interfaces;

import java.io.IOException;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;

public interface IComClientSender extends IComSender, IComEncryption {
	
	default void sendAddress(Address.Command cmd) throws IOException {
		sendAsObject(cmd); 
	}
	
	default void sendInsurance(Insurance.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	default void sendInsuranceData(InsuranceData.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
//	default void sendInvoice(Invoice.Command cmd) throws IOException {
//		sendAsObject(cmd);
//	}
	
	default void sendPatient(Patient.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
//	default void sendProtocol(Protocol.Command cmd) throws IOException {
//		sendAsObject(cmd);
//	}
	
	default void sendServiceProvider(ServiceProvider.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	default void sendTransportDetails(TransportDetails.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	default void sendTransportDocument(TransportDocument.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	default void sendUser(User.Command cmd) throws IOException {
		sendAsObject(cmd);
	}
	
	default void sendPCUser(User.LoginUser loginUser) throws IOException {
		sendAsObject(loginUser);
	}
	
	default void loginUser(String username, String password) throws IOException {
		sendUser(new User.LoginUser(username, password));
	}
	
}

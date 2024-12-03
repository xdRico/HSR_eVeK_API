package de.ehealth.evek.api.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.network.interfaces.IComClientSender;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.Log;

public class ComClientSender implements IComClientSender {
	
	Reference<User> user;
	ObjectOutputStream objSender;
	
	public ComClientSender(Socket client) throws IOException {
		try {
			//this.outputStream =  new PrintWriter(client.getOutputStream(), true);
			this.objSender = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			Log.sendException(e);
			throw e;
		}
	}
	
	public void sendAddress(Address.Command cmd) throws IOException {
		objSender.writeObject(cmd); 
	}
	
	public void sendInsurance(Insurance.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
	public void sendInsuranceData(InsuranceData.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
//	public void sendInvoice(Invoice.Command cmd) throws IOException {
//	objSender.writeObject(cmd);
//	}
	
	public void sendPatient(Patient.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
//	public void sendProtocol(Protocol.Command cmd) throws IOException {
//	objSender.writeObject(cmd);
//	}
	
	public void sendServiceProvider(ServiceProvider.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
	public void sendTransportDetails(TransportDetails.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
	public void sendTransportDocument(TransportDocument.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
	public void sendUser(User.Command cmd) throws IOException {
		objSender.writeObject(cmd);
	}
	
	public void sendPCUser(User.LoginUser loginUser) throws IOException {
		objSender.writeObject(loginUser);
	}

	
}

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

public interface IComServerSender extends IComSender {
	
	
	void send(Address cmd) throws IOException;
	
	void send(Insurance cmd) throws IOException;
	
	void send(InsuranceData cmd) throws IOException;
	
//	void send(Invoice cmd) throws IOException;
	
	void send(Patient cmd) throws IOException;
	
//	void send(Protocol cmd) throws IOException;
	
	void send(ServiceProvider cmd) throws IOException;
	
	void send(TransportDetails cmd) throws IOException;

	void send(TransportDocument cmd) throws IOException;

	void send(User cmd) throws IOException;

	void send(Throwable e) throws IOException;
	
	void send(ArrayList<?> list) throws IOException;

}

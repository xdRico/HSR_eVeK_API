package de.ehealth.evek.api.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;

import javax.crypto.Cipher;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;
import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.exception.WrongObjectTypeException;

public class ComClientReceiver implements IComClientReceiver{

	private final ObjectInputStream objReader;
	private Cipher decryptionCipher;
	
	public ComClientReceiver(Socket server) throws IOException {
		objReader = new ObjectInputStream(server.getInputStream());
	}
	
	@Override
	public Address receiveAddress() throws Exception {
		Object object = readObject();
		if(object instanceof Address) 
			return (Address) object;
		throw wrongObjectType(Address.class, object);
	}
	
	@Override
	public Insurance receiveInsurance() throws Exception {
		Object object = readObject();
		if(object instanceof Insurance) 
			return (Insurance) object;
		throw wrongObjectType(Insurance.class, object);
	}
	
	@Override
	public InsuranceData receiveInsuranceData() throws Exception {
		Object object = readObject();
		if(object instanceof InsuranceData) 
			return (InsuranceData) object;
		throw wrongObjectType(InsuranceData.class, object);
	}
	
//	public Invoice receiveInvoice() throws Exception {
//		Object object = readObject();
//		if(object instanceof Invoice) 
//			return (Invoice) object;
//		else throw new WrongObjectTypeException(Invoice.class, object);
//	}
	
	@Override
	public Patient receivePatient() throws Exception {
		Object object = readObject();
		if(object instanceof Patient) 
			return (Patient) object;
		throw wrongObjectType(Patient.class, object);
	}
	
//	public Protocol receiveProtocol() throws Exception {
//		Object object = readObject();
//		if(object instanceof Protocol) 
//			return (Protocol) object;
//		else throw new WrongObjectTypeException(Protocol.class, object);
//	}
	
	@Override
	public ServiceProvider receiveServiceProvider() throws Exception {
		Object object = readObject();
		if(object instanceof ServiceProvider) 
			return (ServiceProvider) object;
		throw wrongObjectType(ServiceProvider.class, object);
	}
	
	@Override
	public TransportDetails receiveTransportDetails() throws Exception {
		Object object = readObject();
		if(object instanceof TransportDetails) 
			return (TransportDetails) object;
		throw wrongObjectType(TransportDetails.class, object);
	}
	
	@Override
	public TransportDocument receiveTransportDocument() throws Exception {
		Object object = readObject();
		if(object instanceof TransportDocument) 
			return (TransportDocument) object;
		throw wrongObjectType(TransportDocument.class, object);
	}
	
	@Override
	public User receiveUser() throws Exception {
		Object object = readObject();
		if(object instanceof User) 
			return (User) object;
		throw wrongObjectType(User.class, object);
	}

	@Override
	public Throwable receiveException() throws Exception {
		Object object = readObject();
		if(object instanceof Throwable) 
			return (Throwable) object;
		throw wrongObjectType(Throwable.class, object);
	}

	@Override
	public List<?> receiveList() throws Exception {
		Object object = readObject();
		if(object instanceof List<?>) 
			return (List<?>) object;
		throw wrongObjectType(List.class, object);
	}
	
	private static Exception wrongObjectType(Type expected, Object received) {
		if(received instanceof Exception)
			return (Exception) received;
		return new WrongObjectTypeException(expected, received);
	}
	
	private Object readObject() throws IOException {
		Object object;
		try {
			object = objReader.readObject();
			if(!(object instanceof ComEncryptedObject))
				return object;
			if(decryptionCipher == null)
				throw new EncryptionException("Cipher not found!");
			return ((ComEncryptedObject) object).decryptObject(decryptionCipher);
		} catch (EncryptionException | ClassNotFoundException e) {
			throw new IOException(e); 
		}
		
	}
	
	@Override
	public ComEncryptionKey receivePublicKey() throws Exception {
		Object object = readObject();
		if(object instanceof ComEncryptionKey) 
			return (ComEncryptionKey) object;
		throw wrongObjectType(ComEncryptionKey.class, object);
	}
}

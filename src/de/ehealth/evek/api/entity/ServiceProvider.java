package de.ehealth.evek.api.entity;

import java.io.Serializable;
import java.util.List;

import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

public record ServiceProvider(
		Id<ServiceProvider> id, 
		String name,
		String type,
		Boolean isHealthcareProvider,
		Boolean isTransportProvider,
		Reference<Address> address,
		COptional<String> contactInfo
		) implements Serializable {

    private static final long serialVersionUID = 625732975895345128L;

	
	public static sealed interface Command extends Serializable permits Create, CreateFull, Delete, Move, Update, UpdateService, Get, GetList {
	}

	public static record Create(String serviceProviderId, String name, String  type, 
			Boolean isHealthcareProvider, Boolean isTransportProvider,
			Reference<Address> address, COptional<String> contactInfo) implements Command {
	}
	public static record CreateFull(String serviceProviderId, String name, String  type, 
			Boolean isHealthcareProvider, Boolean isTransportProvider,
			Address.Create addressCmd, COptional<String> contactInfo) implements Command {
	}
	public static record Delete(Id<ServiceProvider> id) implements Command {
	}
	
	public static record Move(Id<ServiceProvider> id, 
			Reference<Address> address) implements Command {
	}

	public static record Update(Id<ServiceProvider> id,
			String name, String type, COptional<String> contactInfo) implements Command {
	}
	
	public static record UpdateService(Id<ServiceProvider> id, Boolean providesHealthcare, 
			Boolean providesTransport) implements Command{
	}
	
	public static record Get(Id<ServiceProvider> id) implements Command {
	}
	public static record GetList(Filter filter) implements Command {
	}

	public static record Filter(COptional<Reference<Address>> address, 
			COptional<String> type, COptional<String> name) {
	}

	public static interface Operations {
		ServiceProvider process(Command cmd, Reference<User> processingUser) throws Throwable;

		List<ServiceProvider> getServiceProvider(Filter filter);

		ServiceProvider getServiceProvider(Id<ServiceProvider> id);
	}
	
	public ServiceProvider updateWith(String newName, 
			String newType,	COptional<String> newContactInfo) {
		return new ServiceProvider(this.id, newName, newType, this.isHealthcareProvider, 
				this.isTransportProvider, this.address, newContactInfo);
	}

	public ServiceProvider updateWith(Reference<Address> newAddress) {
		return new ServiceProvider(this.id, this.name, this.type, this.isHealthcareProvider, 
				this.isTransportProvider, newAddress, this.contactInfo);
	}
	
	public ServiceProvider updateWith(Boolean becomesHealthcareProvider,
			Boolean becomesTransportProvider) {
		return new ServiceProvider(this.id, this.name, this.type, becomesHealthcareProvider, 
				becomesTransportProvider, this.address, this.contactInfo);
	}
	
	public String toString() {
		return String.format(
				"ServiceProvider[id=%s, name=%s, type=%s, "
				+ "isHealthcarePeovider=%s, isTransportProvider=%s, "
				+ "address=%s, contactInfo=%s]", 
				id, name, type, isHealthcareProvider.toString(),
				isTransportProvider.toString(), address.toString(), contactInfo);
	}
}
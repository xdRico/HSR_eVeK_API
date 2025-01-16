package de.ehealth.evek.api.entity;

import java.io.Serializable;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

/**
 * record ServiceProvider
 * <p>
 * defines service provider entity with its required properties for handling.
 * 
 * @property Id
 * @property Name
 * @property Type
 * @property IsHealthcareProvider
 * @property IsTransportProvider
 * @property Address
 * @property Contact info
 */
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

    /**
   	 * interface Command
   	 * <p>
   	 * Command interface permitting service provider commands:
   	 * 
   	 * @permits Create
   	 * @permits CreateFull
   	 * @permits Delete
   	 * @permits Move
   	 * @permits Update
   	 * @permits UpdateService
   	 * @permits Get
   	 * @permits GetList
   	 */
	public static sealed interface Command extends Serializable permits Create, CreateFull, Delete, Move, Update, UpdateService, Get, GetList {
	}

	/**
	 * record Create
	 * <p>
	 * Command to create a service provider.
	 * 
	 * @property Service provider Id
	 * @property Name
	 * @property Type
	 * @property Is healthcare provider
	 * @property Is transport provier
	 * @property Address
	 * @property Contact info
	 */
	public static record Create(String serviceProviderId, String name, String  type, 
			Boolean isHealthcareProvider, Boolean isTransportProvider,
			Reference<Address> address, COptional<String> contactInfo) implements Command {
	}
	
	/**
	 * record CreateFull
	 * <p>
	 * Command to create a service provider, including its address.
	 * 
	 * @property Service provider Id
	 * @property Name
	 * @property Type
	 * @property Is healthcare provider
	 * @property Is transport provier
	 * @property Address
	 * @property Contact info
	 */
	public static record CreateFull(String serviceProviderId, String name, String  type, 
			Boolean isHealthcareProvider, Boolean isTransportProvider,
			Address.Create addressCmd, COptional<String> contactInfo) implements Command {
	}
	
	/**
	 * record Delete
	 * <p>
	 * Command to delete a service provider.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<ServiceProvider> id) implements Command {
	}
	
	
	/**
	 * record Move
	 * <p>
	 * Command to change the Address property of a service provider.
	 * 
	 * @property Id
	 * @property Address
	 */
	public static record Move(Id<ServiceProvider> id, 
			Reference<Address> address) implements Command {
	}

	/**
	 * record Update
	 * <p>
	 * Command to change properties of a service provider.
	 * 
	 * @property Id
	 * @property Name
	 * @property Type
	 * @property Contact info
	 */
	public static record Update(Id<ServiceProvider> id,
			String name, String type, COptional<String> contactInfo) implements Command {
	}
	
	/**
	 * record UpdateService
	 * <p>
	 * Command to change the provided services of a service provider.
	 * 
	 * @property Id
	 * @property Provides healthcare
	 * @property Provides transport
	 */
	public static record UpdateService(Id<ServiceProvider> id, Boolean providesHealthcare, 
			Boolean providesTransport) implements Command{
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get an existing service provider by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<ServiceProvider> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of service providers by a provided filter.
	 * 
	 * @property Filter
	 */
	public static record GetList(Filter filter) implements Command {
	}

	/**
	 * record Filter
	 * <p>
	 * Command to use as Filter for GetList command.
	 * 
	 * @property COptional:Address
	 * @property COptional:Type
	 * @property COptional:Name
	 */
	public static record Filter(COptional<Reference<Address>> address, 
			COptional<String> type, COptional<String> name) implements Serializable {
	}

	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for service provider entity.
	 */
	public static interface Operations {
		/**
		 * method process
		 * <p>
		 * Method for processing Commands requiring a User as processing User for permission management.
		 * 
		 * @param cmd - Command to be processed
		 * @param processingUser - User to be used as processing User
		 * 
		 * @return ServiceProvider - the ServiceProvider that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		ServiceProvider process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;
	}
	
	
	/**
	 * method updateWith
	 * <p>
	 * Method returning a new service provider with the given name.
	 * 
	 * @param newName - the name to be set
	 * @param newType - the type to be set
	 * @param newContactInfo - the contact info to be set
	 * 
	 * @return ServiceProvider - the updated service provider Object
	 */
	public ServiceProvider updateWith(String newName, 
			String newType,	COptional<String> newContactInfo) {
		return new ServiceProvider(this.id, newName, newType, this.isHealthcareProvider, 
				this.isTransportProvider, this.address, newContactInfo);
	}
	
	/**
	 * method updateWith
	 * <p>
	 * Method returning a new service provider with the given address.
	 * 
	 * @param newAddress - the address to be set
	 * 
	 * @return ServiceProvider - the updated service provider Object
	 */
	public ServiceProvider updateWith(Reference<Address> newAddress) {
		return new ServiceProvider(this.id, this.name, this.type, this.isHealthcareProvider, 
				this.isTransportProvider, newAddress, this.contactInfo);
	}
	
	/**
	 * method updateWith
	 * <p>
	 * Method returning a new service provider with the given services.
	 * 
	 * @param becomesHealthcareProvider - if the service provider provides healthcare service
	 * @param becomesTransportProvider - if the service provider provides transport service
	 * 
	 * @return ServiceProvider - the updated service provider Object
	 */
	public ServiceProvider updateWith(Boolean becomesHealthcareProvider,
			Boolean becomesTransportProvider) {
		return new ServiceProvider(this.id, this.name, this.type, becomesHealthcareProvider, 
				becomesTransportProvider, this.address, this.contactInfo);
	}
	
	@Override
	public String toString() {
		return String.format(
				"ServiceProvider[id=%s, name=%s, type=%s, "
				+ "isHealthcarePeovider=%s, isTransportProvider=%s, "
				+ "address=%s, contactInfo=%s]", 
				id, name, type, isHealthcareProvider.toString(),
				isTransportProvider.toString(), address.toString(), contactInfo);
	}
}
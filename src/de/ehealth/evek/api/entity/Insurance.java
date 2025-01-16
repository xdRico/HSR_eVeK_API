package de.ehealth.evek.api.entity;

import java.io.Serializable;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

/**
 * record Insurance
 * <p>
 * Record defining Insurance entity with its required properties for handling.
 * 
 * @property Id
 * @property Name
 * @property Address
 */
public record Insurance(
		Id<Insurance> id,
		// ToDo type field?
		String name,
		Reference<Address> address
		) implements Serializable {
	
    private static final long serialVersionUID = -7469835782057358365L;

    /**
	 * interface Command
	 * <p>
	 * Command interface permitting Insurance commands:
	 * 
	 * @permits Create
	 * @permits Delete
	 * @permits Move
	 * @permits Update
	 * @permits Get
	 * @permits GetList
	 */
	public static sealed interface Command extends Serializable permits Create, Delete, Move, Update, Get, GetList {
	}
	
	/**
	 * record Create
	 * <p>
	 * Command to create an insurance.
	 * 
	 * @property Id (provider-id)
	 * @property Name
	 * @property Address
	 */
	public static record Create(String insuranceId, String name, Reference<Address> address) implements Command {
	}
	
	/**
	 * record Delete
	 * <p>
	 * Command to delete an insurance.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<Insurance> id) implements Command {
	}
	
	/**
	 * record Move
	 * <p>
	 * Command to update the address of an insurance.
	 * @property Id
	 * @property Address
	 */
	public static record Move(Id<Insurance> id, 
			Reference<Address> address) implements Command {
	}

	/**
	 * record Update
	 * <p>
	 * Command to update the name of an insurance
	 * 
	 * @property Id
	 * @property Name
	 */
	public static record Update(Id<Insurance> id, String name) implements Command {
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get an existing Insurance by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<Insurance> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of insurances by a provided filter.
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
	 * @property COptional:Street
	 * @property COptional:Postcode
	 * @property COptional:City
	 * @property COptional:Name
	 */
	public static record Filter(COptional<Reference<Address>> address, 
			COptional<String> name) implements Serializable {
	}

	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for Insurance entity.
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
		 * @return Insurance - the Insurance that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		Insurance process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;
	}

	/**
	 * method updateWith
	 * <p>
	 * Method returning a new Insurance with the given name.
	 * 
	 * @param newName - the name to be set
	 * 
	 * @return Insurance - the updated Insurance Object
	 */
	public Insurance updateWith(String newName) {
		return new Insurance(this.id, newName, this.address);
	}
	
	/**
	 * method updateWith
	 * <p>
	 * Method returning a new Insurance with the given Address.
	 * 
	 * @param newAddress- the name to be set
	 * 
	 * @return Insurance - the updated Insurance Object
	 */
	public Insurance updateWith(Reference<Address> newAddress) {
		return new Insurance(this.id, this.name, newAddress);
	}
	
	@Override
	public String toString() {
		return String.format(
				"Insurance[id=%s, name=%s, address=%s]", 
				id, name, address.toString());
	}
}

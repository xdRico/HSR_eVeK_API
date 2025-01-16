package de.ehealth.evek.api.entity;

import java.io.Serializable;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

/**
 * record Address
 * <p>
 * Defines Address entity with its required properties for handling.
 * 
 * @property Id
 * @property Name
 * @property Street
 * @property House number
 * @property Country
 * @property Postcode
 * @property City
 */
public record Address(
		Id<Address> id, 
		COptional<String> name,
		String streetName, 
		String houseNumber,
		String country,
		String postCode,
		String city
		) implements Serializable {

    private static final long serialVersionUID = -4714168968119491955L;

	/**
	 * interface Command
	 * <p>
	 * Command interface permitting Address commands:
	 * 
	 * @permits Create
	 * @permits Delete
	 * @permits Update
	 * @permits Get
	 * @permits GetList
	 */
	public static sealed interface Command extends Serializable permits Create, Delete, Update, Get, GetList{
	}

	/**
	 * record Create
	 * <p>
	 * Command to create an address.
	 * 
	 * @property Name
	 * @property Street
	 * @property House number
	 * @property Country
	 * @property Postcode
	 * @property City
	 */
	public static record Create(COptional<String> name, String streetName, String  houseNumber, 
			String country, String postCode, String city) implements Command {
	}

	/**
	 * record Delete
	 * <p>
	 * Command to delete an address.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<Address> id) implements Command {
	}

	/**
	 * record Update
	 * <p>
	 * Command to change properties of an address.
	 * 
	 * @property Id
	 * @property COptional:Name
	 */
	public static record Update(Id<Address> id, COptional<String> name) implements Command {
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get an existing address by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<Address> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of addresses by a provided filter.
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
	public static record Filter(COptional<String> streetName, 
			COptional<String> postCode, COptional<String> city, COptional<String> name)
	implements Serializable {
	}

	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for address entity.
	 */
	public static interface Operations {
		/**
		 * method process.
		 * <p>
		 * Method for processing Commands requiring a User as processing User for permission management.
		 *
		 * @param cmd - Command to be processed
		 * @param processingUser - User to be used as processing User
		 * 
		 * @return Address - the Address that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		Address process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;
	}

	/**
	 * method updateWith
	 * <p>
	 * Method returning a new address with the given name.
	 * 
	 * @param newName - the name to be set
	 * 
	 * @return Address - the updated address Object
	 */
	public Address updateWith(COptional<String> newName) {
		return new Address(this.id, newName, this.streetName, this.houseNumber, this.country, this.postCode, this.city);
	}
	
	@Override
	public String toString() {
		return String.format(
				"Address[id=%s, name=%s, streetName=%s, houseNumber=%s, country=%s, postCode=%s, city=%s]", 
				this.id,
				this.name,
				this.streetName,
				this.houseNumber,
				this.country,
				this.postCode,
				this.city);
	}
}
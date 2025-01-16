package de.ehealth.evek.api.entity;

import java.io.Serializable;
import java.sql.Date;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

/**
 * record Patient
 * <p>
 * defines patient entity with its required properties for handling.
 * 
 * @property Insurance number
 * @property Insurance Data
 * @property Last name
 * @property First name
 * @property Birth date
 * @property Address
 */
public record Patient (
		Id<Patient> insuranceNumber,
		Reference<InsuranceData> insuranceData,
		String lastName,
		String firstName,
		Date birthDate,
		Reference<Address> address
		) implements Serializable {

    private static final long serialVersionUID = -6734982562356986824L;

    /**
	 * interface Command
	 * <p>
	 * Command interface permitting patient commands:
	 * 
	 * @permits Create
	 * @permits CreateWithInsuranceData
	 * @permits Delete
	 * @permits Move
	 * @permits Update
	 * @permits UpdateInsuranceData
	 * @permits Get
	 * @permits GetList
	 */
	public static sealed interface Command extends Serializable permits Create, 
	CreateWithInsuranceData, Delete, Move, Update, UpdateInsuranceData, Get, GetList{
	}

	/**
	 * record Create
	 * <p>
	 * Command to create a patient.
	 * 
	 * @property Insurance number
	 * @property Insurance data
	 * @property Last name
	 * @property First name
	 * @property Birth date
	 * @property Address
	 */
	public static record Create(
			String insuranceNumber, 
			Reference<InsuranceData> insuranceData, 
			String lastName,
			String firstName, 
			Date birthDate, 
			Reference<Address> address) implements Command {
	}
	
	/**
	 * record CreateWithInsuranceData
	 * <p>
	 * Command to create a patient.
	 * 
	 * @property Insurance number
	 * @property Insurance
	 * @property Insurance status
	 * @property Last name
	 * @property First name
	 * @property Birth date
	 * @property Address
	 */
	public static record CreateWithInsuranceData(
			String insuranceNumber, 
			Reference<Insurance> insurance, 
			int insuranceStatus,
			String lastName,
			String firstName, 
			Date birthDate, 
			Reference<Address> address) implements Command {
	}
	
	/**
	 * record Delete
	 * <p>
	 * Command to delete a patient.
	 * 
	 * @property Id
	 */
	public static record Delete(
			Id<Patient> insuranceNumber) implements Command {
	}
	
	/**
	 * record Move
	 * <p>
	 * Command to change the Address property of a patient.
	 * 
	 * @property Insurance number
	 * @property Address
	 */
	public static record Move(
			Id<Patient> insuranceNumber, 
			Reference<Address> address) implements Command {
	}

	/**
	 * record Update
	 * <p>
	 * Command to change properties of a patient.
	 * 
	 * @property Insurance number
	 * @property Last name
	 * @property First name
	 */
	public static record Update(
			Id<Patient> insuranceNumber, 
			String lastName,
			String firstName) implements Command {
	}

	/**
	 * record UpdateInsuranceData
	 * <p>
	 * Command to change the insurance data of a patient.
	 * 
	 * @property Insurance number
	 * @property Insurance data
	 */
	public static record UpdateInsuranceData(
			Id<Patient> insuranceNumber, 
			Reference<InsuranceData> insuranceData) implements Command {
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get an existing patient by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<Patient> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of patient by a provided filter.
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
	 * @property COptional:Last name
	 * @property COptional:First name
	 * @property COptional:Birth date
	 * @property COptional:Insurance data
	 */
	public static record Filter(COptional<Reference<Address>> address, 
			COptional<String> lastName, COptional<String> firstName, 
			COptional<Date> birthDate, 
			COptional<Reference<InsuranceData>> insuranceData) implements Serializable {
	}

	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for patient entity.
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
		 * @return Patient - the Patient that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		Patient process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;
	}

	/**
	 * method updateWith
	 * <p>
	 * Method returning a new patient with the given name.
	 * 
	 * @param newLastName - the last name to be set
	 * @param newFirstName - the first name to be set
	 * 
	 * @return Patient - the updated patient Object
	 */
	public Patient updateWith(String newLastName, String newFirstName) {
		return new Patient(this.insuranceNumber, this.insuranceData, newLastName, newFirstName, this.birthDate, this.address);
	}
	
	/**
	 * method updateAddress
	 * <p>
	 * Method returning a new patient with the given address.
	 * 
	 * @param newAddress - the address to be set
	 * 
	 * @return Patient - the updated patient Object
	 */
	public Patient updateAddress(Reference<Address> newAddress) {
		return new Patient(this.insuranceNumber, this.insuranceData, this.lastName, this.firstName, this.birthDate, newAddress);
	}
	
	/**
	 * method updateInsuranceData
	 * <p>
	 * Method returning a new patient with the given insurance data.
	 * 
	 * @param newInsuranceData - the insurance data to be set
	 * 
	 * @return Patient - the updated patient Object
	 */
	public Patient updateInsuranceData(Reference<InsuranceData> newInsuranceData) {
		return new Patient(this.insuranceNumber, newInsuranceData, this.lastName, this.firstName, this.birthDate, this.address);
	}
	
	@Override
	public String toString() {
		return String.format(
				"Patient[insuranceNumber=%s, "
				+ "insuranceData=%s, lastName=%s, "
				+ "firstName=%s, birthDate=%s, address=%s]", 
				insuranceNumber, insuranceData.toString(), 
				lastName, firstName, birthDate, address.toString());
	}
}
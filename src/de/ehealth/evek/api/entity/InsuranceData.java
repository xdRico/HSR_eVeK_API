package de.ehealth.evek.api.entity;

import java.io.Serializable;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

/**
 * record InsuranceData
 * <p>
 * Record defining InsuranceData entity with its required properties for handling.
 * 
 * @property Id
 * @property Patient
 * @property Insurance
 * @property Insurance status
 */
public record InsuranceData(
		Id<InsuranceData> id, 
		Reference<Patient> patient,
		Reference<Insurance> insurance, 
		int insuranceStatus
		) implements Serializable {
	
    private static final long serialVersionUID = -946751378556315875L;

    /**
   	 * interface Command
   	 * <p>
   	 * Command interface permitting Insurance commands:
   	 * 
   	 * @permits Create
   	 * @permits Delete
   	 * @permits Get
   	 * @permits GetList
   	 */
	public static sealed interface Command extends Serializable permits Create, Delete, Get, GetList{
	}
	
	/**
	 * record Create
	 * <p>
	 * Command to create insurance data.
	 * 
	 * @property Id
	 * @property Patient
	 * @property Insutance
	 * @property insurance status
	 */
	public static record Create(
			Reference<Patient> patient,
			Reference<Insurance> insurance, 
			int insuranceStatus) implements Command {
	}
	
	/**
	 * record Delete
	 * <p>
	 * Command to delete insurance data.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<InsuranceData> id) implements Command {
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get existing insurance data by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<InsuranceData> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of insurance data by a provided filter.
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
	 * @property COptional:Patient
	 * @property COptional:Insurance
	 */
	public static record Filter(
			COptional<Reference<Patient>> patient,
			COptional<Reference<Insurance>> insurance) {
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
		 * @return InsuranceData - the insurance data that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		InsuranceData process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;
	}
	
	@Override
	public String toString() {
		return String.format(
				"InsuranceData[id=%s, patient=%s, insurance=%s, insuranceStatus=%d]", 
				id, 
				patient.id(),
				insurance.toString(), 
				insuranceStatus);
	}
}
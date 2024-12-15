package de.ehealth.evek.api.entity;

import java.io.Serializable;
import java.sql.Date;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.IsNotArchivableException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.type.TransportReason;
import de.ehealth.evek.api.type.TransportationType;
import de.ehealth.evek.api.util.COptional;

/**
 * record TransportDocument
 * <p>
 * defines transport document entity with its required properties for handling.
 * 
 * @property Id
 * @property Patient
 * @property Insurance data
 * @property Transport reason
 * @property Start date
 * @property End date
 * @property Weekly frequency
 * @property Healthcare service provider
 * @property Transportation type
 * @property Additional info
 * @property Signature
 * @property is Archived
 */
public record TransportDocument(
		Id<TransportDocument> id,
		COptional<Reference<Patient>> patient,
		COptional<Reference<InsuranceData>> insuranceData,
		TransportReason transportReason,
		Date startDate,
		COptional<Date> endDate,
		COptional<Integer> weeklyFrequency,
		Reference<ServiceProvider> healthcareServiceProvider,
		TransportationType transportationType,
		COptional<String> additionalInfo,
		Reference<User> signature,
		boolean isArchived
		) implements Serializable{
	
    private static final long serialVersionUID = 956735861385487658L;
	
    /**
   	 * interface Command
   	 * <p>
   	 * Command interface permitting service provider commands:
   	 * 
   	 * @permits Create
   	 * @permits Update
   	 * @permits AssignPatient
   	 * @permits Archive
   	 * @permits Delete
   	 * @permits Get
   	 * @permits GetList
   	 */
	public static sealed interface Command extends Serializable permits 
	Create, Update, AssignPatient, Archive, Delete, Get, GetList{
	}
	
	/**
	 * record Create
	 * <p>
	 * Command to create a transport document.
	 * 
	 * @property Patient
	 * @property Insurance data
	 * @property Transport reason
	 * @property Start date
	 * @property End date
	 * @property Weekly frequency
	 * @property Healthcare service provider
	 * @property Transportation type
	 * @property Additional info
	 * @property Signature
	 */
	public static record Create(
			COptional<Reference<Patient>> patient,
			COptional<Reference<InsuranceData>> insuranceData,
			TransportReason transportReason,
			Date startDate,
			COptional<Date> endDate,
			COptional<Integer> weeklyFrequency,
			Reference<ServiceProvider> healthcareServiceProvider,
			TransportationType transportationType,
			COptional<String> additionalInfo,
			Reference<User> signature) implements Command {
	}

	/**
	 * record Delete
	 * <p>
	 * Command to delete a transport document.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<TransportDocument> id) implements Command {
	}

	/**
	 * record Update
	 * <p>
	 * Command to update a transport document.
	 * 
	 * @property Id
	 * @property Transport reason
	 * @property Start date
	 * @property End date
	 * @property Weekly frequency
	 * @property Healthcare service provider
	 * @property Transportation type
	 * @property Additional info
	 * @property Signature
	 */
	public static record Update(
			Id<TransportDocument> id,
			TransportReason transportReason,
			Date startDate,
			COptional<Date> endDate,
			COptional<Integer> weeklyFrequency,
			Reference<ServiceProvider> healthcareServiceProvider,
			TransportationType transportationType,
			COptional<String> additionalInfo,
			Reference<User> signature) implements Command {
	}
	
	/**
	 * record AssignPatient
	 * <p>
	 * Command to assign a patient to a transport document.
	 * 
	 * @property Id
	 * @property Patient
	 * @property Insurance data
	 */
	public static record AssignPatient(
			Id<TransportDocument> id,
			Reference<Patient> patient,
			Reference<InsuranceData> insuranceData) implements Command {
	}

	/**
	 * record Archive
	 * <p>
	 * Command to archive a transport document.
	 * 
	 * @property Id
	 */
	public static record Archive(Id<TransportDocument> id) implements Command {
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get existing transport document by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<TransportDocument> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of transport documents by a provided filter.
	 * 
	 * @property Filter
	 */
	public static record GetList(Filter filter) implements Command {
	}
	
	/**
	 * record Filter
	 * <p>
	 * Command to use as Filter for GetList command
	 * 
	 * @property COptional:Patient
	 * @property COptional:Insurance data
	 * @property COptional:Start date
	 * @property COptional:End date
	 * @property COptional:Healthcare service provider
	 * @property COptional:Transportation type
	 * @property COptional:Signature
	 */
	public static record Filter(COptional<Reference<Patient>> patient,
			COptional<InsuranceData> insuranceData,
			COptional<Date> startDate, COptional<Date> endDate,
			COptional<Reference<ServiceProvider>> healthcareServiceProvider,
			COptional<TransportationType> transportationType,
			COptional<Reference<User>> signature) {
	}
	
	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for transport document entity.
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
		 * @return TransportDocument - the TransportDocument that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		TransportDocument process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;	
	}

	/**
	 * method updateWith
	 * <p>
	 * Method returning a new transport document with the given properties.
	 * 
	 * @param newTransportReason - the transport reason to be set
	 * @param newStartDate - the start date to be set
	 * @param newEndDate - the end date to be set
	 * @param newWeeklyFrequency - the weekly frequency to be set
	 * @param newServiceProvider - the service provider to be set
	 * @param newTransportationType - the transportation type to be set
	 * @param newAdditionalInfo - the additional info to be set
	 * @param newSignature - the signature to be set
	 * 
	 * @return TransportDocument - the updated transport document Object
	 */
	public TransportDocument updateWith(
			TransportReason newTransportReason,
			Date newStartDate, 
			COptional<Date> newEndDate,
			COptional<Integer> newWeeklyFrequency,
			Reference<ServiceProvider> newServiceProvider,
			TransportationType newTransportationType,
			COptional<String> newAdditionalInfo,
			Reference<User> newSignature) {
		
		return new TransportDocument(
				this.id, this.patient, insuranceData, newTransportReason, 
				newStartDate, newEndDate, newWeeklyFrequency, 
				newServiceProvider, newTransportationType, 
				newAdditionalInfo, newSignature, this.isArchived);
	}
	
	/**
	 * method assignPatient
	 * <p>
	 * Method returning a new transport document with the given patient and insurance data.
	 * 
	 * @param newPatient - the patient to be set
	 * @param newInsuranceData - the insurance data to be set
	 * 
	 * @return TransportDocument - the updated transport document Object
	 */
	public TransportDocument assignPatient(
			Reference<Patient> newPatient, 
			Reference<InsuranceData> newInsuranceData) {
		return new TransportDocument(
				this.id, COptional.of(newPatient), 
				COptional.of(newInsuranceData),
				this.transportReason, this.startDate, 
				this.endDate, this.weeklyFrequency, 
				this.healthcareServiceProvider, 
				this.transportationType, 
				this.additionalInfo, this.signature, this.isArchived);
	}
	
	/**
	 * method assignSignature
	 * <p>
	 * Method returning a new transport document with the given signature.
	 * 
	 * @param newSignature - the signature to be set
	 * 
	 * @return TransportDocument - the updated transport document Object
	 */
	public TransportDocument assignSignature(Reference<User> newSignature) {
		return new TransportDocument(
				this.id, this.patient,
				this.insuranceData,
				this.transportReason, this.startDate, 
				this.endDate, this.weeklyFrequency, 
				this.healthcareServiceProvider, 
				this.transportationType, 
				this.additionalInfo, newSignature, this.isArchived);
	}
	
	/**
	 * method archive
	 * <p>
	 * Method returning a new archived instance of the transport document.
	 * 
	 * @return TransportDocument - the updated transport document Object
	 * 
	 * @throws IsNotArchivableException - thrown, when properties are missing or 
	 * 		transport document is already archived
	 */
	public TransportDocument archive() throws IsNotArchivableException {
		if(this.patient == null || this.patient.isEmpty() 
				|| this.insuranceData == null || this.insuranceData.isEmpty() 
				|| this.transportReason == null || this.startDate == null 
				|| this.transportationType == null || this.signature == null)
			throw new IsNotArchivableException(this.id, "Missing information!");
		if(this.isArchived)
			throw new IsNotArchivableException(this.id, "Already archived!");
		return new TransportDocument(
				this.id, this.patient,
				this.insuranceData,
				this.transportReason, this.startDate, 
				this.endDate, this.weeklyFrequency, 
				this.healthcareServiceProvider, 
				this.transportationType, 
				this.additionalInfo, this.signature, true);
	}
	
	@Override
	public String toString() {
		return String.format(
				"TransportDocument[id=%s, patient=%s, insuranceData=%s, transportReason=%s, "
				+ "startDate=%s, endDate=%s, weeklyFrequency=%s, "
				+ "healthcareServiceProvider=%s, transportationType=%s, "
				+ "additionalInfo=%s, signature=%s, isArchived=%b]", 
				id, patient.toString(), insuranceData.toString(), 
				transportReason.toString(), startDate.toString(), endDate.toString(),
				weeklyFrequency, healthcareServiceProvider.toString(), 
				transportationType.toString(), additionalInfo.toString(), 
				signature.toString(), this.isArchived);
	}
}

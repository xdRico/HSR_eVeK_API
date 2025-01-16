package de.ehealth.evek.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Direction;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.PatientCondition;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

/**
 * record TransportDetails
 * <p>
 * defines transport details entity with its required properties for handling.
 * 
 * @property Id
 * @property Transport document
 * @property Transport date
 * @property Start address
 * @property End address
 * @property Direction
 * @property Patient Condition
 * @property Transport provider
 * @property Tour number
 * @property Payment exemption
 * @property Patient signature
 * @property Patient signature date
 * @property Transporter signature
 * @property Transporter signature date
 */
public record TransportDetails (
		Id<TransportDetails> id,
		Reference<TransportDocument> transportDocument,
		Date transportDate,
		COptional<Reference<Address>> startAddress,
		COptional<Reference<Address>> endAddress,
		COptional<Direction> direction,
		COptional<PatientCondition> patientCondition,
		COptional<Reference<ServiceProvider>> transportProvider,
		COptional<String> tourNumber,
		COptional<Boolean> paymentExemption,
		COptional<String> patientSignature,
		COptional<Date> patientSignatureDate,
		COptional<String> transporterSignature,
		COptional<Date> transporterSignatureDate
		) implements Serializable {
	
    private static final long serialVersionUID = 6359875465658792642L;

    /**
   	 * interface Command
   	 * <p>
   	 * Command interface permitting service provider commands:
   	 * 
   	 * @permits AssignTransportProvider
   	 * @permits Create
   	 * @permits Delete
   	 * @permits Update
   	 * @permits UpdatePatientSignature
   	 * @permits UpdateTransporterSignature
   	 * @permits Get
   	 * @permits GetList
   	 */
	public static sealed interface Command extends Serializable permits 
	AssignTransportProvider, Create, Delete, Update, UpdatePatientSignature, 
	UpdateTransporterSignature, Get, GetList, GetListByIDList {
	}
	
	/**
	 * record AssignTransportProvider
	 * <p>
	 * Command to assign a service provider to the transport.
	 * 
	 * @property Id
	 * @property Transport provider
	 */
	public static record AssignTransportProvider(
			Id<TransportDetails> id,
			Reference<ServiceProvider> transportProvider
			) implements Command{
		}
	
	/**
	 * record Create
	 * <p>
	 * Command to create a transport.
	 * 
	 * @property Transport document
	 * @property Transport date
	 */
	public static record Create( 
			Reference<TransportDocument> transportDocument, 
			Date transportDate
			) implements Command {
	}

	/**
	 * record Delete
	 * <p>
	 * Command to delete a transport.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<TransportDetails> id) implements Command {
	}

	/**
	 * record Update
	 * <p>
	 * Command to update the properties of a transport.
	 * 
	 * @property Id
	 * @property Start address
	 * @property End address
	 * @property Direction
	 * @property Patient Condition
	 * @property Tour umber
	 * @property Payment exemption
	 */
	public static record Update(Id<TransportDetails> id,
			COptional<Reference<Address>> startAddress,
			COptional<Reference<Address>> endAddress,
			COptional<Direction> direction,
			COptional<PatientCondition> patientCondition,
			COptional<String> tourNumber,
			COptional<Boolean> paymentExemption) implements Command {
	}

	/**
	 * record UpdatePatientSignature
	 * <p>
	 * Command to update the patient signature of a transport.
	 * 
	 * @property Id
	 * @property Patient Signature
	 * @property Patient signature date
	 */
	public static record UpdatePatientSignature(Id<TransportDetails> id,
			String patientSignature,
			Date patientSignatureDate) implements Command {
	}
	
	/**
	 * record UpdateTransporterSignature
	 * <p>
	 * Command to update the transporters signature of a transport.
	 * 
	 * @property Id
	 * @property Transporter signature
	 * @property Transporter signature date
	 */
	public static record UpdateTransporterSignature(Id<TransportDetails> id,
			String transporterSignature,
			Date transporterSignatureDate) implements Command {
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get existing transport details by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<TransportDetails> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of transport details by a provided filter.
	 * 
	 * @property Filter
	 */
	public static record GetList(Filter filter) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of transport details by a provided {@link List} of {@link Id Id's}.
	 * 
	 * @property {@link List}
	 */
	public static record GetListByIDList(List<Id<TransportDetails>> idList) implements Command{
	}
	
	/**
	 * record Filter
	 * <p>
	 * Command to use as Filter for GetList command.
	 * 
	 * @property COptional:Transport document
	 * @property COptional:Transport date
	 * @property COptional:Address
	 * @property COptional:Direction
	 * @property COptional:Transport provider
	 */
	public static record Filter(
			COptional<Reference<TransportDocument>> transportDocument,
			COptional<Date> transportDate, 
			COptional<Reference<Address>> address,
			COptional<Direction> direction,
			COptional<Reference<ServiceProvider>> transportProvider) {
	}

	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for transport details entity.
	 * 
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
		 * @return TransportDetails - the TransportDetails that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		TransportDetails process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;
	}

	/**
	 * method updateWith
	 * <p>
	 * Method returning a new transport with the given properties.
	 * 
	 * @param newStartAddress - the start address to be set
	 * @param newEndAddress - the end address to be set
	 * @param newDirection - the direction to be set
	 * @param newPatientCondition - the patient condition to be set
	 * @param newTourNumber - the tour number to be set
	 * @param newPaymentExemption - payment exemption boolean to be set
	 * 
	 * @return TransportDetails - the updated transport Object
	 */
	public TransportDetails updateWith(
			COptional<Reference<Address>> newStartAddress,
			COptional<Reference<Address>>newEndAddress,
			COptional<Direction> newDirection,
			COptional<PatientCondition> newPatientCondition,
			COptional<String> newTourNumber,
			COptional<Boolean> newPaymentExemption) {
		return new TransportDetails(
				this.id, 
				this.transportDocument,
				this.transportDate,
				newStartAddress, 
				newEndAddress, 
				newDirection, 
				newPatientCondition,
				this.transportProvider,
				newTourNumber,
				newPaymentExemption,
				this.patientSignature, 
				this.patientSignatureDate,
				this.transporterSignature,
				this.transporterSignatureDate);
	}
	
	/**
	 * method updatePatientSignature
	 * <p>
	 * Method returning a new transport with updated patient signature.
	 * 
	 * @param newPatientSignature - the signature to be set
	 * @param newPatientSignatureDate - the signature date to be set
	 * 
	 * @return TransportDetails - the updated transport Object
	 */
	public TransportDetails updatePatientSignature(
			String newPatientSignature,
			Date newPatientSignatureDate){
		return new TransportDetails(
				this.id, 
				this.transportDocument,
				this.transportDate,
				this.startAddress, 
				this.endAddress, 
				this.direction, 
				this.patientCondition,
				this.transportProvider,
				this.tourNumber,
				this.paymentExemption, 
				COptional.of(newPatientSignature), 
				COptional.of(newPatientSignatureDate),
				this.transporterSignature,
				this.transporterSignatureDate);
	}
	
	/**
	 * method updateTransporterSignature
	 * <p>
	 * Method returning a new transport with updated transporter signature.
	 * 
	 * @param newTransporterSignature - the signature to be set
	 * @param newTransporterSignatureDate - the signature date to be set
	 * 
	 * @return TransportDetails - the updated transport Object
	 */
	public TransportDetails updateTransporterSignature(
			String newTransporterSignature,
			Date newTransporterSignatureDate){
		return new TransportDetails(
				this.id, 
				this.transportDocument,
				this.transportDate,
				this.startAddress, 
				this.endAddress, 
				this.direction, 
				this.patientCondition,
				this.transportProvider,
				this.tourNumber,
				this.paymentExemption, 
				this.patientSignature, 
				this.patientSignatureDate,
				COptional.of(newTransporterSignature),
				COptional.of(newTransporterSignatureDate));
	}
	
	/**
	 * method updateTransportProvider
	 * <p>
	 * Method returning a new transport with updated transport provider.
	 * 
	 * @param newTransportProvider - the transport provider to be set
	 * 
	 * @return TransportDetails - the updated transport Object
	 */
	public TransportDetails updateTransportProvider(
			Reference<ServiceProvider> newTransportProvider){
		return new TransportDetails(
				this.id, 
				this.transportDocument,
				this.transportDate,
				this.startAddress, 
				this.endAddress, 
				this.direction, 
				this.patientCondition,
				COptional.of(newTransportProvider),
				this.tourNumber,
				this.paymentExemption, 
				this.patientSignature, 
				this.patientSignatureDate,
				this.transporterSignature,
				this.transporterSignatureDate);
	}
	
	@Override
	public String toString() {
		return String.format(
				"TransportDetails[id=%S, transportDocument=%S, transportDate=%S, startAddress=%S, "
				+ "endAddress=%S, direction=%S, patientCondition=%S, transportProvider=%S, "
				+ "tourNumber=%s, paymentExemption=%S, patientSignature=%S, patientSignatureDate=%S, "
				+ "transporterSignature=%S, transporterSignatureDate=%s]", 
				id,
				transportDocument.toString(),
				transportDate.toString(),
				startAddress.toString(),
				endAddress.toString(),
				direction.toString(),
				patientCondition.toString(),
				transportProvider.toString(),
				tourNumber,
				paymentExemption.toString(),
				patientSignature,
				patientSignatureDate.toString(),
				transporterSignature,
				transporterSignatureDate.toString());
	}	
}

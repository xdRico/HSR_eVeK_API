package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.type.Id;

/**
 * IsNotArchivableException
 * <p>
 * Exception class thrown, when a TransportDocument is tried to be archived but is not archivable.
 * 
 * @extends IllegalProcessException
 */
public class IsNotArchivableException extends IllegalProcessException {

	private static final long serialVersionUID = 7970434035849987198L;
	
	private final Id<TransportDocument> transportDoc;
	
	/**
	 * IsNotArchivableException
	 * <p>
	 * Exception class thrown, when a TransportDocument is tried to be archived but is not archivable.
	 * <p>
	 * Constructor requiring the TransportDocument Id that was tried to be archived and additional information.
	 * 
	 * @param transportDoc - the Id of the TransportDocument that was tried to be archived
	 * @param info - additional information about the exception or cause
	 */
	public IsNotArchivableException(Id<TransportDocument> transportDoc, String info) {
		super(info);
		this.transportDoc = transportDoc;
	}
	
	/**
	 * IsNotArchivableException
	 * <p>
	 * Exception class thrown, when a TransportDocument is tried to be archived but is not archivable.
	 * <p>
	 * Constructor requiring the TransportDocument Id that was tried to be archived.
	 * 
	 * @param transportDoc - the Id of the TransportDocument that was tried to be archived
	 */
	public IsNotArchivableException(Id<TransportDocument> transportDoc) {
		super(transportDoc.toString());
		this.transportDoc = transportDoc;
	}
	
	/**
	 * IsNotArchivableException
	 * <p>
	 * Exception class thrown, when a TransportDocument is tried to be archived but is not archivable.
	 * <p>
	 * Constructor requiring the TransportDocument Id that was tried to be archived and the cause.
	 * 
	 * @param transportDoc - the Id of the TransportDocument that was tried to be archived
	 * @param cause - throwable that was originally thrown
	 */
	public IsNotArchivableException(Id<TransportDocument> transportDoc, Throwable cause) {
		super(transportDoc.toString(), cause);
		this.transportDoc = transportDoc;
	}
	
	/**
	 * method getTransportDocument()
	 * <p>
	 * method to get the Id of the causing TransportDocument.
	 * 
	 * @return Id<TransportDocument> - the Id of the causing TransportDocument
	 */
	public Id<TransportDocument> getTransportDocument(){
		return transportDoc;
	}
}

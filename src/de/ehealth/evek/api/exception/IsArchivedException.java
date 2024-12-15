package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.type.Id;

/**
 * IsArchivedException
 * <p>
 * Exception class thrown, when a TransportDocument is tried to be archived but is already archived.
 * 
 * @extends IsNotArchivableException
 */
public class IsArchivedException extends IsNotArchivableException {

	private static final long serialVersionUID = -7143053200529718273L;

	/**
	 * IsArchivedException
	 * <p>
	 * Exception class thrown, when a TransportDocument is tried to be archived but is already archived.
	 * <p>
	 * Constructor requiring the TransportDocument Id that was tried to be archived.
	 * 
	 * @param transportDoc - the Id of the TransportDocument that was tried to be archived
	 */
	public IsArchivedException(Id<TransportDocument> transportDoc) {
		super(transportDoc, "IsArchivedException");
	}
	
	/**
	 * IsArchivedException
	 * <p>
	 * Exception class thrown, when a TransportDocument is tried to be archived but is already archived.
	 * <p>
	 * Constructor requiring the TransportDocument Id that was tried to be archived and additional infomation.
	 * 
	 * @param transportDoc - the Id of the TransportDocument that was tried to be archived
	 * @param info - additional information about the exception or cause
	 */
	public IsArchivedException(Id<TransportDocument> transportDoc, String info) {
		super(transportDoc, info);
	}
}

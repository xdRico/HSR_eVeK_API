package de.ehealth.evek.api.exception;

import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.type.Id;

public class IsArchivedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7143053200529718273L;

	private final Id<TransportDocument> transportDoc;
	
	public IsArchivedException(Id<TransportDocument> transportDoc) {
		this.transportDoc = transportDoc;
	}
	
	public Id<TransportDocument> getTransportDocument(){
		return transportDoc;
	}
	
}

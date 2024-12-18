package de.ehealth.evek.api.exception;

import java.util.List;

import de.ehealth.evek.api.entity.TransportDocument;

public class GetTransportDocumentListThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271973190530539711L;
	
	private final List<TransportDocument> list;
	
	public GetTransportDocumentListThrowable(List<TransportDocument> transportDocuments) {
		this.list = transportDocuments;
	}
	
	public List<TransportDocument> getList() {
		return list;
	}

}

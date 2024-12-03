package de.ehealth.evek.api.exception;

import java.util.List;

import de.ehealth.evek.api.entity.TransportDetails;


public class GetTransportDetailsListThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271973190530539711L;
	
	private final List<TransportDetails> list;
	
	public GetTransportDetailsListThrowable(List<TransportDetails> transports) {
		this.list = transports;
	}
	
	public List<TransportDetails> getList() {
		return list;
	}

}

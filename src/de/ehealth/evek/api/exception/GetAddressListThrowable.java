package de.ehealth.evek.api.exception;

import java.util.List;

import de.ehealth.evek.api.entity.Address;

public class GetAddressListThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271973190530539711L;
	
	private final List<Address> list;
	
	public GetAddressListThrowable(List<Address> addresses) {
		this.list = addresses;
	}
	
	public List<Address> getList() {
		return list;
	}

}

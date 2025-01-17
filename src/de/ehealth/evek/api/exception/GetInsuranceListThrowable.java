package de.ehealth.evek.api.exception;

import java.util.List;

import de.ehealth.evek.api.entity.Insurance;

public class GetInsuranceListThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271973190530539711L;
	
	private final List<Insurance> list;
	
	public GetInsuranceListThrowable(List<Insurance> insurances) {
		this.list = insurances;
	}
	
	public List<Insurance> getList() {
		return list;
	}

}

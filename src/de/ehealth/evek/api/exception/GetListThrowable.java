package de.ehealth.evek.api.exception;

import java.util.ArrayList;
import java.util.List;

public class GetListThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271973190530539711L;
	
	private final List<?> list;
	
	public GetListThrowable(List<?> list) {
		this.list = list;
	}
	
	public List<?> getList() {
		return list;
	}

	public ArrayList<?> getArrayList(){
		return new ArrayList<>(list);
	}
	
}

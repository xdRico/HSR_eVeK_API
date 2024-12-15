package de.ehealth.evek.api.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * GetListThrowable
 * <p>
 * Throwable used for handling Lists as return of commands as a list is not the specified return parameter.
 * 
 * @extends Throwable
 */
public class GetListThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271973190530539711L;
	
	private final List<?> list;
	
	/**
	 * EncryprionException
	 * <p>
	 * To throw, when a List should be returned, where no List is able to be returned.
	 * <p>
	 * Constructor requiring the List that should be returned.
	 * 
	 * @param list - the list to be provided
	 */
	public GetListThrowable(List<?> list) {
		this.list = list;
	}
	
	/**
	 * method getList
	 * <p>
	 * Method used to get the provided List for further handling.
	 * 
	 * @return List<?> - the provided List
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * method getArrayList
	 * <p>
	 * Method used to get the provided List for further handling as ArrayList.
	 * 
	 * @return ArrayList<?> - the provided List as ArrayList
	 */
	public ArrayList<?> getArrayList(){
		return new ArrayList<>(list);
	}
	
}

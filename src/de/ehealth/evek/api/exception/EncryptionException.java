package de.ehealth.evek.api.exception;

import java.io.IOException;

public class EncryptionException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1372676241291095654L;
	
	private Object object = null;
	
	public EncryptionException(Object sealedObject, String info) {
		super(info);
		this.object = sealedObject;
	}
	
	public EncryptionException(Object sealedObject, Throwable cause) {
		super(cause);
		this.object = sealedObject;
	}
	
	public EncryptionException(String info) {
		super(info);
	}
	
	public EncryptionException(Throwable cause) {
		super(cause);
	}
	
	public Object getObject() {
		return this.object;
	}
	
}

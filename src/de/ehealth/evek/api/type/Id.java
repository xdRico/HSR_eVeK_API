package de.ehealth.evek.api.type;

import java.io.Serializable;

public record Id<T>(String value) implements Serializable {

	private static final long serialVersionUID = 6495384926538862598L;
	
	@Override
	public String toString() {
		return value;
	}
}
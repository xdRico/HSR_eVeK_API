package de.ehealth.evek.api.type;

import java.io.Serializable;

/**
 * record Id
 * <p>
 * Defines Id entity with its required properties for handling.
 * 
 * @property Value
 */
public record Id<T>(String value) implements Serializable {

	private static final long serialVersionUID = 6495384926538862598L;
	
	@Override
	public String toString() {
		return value;
	}
}
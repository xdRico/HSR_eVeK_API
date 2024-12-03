package de.ehealth.evek.api.type;

import java.io.Serializable;

public record Id<T>(String value) implements Serializable {

	@Override
	public String toString() {
		return value;
	}
}
package de.ehealth.evek.api.type;

import java.io.Serializable;

public record Reference<T>(Id<T> id) implements Serializable{
	
	private static final long serialVersionUID = -1648976582316498583L;

	public static <T> Reference<T> to(String id){
		return new Reference<>(new Id<>(id));
	}
	
	@Override
	public String toString() {
		return id.value().toString();
	}
}

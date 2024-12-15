package de.ehealth.evek.api.type;

import java.io.Serializable;

/**
 * record Reference
 * <p>
 * Defines Reference entity with its required properties for handling.
 * 
 * @property Id
 */
public record Reference<T>(Id<T> id) implements Serializable{
	
	private static final long serialVersionUID = -1648976582316498583L;

	/**
	 * method to
	 * <p>
	 * Method to create a reference object to a specified id
	 * @param <T> - the type of the referenced object
	 * @param id - the id of the referenced object
	 * 
	 * @return Reference<T> - the new reference to the object
	 */
	public static <T> Reference<T> to(String id){
		return new Reference<>(new Id<>(id));
	}
	
	@Override
	public String toString() {
		return id.value().toString();
	}
}

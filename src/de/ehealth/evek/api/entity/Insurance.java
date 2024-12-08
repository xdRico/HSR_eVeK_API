package de.ehealth.evek.api.entity;

import java.io.Serializable;
import java.util.List;
import de.ehealth.evek.api.util.COptional;

import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;

public record Insurance(
		Id<Insurance> id,
		// ToDo type field?
		String name,
		Reference<Address> address
		) implements Serializable {
	
    private static final long serialVersionUID = -7469835782057358365L;

	
	public static sealed interface Command extends Serializable permits Create, Delete, Move, Update, Get, GetList {
	}

	public static record Create(String insuranceId, String name, Reference<Address> address) implements Command {
	}
	public static record Delete(Id<Insurance> id) implements Command {
	}
	
	public static record Move(Id<Insurance> id, 
			Reference<Address> address) implements Command {
	}

	public static record Update(Id<Insurance> id, String name) implements Command {
	}
	
	public static record Get(Id<Insurance> id) implements Command {
	}
	public static record GetList(Filter filter) implements Command {
	}
	
	public static record Filter(COptional<Reference<Address>> address, 
			COptional<String> name) {
	}

	public static interface Operations {
		Insurance process(Command cmd, Reference<User> processingUser) throws Throwable;

		List<Insurance> getInsurance(Filter filter);

		Insurance getInsurance(Id<Insurance> id);
	}

	public Insurance updateWith(String newName) {
		return new Insurance(this.id, newName, this.address);
	}
	
	public Insurance updateWith(Reference<Address> newaddress) {
		return new Insurance(this.id, this.name, newaddress);
	}
	
	public String toString() {
		return String.format(
				"Insurance[id=%s, name=%s, address=%s]", 
				id, name, address.toString());
	}
}

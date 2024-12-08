package de.ehealth.evek.api.entity;

import java.io.Serializable;
import java.util.List;

import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.util.COptional;

public record InsuranceData(
		Id<InsuranceData> id, 
		Reference<Patient> patient,
		Reference<Insurance> insurance, 
		int insuranceStatus
		) implements Serializable {
	
    private static final long serialVersionUID = -946751378556315875L;

	
	public static sealed interface Command extends Serializable permits Create, Delete, Get, GetList{
	}
	
	public static record Create(
			Reference<Patient> patient,
			Reference<Insurance> insurance, 
			int insuranceStatus) implements Command {
	}
	
	public static record Delete(Id<InsuranceData> id) implements Command {
	}
	
	public static record Get(Id<InsuranceData> id) implements Command {
	}
	public static record GetList(Filter filter) implements Command {
	}

	public static record Filter(
			COptional<Reference<Patient>> patient,
			COptional<Reference<Insurance>> insurance) {
	}

	public static interface Operations {
		InsuranceData process(Command cmd, Reference<User> processingUser) throws Throwable;

		List<InsuranceData> getInsuranceData(Filter filter);

		InsuranceData getInsuranceData(Id<InsuranceData> id);
	}
	
	public String toString() {
		return String.format(
				"InsuranceData[id=%s, patient=%s, insurance=%s, insuranceStatus=%d]", 
				id, 
				patient.id(),
				insurance.toString(), 
				insuranceStatus);
	}
}
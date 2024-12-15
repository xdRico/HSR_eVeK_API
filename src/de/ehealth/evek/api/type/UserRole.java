package de.ehealth.evek.api.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.ehealth.evek.api.entity.Address;
import de.ehealth.evek.api.entity.Insurance;
import de.ehealth.evek.api.entity.InsuranceData;
import de.ehealth.evek.api.entity.Patient;
import de.ehealth.evek.api.entity.ServiceProvider;
import de.ehealth.evek.api.entity.TransportDetails;
import de.ehealth.evek.api.entity.TransportDocument;
import de.ehealth.evek.api.entity.User;

/**
 * enum UserRole
 * <p>
 * Enum for defining the user roles and their permissions
 */
public enum UserRole implements Serializable {
	HealthcareAdmin(new Class[] {
			Address.Update.class,
			ServiceProvider.CreateFull.class,
			ServiceProvider.Move.class,
			ServiceProvider.Update.class,
			User.Create.class,
			User.Delete.class,
			User.UpdateRole.class
	}),
	HealthcareDoctor(new Class[] {
			Insurance.Create.class,
			Insurance.Update.class,
			Insurance.Move.class,
			InsuranceData.Create.class,
			TransportDetails.Create.class,
			TransportDocument.AssignPatient.class,
			TransportDocument.Create.class,
			TransportDocument.Delete.class,
			TransportDocument.Update.class
	}),
	HealthcareUser(new Class[] {
			Insurance.Create.class,
			Insurance.Update.class,
			Insurance.Move.class,
			InsuranceData.Create.class,
			TransportDetails.Create.class,
			TransportDocument.AssignPatient.class,
			TransportDocument.Create.class,
			TransportDocument.Delete.class,
			TransportDocument.Update.class
	}),
	TransportAdmin(new Class[] {
			Address.Update.class,
			ServiceProvider.Move.class,
			ServiceProvider.Update.class,
			User.Create.class,
			User.Delete.class,
			User.UpdateRole.class
	}),
	TransportDoctor(new Class[] {
			Insurance.Create.class,
			Insurance.Update.class,
			Insurance.Move.class,
			InsuranceData.Create.class,
			TransportDetails.AssignTransportProvider.class,
			TransportDetails.Create.class,
			TransportDetails.Delete.class,
			TransportDetails.Update.class,
			TransportDetails.UpdatePatientSignature.class,
			TransportDetails.UpdateTransporterSignature.class,
			TransportDocument.AssignPatient.class,
			TransportDocument.Create.class,
			TransportDocument.Delete.class,
			TransportDocument.Update.class
	}),
	TransportInvoice(new Class[] {
			TransportDetails.AssignTransportProvider.class,
			TransportDetails.Delete.class,
			TransportDetails.Update.class,
			TransportDetails.UpdatePatientSignature.class,
			TransportDetails.UpdateTransporterSignature.class
	}),
	TransportUser(new Class[] {
			TransportDetails.AssignTransportProvider.class,
			TransportDetails.Delete.class,
			TransportDetails.Update.class,
			TransportDetails.UpdatePatientSignature.class,
			TransportDetails.UpdateTransporterSignature.class
	}),
	InsuranceAdmin(new Class[] {
			Address.Update.class,
			Insurance.Update.class,
			Insurance.Move.class,
			User.Create.class,
			User.Delete.class,
			User.UpdateRole.class
	}),
	InsuranceUser(new Class[] {
			InsuranceData.Create.class,
			Patient.Create.class,
			Patient.CreateWithInsuranceData.class,
			Patient.Move.class,
			Patient.Update.class,
			TransportDocument.Archive.class
			
	}),
	SuperUser(new Class[] {
			Address.Update.class,
			Address.Delete.class,
			Insurance.Create.class,
			Insurance.Update.class,
			Insurance.Move.class,
			Insurance.Delete.class,
			InsuranceData.Create.class,
			InsuranceData.Delete.class,
			Patient.Create.class,
			Patient.CreateWithInsuranceData.class,
			Patient.Move.class,
			Patient.Update.class,
			Patient.Delete.class,
			ServiceProvider.Create.class,
			ServiceProvider.CreateFull.class,
			ServiceProvider.Move.class,
			ServiceProvider.Delete.class,
			ServiceProvider.Update.class,
			ServiceProvider.UpdateService.class,
			TransportDetails.AssignTransportProvider.class,
			TransportDetails.Create.class,
			TransportDetails.Delete.class,
			TransportDetails.Update.class,
			TransportDetails.UpdatePatientSignature.class,
			TransportDetails.UpdateTransporterSignature.class,
			TransportDocument.AssignPatient.class,
			TransportDocument.Create.class,
			TransportDocument.Delete.class,
			TransportDocument.Update.class,
			User.Create.class,
			User.CreateFull.class,
			User.Delete.class,
			User.UpdateRole.class
	});
	
	private static final long serialVersionUID = -6591375785628765586L;

	private List<Class<?>> allowedCommands = new ArrayList<>();
	
	/**
	 * UserRole
	 * <p>
	 * Enum for defining the user roles and their permissions.
	 * <p>
	 * Constructor requiring a list of commands to be allowed to.
	 * 
	 * @param commands - commands the users of this role are allowed to process
	 */
	private UserRole(Class<?>[] commands) {
		for(Class<?> o : commands)
			allowedCommands.add(o);
		allowedCommands.add(Address.Create.class);
		allowedCommands.add(Address.GetList.class);
		allowedCommands.add(Address.Get.class);
		allowedCommands.add(Insurance.GetList.class);
		allowedCommands.add(Insurance.Get.class);
		allowedCommands.add(InsuranceData.GetList.class);
		allowedCommands.add(InsuranceData.Get.class);
		allowedCommands.add(Patient.GetList.class);
		allowedCommands.add(Patient.Get.class);
		allowedCommands.add(ServiceProvider.GetList.class);
		allowedCommands.add(ServiceProvider.Get.class);
		allowedCommands.add(TransportDetails.GetList.class);
		allowedCommands.add(TransportDetails.Get.class);
		allowedCommands.add(TransportDocument.GetList.class);
		allowedCommands.add(TransportDocument.Get.class);
		allowedCommands.add(TransportDocument.GetList.class);
		allowedCommands.add(TransportDocument.Get.class);
		allowedCommands.add(User.GetList.class);
		allowedCommands.add(User.Get.class);
		allowedCommands.add(User.LoginUser.class);
		allowedCommands.add(User.Update.class);
		allowedCommands.add(User.UpdateCredentials.class);
	}
	
	/**
	 * method getAllowedActions
	 * <p>
	 * Method to gget the commands the role is allowed to process.
	 * 
	 * @return List<Class<?>> - the list of commands the users of this role is allowed to process
	 */
	public List<Class<?>> getAllowedActions(){
		return allowedCommands;
	}
}

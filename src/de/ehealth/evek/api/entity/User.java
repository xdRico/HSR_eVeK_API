package de.ehealth.evek.api.entity;

import java.io.Serializable;

import de.ehealth.evek.api.exception.GetListThrowable;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.exception.ProcessingException;
import de.ehealth.evek.api.type.Id;
import de.ehealth.evek.api.type.Reference;
import de.ehealth.evek.api.type.UserRole;
import de.ehealth.evek.api.util.COptional;

/**
 * record User
 * <p>
 * defines user entity with its required properties for handling.
 * 
 * @property Id
 * @property Last name
 * @property First name
 * @property Address
 * @property Service provider
 * @property Role
 */
public record User(
		Id<User> id,
		String lastName,
		String firstName,
		Reference<Address> address,
		Reference<ServiceProvider> serviceProvider,
		UserRole role
		) implements Serializable {
	
    private static final long serialVersionUID = -3485673255491246872L;

    /**
   	 * interface Command
   	 * <p>
   	 * Command interface permitting service provider commands:
   	 * 
   	 * @permits Create
   	 * @permits CreateFull
   	 * @permits Update
   	 * @permits Delete
   	 * @permits UpdateRole
   	 * @permits UpdateCredentials
   	 * @permits LoginUser
   	 * @permits Get
   	 * @permits GetList
   	 */
	public static sealed interface Command extends Serializable permits 
	Create, CreateFull, Update, Delete, UpdateRole, UpdateCredentials, LoginUser, Get, GetList {		
	}
	
	/**
	 * record Create
	 * <p>
	 * Command to create a user.
	 * 
	 * @property User name
	 * @property Password
	 * @property Last name
	 * @property First name
	 * @property Address
	 * @property Service provider
	 * @property Role
	 */
	public static record Create(
			String userName,
			String password,
			String lastName,
			String firstName,
			Reference<Address> address,
			Reference<ServiceProvider> serviceProvider,
			UserRole role) implements Command{	
	}
	
	/**
	 * record CreateFull
	 * <p>
	 * Command to create a user including creating the address and service provider.
	 * 
	 * @property User name
	 * @property Password
	 * @property Last name
	 * @property First name
	 * @property Address Create command
	 * @property Service provider CreateFull command
	 * @property Role
	 */
	public static record CreateFull(
			String userName,
			String password,
			String lastName,
			String firstName,
			Address.Create addressCmd,
			ServiceProvider.CreateFull serviceProviderCmd,
			UserRole role) implements Command{	
	}
	
	/**
	 * record Delete
	 * <p>
	 * Command to delete a user.
	 * 
	 * @property Id
	 */
	public static record Delete(Id<User> id) implements Command{	
	}
	
	/**
	 * record Update
	 * <p>
	 * Command to update a user.
	 * 
	 * @property Id
	 * @property Last name
	 * @property First name
	 * @property Address
	 * @property Service provider
	 */
	public static record Update(
			Id<User> id, 
			String lastName,
			String firstName,
			Reference<Address> address,
			Reference<ServiceProvider> serviceProvider
			) implements Command {
	}
	
	/**
	 * record UpdateRole
	 * <p>
	 * Command to update the role of a user.
	 * 
	 * @property Id
	 * @property Role
	 */
	public static record UpdateRole(
			Id<User> id, 
			UserRole role) implements Command {	
	}
	
	/**
	 * record UpdateCredentials
	 * <p>
	 * Command to update the login credentials of a user.
	 * 
	 * @property Old user name
	 * @property New User name
	 * @property Old Password
	 * @property New Password
	 */
	public static record UpdateCredentials(
			Id<User> oldUserName, 
			String newUserName, 
			String oldPassword, 
			String newPassword) implements Command {
	}
	
	/**
	 * record LoginUser
	 * <p>
	 * Command to login a user to the system.
	 * 
	 * @property User name
	 * @property Password
	 */
	public static record LoginUser(
			String userName, 
			String password) implements Command {	
	}
	
	/**
	 * record Get
	 * <p>
	 * Command to get existing user by its Id.
	 * 
	 * @property Id
	 */
	public static record Get(Id<User> id) implements Command {
	}
	
	/**
	 * record GetList
	 * <p>
	 * Command to get a List of users by a provided filter.
	 * 
	 * @property Filter
	 */
	public static record GetList(Filter filter) implements Command {
	}
	
	/**
	 * record Filter
	 * <p>
	 * Command to use as Filter for GetList command.
	 * 
	 * @property COptional:Last name
	 * @property COptional:First name
	 * @property COptional:Address
	 * @property COptional:Service provider
	 * @property COptional:Role
	 */
	public static record Filter(COptional<String> lastName, COptional<String> firstName, 
			COptional<Reference<Address>> address,
			COptional<Reference<ServiceProvider>> serviceProvider, COptional<UserRole> role) {
	}

	/**
	 * interface Operations
	 * <p>
	 * Interface providing main methods for user entity.
	 */	
	public static interface Operations {
		/**
		 * method process
		 * <p>
		 * Method for processing Commands requiring a User as processing User for permission management.
		 * 
		 * @param cmd - Command to be processed
		 * @param processingUser - User to be used as processing User
		 * 
		 * @return User - the User that was processed
		 * 
		 * @throws GetListThrowable - throwable to be thrown by GetList-Command
		 * @throws IllegalProcessException - exception to be thrown when Arguments or State are not valid for an operation
		 * @throws ProcessingException - exception to be thrown when a process fails by technical problems
		 */
		User process(Command cmd, Reference<User> processingUser) 
				throws GetListThrowable, IllegalProcessException, ProcessingException;		
	}

	/**
	 * method updateWith
	 * <p>
	 * Method returning a new user with the given properties.
	 * 
	 * @param newLastName - the last name to be set
	 * @param newFirstName - the first name to be set
	 * @param newAddress - the address to be set
	 * @param newServiceProvider - the service provider to be set
	 * 
	 * @return User - the updated user Object
	 */
	public User updateWith(
			String newLastName,
			String newFirstName,
			Reference<Address> newAddress,
			Reference<ServiceProvider> newServiceProvider) {
		return new User(this.id, newLastName, newFirstName, newAddress, 
				newServiceProvider, this.role);
	}
	
	/**
	 * method updateWith
	 * <p>
	 * Method returning a new user with the given role.
	 * 
	 * @param newRole - the role to be set
	 * 
	 * @return User - the updated user Object
	 */
	public User updateWith(UserRole newRole) {
		return new User(this.id, this.lastName, this.firstName, this.address, 
				this.serviceProvider, newRole);
	}
	
	/**
	 * method updateCredentials
	 * <p>
	 * Method returning a new user with the given credentials.
	 * 
	 * @param oldUserName - the old user name
	 * @param newUserName - the user name to be set
	 * @param oldPassword - the old password
	 * @param newPassword - the password to be set
	 * 
	 * @return User - the updated user Object
	 * @throws IllegalProcessException - thrown, when old username does not match object username (id)
	 */
	public User updateCredentials(Id<User> oldUserName, Id<User> newUserName) throws IllegalProcessException {
		if(oldUserName != this.id 
				&& oldUserName.value() != this.id.value())
			throw new IllegalProcessException(new IllegalArgumentException());
		return new User(newUserName, this.lastName, this.firstName, this.address, 
				this.serviceProvider, this.role);
	}
	
	@Override
	public String toString() {
		return String.format(
				"User[id=%s, lastName=%s, firstName=%s, "
				+ "address=%s, serviceProvider=%s, "
				+ "role=%s]", 
				id,
				lastName,
				firstName,
				address.toString(),
				serviceProvider.toString(),
				role.toString());
	}
}

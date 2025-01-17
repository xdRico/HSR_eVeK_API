package de.ehealth.evek.api.network;

import java.io.IOException;
import java.io.Serializable;

import de.ehealth.evek.api.exception.EncryptionException;

/**
 * interface IComSender
 * <p>
 * Base interface for sending objects.
 */
interface IComSender {
	
	/**
	 * method sendAsObject
	 * <p>
	 * Method called to send objects.
	 * 
	 * @param object - the object (serializable) to send
	 * 
	 * @throws IOException - when an error occurs on transmission
	 */
	void sendAsObject(Serializable object) throws IOException;
	
	/**
	 * method sendKey
	 * <p>
	 * Method called to send an public encryption key as ComEncryptionKey.
	 * 
	 * @param encryptionKey - the ComEncryptionKey to send
	 * 
	 * @throws IOException - when an error occurs on transmission
	 */
	default void sendKey(ComEncryptionKey encryptionKey) throws IOException{
		sendAsObject(encryptionKey);

	}
	
	/**
	 * method useEncryption
	 * <p>
	 * Method called to initialize and use encryption for the connection.
	 * 
	 * @param receiver - the IComReceiver to receive and decrypt encrypted objects
	 *
	 * @throws EncryptionException - when an error occurs on initializing encryption
	 */
	ComEncryption useEncryption(IComReceiver receiver) throws EncryptionException;
	
	/**
	 * method testConnection
	 * 
	 * Method to send a test package to the server to ensure it is still connected.
	 * 
	 * @return boolean - if the connection is still established
	 */
	default boolean testConnection() {
		try {
			sendAsObject(new ConnectionTest());
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}

package de.ehealth.evek.api.network;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.MGF1ParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.util.Log;

/**
 * ComEncryption
 * <p>
 * Class used for the encryption handling of the IComSender and IComReceiver.
 */
public class ComEncryption {
	
	private boolean useEncryption = false;
	
	private Cipher decryptionCipher;
	private Cipher encryptionCipher;
	
	private boolean isServer;
	
	private IComSender sender;
	
	private IComClientReceiver receiver;
	
	
	@SuppressWarnings("exports")
	/**
	 * ComEncryption
	 * <p>
	 * Used for the encryption handling of the IComSender and IComReceiver.
	 * <p>
	 * Constructor requiring the IComReceiver and IComSender of the connection.
	 * 
	 * @param receiver - the IComReceiver to use for receiving
	 * @param sender - the IComSender to use for sending
	 * 
	 * @throws IllegalProcessException - thrown, when either a sender or receiver is null or sender and receiver are not the same type (Server / Client)
	 */
	public ComEncryption(IComReceiver receiver, IComSender sender) throws IllegalProcessException {
		if(receiver == null || sender == null)
			throw new IllegalProcessException(
					new IllegalArgumentException("IComServerReceiver and IComServerSender shall not be null!"));
		if(receiver instanceof IComClientReceiver 
				&& sender instanceof IComClientSender) {
			isServer = false;
			this.receiver = (IComClientReceiver) receiver;
		}
		else if(receiver instanceof IComServerReceiver 
				&& sender instanceof IComServerSender)
			isServer = true;
		else throw new IllegalProcessException(
				new IllegalArgumentException("IComReceiver and IComSender have to be of the same type (Server or Client)!"));
		
		this.sender = sender;
	}
	
	/**
	 * !CLIENT ONLY!
	 * <p>
	 * method useEncryption
	 * <p>
	 * Method to initialize and activate encryption of the connection
	 * 
	 * @throws EncryptionException - thrown when initializing of the encryption fails
	 */
	public void useEncryption() throws EncryptionException {
		try {
			useEncryption(getCipherRSAInstance());
		} catch (NoSuchAlgorithmException e) {
			Log.sendMessage("Encryption handler could not be initialized!");
			Log.sendException(e);
			throw new EncryptionException(e);
		}
	}
	
	/**
	 * !CLIENT ONLY!
	 * <p>
	 * method useEncryption
	 * <p>
	 * Method to initialize and activate encryption of the connection.
	 * 
	 * @param algorithm - the algorithm to use for the encryption (i.E. "RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
	 * 
	 * @throws EncryptionException - thrown when initializing of the encryption fails
	 * @throws NoSuchAlgorithmException - thrown when the provided Algorithm is not valid
	 */
	private void useEncryption(String algorithm) throws EncryptionException, NoSuchAlgorithmException {
		try {
			if(isServer)
				throw new EncryptionException("This method is explicitly for Clients! Use useEncryption([ComEncryptionKey serverPublicKey]) instead!");
			Log.sendMessage("Initializing encryption handler...");
			
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair keys = generator.generateKeyPair();
			
			decryptionCipher = Cipher.getInstance(algorithm);
			decryptionCipher.init(Cipher.DECRYPT_MODE, keys.getPrivate(), ComEncryption.defaultOAEPParams());
			
			sender.sendKey(new ComEncryptionKey(keys.getPublic()));
			ComEncryptionKey receivedKey = receiver.receivePublicKey();
			
			encryptionCipher = Cipher.getInstance(algorithm);
			encryptionCipher.init(Cipher.ENCRYPT_MODE, receivedKey.getKey(), ComEncryption.defaultOAEPParams());
			
			useEncryption = !(decryptionCipher == null || encryptionCipher == null);
			
		}catch(EncryptionException | NoSuchAlgorithmException e) {
			throw e;
		}catch(Exception e) {
			Log.sendMessage("Encryption handler could not be initialized!");
			Log.sendException(e);
			throw new EncryptionException(e);
		}
	}

	/**
	 * !SERVER ONLY!
	 * <p>
	 * method useEncryption
	 * <p>
	 * Method to initialize and activate encryption of the connection.
	 * 
	 * @param serverPublicKey - Public Key of the client that got received
	 * 
	 * @throws EncryptionException - thrown when initializing of the encryption fails
	 */
	public void useEncryption(ComEncryptionKey serverPublicKey) throws EncryptionException {
		try {
			useEncryption(serverPublicKey, getCipherRSAInstance());
		}catch(NoSuchAlgorithmException e) {
			Log.sendMessage("Encryption handler could not be initialized!");
			Log.sendException(e);
			throw new EncryptionException(e);
		}
	}
	
	/**
	 * !SERVER ONLY!
	 * <p>
	 * method useEncryption
	 * <p>
	 * Method to initialize and activate encryption of the connection.
	 * 
	 * @param serverPublicKey - Public Key of the client that got received
	 * @param algorithm - the algorithm to use for the encryption (i.E. "RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
	 * 
	 * @throws EncryptionException - thrown when initializing of the encryption fails
	 * @throws NoSuchAlgorithmException - thrown when the provided Algorithm is not valid
	 */
	private void useEncryption(ComEncryptionKey serverPublicKey, String algorithm) throws EncryptionException, NoSuchAlgorithmException {
		try {
			if(!isServer)
				throw new EncryptionException("This method is explicitly for Servers! Use useEncryption() instead!");

			encryptionCipher = Cipher.getInstance(algorithm);
			encryptionCipher.init(Cipher.ENCRYPT_MODE, serverPublicKey.getKey(), ComEncryption.defaultOAEPParams());
			
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair keys = generator.generateKeyPair();
			
			decryptionCipher = Cipher.getInstance(algorithm);
			decryptionCipher.init(Cipher.DECRYPT_MODE, keys.getPrivate(), ComEncryption.defaultOAEPParams());
			
			sender.sendKey(new ComEncryptionKey(keys.getPublic()));
			
			useEncryption = !(decryptionCipher == null || encryptionCipher == null);
			
		}catch(EncryptionException | NoSuchAlgorithmException e) {
			throw e;	
		}catch(Exception e) {
			Log.sendMessage("Encryption handler could not be initialized!");
			Log.sendException(e);
			throw new EncryptionException(e);
		}
	}
	
	/**
	 * method getObject
	 * <p>
	 * Method to get the received Object as decrypted object.
	 * 
	 * @param inputObject - the received object
	 * 
	 * @return Serializable - the received, decrypted object
	 * 
	 * @throws EncryptionException - thrown when decryption of the object fails
	 */
	public Serializable getObject(Serializable inputObject) throws EncryptionException {
		if(!(inputObject instanceof ComEncryptedObject)
				|| !useEncryption)
			return inputObject;
		return ((ComEncryptedObject)inputObject).decryptObject(getDecryptionCipher());
	}
	
	/**
	 * method encryptObject
	 * <p>
	 * Method to encrypt the given serializable object to a ComEncryptedObject
	 * 
	 * @param object - the object to be encrypted
	 * 
	 * @return ComEncryptedObject - the as ComEncryptedObject encrypted object
	 * 
	 * @throws EncryptionException - thrown when encryption of the object fails
	 */
	public ComEncryptedObject encryptObject(Serializable object) throws EncryptionException {
		return new ComEncryptedObject(getEncryptionCipher(), object);
	}
	
	/**
	 * method getDecryptionCipher
	 * <p>
	 * Method to get the decryption Cipher
	 * 
	 * @return Cipher - the Cipher instance used for decryption
	 * 
	 * @throws EncryptionException - thrown when Cipher us null or encryption not in usage
	 */
	private Cipher getDecryptionCipher() throws EncryptionException {
		if(decryptionCipher == null)
			throw new EncryptionException("Cipher not yet set up!");
		if(!useEncryption)
			throw new EncryptionException("Encryption not in usage!");
		return decryptionCipher;
	}
	
	/**
	 * method getEncryptionCipher
	 * <p>
	 * Method to get the encryption Cipher
	 * 
	 * @return Cipher - the Cipher instance used for encryption
	 * 
	 * @throws EncryptionException - thrown when Cipher us null or encryption not in usage
	 */
	private Cipher getEncryptionCipher() throws EncryptionException {
		if(encryptionCipher == null)
			throw new EncryptionException("Cipher not yet set up!");
		if(!useEncryption)
			throw new EncryptionException("Encryption not in usage!");
		return encryptionCipher;
	}
	
	/**
	 * method getCipherRSAInstance
	 * <p>
	 * Method to get the currently used Cipher instance
	 * 
	 * @return String - the current Cipher instance
	 */
	private static String getCipherRSAInstance() {
		//TODO create custom Cipher Instances
		return defaultCipherRSAInstance();
	}
	
	/**
	 * method defaultCipherRSAInstance
	 * <p>
	 * Method to get the default Cipher instance with RSA encryption
	 * 
	 * @return String - the default Cipher instance
	 */
	private static String defaultCipherRSAInstance() {
		return "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
	}
	
	/**
	 * method defaultOAEPParams
	 * <p>
	 * Method to get the default OAEP Parameters for encryption
	 * 
	 * @return OAEPParameterSpec - the default OAEP Parameters
	 */
	private static OAEPParameterSpec defaultOAEPParams() {
		return new OAEPParameterSpec(
			    "SHA-256",                      // Hash-Algorithmus
			    "MGF1",                         // Mask Generating Function (MGF)
			    MGF1ParameterSpec.SHA256,       // MGF1 mit SHA-256
			    PSource.PSpecified.DEFAULT      // Standardwert für PSource
			);
	}
}

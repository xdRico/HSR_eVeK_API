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
import de.ehealth.evek.api.util.Log;

public class ComEncryption {
	
	private boolean useEncryption = false;
	
	private Cipher decryptionCipher;
	private Cipher encryptionCipher;
	
	private boolean isServer;
	private IComSender sender;
	private IComClientReceiver receiver;
	
	
	@SuppressWarnings("exports")
	public ComEncryption(IComReceiver receiver, IComSender sender) throws IllegalArgumentException{
		if(receiver == null || sender == null)
			throw new IllegalArgumentException("IComServerReceiver and IComServerSender shall not be null!");
		if(receiver instanceof IComClientReceiver 
				&& sender instanceof IComClientSender) {
			isServer = false;
			this.receiver = (IComClientReceiver) receiver;
		}
		else if(receiver instanceof IComServerReceiver 
				&& sender instanceof IComServerSender)
			isServer = true;
		else throw new IllegalArgumentException("IComReceiver and IComSender have to be of the same type (Server or Client)!");
		
		this.sender = sender;
	}
	
	//ClientOnly
	public void useEncryption() throws EncryptionException {
		try {
			useEncryption(getCipherRSAInstance());
		} catch (NoSuchAlgorithmException e) {
			Log.sendMessage("Encryption handler could not be initialized!");
			Log.sendException(e);
			throw new EncryptionException(e);
		}
	}
	
	//ClientOnly
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

	//ServerOnly
	public void useEncryption(ComEncryptionKey serverPublicKey) throws EncryptionException {
		try {
			useEncryption(serverPublicKey, getCipherRSAInstance());
		}catch(NoSuchAlgorithmException e) {
			Log.sendMessage("Encryption handler could not be initialized!");
			Log.sendException(e);
			throw new EncryptionException(e);
		}
	}
	
	//ServerOnly
	private void useEncryption(ComEncryptionKey serverPublicKey, String algorithm) throws EncryptionException, NoSuchAlgorithmException {
		try {
			if(!isServer)
				//throw new EncryptionException("This method is explicitly for Servers! Use useEncryption([String algorithm]) instead!");
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
	
	public Serializable getObject(Serializable inputObject) throws EncryptionException {
		if(!(inputObject instanceof ComEncryptedObject)
				|| !useEncryption)
			return inputObject;
		return ((ComEncryptedObject)inputObject).decryptObject(getDecryptionCipher());
	}
	
	public ComEncryptedObject encryptObject(Serializable object) throws EncryptionException {
		return new ComEncryptedObject(getEncryptionCipher(), object);
	}
	
	private Cipher getDecryptionCipher() throws EncryptionException {
		if(decryptionCipher == null)
			throw new EncryptionException("Cipher not yet set up!");
		if(!useEncryption)
			throw new EncryptionException("Encryption not in usage!");
		return decryptionCipher;
	}
	
	private Cipher getEncryptionCipher() throws EncryptionException {
		if(encryptionCipher == null)
			throw new EncryptionException("Cipher not yet set up!");
		if(!useEncryption)
			throw new EncryptionException("Encryption not in usage!");
		return encryptionCipher;
	}
	
	private static String getCipherRSAInstance() {
		//TODO create custom Cipher Instances
		return defaultCipherRSAInstance();
	}
	
	private static String defaultCipherRSAInstance() {
		return "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
	}
	
	private static OAEPParameterSpec defaultOAEPParams() {
		return new OAEPParameterSpec(
			    "SHA-256",                      // Hash-Algorithmus
			    "MGF1",                         // Mask Generating Function (MGF)
			    MGF1ParameterSpec.SHA256,       // MGF1 mit SHA-256
			    PSource.PSpecified.DEFAULT      // Standardwert für PSource
			);
	}
}

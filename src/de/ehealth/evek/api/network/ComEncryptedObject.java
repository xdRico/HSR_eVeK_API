package de.ehealth.evek.api.network;

import java.io.Serializable;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.util.Log;

/**
 * ComEncryptionObject
 * <p>
 * Class used for symmetrical encryption and transmission of objects and their asymmetrical encrypted key.
 * 
 * @implements Serializable
 */
public class ComEncryptedObject implements Serializable {
	
	private static final long serialVersionUID = -2909115155234428410L;
	
	private final SealedObject sealedObject;
	
	private final String aesKey;
	
	/**
	 * ComEncryptedObject
	 * <p>
	 * Class used for symmetrical encryption and transmission of objects and their asymmetrical encrypted key.
	 * <p>
	 * Constructor requires Cipher for the asymmetric encryption of the symmetrical key and the object to symmetricaly encrypt
	 * 
	 * @param rsaEncryptionCipher - the Cipher used for the asymmetric encryption of the key
	 * @param object - the object to encrypt symmetricaly
	 * 
	 * @throws EncryptionException - thrown when the encryption process fails
	 */
	ComEncryptedObject(Cipher rsaEncryptionCipher, Serializable object) throws EncryptionException {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256); // 256-Bit AES
			SecretKey aesKey = keyGen.generateKey();
			
			byte[] encryptedAesKey = rsaEncryptionCipher.doFinal(aesKey.getEncoded());
			
			// 3. Verschlüssele die Daten mit AES
			Cipher aesCipher = Cipher.getInstance("AES");
			aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
			this.sealedObject = new SealedObject(object, aesCipher);
			
			// Rückgabe: Base64-kodierter AES-Schlüssel und verschlüsselte Daten
			this.aesKey = Base64.getEncoder().encodeToString(encryptedAesKey);
		} catch(Exception e) {
			Log.sendException(e);
			Log.sendMessage("Object could not be encrypted!");
			throw new EncryptionException(e);
		}

	}
	
	/**
	 * method decryptObject
	 * <p>
	 * Method to get the decrypted Object when provided with the fitting Cipher
	 * 
	 * @param rsaDecryptionCipher - the decryption cipher for the asymmetrical encryption
	 * 
	 * @return Serializable - the Object that was transmitted and encrypted
	 * 
	 * @throws EncryptionException - thrown when the decryption process fails
	 */
	Serializable decryptObject(Cipher rsaDecryptionCipher) throws EncryptionException {

		if(rsaDecryptionCipher == null)
			throw new EncryptionException(sealedObject, "No valid Cipher!");
		
		try {
			
			byte[] aesKeyBytes = rsaDecryptionCipher.doFinal(Base64.getDecoder().decode(aesKey));
			if (aesKeyBytes.length != 16 && aesKeyBytes.length != 24 && aesKeyBytes.length != 32) {
			    throw new EncryptionException("Ungültige AES-Schlüssellänge: " + aesKeyBytes.length);
			}
	        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

	        // 2. Entschlüssele die Daten mit AES
	        Cipher aesCipher = Cipher.getInstance("AES");
	        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
			return (Serializable) sealedObject.getObject(aesCipher);
		} catch (Exception e) {
			Log.sendException(e);
			Log.sendMessage("Object could not be decrypted!");
			throw new EncryptionException(sealedObject, e);
		}
	}
	
}

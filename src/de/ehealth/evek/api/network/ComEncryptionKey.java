package de.ehealth.evek.api.network;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.util.Log;

/**
 * ComEncryptionKey
 * <p>
 * Class used for transmitting public encryption key.
 * 
 * @implements Serializable
 */
public class ComEncryptionKey implements Serializable {

	private static final long serialVersionUID = -6725179533634741300L;

	private final String encodedKey;
	
	/**
	 * ComEncryptionKey
	 * <p>
	 * Class used for transmitting public encryption key.
	 * <p>
	 * Constructor requires the public key for the encryption.
	 * 
	 * @param publicKey - the public key to use by the opposit connection side
	 */
	public ComEncryptionKey(PublicKey publicKey) {
		this.encodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}
	
	/**
	 * method getKey
	 * <p>
	 * Method to get the plain public key provided by the opposit connection side.
	 * 
	 * @return PublicKey - the public key to use for encrypt
	 * @throws EncryptionException - thrown when an exception occures
	 */
	public PublicKey getKey() throws EncryptionException {
		try {
			byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
			return KeyFactory.getInstance("RSA").generatePublic(keySpec);	
		} catch(Exception e) {
			Log.sendException(e);
			Log.sendMessage("Could not read public Key!");
			throw new EncryptionException(e);
		}
	}
}

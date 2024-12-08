package de.ehealth.evek.api.network;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.util.Log;

public class ComEncryptionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725179533634741300L;

	private final String encodedKey;
	
	public ComEncryptionKey(PublicKey publicKey) {
		this.encodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}
	
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

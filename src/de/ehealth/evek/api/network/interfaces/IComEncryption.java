package de.ehealth.evek.api.network.interfaces;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.MGF1ParameterSpec;

import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import de.ehealth.evek.api.exception.EncryptionException;

public interface IComEncryption {

	default KeyPair useEncryption(String algorithm) throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(2048);
		
		return generator.generateKeyPair();
	}
	
	default KeyPair useEncryption() throws EncryptionException {
		try {
			return useEncryption("RSA");
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		}
	}
	
	public static String defaultCipherRSAInstance() {
		return "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
	}
	
	public static OAEPParameterSpec defaultOAEPParams() {
		return new OAEPParameterSpec(
			    "SHA-256",                      // Hash-Algorithmus
			    "MGF1",                         // Mask Generating Function (MGF)
			    MGF1ParameterSpec.SHA256,       // MGF1 mit SHA-256
			    PSource.PSpecified.DEFAULT      // Standardwert für PSource
			);
	}
}

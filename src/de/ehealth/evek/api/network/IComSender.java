package de.ehealth.evek.api.network;

import java.io.IOException;
import java.io.Serializable;

import de.ehealth.evek.api.exception.EncryptionException;

interface IComSender {
	
	void sendAsObject(Serializable object) throws IOException;
	
	default void sendKey(ComEncryptionKey encryptionKey) throws IOException{
		sendAsObject(encryptionKey);

	}
	
	ComEncryption useEncryption(IComReceiver receiver) throws EncryptionException;
}

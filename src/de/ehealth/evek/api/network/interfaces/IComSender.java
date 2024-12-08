package de.ehealth.evek.api.network.interfaces;

import java.io.IOException;
import java.io.Serializable;

import de.ehealth.evek.api.network.ComEncryptionKey;

interface IComSender {
	
	default void sendKey(ComEncryptionKey encryptionKey) throws IOException {
		sendAsObject(encryptionKey);
	}
	
	void sendAsObject(Serializable object) throws IOException;
	
	void setKeyToUse(ComEncryptionKey encryptionKey);

}

package de.ehealth.evek.api.network.interfaces;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.crypto.Cipher;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.network.ComEncryptedObject;
import de.ehealth.evek.api.network.ComEncryptionKey;
import de.ehealth.evek.api.util.Log;

public class ComSender implements IComSender {

	protected ComEncryptionKey publicKey;
	protected ObjectOutputStream objSender;
	protected Cipher encryptCipher;
	
	protected ComSender(Socket socket) throws IOException {
		try {
			this.objSender = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			Log.sendException(e);
			throw e;
		}
	}

	@Override
	public void sendAsObject(Serializable object) throws IOException {
		
		if(publicKey == null) {
			objSender.writeObject(object);
			return;
		}
		try {
			if(encryptCipher == null) {
				encryptCipher = Cipher.getInstance(IComEncryption.defaultCipherRSAInstance());
				encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey.getKey(), IComEncryption.defaultOAEPParams());
			}
			ComEncryptedObject sealedObject = new ComEncryptedObject(encryptCipher, object);
			objSender.writeObject(sealedObject);
		}catch(Exception e){
			Log.sendException(e);
			if(e instanceof IOException)
				throw (IOException) e;
			if(e instanceof EncryptionException)
				throw new IOException(e);
			throw new IOException(new EncryptionException(e));
		}
		
	}

	@Override
	public void setKeyToUse(ComEncryptionKey publicKey) {
		this.publicKey = publicKey;
		try {
			encryptCipher = Cipher.getInstance(IComEncryption.defaultCipherRSAInstance());
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey.getKey(), IComEncryption.defaultOAEPParams());

		} catch (Exception e) {
			Log.sendException(e);
			Log.sendMessage("Cipher could not be set up!");
		}
	}

}

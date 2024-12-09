package de.ehealth.evek.api.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.util.Log;

public class ComSender implements IComSender {

	protected ObjectOutputStream objSender;
	protected ComEncryption encryption;
	
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
		try {
			if(encryption == null || object instanceof ComEncryptionKey) {
				objSender.writeObject(object);
				return;
			}
			ComEncryptedObject sealedObject = encryption.encryptObject(object);
			objSender.writeObject(sealedObject);
		}catch(IOException e){
			Log.sendException(e);
			throw e;
		}catch(Exception e){
			Log.sendException(e);
			throw new IOException(e);
		}
		
	}

	@SuppressWarnings("exports")
	@Override
	public ComEncryption useEncryption(IComReceiver receiver) throws EncryptionException {
		this.encryption = new ComEncryption(receiver, this);
		this.encryption.useEncryption();
		return encryption;
	}

}

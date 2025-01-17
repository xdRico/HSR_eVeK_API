package de.ehealth.evek.api.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import de.ehealth.evek.api.exception.EncryptionException;
import de.ehealth.evek.api.exception.IllegalProcessException;
import de.ehealth.evek.api.util.Log;

/**
 * ComSender
 * <p>
 * Standard implementation of IComSender.
 * <p>
 * For Output of e-VeK objects.
 * 
 * @implements IComSender
 */
public class ComSender implements IComSender {
	
	protected ComEncryption encryption;
	
	protected ObjectOutputStream objSender;
	
	/**
	 * ComSender
	 * <p>
	 * Creating OutputStream for sending objects.
	 * Constructor requiring Socket for its stream.
	 * 
	 * @param server - Socket with the server connection
	 * 
	 * @throws IOException - Exception thrown, when ObjectOutputStream cannot be created
	 */
	protected ComSender(Socket socket) throws IOException {
		this.objSender = new ObjectOutputStream(socket.getOutputStream());
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

	@Override
	public ComEncryption useEncryption(@SuppressWarnings("exports") IComReceiver receiver) throws EncryptionException {
		try {
			this.encryption = new ComEncryption(receiver, this);
			this.encryption.useEncryption();
			return encryption;
		}catch(IllegalProcessException e) {
			throw new EncryptionException(e);
		}
	}
}

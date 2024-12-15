package de.ehealth.evek.api.network;

import java.io.IOException;
import java.net.Socket;

/**
 * ComClientSender
 * <p>
 * Standard implementation of IComClientSender.
 * <p>
 * For Client sided Output of e-VeK objects.
 * 
 * @extends ComSender
 * 
 * @implements IComClientSender
 */
public class ComClientSender extends ComSender implements IComClientSender {
	
	/**
	 * ComClientSender
	 * <p>
	 * Creating OutputStream for sending objects.
	 * Constructor requiring Socket for its stream.
	 * 
	 * @param server - Socket with the server connection
	 * 
	 * @throws IOException - Exception thrown, when ObjectOutputStream cannot be created
	 */
	public ComClientSender(Socket client) throws IOException {
		super(client);
	}
}

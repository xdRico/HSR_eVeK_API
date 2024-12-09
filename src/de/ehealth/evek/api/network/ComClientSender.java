package de.ehealth.evek.api.network;

import java.io.IOException;
import java.net.Socket;

public class ComClientSender extends ComSender implements IComClientSender {
	
	public ComClientSender(Socket client) throws IOException {
		super(client);
	}
}

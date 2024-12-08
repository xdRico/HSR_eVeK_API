package de.ehealth.evek.api.network;

import java.io.IOException;
import java.net.Socket;

import de.ehealth.evek.api.network.interfaces.ComSender;
import de.ehealth.evek.api.network.interfaces.IComClientSender;

public class ComClientSender extends ComSender implements IComClientSender {
	
	public ComClientSender(Socket client) throws IOException {
		super(client);
	}
}

package de.ehealth.evek.api.network.interfaces;

import java.io.IOException;

import de.ehealth.evek.api.entity.User;

interface IComSender {
	
	void sendPCUser(User.LoginUser pcUser) throws IOException;


}

module evek.api {
	exports de.ehealth.evek.api.exception;
	exports de.ehealth.evek.api.util;
	exports de.ehealth.evek.api.entity;
	exports de.ehealth.evek.api.type;
	exports de.ehealth.evek.api.network;
	exports de.ehealth.evek.api.network.interfaces;

	requires transitive java.sql;
}
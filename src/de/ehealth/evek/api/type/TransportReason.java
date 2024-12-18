package de.ehealth.evek.api.type;

/**
 * enum TransportReason
 * <p>
 * Enum for defining the reason of a transport
 */
public enum TransportReason {
	EmergencyTransport, 
	FullPartStationary, 
	PrePostStationary,
	AmbulantTaxi, 
	OtherPermitFree, 
	HighFrequent,
	HighFrequentAlike,
	ContinuousImpairment,
	OtherKTW;
	
	@Override
	public String toString(){
		return switch(this) {
		case EmergencyTransport -> "a1) Notfalltransport";
		case FullPartStationary -> "a2) voll-/teilstationäre Krankenhausbehandlung";
		case PrePostStationary -> "a3) vor-/nachstationäre Behandlung";
		case AmbulantTaxi -> "b) ambulante Behandlung (nur Taxi/Mietwagen! - bei Merkzeichen \"aG\", \"Bl\" oder \"H\", Pflegegrad 3 mit dauerhafter Mobilitätsbeeinträchtigung, Pflegegrad 4 oder 5)";
		case OtherPermitFree -> "c) anderer Grund (Genehmigungsfrei, z.B. Fahrten zu Hospizen)";
		case HighFrequent -> "d1) hochfrequente Behandlung (Dialyse, onkol. Chemo- oder Strahlentherapie)";
		case HighFrequentAlike -> "d2) vergleichbarer Ausnahmefall (wie d1, Begründung unter 4. erforderlich)";
		case ContinuousImpairment -> "e) dauerhafte Mobilitätsbeeinträchtigung vergleichbar mit b und Behandlungsdauer mindestens 6 Monate (Begründung unter 4. erforderlich)";
		case OtherKTW -> "f) anderer Grund für Fahrt mit KTW (z.B. fachgerechtes Lagern, Tragen, Heben erforderlich, Begründung unter 3. und ggf. 4. erforderlich)";
		};
	}
}


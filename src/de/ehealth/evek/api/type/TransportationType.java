package de.ehealth.evek.api.type;

/**
 * enum TransportationType
 * <p>
 * Enum for defining the type of a transport vehicle
 */
public enum TransportationType {

	Taxi,
	KTW,
	RTW,
	NAWorNEF,
	Other;
	
	@Override
	public String toString() {
		return switch(this) {
			case Taxi -> "Taxi/Mietwagen";
			case KTW -> "KTW, da medizinisch-fachliche Betreuung und/oder Einrichtung notwendig ist (Begründung unter 4. erforderlich)";
			case RTW -> "RTW";
			case NAWorNEF -> "NAW/NEF";
			case Other -> "andere (Spezifizierung unter 4. erforderlich)";
		};
	}
}

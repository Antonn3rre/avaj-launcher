package flyable;

public final class AircraftFactory {

	// Creation de l'instance unique
	private static AircraftFactory instance;

	// Empeche de creer d'autres instances
	private AircraftFactory() {};

	// Fonction pour recuperer l'instance
	public static AircraftFactory getInstance() {
		if (instance == null) {
			instance = new AircraftFactory();
		}
		return instance;
	}
}

package simulation.flyable;

import simulation.Coordinates;
import java.io.IOException;

public final class AircraftFactory {

	private int idNumber = 1;

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

	private int getNextId() {
		return idNumber++;
	}

	// Create New Aircraft
	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		
		if (p_type.equals("Baloon"))
			return new Baloon(getNextId(), p_name, p_coordinates);
		if (p_type.equals("Helicopter"))
			return new Helicopter(getNextId(), p_name, p_coordinates);
		if (p_type.equals("JetPlane"))
			return new JetPlane(getNextId(), p_name, p_coordinates);
		throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
	}
}

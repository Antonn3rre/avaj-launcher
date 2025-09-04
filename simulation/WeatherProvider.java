package simulation;

public final class WeatherProvider {

	private String[] weather;

	// Creation de l'instance unique
	private static WeatherProvider instance;

	// Empeche de creer d'autres instances
	private WeatherProvider() {};

	// Fonction pour recuperer l'instance
	public static WeatherProvider getInstance() {
		if (instance == null) {
			instance = new WeatherProvider();
		}
		return instance;
	}

	public String getCurrentWeather(Coordinates p_coordinates) {
		// TODO: jsp comment faire
		return ("RAIN");
	}
}

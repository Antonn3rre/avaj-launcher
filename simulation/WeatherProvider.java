package simulation;

public final class WeatherProvider {

	private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

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

		int index = p_coordinates.getLatitude() * p_coordinates.getHeight() + p_coordinates.getLongitude();
		return (weather[index % 4]);
	}
}

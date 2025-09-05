package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;
import simulation.Main;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower tower;
	public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	public void updateConditions() {

		String weather = tower.getWeather(coordinates);

		if (weather.equals("SUN")) {
			coordinates.latitude += 10;
			coordinates.height += 2;
			Main.writeToFile("JetPlane#" + name + "(" + id + "): sunnyyyyyyy 💅\n");
		} else if (weather.equals("RAIN")) {
			coordinates.latitude += 5;
			Main.writeToFile("JetPlane#" + name + "(" + id + "): rainnnnnnyyyyy\n");
		} else if (weather.equals("FOG")) {
			coordinates.latitude += 1;
			Main.writeToFile("JetPlane#" + name + "(" + id + "): foggggyyyyyyyyyy\n");
		} else if (weather.equals("SNOW")) {
			coordinates.height -= 7;
			Main.writeToFile("JetPlane#" + name + "(" + id + "): snooooooowwwwwyyyyyyyyyy\n");
		}
		if (coordinates.height > 100)
			coordinates.height = 100;
		if (coordinates.height <= 0) {
			tower.unregister(this);
			Main.writeToFile("JetPlane#" + name + "(" + id + ") landing\n");
			Main.writeToFile("Tower says: JetPlane#" + name + "(" + id + ") unregistered to weather tower.\n");
		}
	}

	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		Main.writeToFile("Tower says: JetPlane#" + name + "(" + id + ") registered to weather tower.\n");
	}
}

package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;
import simulation.Main;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower tower;
	public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	public void updateConditions() {
		
		String weather = tower.getWeather(coordinates);

		if (weather.equals("SUN")) {
			coordinates.longitude += 10;
			coordinates.height += 2;
			Main.writeToFile("Helicopter#" + name + "(" + id + "): sunny\n");
		} else if (weather.equals("RAIN")) {
			coordinates.longitude += 5;
			Main.writeToFile("Helicopter#" + name + "(" + id + "): can we close the door ? it's getting wet\n");
		} else if (weather.equals("FOG")) {
			coordinates.longitude += 1;
			Main.writeToFile("Helicopter#" + name + "(" + id + "): i don't see anything..\n");
		} else if (weather.equals("SNOW")) {
			coordinates.height -= 12;
			Main.writeToFile("Helicopter#" + name + "(" + id + "): is the rotor getting frozen ?\n");
		}
		if (coordinates.height > 100)
			coordinates.height = 100;
		if (coordinates.height <= 0) {
			tower.unregister(this);
			Main.writeToFile("Helicopter#" + name + "(" + id + ") landing\n");
			Main.writeToFile("Tower says: Helicopter#" + name + "(" + id + ") unregistered to weather tower.\n");
		}
	}

	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		Main.writeToFile("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower.\n");
	}
}

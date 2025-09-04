package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

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
			System.out.println("Helicopter#" + name + "(" + id + "): sunny");
		} else if (weather.equals("RAIN")) {
			coordinates.longitude += 5;
			System.out.println("Helicopter#" + name + "(" + id + "): can we close the door ? it's getting wet");
		} else if (weather.equals("FOG")) {
			coordinates.longitude += 1;
			System.out.println("Helicopter#" + name + "(" + id + "): i don't see anything..");
		} else if (weather.equals("SNOW")) {
			coordinates.height -= 12;
			System.out.println("Helicopter#" + name + "(" + id + "): is the rotor getting frozen ?");
		}
		if (coordinates.height > 100)
			coordinates.height = 100;
		if (coordinates.height <= 0) {
			tower.unregister(this);
			System.out.println("Helicopter#" + name + "(" + id + ") landing");
			System.out.println("Tower says: Helicopter#" + name + "(" + id + ") unregistered to weather tower." );
		}
	}

	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		System.out.println("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower." );
	}
}

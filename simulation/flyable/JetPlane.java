package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

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
			System.out.println("JetPlane#" + name + "(" + id + "): sunnyyyyyyy 💅");
		} else if (weather.equals("RAIN")) {
			coordinates.latitude += 5;
			System.out.println("JetPlane#" + name + "(" + id + "): rainnnnnnyyyyy");
		} else if (weather.equals("FOG")) {
			coordinates.latitude += 1;
			System.out.println("JetPlane#" + name + "(" + id + "): foggggyyyyyyyyyy");
		} else if (weather.equals("SNOW")) {
			coordinates.height -= 7;
			System.out.println("JetPlane#" + name + "(" + id + "): snooooooowwwwwyyyyyyyyyy");
		}
		if (coordinates.height > 100)
			coordinates.height = 100;
		if (coordinates.height <= 0) {
			tower.unregister(this);
			System.out.println("JetPlane#" + name + "(" + id + ") landing");
			System.out.println("Tower says: JetPlane#" + name + "(" + id + ") unregistered to weather tower." );
		}
	}

	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		System.out.println("Tower says: JetPlane#" + name + "(" + id + ") registered to weather tower." );
	}
}

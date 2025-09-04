package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

public class Balloon extends Aircraft implements Flyable {

	private WeatherTower tower;

	public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	public void updateConditions() {
		String weather = tower.getWeather(coordinates);

		if (weather.equals("SUN")) {
			coordinates.longitude += 2;
			coordinates.height += 4;
			System.out.println("Balloon#" + name + "(" + id + "): it's hot up there");
		} else if (weather.equals("RAIN")) {
			coordinates.height -= 5;
			System.out.println("Balloon#" + name + "(" + id + "): will the flame go out ??");
		} else if (weather.equals("FOG")) {
			coordinates.height -= 3;
			System.out.println("Balloon#" + name + "(" + id + "): what a beautiful view! yes i'm being ironic");
		} else if (weather.equals("SNOW")) {
			coordinates.height -= 15;
			System.out.println("Balloon#" + name + "(" + id + "): I should have taken my gloves");
		}
		if (coordinates.height > 100)
			coordinates.height = 100;
		if (coordinates.height <= 0) {
			tower.unregister(this);
			System.out.println("Balloon#" + name + "(" + id + ") landing");
			System.out.println("Tower says: Balloon#" + name + "(" + id + ") unregistered to weather tower." );
		}
	}

	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		System.out.println("Tower says: Balloon#" + name + "(" + id + ") registered to weather tower." );
	}
}

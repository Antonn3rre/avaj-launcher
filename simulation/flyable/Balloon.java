package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;
import simulation.Main;

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
			Main.writeToFile("Balloon#" + name + "(" + id + "): it's hot up there\n");
		} else if (weather.equals("RAIN")) {
			coordinates.height -= 5;
			Main.writeToFile("Balloon#" + name + "(" + id + "): will the flame go out ??\n");
		} else if (weather.equals("FOG")) {
			coordinates.height -= 3;
			Main.writeToFile("Balloon#" + name + "(" + id + "): what a beautiful view! yes i'm being ironic\n");
		} else if (weather.equals("SNOW")) {
			coordinates.height -= 15;
			Main.writeToFile("Balloon#" + name + "(" + id + "): I should have taken my gloves\n");
		}
		if (coordinates.height > 100)
			coordinates.height = 100;
		if (coordinates.height <= 0) {
			tower.unregister(this);
			Main.writeToFile("Balloon#" + name + "(" + id + ") landing\n");
			Main.writeToFile("Tower says: Balloon#" + name + "(" + id + ") unregistered to weather tower.\n" );
		}
	}

	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		Main.writeToFile("Tower says: Balloon#" + name + "(" + id + ") registered to weather tower.\n");
	}
}

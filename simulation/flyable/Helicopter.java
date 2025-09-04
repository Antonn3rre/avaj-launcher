package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower tower;
	public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	public void updateConditions() {
		// TODO: do
	}
	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
		tower.register(this);
		System.out.println("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower." );
	}
}

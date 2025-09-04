package flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower tower;
	public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	public void updateConditions() {
		// TODO: do
	}
	public void registerTower(WeatherTower p_tower) {
		tower = p_tower;
	}
}

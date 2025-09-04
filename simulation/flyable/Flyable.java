package flyable;

import simulation.WeatherTower;

public interface Flyable {

//	protected WeatherTower weatherTower;

	void updateConditions();
	void registerTower(WeatherTower p_tower);
}

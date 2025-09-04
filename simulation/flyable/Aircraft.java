package simulation.flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

public class Aircraft {

	protected long id;
	protected String name;
	protected Coordinates coordinates;


	protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
		id = p_id;
		name = p_name;
		coordinates = p_coordinate;
	}
	
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
}

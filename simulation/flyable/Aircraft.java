package flyable;

import simulation.Coordinates;
import simulation.WeatherTower;

public class Aircraft {

	protected long id;
	protected String name;
	protected Coordinates coordinates;

//	public abstract void updateConditions();
//	// TODO: do
//	}

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
		id = p_id;
		name = p_name;
		p_coordinate = coordinates; // operateur = ?
	}
	
}

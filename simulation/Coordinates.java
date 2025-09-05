package simulation;

import simulation.exceptions.InvalidCoordinates;

public class Coordinates {
	public int	longitude;
	public int latitude;
	public int height;

	public Coordinates(int p_longitude, int p_latitude, int p_height) {
		// TODO: check coordinates height + 100 || < 0 and throw exception
		if (p_height > 100 || p_height < 0)
			throw new InvalidCoordinates("height must be between 0 and 100");
		longitude = p_longitude;
		latitude = p_latitude;
		height = p_height;
	}

	public int getLongitude() {
		return longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public int getHeight() {
		return height;
	}
}

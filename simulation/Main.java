package simulation;

import simulation.flyable.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

class Main {
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Wrong number of arguments");
			return;
		}

		WeatherTower tower = new WeatherTower();
		int numSimulation = 0;

		// Open argv[0], parse to create the object
		File outFile = new File(args[0]);
		try (Scanner myReader = new Scanner(outFile)) {

			AircraftFactory factory = AircraftFactory.getInstance();

			if (!myReader.hasNextLine()) {
				System.out.println("The input file is empty");
				return;
			}
			if (!myReader.hasNextInt()) {
				System.out.println("Bad input file");
				return;
			}
			numSimulation = myReader.nextInt();
			myReader.nextLine(); // TODO: check si rien apres  le num
			while (myReader.hasNextLine()) {
				Scanner data = new Scanner(myReader.nextLine());
				String[] tab = new String[5];
				int i = 0;
				while (data.hasNext() && i < 5) {
					tab[i] = data.next();
					i++;
				}
				if (data.hasNext() || i != 5)
					throw new IOException("Bad input file");
				
				// Create new flyable
				// Add flyable to tower
				factory.newAircraft(tab[0], tab[1],
					new Coordinates(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]))
				).registerTower(tower);
			}
			myReader.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}		

		try {
		// Open simulation.txt
			File inFile = new File("simulation.txt");
			
			// If exists but not writable or cannot be created
			if ((inFile.exists() && !inFile.canWrite()) || (!inFile.exists() && !inFile.createNewFile())) {
				System.out.println("Error opening simulation.txt");
				return;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		for (int i = 0; i < numSimulation; i++) {
			tower.changeWeather();
		}

	}
}

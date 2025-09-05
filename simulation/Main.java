package simulation;

import simulation.flyable.*;

import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;
import java.lang.Throwable;
import simulation.exceptions.*;

public class Main {

	private static FileWriter outFile;
	private static int numSimulation = 0;

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Wrong number of arguments");
			return;
		}

		try {
			openOutputFile();
		} catch (OutfileError e) {
			System.out.println(e.getMessage());
			return ;
		}

		WeatherTower tower = new WeatherTower();
		
		if (parseInputFile(args[0], tower) == 0)
			return;
		
		for (int i = 0; i < numSimulation; i++) {
			tower.changeWeather();
		}
		
		closeOutputFile();
		tower.removeAllFlyables();
	}

	public static void	openOutputFile() {
		try {
		// Open simulation.txt
			File file = new File("simulation.txt");
			
			// If exists but not writable or cannot be created
			if ((file.exists() && !file.canWrite()))
				throw new OutfileError("Can't write in file");
			if ((!file.exists() && !file.createNewFile()))
				throw new OutfileError("Error creating simulation.txt");
			outFile = new FileWriter(file);
		} catch (IOException e) {
			throw new OutfileError(e.getMessage());
		}
	}

	public static void writeToFile(String s) {
		try {
			outFile.write(s);
		} catch (IOException e) {
			throw new OutfileError(e.getMessage());
		}
	}

	public static void	closeOutputFile() {
		try {
			outFile.close();
			outFile = null;
		} catch (IOException e) {
			throw new OutfileError(e.getMessage());
		}
	}

	private static int parseInputFile(String arg, WeatherTower tower) {

		// Open argv[0], parse to create the object
		File inFile = new File(arg);
		try (Scanner myReader = new Scanner(inFile)) {

			AircraftFactory factory = AircraftFactory.getInstance();

			if (!myReader.hasNextLine())
				throw new InvalidArguments("the input file is empty");

			boolean first = true;
			while (myReader.hasNextLine()) {
				try (Scanner data = new Scanner(myReader.nextLine())) {
					
					// If first line, get the amount of simulations
					if (first) {
						if (!data.hasNextInt())
							throw new InvalidArguments("first line must be a number");
						numSimulation = data.nextInt();
						if (data.hasNext())
							throw new InvalidArguments("first line must only contain a number");
						first = false;
						data.close();
						continue;
					}

					String[] tab = new String[5];
					int i = 0;
					while (data.hasNext() && i < 5) {

						if (i >= 2 && !data.hasNextInt())
							throw new InvalidArguments();
						tab[i] = data.next();
						i++;
					}
					if (data.hasNext() || i != 5)
						throw new InvalidArguments();
				
					// Create new flyable and Add flyable to tower
					factory.newAircraft(tab[0], tab[1],
						new Coordinates(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]))
					).registerTower(tower);

					data.close();
				} catch (Throwable e) {
					throw new IOException(e.getMessage());
				}
			}
			myReader.close();

		} catch (IOException e) {
			closeOutputFile();
			tower.removeAllFlyables();
			System.out.println(e.getMessage());
			return (0);
		}
		return (1);
	}

}

package simulation;

import simulation.flyable.*;

import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;
import java.lang.Throwable;

public class Main {

	private static FileWriter outFile;

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Wrong number of arguments");
			return;
		}

		if (openOutputFile() == 0)
			return;

		WeatherTower tower = new WeatherTower();
		int numSimulation = 0;

		// Open argv[0], parse to create the object
		File inFile = new File(args[0]);
		try (Scanner myReader = new Scanner(inFile)) {

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
				try(Scanner data = new Scanner(myReader.nextLine())) {
					String[] tab = new String[5];
					int i = 0;
					while (data.hasNext() && i < 5) {

						if (i >= 2 && !data.hasNextInt())
							throw new IOException("Wrong int variable");
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
			return;
		}		

		for (int i = 0; i < numSimulation; i++) {
			tower.changeWeather();
		}

		closeOutputFile();
		tower.removeAllFlyables();

	}

	public static int	openOutputFile() {
		try {
		// Open simulation.txt
			File file = new File("simulation.txt");
			
			// If exists but not writable or cannot be created
			if ((file.exists() && !file.canWrite()) || (!file.exists() && !file.createNewFile())) {
				System.out.println("Error opening simulation.txt");
				return (0);
			}
			outFile = new FileWriter(file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return (0);
		}
		return (1);
	}

	public static void writeToFile(String s) {
		try {
			outFile.write(s);
		} catch (IOException e) {
			System.out.println(e.getCause());
			return;  // TODO: mettre mon exception
		}
	}

	public static void	closeOutputFile() {
		try {
			outFile.close();
			outFile = null;
		} catch (IOException e) {
			System.out.println(e.getCause());
		}
	}

}

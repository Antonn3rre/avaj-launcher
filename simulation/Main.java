package simulation;

import simulation.flyable.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

class Main {
	public static void main(String[] args) {
	/*
		Coordinates myCoord;
		myCoord = new Coordinates(5,8,9);
		int tab = myCoord.getHeight();
		System.out.println("Height = " + tab);

		if (args.length != 1) {
			System.out.println("Wrong number of arguments\n");
			return;
		}
		try {
			File myObj = new File(args[0]);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
*/
		if (args.length != 1) {
			System.out.println("Wrong number of arguments");
			return;
		}

		WeatherTower tower = new WeatherTower();

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
			int num = myReader.nextInt(); // TODO: voir ou stocker
			myReader.nextLine(); // TODO: check si rien apres  le num
			System.out.println("Num: " + num);
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
				factory.newAircraft(tab[0], tab[1],
					new Coordinates(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]))
				).registerTower(tower);
				// Add flyable to tower
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

		
	}
}

import Coord.Coordinates;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Main {
	public static void main(String[] args) {
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


	}
}

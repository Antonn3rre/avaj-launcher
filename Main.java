import Coord.Coordinates;

class Main {
	public static void main(String[] args) {
		Coordinates myCoord;
		myCoord = new Coordinates(5,8,9);
		int tab = myCoord.getHeight();
		System.out.println("Height = " + tab);
	}
}

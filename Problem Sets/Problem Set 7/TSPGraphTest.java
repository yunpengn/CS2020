package sg.edu.nus.cs2020;

public class TSPGraphTest {
	public static void MSTTest(String name) {
		TSPMap map = new TSPMap("src/data_TSP/" + name);
		TSPGraph solver = new TSPGraph();
		solver.initialize(map);
		solver.MST();

		if (solver.isValidTour()) {
			System.out.println("This is a valid tour.");
			System.out.println("Tour distance: " + solver.tourDistance());
		}

		map.redraw();
	}

	public static void TSPTest(String name) {
		TSPMap map = new TSPMap("src/data_TSP/" + name);
		TSPGraph solver = new TSPGraph();
		solver.initialize(map);
		solver.MST();
		solver.TSP();

		if (solver.isValidTour()) {
			System.out.println("This is a valid tour.");
		}
		System.out.println("Tour distance: " + solver.tourDistance());

		map.redraw();
	}

	public static void main(String[] args) {
		String[] library = { "tenpoints.txt", "twentypoints.txt", "fiftypoints.txt", 
				"hundredpoints.txt", "thousandpoints.txt", "tenclusters.txt", 
				"fifteenclusters.txt", "twentyclusters.txt", "twentyfiveclusters.txt", 
				"funnylines.txt" };
		// MSTTest(library[2]);
		TSPTest(library[2]);
	}
}

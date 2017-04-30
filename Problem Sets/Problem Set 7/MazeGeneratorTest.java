package sg.edu.nus.cs2020;

import java.io.IOException;
import java.util.Random;

public class MazeGeneratorTest {
	// Random number generator.
	private static Random generator = new Random();
	// The bound for maze map size.
	private static final int BOUND = 15;

	private static void generate(String path) {
		// Randomly generate the size of the maze map.
		int height = generator.nextInt(BOUND) + 1;
		int width = generator.nextInt(BOUND) + 1;
		
		// Initialize the generator.
		MazeGenerator generator = new MazeGenerator(height, width);
		generator.generate(path);
	}

	public static void main(String[] args) {
		// Defines the path to store the result.
		String name1 = "src/data_maze_generated/maze_";
		String name2 = ".out";
		Maze map = null;

		for (int i = 0; i < 10; i++) {
			String path = name1 + i + name2;
			String name = "maze_" + i + name2;
			generate(path);

			try {
				map = Maze.readMaze(path);
			} catch (IOException e) {
				System.err.println("Unable to read maze at" + path);
			}
			System.out.println("==========================================");
			System.out.println("                " + name);
			ImprovedMazePrinter.printMaze(map);
		}
	}
}

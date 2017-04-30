package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class MazeSolverTest {
	@Test
	public void simpleTest() {
		MazeSolver solver = new MazeSolver();
		Maze map = null;

		try {
			map = Maze.readMaze("src/data_maze/maze.txt");
		} catch (IOException e) {
			System.err.println("Unable to read maze.txt");
		}
		solver.initialize(map);

		int endRow = map.getRows() - 1;
		int endCol = map.getColumns() - 1;
		Integer result = solver.pathSearch(0, 0, endRow, endCol);

		// Prints the result to standard output.
		System.out.println("=================================");
		System.out.printf("            %s          \n", "maze.txt");
		System.out.println("The number of moves is " + result);
		ImprovedMazePrinter.printMaze(map);
	}

	@Test
	public void findPathTest() {
		MazeSolver solver = new MazeSolver();

		String[] library = { "maze-dense.txt", "maze-empty.txt", "maze-hdense.txt", 
				"maze-horizontal.txt", "maze-hsnake.txt", "maze-spiral.txt", 
				"maze-vdense.txt", "maze-vsnake.txt", "mymaze.txt", "maze1.txt", 
				"maze2.txt" };

		for (String name : library) {
			Maze map = null;
			try {
				map = Maze.readMaze("src/data_maze/" + name);
			} catch (IOException e) {
				System.err.println("Unable to read " + name);
			}
			solver.initialize(map);

			int endRow = map.getRows() - 1;
			int endCol = map.getColumns() - 1;
			Integer result = solver.pathSearch(0, 0, endRow, endCol);
			String mapName = name.substring(0, name.length() - 4);

			// Prints the result to standard output.
			System.out.println("=================================");
			System.out.printf("            %s          \n", mapName);
			System.out.println("The number of moves is " + result);
			try {
				ImprovedMazePrinter.printMaze(map);
			} catch (Exception e) {
				System.err.println("The printer is faulty.");
			}

		}
	}
	
	@Test
	public void numReachableTest() {
		MazeSolver solver = new MazeSolver();
		Maze map = null;
		try {
			map = Maze.readMaze("src/data_maze/maze.txt");
		} catch (IOException e) {
			System.err.println("Cannot read maze.txt");
		}
		solver.initialize(map);
		solver.pathSearch(0, 0, 3, 3);
		
		int result = solver.numReachable(12);

		assertEquals("numReachable Simple Test", 1, result);
	}
}

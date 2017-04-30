package sg.edu.nus.cs2020;

/**
 * Public Class: MazeSolverNaive
 * 
 * Description: This class solves the shortest path in a maze problem using
 * Depth-First Search method.
 * 
 * @author Joe, a the Average but Enthusiastic Coder.
 */
public class MazeSolverNaive implements IMazeSolver {
	private Maze maze;
	private boolean solved = false;
	private boolean[][] visited;

	// The index for the four directions.
	private final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;

	// Use a 2nd dimensional array to represent the wall at north, south, east
	// and west directions.
	private int[][] ddir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	// Takes note of the destination coordinate.
	private int endRow, endCol;

	public MazeSolverNaive() {
		solved = false;
		maze = null;
	}

	// Inputs a maze to initialize the solver.
	public void initialize(Maze maze) {
		this.maze = maze;
		visited = new boolean[maze.getRows()][maze.getColumns()];
		solved = false;
	}

	// Searches the path from a starting point to an end point
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) {
		if (maze == null) {
			String message = "Oh No! You cannot call me without initializing the maze!";
			throw new IllegalArgumentException(message);
		} else if (startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns()) {
			throw new IllegalArgumentException("Invalid end coordinate");
		} else if (endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns()) {
			throw new IllegalArgumentException("Invalid end coordinate");
		}

		// Set all visited flag to false before we begin our search.
		for (int i = 0; i < maze.getRows(); ++i) {
			for (int j = 0; j < maze.getColumns(); ++j) {
				this.visited[i][j] = false;
				maze.getRoom(i, j).onPath = false;
			}
		}

		this.endRow = endRow;
		this.endCol = endCol;
		solved = true;

		return solve(startRow, startCol, 0);
	}

	// Checks whether we can go to one direction from a certain room.
	private boolean canGo(int row, int col, int dir) {
		// This is not necessary since our maze has a surrounding block of wall.
		if (row + ddir[dir][0] < 0 || row + ddir[dir][0] >= maze.getRows()) {
			return false;
		} else if (col + ddir[dir][1] < 0 || col + ddir[dir][1] >= maze.getColumns()) {
			return false;
		}

		switch (dir) {
		case NORTH:
			return maze.getRoom(row, col).hasNorthWall() == false;
		case SOUTH:
			return maze.getRoom(row, col).hasSouthWall() == false;
		case EAST:
			return maze.getRoom(row, col).hasEastWall() == false;
		case WEST:
			return maze.getRoom(row, col).hasWestWall() == false;
		}

		return false;
	}

	private Integer solve(int row, int col, int rooms) {
		if (visited[row][col]) {
			return null;
		}

		visited[row][col] = true;
		maze.getRoom(row, col).onPath = true;

		// Arrives at the destination.
		if (row == endRow && col == endCol) {
			// Returns the length of the path.
			return rooms;
		}

		// for each of the 4 directions
		for (int direction = 0; direction < 4; direction++) {
			if (canGo(row, col, direction)) {
				// Proceed to that direction if there is no wall.
				Integer soln = solve(row + ddir[direction][0], col + ddir[direction][1], rooms + 1);
				// Returns the result if this direction is successful.
				if (soln != null) {
					return soln;
				}
			}
		}

		maze.getRoom(row, col).onPath = false;
		return null;
	}

	public boolean isSolved() {
		return solved;
	}

	public Integer numReachable(int k) throws Exception {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		try {
			Maze maze = Maze.readMaze("src/data_maze/maze.txt");
			IMazeSolver solver = new MazeSolverNaive();

			solver.initialize(maze);
			System.out.println(solver.pathSearch(0, 0, 3, 3));
			ImprovedMazePrinter.printMaze(maze);

			System.out.println();

			System.out.println(solver.pathSearch(0, 0, 2, 3));
			ImprovedMazePrinter.printMaze(maze);

			System.out.println("Joe is the new cool!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

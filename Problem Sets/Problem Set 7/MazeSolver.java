package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Public Class: MazeSolver
 * 
 * Description: This class implements a simple maze solver using Breadth-first
 * Search (BFS) method. It can find the shortest path from one room to another
 * room.
 * 
 * @author Niu Yunpeng
 */
public class MazeSolver implements IMazeSolver {
	// A simple pair class to store the room coordinate.
	private class Pair {
		private int row;
		private int col;

		public Pair(int x, int y) {
			this.row = x;
			this.col = y;
		}

		@Override
		public boolean equals(Object other) {
			if (other == null) {
				return false;
			} else if (other == this) {
				return true;
			} else if (!(other instanceof Pair)) {
				return false;
			} else {
				Pair pair = (Pair) other;

				return this.row == pair.row && this.col == pair.col;
			}
		}
	}

	// Stores the maze to be solved
	private Maze maze;
	// The number of rows and columns in the maze.
	private int mazeRow;
	private int mazeCol;

	// The starting coordinate from the most recent call of pathSearch.
	private Pair start = new Pair(-1, -1);
	// Checks whether a certain room has been visited.
	private boolean[][] visited = null;
	// Stores the parent room to allow backtrack.
	private Pair[][] parent = null;
	// Stores the minimal number of moves from the current starting coordinate.
	private int[][] numMoves = null;
	// Stores the number of rooms which need exactly k minimal moves to reach.
	private ArrayList<Integer> reachable = null;

	/**
	 * Public Method: void initialize(Maze)
	 * 
	 * Description: Given a certain maze map, this method initializes the solver
	 * class. This method should be called before pathSearch.
	 */
	public void initialize(Maze maze) {
		if (maze == null) {
			throw new IllegalArgumentException("Maze cannot be null.");
		}

		this.maze = maze;
		mazeRow = maze.getRows();
		mazeCol = maze.getColumns();

		start = new Pair(-1, -1);
		visited = new boolean[mazeRow][mazeCol];
		parent = new Pair[mazeRow][mazeCol];
		numMoves = new int[mazeRow][mazeCol];

		reachable = new ArrayList<Integer>();
	}

	/**
	 * Public Method: Integer pathSearch(int, int, int, int)
	 * 
	 * Description: Given the coordinates of source and destination, this method
	 * returns the shortest path in the maze map or null if there is no path.
	 * Breadth-first search (BFS) is adopted here.
	 * 
	 * @return the minimum number of moves from source to destination; or null
	 *         if there is no path.
	 */
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) {
		if (startRow < 0 || startCol < 0 || startRow >= mazeRow || startCol >= mazeCol) {
			throw new IllegalArgumentException("Illegal start position.");
		} else if (endRow < 0 || endCol < 0 || endRow >= mazeRow || endCol >= mazeCol) {
			throw new IllegalArgumentException("Illegal end position.");
		} else if (startRow == start.row && startCol == start.col) {
			// The starting point does not change, return the result directly.
			return numMoves[endRow][endCol];
		}

		// Stores the new starting coordinate.
		this.start = new Pair(startRow, startCol);
		// Stores the destination coordinate.
		Pair destination = new Pair(endRow, endCol);
		
		// Initialization of relevant variables.
		Pair samplePair = new Pair(-1, -1);
		for (int i = 0; i < mazeRow; i++) {
			for (int j = 0; j < mazeCol; j++) {
				maze.getRoom(i, j).onPath = false;
				visited[i][j] = false;
				parent[i][j] = samplePair;
				numMoves[i][j] = -1;
			}
		}

		// Stores the coordinates of all rooms in the current round.
		Queue<Pair> frontier = new LinkedList<Pair>();
		
		// Adds the starting coordinate to the frontier queue.
		frontier.add(start);

		// Stores the distance of the current level.
		int pathLength = 0;

		// Search level by level on the maze map until no more neighbors.
		while (!frontier.isEmpty()) {
			// Stores all the neighboring rooms to visit in the next round.
			ArrayList<Pair> neighbors = new ArrayList<Pair>();
			reachable.add(frontier.size());

			for (Pair now : frontier) {
				// Marks all the rooms in the current level as visited.
				visited[now.row][now.col] = true;
				numMoves[now.row][now.col] = pathLength;

				// Adds all the neighboring rooms of the current room.
				addNeighbors(neighbors, now);
			}

			// Updates the frontier to the next level.
			frontier = new LinkedList<Pair>(neighbors);
			pathLength++;
		}

		if (numMoves[destination.row][destination.col] == -1) {
			return null;
		} else {
			// Marks all rooms on the path as on the path.
			backtrack(destination);
			return numMoves[destination.row][destination.col];
		}
	}

	/**
	 * Private method: void addNeighbors(ArrayList<Pair>, Pair)
	 * 
	 * Description: Given the current room coordinate, this method checks
	 * whether there is any wall on the north/south/east/west. Then, it will add
	 * the rooms which are accessible from here (there is no wall).
	 */
	private void addNeighbors(ArrayList<Pair> neighbors, Pair now) {
		Room currentRoom = maze.getRoom(now.row, now.col);

		// Checks in the four directions respectively. There is no need to do
		// boundary checking since the maze map is surrounded by walls.
		if (!currentRoom.hasNorthWall() && !visited[now.row - 1][now.col]) {
			parent[now.row - 1][now.col] = now;
			neighbors.add(new Pair(now.row - 1, now.col));
		}
		if (!currentRoom.hasSouthWall() && !visited[now.row + 1][now.col]) {
			parent[now.row + 1][now.col] = now;
			neighbors.add(new Pair(now.row + 1, now.col));
		}
		if (!currentRoom.hasEastWall() && !visited[now.row][now.col + 1]) {
			parent[now.row][now.col + 1] = now;
			neighbors.add(new Pair(now.row, now.col + 1));
		}
		if (!currentRoom.hasWestWall() && !visited[now.row][now.col - 1]) {
			parent[now.row][now.col - 1] = now;
			neighbors.add(new Pair(now.row, now.col - 1));
		}
	}

	/**
	 * Private Method: void backtrack(Pair)
	 * 
	 * Description: Given the destination room and the parent nodes, this method
	 * will backtrack from the destination to the source. Along the path, it
	 * will mark all rooms to be onPath (in order to print the maze).
	 */
	private void backtrack(Pair destination) {
		// Starts from the destination room.
		Pair now = destination;

		while (!now.equals(start)) {
			// Marks the current room as on the path.
			Room currentRoom = maze.getRoom(now.row, now.col);
			currentRoom.onPath = true;

			now = parent[now.row][now.col];
		}
		// Marks the start room as on the path.
		Room startRoom = maze.getRoom(start.row, start.col);
		startRoom.onPath = true;
	}

	/**
	 * Public Method: Integer numReachable(int)
	 * 
	 * @return the number of rooms that are reachable from the source as defined
	 *         in the most recent call to pathSearch in k steps.
	 */
	public Integer numReachable(int k) {
		if (start.equals(new Pair(-1, -1))) {
			String message = "There is no recent call of pathSearch on this maze";
			throw new IllegalStateException(message);
		} else if (k < 0) {
			String message = "The number of steps must be larger than 0.";
			throw new IllegalArgumentException(message);
		}
		
		if (k >= reachable.size()) {
			// k is larger than the longest path from the starting room.
			return 0;
		} else {
			return reachable.get(k);
		}
	}
}

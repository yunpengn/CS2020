package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Public Class: MazeSolverWithPower
 * 
 * Description: This class implements a simple maze solver if a few times of
 * super-powers are allowed to destroy the walls.
 * 
 * @author Niu Yunpeng
 */
public class MazeSolverWithPower implements IMazeSolverWithPower {
	// A simple pair class to store the room 2D coordinate.
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
	 * Private Method: void backtrack(Pair[][], Pair)
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


	/************************************************************************
	 * Below is with some super-powers.
	 ************************************************************************/

	// A simple triple class to store the room 3D coordinate.
	private class Triple {
		private int row;
		private int col;
		private int pow;

		public Triple(int x, int y, int z) {
			this.row = x;
			this.col = y;
			this.pow = z;
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
				Triple pair = (Triple) other;

				return this.row == pair.row && this.col == pair.col && 
						this.pow == pair.pow;
			}
		}
	}

	// These two 3D arrays should only be used when there is super-power.
	private boolean[][][] superVisited = null;
	private Triple[][][] superParent = null;
	private Triple superStart = null;
	private int superPowers = 0;

	/**
	 * Public Method: Integer pathSearch(int, int, int, int, int)
	 * 
	 * Description: This method finds out the shortest path from the source to
	 * destination with a few times of super-powers allowed.
	 * 
	 * @return the minimum number of moves from source to destination; or null
	 *         if there is no path.
	 */
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol, 
			int superpowers) {
		if (startRow < 0 || startCol < 0 || startRow >= mazeRow || startCol >= mazeCol) {
			throw new IllegalArgumentException("Illegal start position.");
		} else if (endRow < 0 || endCol < 0 || endRow >= mazeRow || endCol >= mazeCol) {
			throw new IllegalArgumentException("Illegal end position.");
		} else if (superpowers < 0) {
			throw new IllegalArgumentException("Superpowers must be non-negative.");
		} else if (startRow == start.row && startCol == start.col) {
			// The starting point does not change, return the result directly.
			return numMoves[endRow][endCol];
		}

		// Initialization or relevant variables.
		superStart = new Triple(startRow, startCol, 0);
		this.superPowers = superpowers + 1;
		superVisited = new boolean[mazeRow][mazeCol][this.superPowers];
		superParent = new Triple[mazeRow][mazeCol][this.superPowers];

		// Define starting and ending room.
		this.start = new Pair(startRow, startCol);
		Pair destination = new Pair(endRow, endCol);

		// Fills the arrays with dummy values.
		Triple sampleTriple = new Triple(-1, -1, -1);
		for (int i = 0; i < mazeRow; i++) {
			for (int j = 0; j < mazeCol; j++) {
				maze.getRoom(i, j).onPath = false;
				numMoves[i][j] = -1;

				for (int k = 0; k < superpowers; k++) {
					superVisited[i][j][k] = false;
					superParent[i][j][k] = sampleTriple;
				}
			}
		}

		// Stores the coordinates of all rooms in the current round.
		Queue<Triple> frontier = new LinkedList<Triple>();

		// Adds the starting coordinate to the frontier queue.
		frontier.add(superStart);

		// Stores the distance of the current level.
		int pathLength = 0;

		// Stores the number of super-powers used when reaching the destination.
		int superPowerUsed = 0;

		// Search level by level on the maze map until no more neighbors.
		while (!frontier.isEmpty()) {
			// The number of frontiers which appear for the 1st time.
			int newFrontier = 0;

			// Stores all the neighboring rooms to visit in the next round.
			ArrayList<Triple> neighbors = new ArrayList<Triple>();

			for (Triple now : frontier) {
				// Marks all the rooms in the current level as visited.
				superVisited[now.row][now.col][now.pow] = true;
				
				// We will only update the path length when this room has not
				// been visited before.
				if (numMoves[now.row][now.col] == -1) {
					numMoves[now.row][now.col] = pathLength;
					newFrontier++;

					// The first time to visit the destination, record the
					// number of super-powers used.
					if (now.row == destination.row && now.col == destination.col) {
						superPowerUsed = now.pow;
					}
				}

				// Adds all the neighboring rooms of the current room.
				addSuperNeighbors(neighbors, now);
			}

			// Updates the frontier to the next level.
			frontier = new LinkedList<Triple>(neighbors);
			pathLength++;
			reachable.add(newFrontier);
		}

		if (numMoves[destination.row][destination.col] == -1) {
			return null;
		} else {
			// Marks all rooms on the path as on the path.
			superBacktrack(destination, superPowerUsed);
			return numMoves[destination.row][destination.col];
		}
	}

	/**
	 * Private method: void addSuperNeighbors(ArrayList<Triple>, Triple)
	 * 
	 * Description: Given the current room coordinate, this method checks
	 * whether there is any wall on the north/south/east/west. If there is no
	 * wall, do the same as last part; otherwise, use one time of super-power to
	 * destroy the wall. It will not destroy any outer walls.
	 */
	private void addSuperNeighbors(ArrayList<Triple> neighbors, Triple now) {
		Room currentRoom = maze.getRoom(now.row, now.col);

		// Checks in the four directions respectively. Special note: We cannot
		// destroy the outer surrounding walls.
		if (!currentRoom.hasNorthWall()) {
			// No walls between them, do the same thing as last part.
			if (!superVisited[now.row - 1][now.col][now.pow]) {
				superParent[now.row - 1][now.col][now.pow] = now;
				neighbors.add(new Triple(now.row - 1, now.col, now.pow));
			}
		} else if (now.row - 1 >= 0) {
			// There is an inner wall between them, use one super-power.
			if (now.pow + 1 < superPowers && 
					!superVisited[now.row - 1][now.col][now.pow + 1]) {
				// To check whether there is any super-power left.
				superParent[now.row - 1][now.col][now.pow + 1] = now;
				neighbors.add(new Triple(now.row - 1, now.col, now.pow + 1));
			}
		}

		if (!currentRoom.hasSouthWall()) {
			// No walls between them, do the same thing as last part.
			if (!superVisited[now.row + 1][now.col][now.pow]) {
				superParent[now.row + 1][now.col][now.pow] = now;
				neighbors.add(new Triple(now.row + 1, now.col, now.pow));
			}
		} else if (now.row + 1 < mazeRow) {
			// There is an inner wall between them, use one super-power.
			if (now.pow + 1 < superPowers && 
					!superVisited[now.row + 1][now.col][now.pow + 1]) {
				// To check whether there is any super-power left.
				superParent[now.row + 1][now.col][now.pow + 1] = now;
				neighbors.add(new Triple(now.row + 1, now.col, now.pow + 1));
			}
		}

		if (!currentRoom.hasEastWall()) {
			// No walls between them, do the same thing as last part.
			if (!superVisited[now.row][now.col + 1][now.pow]) {
				superParent[now.row][now.col + 1][now.pow] = now;
				neighbors.add(new Triple(now.row, now.col + 1, now.pow));
			}
		} else if (now.col + 1 < mazeCol) {
			// There is an inner wall between them, use one super-power.
			if (now.pow + 1 < superPowers && 
					!superVisited[now.row][now.col + 1][now.pow + 1]) {
				// To check whether there is any super-power left.
				superParent[now.row][now.col + 1][now.pow + 1] = now;
				neighbors.add(new Triple(now.row, now.col + 1, now.pow + 1));
			}
		}

		if (!currentRoom.hasWestWall()) {
			// No walls between them, do the same thing as last part.
			if (!superVisited[now.row][now.col - 1][now.pow]) {
				superParent[now.row][now.col - 1][now.pow] = now;
				neighbors.add(new Triple(now.row, now.col - 1, now.pow));
			}
		} else if (now.col - 1 >= 0) {
			// There is an inner wall between them, use one super-power.
			if (now.pow + 1 < superPowers && 
					!superVisited[now.row][now.col - 1][now.pow + 1]) {
				// To check whether there is any super-power left.
				superParent[now.row][now.col - 1][now.pow + 1] = now;
				neighbors.add(new Triple(now.row, now.col - 1, now.pow + 1));
			}
		}
	}

	/**
	 * Private Method: void backtrack(Pair, int)
	 * 
	 * Description: Given the destination room and the parent nodes, this method
	 * will backtrack from the destination to the source (according to the times
	 * of super-powers used). Along the path, it will mark all rooms to be on
	 * the path (in order to print the maze).
	 */
	private void superBacktrack(Pair destination, int superPower) {
		// Starts from the destination room.
		Triple now = new Triple(destination.row, destination.col, superPower);

		while (now.row != start.row || now.col != start.col) {
			// Marks the current room as on the path.
			Room currentRoom = maze.getRoom(now.row, now.col);
			currentRoom.onPath = true;

			now = superParent[now.row][now.col][now.pow];
		}
		// Marks the start room as on the path.
		Room startRoom = maze.getRoom(start.row, start.col);
		startRoom.onPath = true;
	}
}

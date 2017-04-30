package sg.edu.nus.cs2020;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Public Class: MazeGenerator
 * 
 * Description: This class implements a maze generator using recursive backtrack
 * method, which is similar to a randomized DFS. For more information, see
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 * 
 * @author Niu Yunpeng
 */
public class MazeGenerator {
	// Stores the size of the maze generated.
	private int height = 0;
	private int width = 0;

	// Characters to represent walls.
	private final char WALL = '#';
	private final char SPACE = ' ';

	// Random number generator
	private Random generator = null;

	// Represent spaces and walls as a 2nd dimensional array of characters.
	private char[][] maze = null;

	// Checks whether a certain room has been visited.
	private boolean[][] visited = null;

	// The neighboring room on the four directions respectively.
	private int[][] neighbors = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	// File Writer to output to the disk text file.
	private FileWriter fWriter = null;

	/**
	 * Public Constructor: MazeGenerator(int, int)
	 * 
	 * Description: This method defines the size of the output maze.
	 */
	public MazeGenerator(int height, int width) {
		this.height = height;
		this.width = width;
		maze = new char[height * 2 + 1][width * 2 + 1];
		visited = new boolean[height][width];

		generator = new Random();
	}

	/**
	 * Public Method: void generate(String)
	 * 
	 * @param path
	 *            is the filename to save the maze as.
	 */
	public void generate(String path) {
		// Initialization of relevant arrays.
		Arrays.fill(maze[0], WALL);
		for (int i = 1; i < height * 2 + 1; i += 2) {
			maze[i][0] = WALL;
			for (int j = 1; j < width * 2 + 1; j += 2) {
				maze[i][j] = SPACE;
				maze[i][j + 1] = WALL;
			}
			Arrays.fill(maze[i + 1], WALL);
		}

		// Sets all visited tag as false.
		for (boolean[] line : visited) {
			Arrays.fill(line, false);
		}

		// Pick up a random starting point.
		int startH = generator.nextInt(height);
		int startW = generator.nextInt(width);

		// Do recursive backtrack (randomized DFS)
		DFS(startH, startW);

		// Saves the output maze map.
		try {
			fWriter = new FileWriter(path);
		} catch (IOException e) {
			System.err.println("Unable to output as a text file.");
		}
		saveMaze();
		try {
			fWriter.close();
		} catch (IOException e) {
			System.err.println("Unable to close the file writer.");
		}
	}

	/**
	 * Private Method: void DFS(char[][])
	 * 
	 * Description: This method performs a randomized DFS on the maze. It will
	 * destroy the wall between the current room and the next room (randomly
	 * chosen among 4 neighbors).
	 */
	private void DFS(int x, int y) {
		visited[x][y] = true;
		int[] dirOrder = shuffle();

		for (int dir : dirOrder) {
			int nextX = x + neighbors[dir][0];
			int nextY = y + neighbors[dir][1];
			if (canGo(nextX, nextY)) {
				destroyWall(x, y, dir);
				DFS(nextX, nextY);
			}
		}
	}

	/**
	 * Private Method: int[] shuffle()
	 * 
	 * Description: This method returns a random permutation of the four
	 * directions, so that we can make a randomized DFS traverse. Knuth's
	 * Shuffle algorithm is applied here.
	 * 
	 * @return an array of length 4.
	 */
	private int[] shuffle() {
		int[] direction = { 0, 1, 2, 3 };

		for (int i = 1; i < 4; i++) {
			int n = generator.nextInt(i);
			int temp = direction[n];
			direction[n] = direction[i];
			direction[i] = temp;
		}

		return direction;
	}

	/**
	 * Private Method: boolean canGo(int, int)
	 * 
	 * Description: This method returns whether we can go to a certain room. It
	 * has both boundary check and visited check.
	 */
	private boolean canGo(int x, int y) {
		if (x < 0 || x >= height) {
			return false;
		} else if (y < 0 || y >= width) {
			return false;
		} else {
			return !visited[x][y];
		}
	}

	/**
	 * Private Method: void destroyWall(int, int, int)
	 * 
	 * Description: Given a certain room, this method destroys the surrounding
	 * wall on one direction.
	 */
	private void destroyWall(int x, int y, int dir) {
		int wallX = x * 2 + 1 + neighbors[dir][0];
		int wallY = y * 2 + 1 + neighbors[dir][1];

		maze[wallX][wallY] = SPACE;
	}

	/**
	 * Private Method: void saveMaze(String)
	 * 
	 * Description: This method saves the output maze to a given location.
	 */
	private void saveMaze() {
		for (char[] line : maze) {
			String str = new String(line);

			try {
				fWriter.write(str);
				fWriter.write(10);
			} catch (IOException e) {
				System.err.println("Unable to write " + str);
			}
		}
	}
}

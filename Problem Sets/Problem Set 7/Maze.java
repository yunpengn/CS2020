package sg.edu.nus.cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
	// Stores the size of the maze
	private int rows, columns;

	private Room[][] room;

	// Characters to represent walls.
	private final static char WALL = '#';

	public Maze() {
		rows = 0;
		columns = 0;
		room = null;
	}

	// Get a room by its row and column (index starts from 0).
	public Room getRoom(int row, int column) {
		if (row >= rows || column >= columns || row < 0 || column < 0) {
			throw new IllegalArgumentException();
		}

		return room[row][column];
	}

	// Get the number of rows in the maze.
	public int getRows() {
		return rows / 2;
	}

	// Get the number of columns in the maze.
	public int getColumns() {
		return columns / 2;
	}

	// Reads in a ASCII description of a maze and returns that maze object.
	public static Maze readMaze(String fileName) throws IOException {
		// File and buffer reader for text file input.
		FileReader fileIn = new FileReader(fileName);
		BufferedReader bufferIn = new BufferedReader(fileIn);

		// The returned maze object.
		Maze maze = new Maze();

		List<String> input = new ArrayList<String>();
		String line = null;

		// Reads in each line from the text file.
		while ((line = bufferIn.readLine()) != null) {
			if (maze.columns == 0 && line.length() > 0) {
				maze.columns = line.length();
			} else if (line.length() == 0) {
				// reach a new line => end of input
				break;
			} else if (line.length() != maze.columns) {
				bufferIn.close();
				throw new IOException("Invalid input format");
			}

			maze.columns = Math.max(maze.columns, line.length());
			maze.rows++;
			input.add(line);
		}

		if (maze.rows == 0 || maze.rows % 2 == 0 || maze.columns % 2 == 0) {
			bufferIn.close();
			throw new IOException("Invalid input format");
		}

		// The collection of all rooms in this maze.
		maze.room = new Room[maze.rows / 2][maze.columns / 2];

		for (int i = 1; i < maze.rows - 1; i += 2) {
			for (int j = 1; j < maze.columns - 1; j += 2) {
				boolean north = input.get(i - 1).charAt(j) == WALL;
				boolean south = input.get(i + 1).charAt(j) == WALL;
				boolean east = input.get(i).charAt(j + 1) == WALL;
				boolean west = input.get(i).charAt(j - 1) == WALL;

				maze.room[i / 2][j / 2] = new Room(north, south, east, west);
			}
		}

		// Checks whether there is still something left.
		assert (!bufferIn.ready());

		// Closes both file and buffer readers.
		fileIn.close();
		bufferIn.close();

		return maze;
	}
}

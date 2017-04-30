package sg.edu.nus.cs2020;

public class Room {
	// Stores whether there is a wall in four directions.
	private boolean westWall, eastWall, northWall, southWall;
	// Stores whether it is on the path, must be public (for printing of mazes).
	public boolean onPath;

	public Room(boolean north, boolean south, boolean east, boolean west) {
		northWall = north;
		southWall = south;
		eastWall = east;
		westWall = west;

		onPath = false;
	}

	public boolean hasWestWall() {
		return westWall;
	}

	public boolean hasEastWall() {
		return eastWall;
	}

	public boolean hasNorthWall() {
		return northWall;
	}

	public boolean hasSouthWall() {
		return southWall;
	}
}

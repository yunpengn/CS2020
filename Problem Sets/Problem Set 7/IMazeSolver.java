package sg.edu.nus.cs2020;

public interface IMazeSolver {
	/**
	 * (Re-)Initialize the solver with a maze
	 */
	void initialize(Maze maze);

	/**
	 * Finds the shortest path from a given starting coordinate to an ending
	 * coordinate.
	 * 
	 * @return minimum moves from start to end
	 * @return null if there is no path from start to end
	 * @throws Exception
	 */
	Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception;

	/**
	 * Returns the number of rooms that are reachable from the starting
	 * coordinate as defined in the most recent call to pathSearch in k steps
	 * 
	 * @param k
	 * @return number of reachable rooms
	 * @throws Exception
	 */
	Integer numReachable(int k) throws Exception;
}

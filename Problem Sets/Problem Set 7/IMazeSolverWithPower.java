package sg.edu.nus.cs2020;

public interface IMazeSolverWithPower extends IMazeSolver {
	/**
	 * Finds the shortest path from a given starting coordinate to an ending
	 * coordinate with a fixed number of Powers given
	 * 
	 * @return minimum moves from start to end; null if there is no path from
	 *         start to end.
	 */
	Integer pathSearch(int startRow, int startCol, int endRow, int endCol, int superpowers) throws Exception;
}

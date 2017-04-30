package sg.edu.nus.cs2020;

public interface IApproximateTSP {
	/**
	 * Set up your object with a new map.
	 */
	public void initialize(TSPMap map);
	
	/**
	 * MST: Calculate an MST for the specified map. For each point in the map,
	 * its link should point to its parent.
	 */
	void MST();
	
	/**
	 * TSP: Calculate a solution to the TSP problem, using the approximation
	 * algorithm described in problem set 7. Assume that the tour (and the DFS)
	 * should start with node 0. For each point in the map, its link should
	 * point to the next point on the tour.
	 */
	void TSP();
	
	/**
	 * isValidTour: If the links in the map form a valid tour, then return true.
	 * Otherwise, return false.
	 */
	boolean isValidTour();
	
	/**
	 * tourDistance: returns the total distance of the tour, as specified by the
	 * links in the map. If the links do not from a valid tour, then return -1.
	 */
	double tourDistance();

}

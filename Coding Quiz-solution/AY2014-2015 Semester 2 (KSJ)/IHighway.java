package cs2020;

/*
 * interface IHighway
 * 
 * This interface is part of the 2015 Coding Quiz for CS2020.
 * 
 * See the problem for a complete description of the requirements.  In brief:
 * 
 * (1) addCustomer should run in O(1) time.  (Amortized O(1) is ok.)  The number of customers
 * is initially unknown and unbounded.  If the location specified is invalid (i.e., not a location
 * on the highway), then this method should throw an IllegalArgumentException.
 * 
 * (2) numberOfStations takes one parameter: the range.  Any customer whose distance from the closest
 * station is <= range is considered to be "within range" of the station.  This method should return the
 * minimum number of stations k such that it is possible to deploy k stations so that every customer is
 * within range of a least one station.  The method should run in O(n log n) time, where n is the number
 * of customers.
 * 
 * (3) findSmallestRange takes one parameter: the number of stations.  You are allowed to deploy at 
 * most numStations stations.  What is the minimum range needed to ensure that we can deploy the stations
 * so that every customer is within range of at least one station?  The method should run in O(n log n + n log H) 
 * time where n is the number of customers and H is the length of the highway.
 * 
 * See the JUnit tests and the Coding Quiz for some examples of how these methods are used.
 * 
 */
public interface IHighway {

	// Adds a new customer at the specified location on the highway
	public void addCustomer(int location) throws IllegalArgumentException;
	
	// If each station has a given range, determine the number of stations needed
	// to ensure that every customer is within range of at least one station.
	public int numberOfStations(int range) throws IllegalArgumentException;
	
	// Finds the smallest range so that we can cover every customer using at most numStations stations.
	// That is, if this function returns the integer R, then if we call the function numberOfStations(R),
	// we should get back an answer of at most numStations.
	public int findSmallestRange(int numStations) throws IllegalArgumentException;
}

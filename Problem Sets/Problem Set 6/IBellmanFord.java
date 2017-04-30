package sg.edu.nus.cs2020;

/**
 * Public Interface: IBellmanFord
 * 
 * Description: Simple Bellman-Ford interface for an object that performs the
 * Bellman-Ford algorithm on a Graph.
 */
public interface IBellmanFord {
	public void constructPath(int source, int destination);
	
	public boolean hasNegativeCycle();
	
	public String getPath();
}

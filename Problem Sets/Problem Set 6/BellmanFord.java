package sg.edu.nus.cs2020;

import java.util.Arrays;

/**
 * Public Class: BellmanFord
 * 
 * Description: This class implements the Bellman-Ford algorithm to find the
 * shortest path length from one node to the other in a graph. It is also able
 * to check whether there is a negative cycle in the graph.
 * 
 * @author Niu Yunpeng
 */
public class BellmanFord implements IBellmanFord {
	// Stores the Adjacency Matrix of the current Graph to run Bellman-Ford on.
	private AdjacencyMatrixGraph m_amg;
	
	// Tracks who is the current parent vertex, for each vertex in the graph.
	private int[] m_predecessors;
	
	// Stores the total distance of each vertex from the source vertex.
	private int[] m_distanceFromSource;
	
	// Stores the number of nodes in the Graph.
	private int m_numOfNodes = -1;

	// Stores the source and destination vertices chosen for this iteration. The
	// index should start from 0.
	private int m_source = -1;
	private int m_destination = -1;

	// Stores whether the Graph has at least one negative cycle. -1 means the
	// method hasNegativeCycle has not been executed before, 0 means no negative
	// cycle, 1 means at least 1 negative cycle.
	private int hasNegativeCycle = -1;


	/**
	 * Public Constructor: BellmanFord(AdjacencyMatrixGraph)
	 * 
	 * Description: Initialize with the graph to run Bellman-Ford algorithm on
	 * with an adjacency matrix representation.
	 */
	public BellmanFord(AdjacencyMatrixGraph amg) {
		if (amg == null) {
			throw new IllegalArgumentException("Graph cannot be null");
		}

		// Initializes the graph and stores it in the current class.
		this.m_amg = amg;

		// Initializes the arrays of predecessors and distances according to the
		// number of nodes (vertices) in the graph.
		this.m_numOfNodes = amg.getNumberOfVertices();
		this.m_predecessors = new int[this.m_numOfNodes];
		this.m_distanceFromSource = new int[this.m_numOfNodes];
	}
	
	/**
	 * Public Method: void constructPath(int, int)
	 * 
	 * Description: This method constructs the shortest path from the source
	 * node to the destination node.
	 */
	public void constructPath(int source, int destination) {
		// Checks whether the source and destination index is legal.
		if (source < 0 || source >= this.m_numOfNodes) {
			throw new IllegalArgumentException("The source index is illegal.");
		}
		if (destination < 0 || destination >= this.m_numOfNodes) {
			throw new IllegalArgumentException("The destination index is illegal");
		}

		// Updates the index for source and destination node.
		this.m_source = source;
		this.m_destination = destination;

		// Checks whether the graph has a negative cycle. If so, we are not able
		// to compute the shortest path and have to throw a runtime exception.
		if (this.hasNegativeCycle == 1) {
			throw new IllegalStateException("The graph has negative cycle");
		}

		// Initializes the predecessors and distances from the source.
		Arrays.fill(m_predecessors, -1);
		Arrays.fill(m_distanceFromSource, Integer.MAX_VALUE);
		m_distanceFromSource[source] = 0;

		// Checks whether there is any improvement in the last round.
		boolean status = true;

		// Core part for Bellman-Ford algorithm.
		for (int i = 0; i <= m_numOfNodes; i++) {
			// Terminates earlier if there is no more improvement.
			if (!status) {
				return;
			} else {
				status = false;

				for (int m = 0; m < m_numOfNodes; m++) {
					for (int n = 0; n < m_numOfNodes; n++) {
						boolean thisStatus = relax(m, n);
						status = status || thisStatus;
					}
				}
			}
		}

		// The method has not been terminated until n + 1 rounds, which means
		// there exists at least 1 negative cycle in the Graph.
		this.hasNegativeCycle = 1;
		throw new IllegalStateException("The graph has negative cycle");
	}
	
	/**
	 * Private Method: boolean relax(int, int)
	 * 
	 * Description: This method executes the relax operation for a given edge in
	 * the Graph.
	 * 
	 * @return whether the relax has been done on this edge.
	 */
	private boolean relax(int m, int n) {
		int weight = m_amg.getEdgeWeight(m, n);

		// First checks whether the edge exists. The edge exists if this edge's
		// corresponding weight is not equal to 0.
		if (weight != 0) {
			if (m_distanceFromSource[n] > m_distanceFromSource[m] + weight) {
				m_distanceFromSource[n] = m_distanceFromSource[m] + weight;
				m_predecessors[n] = m;

				return true;
			}
		}

		return false;
	}

	/**
	 * Public Method: boolean hasNegativeCycle()
	 * 
	 * Description: This method checks if at least 1 negative cycle exists in
	 * the Graph.
	 */
	public boolean hasNegativeCycle() {
		// Avoids duplicate execution if this has been checked before.
		if (this.hasNegativeCycle != -1) {
			return this.hasNegativeCycle == 1;
		}

		// Initializes the predecessors and distances from the source.
		Arrays.fill(m_predecessors, -1);
		Arrays.fill(m_distanceFromSource, Integer.MAX_VALUE);
		m_distanceFromSource[0] = 0;

		// Checks whether there is any improvement in the last round.
		boolean status = true;

		// Core part for Bellman-Ford algorithm.
		for (int i = 0; i <= m_numOfNodes; i++) {
			// No negative cycle in the Graph if the algorithm terminates before
			// complete of n + 1 rounds.
			if (!status) {
				this.hasNegativeCycle = 0;
				return false;
			} else {
				status = false;

				for (int m = 0; m < m_numOfNodes; m++) {
					for (int n = 0; n < m_numOfNodes; n++) {
						boolean thisStatus = relax(m, n);
						status = status || thisStatus;
					}
				}
			}
		}

		this.hasNegativeCycle = 1;
		return true;
	}
	
	/**
	 * Public Method: String getPath()
	 * 
	 * Description: This method represents the shortest path computed recently
	 * by a string and returns the string.
	 */
	public String getPath() {
		int currentVertex = this.m_destination;
		
		StringBuilder prepender = new StringBuilder();
		StringBuilder resultHolder = new StringBuilder("" + this.m_destination);
		
		while (currentVertex != this.m_source) {
			currentVertex = this.m_predecessors[currentVertex];
			
			prepender.append(currentVertex);
			prepender.append(" -> ");
			prepender.append(resultHolder.toString());
			
			resultHolder.replace(0, resultHolder.length(), prepender.toString());
			prepender.delete(0, prepender.length());
		}
		
		return resultHolder.toString();
	}
}

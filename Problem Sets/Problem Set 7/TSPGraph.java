package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Public Class: TSPGraph
 * 
 * Description: This class provides an approximation solution for Traveling
 * Salesman Problem (TSP). Minimum Spanning Tree (MST) and Depth-first search
 * are both applied in it.
 * 
 * @author Niu Yunpeng
 */
public class TSPGraph implements IApproximateTSP {
	// A simple class to represent node in a graph.
	private class Node {
		private ArrayList<Integer> adjacentNodes = new ArrayList<Integer>();

		public void addEdge(int other) {
			adjacentNodes.add(other);
		}
	}

	// A simple class to represent edge in a graph.
	private class Edge implements Comparable<Edge> {
		private int x;
		private int y;
		private double distance;

		public Edge(int x, int y, double distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge other) {
			// Edges are compared based on their weights, for Kruskal¡¯s Algorithm.
			if (this.distance < other.distance) {
				return -1;
			} else if (this.distance == other.distance) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// The map containing all points and distances between them.
	private TSPMap map = null;
	// Number of points in the map.
	private int numNodes = 0;
	// An adjacent list representation of the MST.
	private Node[] graph = null;
	// The DFS visiting order according to discovery time (i.e, the first time visited).
	private ArrayList<Integer> dfsOrder = null;

	/**
	 * Public Method: void initialize(TSPMap)
	 * 
	 * Description: This method reads in a map of all the points. It should be
	 * always called before the two methods MST() and TSP(). When the method
	 * returns, each point in the map should have its link set to its parent in
	 * the minimum spanning tree (MST).
	 */
	public void initialize(TSPMap map) {
		if (map == null) {
			throw new IllegalArgumentException("Map cannot be null.");
		}

		this.map = map;
		numNodes = map.getCount();
		graph = new Node[numNodes];
		dfsOrder = new ArrayList<Integer>();
	}

	/**
	 * Public Method: void MST()
	 * 
	 * Description: This method finds the minimum spanning tree of the given map
	 * using Kruskal¡¯s Algorithm.
	 */
	public void MST() {
		// Stores all edges and remaining edges in MST.
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<Edge> mstEdges = new ArrayList<Edge>();

		// Initialization of all edges and sort them according to weights.
		for (int i = 0; i < numNodes; i++) {
			for (int j = i + 1; j < numNodes; j++) {
				edges.add(new Edge(i, j, map.pointDistance(i, j)));
			}
		}
		Collections.sort(edges);

		// Union-find data structure for use.
		UnionFind ufds = new UnionFind(numNodes);

		// Adds edge if it does not form a cycle.
		for (Edge edge : edges) {
			int x = edge.x;
			int y = edge.y;
			
			if (!ufds.isSameSet(x, y)) {
				ufds.unionSet(x, y);
				mstEdges.add(edge);
			}
		}

		// Constructs a MST (adjacent list) according to the remaining edges.
		for (int i = 0; i < numNodes; i++) {
			graph[i] = new Node();
		}

		// Adds all (undirected) edges in the MST.
		for (Edge e : mstEdges) {
			graph[e.x].addEdge(e.y);
			graph[e.y].addEdge(e.x);
		}

		// Adds links between the points in the map in tree traversing order. It
		// is okay to define any node to be the root, we choose 0 for ease.
		DFS(0);
	}

	/**
	 * Private Method: void DFS(int)
	 * 
	 * Description: This method performs a DFS walk on the MST generated to add
	 * links between points and prepare for later TSP.
	 * 
	 * @param nowIndex
	 *            is the node that is currently being visited in DFS walk.
	 */
	private void DFS(int nowIndex) {
		Node nowNode = graph[nowIndex];
		dfsOrder.add(nowIndex);

		// A recursive implementation of DFS on a tree (graph).
		for (int adjacentIndex : nowNode.adjacentNodes) {
			// This condition is equal to the visited array in standard DFS, to
			// check whether adajcentNode is an ancestor of nowNode.
			if (map.getLink(nowIndex) != adjacentIndex) {
				// Meanwhile, add the link from child to parent in the tree.
				map.setLink(adjacentIndex, nowIndex, false);
				DFS(adjacentIndex);
			}
		}
	}

	/**
	 * Public Method: void TSP()
	 * 
	 * Description: This method implements an approximation algorithm for TSP
	 * problem. It will perform a complete DFS walk on the map and create some
	 * shortcuts by skipping some points.
	 * 
	 * Property: This algorithm is a 2-approximation scheme. The result is at
	 * most twice as long as the optimal tour.
	 */
	public void TSP() {
		// According to the DFS traverse order, we reset the link for each point.
		for (int i = 0; i < numNodes - 1; i++) {
			map.setLink(dfsOrder.get(i), dfsOrder.get(i + 1), false);
		}
		// Connects back to the starting point.
		map.setLink(dfsOrder.get(numNodes - 1), dfsOrder.get(0), false);
	}

	/**
	 * Public Method: boolean isValidTour()
	 * 
	 * Description: This method checks whether the linking in the map forms a
	 * valid tour. A valid tour will visit each city in the map exactly once and
	 * form a cycle (it will return to the start point). This is also known as a
	 * Hamiltonian Cycle.
	 * 
	 * Property: This does that guarantee the tour is shortest, which means this
	 * will not yield an optimal solution for Traveling Salesman Problem.
	 */
	public boolean isValidTour() {
		int startIndex = 0;
		int nowIndex = map.getLink(startIndex);
		int count = 0;
		boolean[] visited = new boolean[numNodes];
		Arrays.fill(visited, false);

		// Stops when the tour comes back to the start point.
		while (nowIndex != startIndex) {
			if (nowIndex < 0 || nowIndex >= numNodes) {
				// Contains an illegal link, return false.
				return false;
			} else if (visited[nowIndex]) {
				// If this point has been visited before, return false.
				return false;
			} else {
				// Mark the current point as visited.
				visited[nowIndex] = true;
				// Increment the counter by 1.
				count++;
				// Go to the next point.
				nowIndex = map.getLink(nowIndex);
			}
		}
		count++;

		// A valid tour should contain the same number of nodes and edges.
		return count == numNodes;
	}

	/**
	 * Public Method: double tourDistance()
	 * 
	 * Description: Assuming that the linking in the map has formed a valid
	 * tour, this method will calculate the total distance of this tour.
	 * 
	 * @return the distance of the tour; -1 if there is no valid tour.
	 */
	public double tourDistance() {
		if (!isValidTour()) {
			String message = "The tour is illegal. Cannot calcualte tour distance.";
			throw new IllegalStateException(message);
		}

		// Records along the traversing the tour.
		double distance = 0;
		int startIndex = 0;
		int nowIndex = startIndex;
		int nextIndex = map.getLink(nowIndex);
		
		// Sums up the distance on each link.
		while (nextIndex != startIndex) {
			distance += map.pointDistance(nowIndex, nextIndex);
			nowIndex = nextIndex;
			nextIndex = map.getLink(nowIndex);
		}
		// Includes the cost of returning back to the start point.
		distance += map.pointDistance(nowIndex, startIndex);

		return distance;
	}
}

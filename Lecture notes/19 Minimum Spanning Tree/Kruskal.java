package lectures;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
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
			// Edges are compared based on their weights, for Kruskal¡¯s
			// Algorithm.
			if (this.distance < other.distance) {
				return -1;
			} else if (this.distance == other.distance) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	/**
	 * Public Method: void MST()
	 * 
	 * Description: This method finds the minimum spanning tree of the given map
	 * using Kruskal¡¯s Algorithm.
	 */
	public Edge[] MST(Edge[] edges) {
		int numNodes = edges.length;

		// The remaining edges in the MST.
		ArrayList<Edge> mstEdges = new ArrayList<Edge>();


		// Initialization of all edges and sort them according to weights.
		Arrays.sort(edges);

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

		Edge[] result = null;
		mstEdges.toArray(result);

		return result;
	}
}

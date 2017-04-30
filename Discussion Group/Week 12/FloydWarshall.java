package discussion_group;

/**
 * Public Class: FloydWarshall
 * 
 * Description: This class implements the Floyd Warshall algorithm to find
 * all-pairs shortest path in a given graph.
 * 
 * @author Niu Yunpeng
 */
public class FloydWarshall {
	// The graph is in an adjacent matrix representation.
	public int[][] allPairShortestPath(int[][] graph) {
		int[][][] distance = new int[graph.length][graph.length][graph.length];
		
		// Initialization for all direct distances (either the direct edge or infinity).
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				distance[0][i][j] = graph[i][j];
			}
		}

		// Mid set of nodes, incrementally.
		for (int k = 1; k < graph.length; k++) {
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < graph.length; j++) {
					int currentDist = distance[k - 1][i][j];
					int sourceToK = distance[k - 1][i][k];
					int kToDest = distance[k - 1][k][j];

					distance[k][i][j] = Math.min(currentDist, sourceToK + kToDest);
				}
			}
		}

		return distance[graph.length - 1];
	}

	// The graph is in an adjacent matrix representation.
	public int[][] allPairShortestPathBetter(int[][] graph) {
		int[][] dist = new int[graph.length][graph.length];

		// Initialization for all direct distances (either the direct edge or
		// infinity).
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				dist[i][j] = graph[i][j];
			}
		}

		// Mid set of nodes, incrementally.
		for (int k = 0; k < graph.length; k++) {
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < graph.length; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		return dist;
	}
}

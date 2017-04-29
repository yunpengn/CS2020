package lectures;

/**
 * Public Class: UnionFind
 * 
 * Description: This class implements a simple union-find data structure (also
 * known as disjoint-set problem). Weighted union and path compression are both
 * adopted to ensure time complexity bounds of different operations.
 * 
 * @author Niu Yunpeng
 */
public class UnionFind {
	// Stores the parent of each element in an array.
	private int[] parent;
	// Stores the number of elements in the subtree rooted at each element
	// (including itself). So, the minimum rank is 1.
	private int[] rank;
	// The number of sets present in this data structure.
	private int numOfSets;
	// The number of elements in this data structure.
	private int num;

	/**
	 * Public Constructor: UnionFind(int)
	 * 
	 * Property: As restricted by this constructor, this union-find data
	 * structure cannot accept any more new elements. Also, any of the element
	 * cannot be deleted. Only the set each of them belongs to will change.
	 * 
	 * @param N
	 *            is the total number of elements in this data structure.
	 */
	public UnionFind(int N) {
		if (N <= 0) {
			String message = "The number of elements should be at least 1.";
			throw new IllegalArgumentException(message);
		}

		// Initialize the number of elements.
		num = N;

		// Opens two arrays of size N.
		parent = new int[N];
		rank = new int[N];

		for (int i = 0; i < N; i++) {
			// Initialize the set identifier for each element (to its index).
			parent[i] = i;
			// Initialize the rank for each element, equal to 1.
			rank[i] = 1;
		}

		// The number of sets is equal to the number of elements initially.
		numOfSets = N;
	}

	/**
	 * Public Method: int findSet(int)
	 * 
	 * Description: This method returns the set identifier for a given element.
	 * Path compression is used here so that that element is re-linked to the
	 * root after this query.
	 */
	public int findSet(int i) {
		// Special Notice: Index starts from 0.
		if (i < 0 || i >= num) {
			throw new IllegalArgumentException("Index out of range - " + i);
		}

		// Stores the root of the whole set.
		int root = i;

		// Find the root element of the set first.
		while (parent[root] != root) {
			root = parent[root];
		}

		// Re-link every element on the path to the root directly, and updates
		// the rank for each of them.
		while (parent[i] != root) {
			int temp = parent[i];
			parent[i] = root;
			rank[temp] = rank[temp] - rank[i];
			i = temp;
		}

		return root;
	}

	/**
	 * Public Method: boolean isSameSet(int, int)
	 * 
	 * Description: This method checks whether two elements are in the same set.
	 * The method findSet is called for both of them, thus, path compression may
	 * be involved as well.
	 */
	public boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

	/**
	 * Public Method: void unionSet(int, int)
	 * 
	 * Description: This methods unions two disjoint sets into one. It will find
	 * the root of these two sets and then link them. Weighted union is used so
	 * that the one with a larger subtree will be the parent of the other. Path
	 * compression may be involved as well.
	 */
	public void unionSet(int i, int j) {
		// Replaces i and j by the roots of two sets they belong to. For these
		// two sets, path compression may happen.
		i = findSet(i);
		j = findSet(j);

		// Decides which one has a larger rank and becomes the new root.
		if (rank[i] >= rank[j]) {
			parent[j] = i;
			rank[i] = rank[i] + rank[j];
		} else {
			parent[i] = j;
			rank[j] = rank[i] + rank[j];
		}

		numOfSets--;
	}

	/**
	 * Public Method: int setSize(int)
	 * 
	 * Description: This method returns the number of elements in the set which
	 * a certain element belongs to.
	 */
	public int setSize(int i) {
		return rank[findSet(i)];
	}

	/**
	 * Public Method: int numSet()
	 * 
	 * @return the number of total sets present in this data structure.
	 */
	public int numSet() {
		return numOfSets;
	}
}

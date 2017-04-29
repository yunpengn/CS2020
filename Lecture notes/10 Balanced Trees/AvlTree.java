package lectures;

public class AvlTree<Key extends Comparable<Key>, Value> {
	// Property 1: All in left tree < key < all in right tree
	// Property 2: The difference of heights between left and right sub-trees is
	// no larger than 1.
	private AvlTree<Key, Value> m_leftTree = null;
	private AvlTree<Key, Value> m_rightTree = null;
	private AvlTree<Key, Value> m_parentTree = null;

	// Information variables for the current tree node
	private Key m_key = null;
	private Value m_value = null;
	private int m_height = -1;

	public AvlTree(Key k, Value v, AvlTree<Key, Value> parent) {
		m_key = k;
		m_value = v;
		m_parentTree = parent;
		m_height = 0;
	}

	// Getters for information variables
	public Key getKey() {
		return m_key;
	}

	public Value getValue() {
		return m_value;
	}

	public int getHeight() {
		return m_height;
	}

	// Need to check balance every time we insert a new element
	public void checkBalance() {
		int leftHeight = m_leftTree == null ? -1 : m_leftTree.m_height;
		int rightHeight = m_rightTree == null ? -1 : m_rightTree.m_height;
		boolean isBalanced = Math.abs(leftHeight - rightHeight) < 2;

		if (isBalanced && m_parentTree != null) {
			// The current node is balanced, walk up and check
			checkBalance();
		} else if (!isBalanced) {
			leftRotate();
		}
	}

	private void leftRotate() {

	}
}

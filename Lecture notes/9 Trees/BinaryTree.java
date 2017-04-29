package lectures;

import java.util.Iterator;

public class BinaryTree<Key extends Comparable<Key>, Value> {
	private class TreeIterator implements Iterator<BinaryTree<Key, Value>> {
		@Override
		public boolean hasNext() {
			return successor() != null;
		}

		@Override
		public BinaryTree<Key, Value> next() {
			return successor();
		}

	}

	// Iterator for the outer class
	public TreeIterator getIterator() {
		return new TreeIterator();
	}

	// Property: All in left tree < key < all in right tree
	private BinaryTree<Key, Value> m_leftTree = null;
	private BinaryTree<Key, Value> m_rightTree = null;
	private BinaryTree<Key, Value> m_parentTree = null;

	private Key m_key;
	private Value m_value;
	
	public BinaryTree(Key k, Value v, BinaryTree<Key, Value> parent) {
		m_key = k;
		m_value = v;
		m_parentTree = parent;
	}

	public BinaryTree<Key, Value> getLeftTree() {
		return m_leftTree;
	}

	public BinaryTree<Key, Value> getRightTree() {
		return m_rightTree;
	}

	public Key getKey() {
		return m_key;
	}

	public Value getValue() {
		return m_value;
	}

	public int height() {
		int leftHeight = -1;
		int rightHeight = -1;

		if (m_leftTree != null) {
			leftHeight = m_leftTree.height();
		}

		if (m_rightTree != null) {
			rightHeight = m_rightTree.height();
		}

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public BinaryTree<Key, Value> searchMax() {
		if (m_rightTree == null) {
			return this;
		} else {
			return m_rightTree.searchMax();
		}
	}

	public BinaryTree<Key, Value> searchMin() {
		if (m_leftTree == null) {
			return this;
		} else {
			return m_leftTree.searchMin();
		}
	}
	
	public BinaryTree<Key, Value> search(Key k) {
		if (k.compareTo(m_key) < 0) {
			if (m_leftTree == null) {
				return null;
			} else {
				return m_leftTree.search(k);
			}
		} else if (k.compareTo(m_key) > 0) {
			if(m_rightTree == null) {
				return null;
			} else {
				return m_rightTree.search(k);
			}
		} else {
			return this;
		}
	}

	public void insert(Key k, Value v) {
		if (k.compareTo(m_key) < 0) {
			if (m_leftTree == null) {
				m_leftTree = new BinaryTree<Key, Value>(k, v, this);
			} else {
				m_leftTree.insert(k, v);
			}
		} else if (k.compareTo(m_key) > 0) {
			if (m_rightTree == null) {
				m_rightTree = new BinaryTree<Key, Value>(k, v, this);
			} else {
				m_rightTree.insert(k, v);
			}
		} else {
			// The key already exists in the tree, no need to insert
			return;
		}
	}

	private void visit() {
		System.out.println("Current Tree - Key: " + m_key + " Value: " + m_value);
	}

	// Traversal by in/pre/pose-order are indeed DFS (Depth-first Search)
	public void inOrderTraversal() {
		if (m_leftTree != null) {
			m_leftTree.inOrderTraversal();
		}
		
		this.visit();

		if (m_rightTree != null) {
			m_rightTree.inOrderTraversal();
		}
	}

	public void preOrderTraversal() {
		this.visit();

		if (m_leftTree != null) {
			m_leftTree.preOrderTraversal();
		}

		if (m_rightTree != null) {
			m_rightTree.preOrderTraversal();
		}
	}

	public void postOrderTraversal() {
		if (m_leftTree != null) {
			m_leftTree.postOrderTraversal();
		}

		if (m_rightTree != null) {
			m_rightTree.postOrderTraversal();
		}

		this.visit();
	}

	// Traversal by level is indeed BFS (Breadth-first Search)
	public void levelOrderTraversal() {
		if (m_leftTree != null) {
			m_leftTree.visit();
		}

		if (m_rightTree != null) {
			m_rightTree.visit();
		}
	}

	// Version 1: Returns the next tree node after the key k
	public BinaryTree<Key, Value> successor(Key k) {
		if (k.compareTo(m_key) < 0) {
			if (m_leftTree == null) {
				return this;
			} else {
				BinaryTree<Key, Value> leftResult = m_leftTree.successor(k);

				if (leftResult == null) {
					return this;
				} else {
					return leftResult;
				}
			}
		} else if (k.compareTo(m_key) > 0) {
			if (m_rightTree == null) {
				return null;
			} else {
				return m_rightTree.successor(k);
			}
		} else {
			// The right sub-tree may be null, but this does not really matter.
			// We will simply return null in this case.
			if (m_rightTree == null) {
				return null;
			} else {
				return m_rightTree.searchMin();
			}
		}
	}
	
	// Version 2: Returns the next tree node to this
	public BinaryTree<Key, Value> successor() {
		if (m_rightTree != null) {
			return m_rightTree.searchMin();
		} else {
			BinaryTree<Key, Value> parent = m_parentTree;
			BinaryTree<Key, Value> child = this;

			while (parent != null && child == parent.m_rightTree) {
				child = parent;
				parent = child.m_parentTree;
			}

			return parent;
		}
	}

	public void delete() {
		if (m_leftTree == null && m_rightTree == null) {
			// If this node has not child
			if (m_parentTree.m_leftTree == this) {
				m_parentTree.m_leftTree = null;
			} else {
				m_parentTree.m_rightTree = null;
			}
		} else if (m_leftTree == null) {
			// If this node only has a right child
			if (m_parentTree.m_leftTree == this) {
				m_parentTree.m_leftTree = this.m_rightTree;
			} else {
				m_parentTree.m_rightTree = this.m_rightTree;
			}

		} else if (m_rightTree == null) {
			// If this node only has a left child
			if (m_parentTree.m_leftTree == this) {
				m_parentTree.m_leftTree = m_leftTree;
			} else {
				m_parentTree.m_rightTree = m_leftTree;
			}
		} else {
			// If this node only has two children
			BinaryTree<Key, Value> thisSuccessor = successor();
			thisSuccessor.delete();

			thisSuccessor.m_leftTree = m_leftTree;
			thisSuccessor.m_rightTree = m_rightTree;
			thisSuccessor.m_parentTree = m_parentTree;

			if (m_parentTree.m_leftTree == this) {
				m_parentTree.m_leftTree = thisSuccessor;
			} else {
				m_parentTree.m_rightTree = thisSuccessor;
			}
		}

		// Destroy all the links of this node for security purpose.
		this.m_parentTree = null;
		this.m_leftTree = null;
		this.m_rightTree = null;
	}
}

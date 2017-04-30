package discussion_group;

import java.util.Iterator;
import java.util.Stack;

import lectures.BinaryTree;

/**
 * Public Class: PostOrder implements Iterator
 * 
 * Description: This class implements a pre-order iterator for a tree. The
 * traversal should start from the leftmost child of the tree, however, the
 * constructor only needs the root of the tree for convenience.
 * 
 * @author Niu Yunpeng
 */
public class PostOrder<Key extends Comparable<Key>, Value> implements Iterator<Value> {
	Stack<BinaryTree<Key, Value>> walk = new Stack<BinaryTree<Key, Value>>();

	public PostOrder(BinaryTree<Key, Value> root) {
		findNextLeaf(root);
	}

	public boolean hasNext() {
		return !walk.isEmpty();
	}

	public Value next() {
		if (!hasNext()) {
			System.err.println("All nodes in this tree has been visited.");
		}

		// This is the next tree node to visit
		BinaryTree<Key, Value> now = walk.pop();

		// Stores the parent node before the right branch in the stack so that
		// the right branch will be visited earlier.
		if (hasNext()) {
			BinaryTree<Key, Value> parent = walk.peek();

			if (parent.getLeftTree() == now && parent.getRightTree() != null) {
				findNextLeaf(parent.getRightTree());
			}
		}

		return now.getValue();
	}

	private void findNextLeaf(BinaryTree<Key, Value> now) {
		while (now != null) {
			walk.push(now);

			if (now.getLeftTree() != null) {
				now = now.getLeftTree();
			} else if (now.getRightTree() != null) {
				now = now.getRightTree();
			}
		}
	}
}

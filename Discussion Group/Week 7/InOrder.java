package discussion_group;

import java.util.Iterator;
import java.util.Stack;

import lectures.BinaryTree;

/**
 * Public Class: InOrder implements Iterator
 * 
 * Description: This class implements a pre-order iterator for a tree. The
 * traversal should start from the leftmost child of the tree, however, the
 * constructor only needs the root of the tree for convenience.
 * 
 * @author Niu Yunpeng
 */
public class InOrder<Key extends Comparable<Key>, Value> implements Iterator<Value> {
	Stack<BinaryTree<Key, Value>> walk = new Stack<BinaryTree<Key, Value>>();

	public InOrder(BinaryTree<Key, Value> root) {
		pushAllLeftChildren(root);
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

		// The right branch will be visited after the parent node.
		BinaryTree<Key, Value> right = now.getRightTree();
		if (right != null) {
			pushAllLeftChildren(right);
		}

		return now.getValue();
	}

	private void pushAllLeftChildren(BinaryTree<Key, Value> now) {
		while(now !=null) {
			walk.add(now);
			now = now.getLeftTree();
		}
	}
}

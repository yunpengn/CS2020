package discussion_group;

import java.util.Iterator;
import java.util.Stack;

import lectures.BinaryTree;

/**
 * Public Class: PreOrder implements Iterator
 * 
 * Description: This class implements a pre-order iterator for a tree. The
 * traversal should start from the root of the tree.
 * 
 * @author Niu Yunpeng
 */
public class PreOrder<Key extends Comparable<Key>, Value> implements Iterator<Value> {
	Stack<BinaryTree<Key, Value>> walk = new Stack<BinaryTree<Key, Value>>();

	public PreOrder(BinaryTree<Key, Value> root) {
		walk.push(root);
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

		// Push the right subtree before the left one so that we can pop the
		// left one before the right one.
		if (now.getRightTree() != null) {
			walk.push(now.getRightTree());
		}
		if (now.getLeftTree() != null) {
			walk.push(now.getLeftTree());
		}


		return now.getValue();
	}
}

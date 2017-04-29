package lectures;

import java.util.Iterator;
import java.util.Stack;

// See http://blog.myxpweb.com/2016/03/03/CS2020-AVL-Tree/
public class Tree<Key extends Comparable<Key>, Value> {
	private Node<Key, Value> dummyNode;
	private Node<Key, Value> m_root;
	
	public Tree() {
		m_root = null;
		dummyNode = new Node<Key, Value>();
	}
	
	public void insert(Key key, Value value) {
		if (m_root == null) {
			m_root = new Node<Key, Value>(key, value);
			m_root.m_parent = dummyNode;
			dummyNode.m_rightTree = m_root;
		} else {
			m_root = dummyNode.m_rightTree;
			m_root.insert(key, value);
		}
	}
	
	public void delete(Key key) throws Exception {
		if (dummyNode.m_rightTree == null) {
			m_root = null;
			throw new Exception("Tree is empty");
		} else {
			m_root.delete(key);
		}
	}
	
	public void search(Key key) throws Exception {
		if (dummyNode.m_rightTree == null) {
			m_root = null;
			throw new Exception("Tree is empty");
		} else {
			m_root.search(key);
		}
	}
	
	private class preOrderIterator implements Iterator<Node<Key, Value>> {
		Stack<Node<Key, Value>> nodeStack;
		
		public preOrderIterator(Node<Key, Value> current) {
			nodeStack = new Stack<Node<Key, Value>>();
			nodeStack.push(current);
		}
		
		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty();
		}

		@Override
		public Node<Key, Value> next() {
			Node<Key, Value> current = nodeStack.pop();
			if (current.m_rightTree != null) {
				nodeStack.push(current.m_rightTree);
			}
			if (current.m_leftTree != null) {
				nodeStack.push(current.m_leftTree);
			}
			return current;
		}
		
	}
	
	private class InOrderIterator implements Iterator<Node<Key, Value>> {
		Stack<Node<Key, Value>> nodeStack;
		Node<Key, Value> current;
		
		public InOrderIterator(Node<Key, Value> c) {
			nodeStack = new Stack<Node<Key, Value>>();
			current = c;
		}
		
		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty() || current != null;
		}

		@Override
		public Node<Key, Value> next() {
			while(current != null) {
				nodeStack.push(current);
				current = current.m_leftTree;
			}
			
			current = nodeStack.pop();
			Node<Key, Value> result = current;
			current = current.m_rightTree;
			return result;
		}
		
	}
	
	private class PostOrderIterator implements Iterator<Node<Key, Value>> {
		Stack<Node<Key, Value>> nodeStack;
		Node<Key, Value> current;
		
		public PostOrderIterator(Node<Key, Value> c) {
			nodeStack = new Stack<Node<Key, Value>>();
			current = c;
		}
		
		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty() || current != null;
		}

		@Override
		public Node<Key, Value> next() {
			boolean done = false;
			while(!done && current != null) {
				done = true;
				while(current != null) {
					nodeStack.push(current);
					current = current.m_leftTree;
					done = false;
				}
				current = nodeStack.peek().m_rightTree;
			}
			
			Node<Key, Value> result = nodeStack.pop();
			Node<Key, Value> prev;
			try {
				prev = nodeStack.peek();
			} catch (Exception e) { 
				prev = null; // nodeStack is empty
			}
			current = result;
			
			if (prev != null && prev.m_leftTree == current) {
				current = prev.m_rightTree;
			} else {
				current = null;
			}
			
			return result;
		}
		
	}
	
	public Iterator<Node<Key, Value>> getPreOrderIterator() {
		return new preOrderIterator(dummyNode.m_rightTree);
	}
	
	public Iterator<Node<Key, Value>> getInOrderIterator() {
		return new InOrderIterator(dummyNode.m_rightTree);
	}
	
	public Iterator<Node<Key, Value>> getPostOrderIterator() {
		return new PostOrderIterator(dummyNode.m_rightTree);
	}
	
	public static void main(String[] args) throws Exception {
		Tree<Integer, String> myTree = new Tree<Integer, String>();
		myTree.insert(10, "A");
		myTree.insert(2, "B");
		myTree.insert(3, "C");
		myTree.insert(1, "D");
		myTree.insert(5, "F");
		myTree.insert(8, "G");
		myTree.insert(6, "H");
		myTree.insert(14, "I");
		myTree.insert(13, "J");
		myTree.insert(11, "K");
		myTree.insert(20, "L");
		myTree.insert(9, "M");
		
		myTree.delete(6);
		myTree.delete(5);
		myTree.delete(1);
		myTree.delete(3);
		Iterator<Node<Integer, String>> preOrderIterator = myTree.getPreOrderIterator();
		
		while(preOrderIterator.hasNext()) {
			Node<Integer, String> current = preOrderIterator.next();
			System.out.printf("(%d , %s) ", current.m_key, current.m_value);
		}
	
		System.out.println();
		
		Iterator<Node<Integer, String>> getInOrderIterator = myTree.getInOrderIterator();
		
		while(getInOrderIterator.hasNext()) {
			Node<Integer, String> current = getInOrderIterator.next();
			System.out.printf("(%d , %s) ", current.m_key, current.m_value);
		}
		
		System.out.println();
		
		Iterator<Node<Integer, String>> getPostOrderIterator = myTree.getPostOrderIterator();
		
		while(getPostOrderIterator.hasNext()) {
			Node<Integer, String> current = getPostOrderIterator.next();
			System.out.printf("(%d , %s) ", current.m_key, current.m_value);
		}
		
	}
	
	static class Node<Key extends Comparable<Key>, Value> {
		protected Node<Key, Value> m_leftTree = null;
		protected Node<Key, Value> m_rightTree = null;
		protected Node<Key, Value> m_parent = null;
		protected Value m_value;
		protected Key m_key;
		protected int m_height;
		
		
		public Node(Key key, Value value) {
			m_key = key;
			m_value = value;
			m_height = 0;
		}
		
		public Node() {
			m_key = null;
			m_value = null;
		}
		
		public int height() {
			return m_height;
		}
		
		public int height(Node<Key, Value> node) {
			if (node == null) {
				return -1;
			} else {
				return node.m_height;
			}
		}
		
		public boolean isDummy() {
			return m_key == null;
		}
		
		public void setLeftTree(Node<Key, Value> node) {
			m_leftTree = node;
			if (node != null) {
				node.m_parent = this;
			}
		}
		
		public void setRightTree(Node<Key, Value> node) {
			m_rightTree = node;
			if (node != null) {
				node.m_parent = this;
			}
		}
		
		public void setNodeWithOriginalPositionOfParent(Node<Key, Value> node) {
			if (m_parent != null) {
				if (m_parent.m_leftTree == this) {
					m_parent.setLeftTree(node);
				} else {
					m_parent.setRightTree(node);
				}
			}
		}
		
		
		public Node<Key, Value> searchMin() {
			if (m_leftTree != null) {
				return m_leftTree.searchMin();
			} else {
				return this;
			}
		}
		
		public Node<Key, Value> searchMax() {
			if (m_rightTree != null) {
				return m_rightTree.searchMax();
			} else {
				return this;
			}
		}
		
		public Node<Key, Value> search(Key key) {
			if (key.compareTo(m_key) > 0) {
				if (m_rightTree != null) {
					return m_rightTree.search(key);
				} else {
					return null;
				}
			}
			if (key.compareTo(m_key) < 0) {
				if (m_leftTree != null) {
					return m_leftTree.search(key);
				} else {
					return null;
				}
			}
			return this;
		}
		
		public void updateHeight() {
			int leftHeight = (m_leftTree == null) ? -1 : m_leftTree.height();
			int rightHeight = (m_rightTree == null) ? -1 : m_rightTree.height();
			m_height = Math.max(leftHeight + 1, rightHeight + 1);
			if (isDummy()) {
				
			} else {
				m_parent.updateHeight();
			}
		}
		
		public void insert(Key key, Value value) {
			if (key.compareTo(m_key) > 0) {
				if (m_rightTree != null) {
					m_rightTree.insert(key, value);
				} else {
					Node<Key, Value> newTree = new Node<Key, Value>(key, value);
					newTree.m_parent = this;
					m_rightTree = newTree;
					updateHeight();
					balancing();
				}
			}
			if (key.compareTo(m_key) < 0) {
				if (m_leftTree != null) {
					m_leftTree.insert(key, value);
				} else {
					Node<Key, Value> newTree = new Node<Key, Value>(key, value);
					newTree.m_parent = this;
					m_leftTree = newTree;
					updateHeight();
					balancing();
				}
			}
		}
		
		private boolean balancing() {
			if(isDummy()) {
				return false;
			} 
			
			m_height = Math.max(height(m_leftTree), height(m_rightTree)) + 1;
			
			// leftHeavy
			if (height(m_leftTree) > height(m_rightTree) + 1) {
				if (height(m_leftTree.m_leftTree) == height(m_leftTree.m_rightTree)) {
					// case 1
					rightRotate();
					m_height--;
					m_parent.m_height++;
				} else if (height(m_leftTree.m_leftTree) > height(m_leftTree.m_rightTree)) {
					// case 2
					rightRotate();
					m_height -= 2;
				} else if (height(m_leftTree.m_leftTree) < height(m_leftTree.m_rightTree)) {
					// case 3
					m_leftTree.leftRotate();
					rightRotate();
					m_height -= 2;
					m_parent.m_height++;
					m_parent.m_leftTree.m_height--;
				}
				
			}
			
			// rightHeavy
			if (height(m_leftTree) + 1 < height(m_rightTree)) {
				if (height(m_rightTree.m_leftTree) == height(m_rightTree.m_rightTree)) {
					// case 1
					leftRotate();
					m_height--;
					m_parent.m_height++;
				} else if (height(m_rightTree.m_leftTree) < height(m_rightTree.m_rightTree)) {
					// case 2
					leftRotate();
					m_height -= 2;
				} else if (height(m_rightTree.m_leftTree) > height(m_rightTree.m_rightTree)) {
					// case 3
					m_rightTree.rightRotate();
					leftRotate();
					m_height -= 2;
					m_parent.m_height++;
					m_parent.m_rightTree.m_height--;
				}
				
			}
			
			return m_parent.balancing();
		}
		
		public void rightRotate() {
			if (isDummy() || m_leftTree == null) {
				// cannot rotate
			} else {
				Node<Key, Value> leftTree = m_leftTree;
				setNodeWithOriginalPositionOfParent(leftTree);
				setLeftTree(leftTree.m_rightTree);
				leftTree.setRightTree(this);
			}
		}
		
		public void leftRotate() {
			if (isDummy() || m_rightTree == null) {
				// cannot rotate
			} else {
				Node<Key, Value> rightTree = m_rightTree;
				setNodeWithOriginalPositionOfParent(rightTree);
				setRightTree(rightTree.m_leftTree);
				rightTree.setLeftTree(this);
			}
		}
		
		public Node<Key, Value> successor() {
			if (m_rightTree != null) {
				return m_rightTree.searchMin();
			} else {
				Node<Key, Value> current = this;
				while((current.m_parent != null) && (current.m_parent.m_rightTree == current)) {
					current = current.m_parent;
				}
				return current.m_parent;
			}
		}
		
		public boolean delete() {
			// no child
			// bugs here, no permission to delete root
			if (m_leftTree == null && m_rightTree == null) {
				if (m_parent.m_leftTree == this) {
					m_parent.m_leftTree = null;
				} else {
					m_parent.m_rightTree = null;
				}
				balancing();
				return true;
			}
			
			
			// two child
			if (m_leftTree != null && m_rightTree != null) {
				Node<Key, Value> successor = this.successor();
				swap(this, successor);
				return successor.delete();
			}
			
			// one child
			Node<Key, Value> child = (m_leftTree) == null ? m_rightTree : m_leftTree;
			if (m_parent.m_leftTree == this) {
				m_parent.m_leftTree = child;
			} else {
				m_parent.m_rightTree = child;
			}
			balancing();
			return true;
		}
		
		// Overload
		public boolean delete(Key key) {
			Node<Key, Value> searchResult = search(key);
			if (searchResult != null) {
				searchResult.delete();
				return true;
			}
			return false;
		}

			
		private void swap(Node<Key, Value> t1, Node<Key, Value> t2) {
			Key tempKey = t1.m_key;
			Value tempValue = t1.m_value;
			
			t1.m_key = t2.m_key;
			t1.m_value = t2.m_value;
			
			t2.m_key = tempKey;
			t2.m_value = tempValue;
			
		}
		
	}
}
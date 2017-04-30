package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Public Class: QuestionTree
 * 
 * Description: This class extends the abstract class QuestionTreeBase and
 * implements the buildTree and findQuery methods. Therefore, we can find an
 * optimized way to solve the 20-Questions problem.
 * 
 * @author Niu Yunpeng
 * based on
 * @author Gilbert
 */
public class QuestionTree extends QuestionTreeBase {
	// The number of objects in this tree, which is the number of leaf nodes.
	private int m_numObj = 0;

	/**
	 * Public Method: void buildTree(ArrayList<QuestionObject>)
	 * 
	 * Description: Given a list of QuestionObjects, this method builds a binary
	 * logic tree by inserting them one by one. The arrayList is sorted before
	 * insertion so as to avoid moving previously inserted objects when trying
	 * to insert a new one.
	 * 
	 * @param objects
	 *            is an arrayList collection of QuestionObjects to insert.
	 */
	public void buildTree(ArrayList<QuestionObject> objects) {
		if (objects == null || objects.size() == 0) {
			String message = "No meaning to build an empty tree";
			throw new IllegalArgumentException(message);
		}

		// Sort them according to the number of properties first. Those with
		// more properties come first.
		Collections.sort(objects);

		m_numObj = objects.size();

		// Insert questionObjects into the tree one by one.
		for (QuestionObject i : objects) {
			insert(i);
		}
	}
	
	/**
	 * Private Method: void insert(QuestionObject)
	 * 
	 * Description: This method inserts a given QuestionObject into the tree.
	 * Each property of the object is a interior node, the name of this object
	 * is a leafNode.
	 * 
	 * @param obj
	 *            is the current object to insert into the tree.
	 */
	private void insert(QuestionObject obj) {
		// It is hard to deal with the tree encapsulated in the question object,
		// so we take all properties out and build a new tree. This is also to
		// ensure, when we remove a property from the tree, the original data
		// from that QuestionObject does not change.
		TreeSet<String> properties = new TreeSet<String>();
		Iterator<String> iterator = obj.propertyIterator();
		
		while(iterator.hasNext()) {
			properties.add(iterator.next());
		}

		// This happens only when we call insert method for the first time.
		if (m_root == null) {
			// This is the 1st QuestionObject who has the most property. If it
			// has no property, that means all of them have no property.
			if (properties.size() == 0) {
				// If there is only 1 object, the root is just the object
				// itself. Otherwise, we do not have enough information, you can
				// never win the game.
				if (m_numObj == 1) {
					m_root = new LeafNode<String>(obj.getName(), obj);
				} else {
					String messsage = "Not enough information for the game";
					throw new IllegalStateException(messsage);
				}
			} else {
				m_root = new TreeNode<String>(properties.first());
			}
		}

		// We traverse from the root to the desired position to insert obj.
		// Along the way, the properties of obj may become interior nodes. At
		// last, the name of obj becomes a leaf node.
		TreeNode<String> currentNode = m_root;
		TreeNode<String> newNode = null;

		while (currentNode != null) {
			if (properties.contains(currentNode.getValue())) {
				// No need for this property anymore, remove it.
				properties.remove(currentNode.getValue());

				// The current object contains this property, go right.
				if (currentNode.getRight() != null) {
					currentNode = currentNode.getRight();
				} else {
					// The right subtree is empty now.
					if (properties.size() > 0) {
						// Create an interior node using the first property.
						newNode = new TreeNode<String>(properties.first());
						
						newNode.setParent(currentNode);
						currentNode.setRight(newNode);
						currentNode = newNode;
					} else {
						// Create a leaf node if no property left.
						newNode = new LeafNode<String>(obj.getName(), obj);

						newNode.setParent(currentNode);
						currentNode.setRight(newNode);

						// This QuestionObject has been inserted into the tree.
						break;
					}
				}
			} else {
				// The current object does not contain this property, go left.
				if (currentNode.getLeft() != null) {
					currentNode = currentNode.getLeft();
				} else {
					// The left subtree is empty now.
					if (properties.size() > 0) {
						// Create an interior node using the first property.
						newNode = new TreeNode<String>(properties.first());

						newNode.setParent(currentNode);
						currentNode.setLeft(newNode);
						currentNode = newNode;
					} else {
						// Create a leaf node if no property left.
						newNode = new LeafNode<String>(obj.getName(), obj);

						newNode.setParent(currentNode);
						currentNode.setLeft(newNode);

						// This QuestionObject has been inserted into the tree.
						break;
					}
				}
			}
		}
	}

	/**
	 * Public Method: Query findQuery()
	 * 
	 * Description: This method finds the next question to ask in the game. It
	 * finds a node in the tree that has [n/3,2n/3] objects as descendants,
	 * where n is the total number of objects in the tree.
	 * 
	 * @return the next query to ask in the game.
	 */
	public Query findQuery() {
		return findQuery(m_root);
	}
	
	/**
	 * Private Method: Query findQuery(TreeNode<String>)
	 * 
	 * Description: This method finds the node that has [n/3,2n/3] objects as
	 * descendants recursively.
	 * 
	 * @param node
	 *            is the root node of the current subtree.
	 * 
	 * @return the next query to ask in the game.
	 */
	private Query findQuery(TreeNode<String> node) {
		int leftCount = countObjects(node.getLeft());
		int totalCount = countObjects();
		int rightCount = countObjects(node.getRight());
		
		if (satisfyRange(leftCount, totalCount)) {
			// If the left node is within the range, return it directly
			return constructQuery(node.getLeft());
		} else if (satisfyRange(rightCount, totalCount)) {
			// If the right node is within the range, return it directly
			return constructQuery(node.getRight());
		} else if (leftCount >= rightCount) {
			// Otherwise, recurse on the larger child.
			return findQuery(node.getLeft());
		} else {
			return findQuery(node.getRight());
		}
	}

	/**
	 * Private Method: boolean satisfyRange(int, int)
	 * 
	 * @param selectCount
	 *            is the count(v) for the selected subtree
	 * @param totalCount
	 *            is the count(v) for the whole tree, which is n
	 * 
	 * @return whether selectCount is within (n/3, 2n/3]
	 */
	private boolean satisfyRange(int selectCount, int totalCount) {
		return selectCount * 3 > totalCount && selectCount * 3 <= 2 * totalCount;
	}
}

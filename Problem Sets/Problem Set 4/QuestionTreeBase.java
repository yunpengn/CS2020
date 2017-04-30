package sg.edu.nus.cs2020;

import java.util.ArrayList;

/**
 * The main class for maintaining the player's state in the 20 Questions Game,
 * based on questions that have already been asked. The QuestionTree stores the
 * possible objects in a tree indexed by their properties.
 * 
 * Every internal node in the tree is a TreeNode and has a property as its
 * value. Every leaf node is a LeafNode and has an object name as its value. Be
 * sure to use the LeafNode class for objects to ensure that properties and
 * objects do not get confused.
 * 
 * As the game proceeds, parts of the tree are eliminated, allowing the player
 * to eventually discover which is the chosen item.
 * 
 * @author gilbert
 */
public abstract class QuestionTreeBase {
	// The root of the tree
	protected TreeNode<String> m_root = null;

	// This constructor builds an empty tree.
	protected QuestionTreeBase() {
		m_root = null;
	}
	

	/***********************************************
	 * Methods to be developed in QuestionTree class
	 ***********************************************/

	/**
	 * Public Abstract Method: void buildTree(ArrayList<QuestionObject>)
	 * 
	 * Description: This method builds the tree of objects. This method should
	 * be implemented in Problem 1.a
	 * 
	 * @param objects
	 *            is an array of QuestionObjects
	 */
	public abstract void buildTree(ArrayList<QuestionObject> objects);
	
	/**
	 * Public Abstract Method: Query findQuery()
	 * 
	 * Description: This method calculates the next question to ask in the game.
	 * It finds a node in the tree that has at least n/3 objects as descendants,
	 * and at most 2n/3 objects as descendants, where n is the total number of
	 * objects in the tree. This should be implemented in Problem 1.c
	 * 
	 * @return the next query that eliminates at least n/3 objects in the tree.
	 */
	public abstract Query findQuery();

	/**
	 * Public Method: void updateTree(Query, boolean)
	 * 
	 * Description: This method updates the tree based on the response to the
	 * query. If the query is satisfied (i.e., answer is true), then the value
	 * of m_searchRoot is updated to the matching node in the tree. Otherwise,
	 * the matching node in the tree is eliminated and the tree is re-trimmed.
	 * 
	 * @param query
	 *            is the question that is asked.
	 * @param answer
	 *            is the answer to the query (true/false).
	 */
	public void updateTree(Query query, boolean answer) {
		// Error checking
		if (query == null) {
			throw new IllegalArgumentException("No query to update");
		}
		
		// Translate the query into a node in the tree
		TreeNode<String> node = search(query);
		
		if (node == null) {
			// Oops, this was not a good query.
			String message = "Bad query. Cannot update tree.";
			throw new IllegalArgumentException(message);
		}
		
		// Get the parent of the node in question
		TreeNode<String> parent = node.getParent();
		
		// Was the query answered yes or no? If the answer is yes, the result
		// must be in the subtree of this node; if the answer is no, the result
		// cannot be in the subtree of this node.
		if (answer == true) {
			// Update the tree. All future queries start here. The rest of the
			// tree is abandoned.
			m_root = node;
		} else {
			// answer == false, hence prune the tree
			if (parent.getLeft() == node) {
				parent.setLeft(null);
			} else if (parent.getRight() == node) {
				parent.setRight(null);
			} else {
				// Should never get here
				throw new IllegalStateException("Bad tree state.");
			}
			
			// Now walk up the tree from the leaf to the root. If this branch of
			// the tree is now empty, then delete it.
			node = parent;
			parent = node.getParent();
			
			// Walk up the tree
			while (parent != null) {
				// If node has no children, delete it
				if (node.getLeft() == null && node.getRight() == null) {
					if (parent.getLeft() == node) {
						parent.setLeft(null);
					} else if (parent.getRight() == node) {
						parent.setRight(null);
					} else {
						// Should never get here
						assert(false);
					}
				} else {
					// In this case, we are done
					break;
				}

				node = parent;
				parent = node.getParent();
			}
		}
	}

	/*******************************************************
	 * Functionality for translating between nodes in the tree and queries
	 *******************************************************/
	
	/**
	 * Protected Method: Query constructQuery(TreeNode<String>)
	 * 
	 * Description: This method constructs a query based on the given node in
	 * the tree. If the chosen object satisfies the query, then it is guaranteed
	 * to be in the subtree of this node. If the chosen object does not satisfy
	 * the query, then it is guaranteed not to be in the subtree indicated by
	 * this node.
	 * 
	 * @param node
	 *            is the node whose properties we want to turn into a query.
	 * 
	 * @return query associated with the node in the tree.
	 */
	protected Query constructQuery(TreeNode<String> node) {
		// Error checking
		if (node == null) {
			String message = "Cannot construct a query based on a null node.";
			throw new IllegalArgumentException(message);
		}
		
		// Initialize the nodes for walking up the tree
		TreeNode<String> leaf = node;
		TreeNode<String> parent = leaf.getParent();
		
		// Create a new query
		Query query = new Query();
		
		// Now walk up the tree...
		while (parent != null) {
			// Get the property of the parent node
			String property = parent.getValue();
			
			// Add it to the query as a positive/negative property, depending on
			// whether it is a left or right child.
			if (leaf == parent.getLeft()) {
				query.addNotProperty(property);
			} else if (leaf == parent.getRight()) {
				query.addProperty(property);
			} else {
				throw new IllegalStateException("Invalid tree state.");
			}
			
			// Progress up the tree
			leaf = parent;
			parent = leaf.getParent();
		}
		
		return query;
	}
	
	/**
	 * Private Method: TreeNode<String> search(Query)
	 * 
	 * Description: This method returns the node in the tree that satisfies the
	 * query. The search stops as soon as reaching a node not represented by the
	 * query. It does not guarantee that every property or non-property in the
	 * query is traversed. The search performs a treeWalk only as far as it can,
	 * as specified by the query.
	 * 
	 * Property: If the query is true, then the chosen item is in the subtree of
	 * the returned node; if the query is false, then the chosen item is not in
	 * the subtree of the returned node.
	 * 
	 * @return the node associated with the query.
	 */
	private TreeNode<String> search(Query query) {
		return search(query, m_root);
	}
	
	/**
	 * Private Method: TreeNode<String> search(Query, TreeNode<String>)
	 * 
	 * Description: As soon as it finds a node that is not represented in the
	 * query, it stops. Thus, it does not guarantee that every property or non-
	 * property in the query is traversed. The search performs a treeWalk only
	 * as far as it can, as specified by the query.
	 */
	private TreeNode<String> search(Query query, TreeNode<String> node) {
		// Error checking
		if (query == null || node == null) {
			String message = "Query or node is null.";
			message += "Cannot search for a null query, or from a null node.";

			throw new IllegalArgumentException(message);
		}
		
		// If we are at a leaf, then we are done
		if (node.isLeaf()) {
			return node;
		}
		
		// Get the property of the current node
		String s = node.getValue();
		
		// Check if the property is part of the query as either a positive or
		// negative property.
		boolean satisfied = query.containsProperty(s);
		boolean unsatisfied = query.containsNotProperty(s);

		if (satisfied) {
			// If it is positive, then recurse right.
			if (node.getRight() != null){
				// Recurse to search in the right subtree.
				return search(query, node.getRight());
			} else {
				// If we can't go right, then stop here and return this node.
				return node;
			}
		} else if (unsatisfied) {
			// If it is negative, then recurse left.
			if (node.getLeft() != null){
				// Recurse to search in the left subtree.
				return search(query, node.getLeft());
			} else {
				// If we can't go left, then stop here and return this node.
				return node;
			}
		}

		// The query no longer specifies what to do. This is as far as we can
		// walk with this query.
		return node;
	}
	
	
	/**********************************
	 * Helper functionality
	 **********************************/
	/**
	 * This method counts the number of objects in the whole tree.
	 * 
	 * @return the number of objects in the whole tree.
	 */
	public int countObjects() {
		return countObjects(m_root);
	}
	
	/**
	 * This method counts the number of objects that are descendants of a node.
	 * 
	 * @param node
	 *            is the root node of the subtree.
	 * 
	 * @return the number of objects contained in the subtree.
	 */
	protected int countObjects(TreeNode<String> node) {
		if (node == null) {
			// A null node has no objects.
			return 0;
		} else if (node.isLeaf()) {
			// A leaf has exactly one object.
			return 1;
		} else {
			// Recursively count the two children.
			return countObjects(node.getLeft()) + countObjects(node.getRight());
		}
	}
	
	/**
	 * This method returns any one object from the tree, which will be most
	 * useful if there is only one object left in the tree.
	 * 
	 * @return any one object in the tree.
	 */
	public String getOneObject() {
		return getOneObject(m_root).getValue();
	}
	
	/**
	 * This method returns any one object from the subtree of a node, which will
	 * be most useful if there is only one object left in the subtree. It may be
	 * able to find the rightmost object in the tree.
	 * 
	 * @param node
	 *            is the root of the tree.
	 * 
	 * @return any one object from the tree.
	 */
	protected TreeNode<String> getOneObject(TreeNode<String> node) {
		if (node == null) {
			throw new IllegalArgumentException("Cannot get an object from null node.");
		} else if (node.isLeaf()) {
			// If we are at a leaf, then we have found an object. Return it.
			return node;
		} else if (node.getRight() != null) {
			// Otherwise, if we have a right child, then recurse right.
			return getOneObject(node.getRight());
		} else if (node.getLeft() != null) {
			// Otherwise, if we have a left child, then recurse left.
			return getOneObject(node.getLeft());
		} else {
			// Otherwise, we have no children. That's not allowed! Every leaf in
			// the tree has to be an object.
			throw new IllegalStateException("Illegal tree state: no children.");
		}
	}
	
	/**
	 * Prints all the objects in the tree, and specify the root-to-leaf path for
	 * each object in the whole tree.
	 */
	public void printTree() {
		printTree(m_root);
	}

	/**
	 * Prints all the objects in the subtree of a node, and specify the root-to-
	 * leaf path for each object.
	 */
	private void printTree(TreeNode<String> node) {
		if (node == null) {
			return;
		} else if (node.isLeaf()) {
			printLeaf(node);
		} else {
			printTree(node.getLeft());
			printTree(node.getRight());
		}
	}
	
	/**
	 * First, prints an object. Then, iterates up the tree and print each node
	 * on the root-to-leaf path.
	 */
	private void printLeaf(TreeNode<String> leaf) {
		// Error checking
		if (leaf == null) {
			throw new IllegalArgumentException("Invalid leaf.");
		}

		// Get the parent.
		TreeNode<String> parent = leaf.getParent();

		// Print out the object name
		System.out.print(leaf.getValue() + ": ");

		// Parents are all interior nodes. Call printNode rather than printLeaf.
		if (parent != null) {
			if (parent.getLeft() == leaf) {
				printNode(parent, false);
			} else {
				printNode(parent, true);
			}
		}

		System.out.print('\n');
	}
	
	/**
	 * Prints the root-to-leaf path up to this node.
	 * 
	 * @param direction
	 *            specifies whether the property is a positive or negative
	 *            property for the path in question.
	 */
	private void printNode(TreeNode<String> node, boolean direction) {
		// Error checking
		if (node == null) {
			throw new IllegalArgumentException("Invalid leaf.");
		}

		// Get the parent
		TreeNode<String> parent = node.getParent();

		if (parent != null) {
			// Recursively print out the root-to-leaf path to the parent. Set
			// direction false/true based on whether node is the left/right
			// child of parent.
			if (parent.getLeft() == node) {
				printNode(parent, false);
			} else {
				printNode(parent, true);
			}
		}

		// Now print out this node. Add a negative sign if direction is false.
		if (direction == true) {
			System.out.print(" " + node.getValue());
		} else {
			System.out.print(" " + '-' + node.getValue());
		}
	}
	
}

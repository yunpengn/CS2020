package lectures;

public class BinaryTreeTest {
	public static void main(String[] args) {
		BinaryTree<Integer, String> myTree = new BinaryTree<Integer, String>(41, "41", null);
		myTree.insert(20, "20");
		myTree.insert(65, "65");
		myTree.insert(11, "11");
		myTree.insert(29, "29");
		myTree.insert(32, "32");
		myTree.insert(50, "50");
		myTree.insert(91, "91");
		myTree.insert(72, "72");
		myTree.insert(99, "99");

		System.out.println("======Binary Search Tree - Test======");
		System.out.println("\n==========In Order Traversal=========");
		myTree.inOrderTraversal();
		System.out.println("\n=========Pre Order Traversal=========");
		myTree.preOrderTraversal();
		System.out.println("\n=========Post Order Traversal========");
		myTree.postOrderTraversal();
		System.out.println("\n========Level Order Traversal========");
		myTree.levelOrderTraversal();

		System.out.println("\n========Successor Testing ========");
		BinaryTree<Integer, String> mySuccessor = myTree.successor(27);
		System.out.println("The successor of 27 is the tree node - Key: " + mySuccessor.getKey() +
				  " Value: " + mySuccessor.getValue());
		mySuccessor = myTree.successor(33);
		System.out.println("The successor of 33 is the tree node - Key: " + mySuccessor.getKey() +
			  	  " Value: " + mySuccessor.getValue());
		mySuccessor = myTree.successor(33);
		System.out.println("The successor of 33 is the tree node - Key: " + mySuccessor.getKey() +
			  	  " Value: " + mySuccessor.getValue());
		mySuccessor = myTree.successor(47);
		System.out.println("The successor of 47 is the tree node - Key: " + mySuccessor.getKey() +
			  	  " Value: " + mySuccessor.getValue());
		mySuccessor = myTree.successor(31);
		System.out.println("The successor of 31 is the tree node - Key: " + mySuccessor.getKey() +
			  	  " Value: " + mySuccessor.getValue());
		BinaryTree<Integer, String> thatSuccessor = mySuccessor.successor();
		System.out.printf("The successor of the node (%d, %s) is the node (%d, %s).\n", mySuccessor.getKey(),
				mySuccessor.getValue(), thatSuccessor.getKey(), thatSuccessor.getValue());
	}
}

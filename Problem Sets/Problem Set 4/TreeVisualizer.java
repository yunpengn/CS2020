package sg.edu.nus.cs2020;

import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

/**
 * Public Class: TreeVisualizer
 * 
 * Description: This class visualizes any given binary tree. Java Abstract
 * Windows Tools kit is used. The visualization is shown in a JFrame window.
 * 
 * @author Niu Yunpeng
 */
public class TreeVisualizer<T> {
	private JFrame window = null;
	private Container panel = null;

	// The size of each rectangle
	private final int unitX = 100;
	private final int unitY = 25;

	// Stores all rectangles, strings and connecting lines
	private ArrayList<Integer[]> rectangles = null;
	private ArrayList<StringComponent> values = null;
	private ArrayList<Integer[]> lines = null;

	/**
	 * Public Constructor: TreeVisualizer()
	 * 
	 * Description: This constructor sets up the window to default size and
	 * initialize the relevant environment.
	 */
	public TreeVisualizer() {
		// Sets up a swing window for display first.
		window = new JFrame("Tree Visualizer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(10, 10, 1000, 1000);
		window.setVisible(true);
		window.setFocusable(true);
		window.setResizable(true);

		// Gets the container in the window for drawing.
		panel = window.getContentPane();

		// Initialize the arrayLists for storing positions.
		rectangles = new ArrayList<Integer[]>();
		values = new ArrayList<StringComponent>();
		lines = new ArrayList<Integer[]>();
	}

	/**
	 * Public Method: draw(TreeNode<T>)
	 * 
	 * Description: Given a binary tree, this methods visualizes it by printing
	 * the value of each node in a rectangle. Notice that the width of each
	 * rectangle is 200, while the height is 50.
	 * 
	 * @param root
	 *            is the root node of a tree.
	 */
	public void draw(TreeNode<T> root) {
		if (root == null) {
			String message = "No meaning to draw an empty tree";
			throw new IllegalArgumentException(message);
		}

		// Gets the height of the whole tree, customize the window
		int height = getHeight(root);
		int rootX = resetWindowScale(height) / 3;
		int rootY = unitY;

		// Distance is the distance between centers of the left and right child.
		// It is divided by 2 as we go deeper each level.
		int distance = rootX;
		draw(root, rootX, rootY, distance);
		panel.add(new TreeNodeComponent(rectangles, values, lines));
		finishOperation();
	}

	/**
	 * Private Method: void draw(TreeNode<T>, int, int, int)
	 * 
	 * Description: This is the actual method to perform the drawing process. It
	 * will recursively call itself on its left and right subtree until leaves.
	 * 
	 * @param root
	 *            is the root node of the tree.
	 * @param rootX
	 *            is the displayed x-axis position of the root.
	 * @param rootY
	 *            is the displayed x-axis position of the root.
	 * @param distance
	 *            is the distance between centers of the left and right child.
	 */
	private void draw(TreeNode<T> root, int rootX, int rootY, int distance) {
		// Draw the rectangle boundary
		Integer[] coordinate = {rootX, rootY};
		rectangles.add(coordinate);

		// Draw the value of the root node on the window
		String value = root.getValue().toString();
		values.add(new StringComponent(rootX, rootY, value));
		
		if(root.getLeft()!= null) {
			// Calculate the coordinate for left child.
			int leftX = rootX - distance / 2;
			int leftY = rootY + 2 * unitY;

			// Draw the connecting line
			Integer[] line = {rootX, rootY, leftX, leftY};
			lines.add(line);

			draw(root.getLeft(), leftX, leftY, distance / 2);
		}

		if (root.getRight() != null) {
			// Calculate the coordinate for right child.
			int rightX = rootX + distance / 2;
			int rightY = rootY + 2 * unitY;

			// Draw the connecting line
			Integer[] line = {rootX, rootY, rightX, rightY};
			lines.add(line);

			draw(root.getRight(), rightX, rightY, distance / 2);
		}
	}

	/**
	 * Private Method: getHeight(TreeNode<T>)
	 * 
	 * @param root
	 *            is the root of the whole binary tree.
	 * 
	 * @return the height of the whole binary tree.
	 */
	private int getHeight(TreeNode<T> root) {
		if (root == null) {
			return -1;
		} else {
			int leftHeight = getHeight(root.getLeft()) + 1;
			int rightHeight = getHeight(root.getRight()) + 1;

			return leftHeight >= rightHeight ? leftHeight : rightHeight;
		}
	}

	/**
	 * Private Method: void resetWindowScale(int)
	 * 
	 * Description: The original window may be too small. Therefore, we reset
	 * the window scale to become suitable for display.
	 * 
	 * @param height
	 *            is the height of the tree being visualized.
	 * 
	 * @return the x (center) position for the root node.
	 */
	private int resetWindowScale(int height) {
		int numOfUnitsOnX = 2 * (int) Math.pow(2, height) + 2;
		int numOfUnitsOnY = 4 * height + 3;

		int width = unitX * numOfUnitsOnX;
		int high = unitY * numOfUnitsOnY;

		// window.setSize(unitX * numOfUnitsOnX, unitY * numOfUnitsOnY);
		window.setBounds(0, 0, width, high);

		return unitX * numOfUnitsOnX / 2;
	}

	/**
	 * Description: Display information to the console after finishing drawing.
	 */
	private void finishOperation() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("                      "+ dateFormat.format(new Date()));
		System.out.println("Visualization finishes.");
		System.out.println("Tree visualizer brought to you by Niu Yunpeng.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	// Main method for debugging purpose
	public static void main(String[] args) {
		TreeVisualizer<String> visual = new TreeVisualizer<String>();
		TreeBuilder builder = new TreeBuilder();
		TreeNode<String> root = builder.build("src/data/SmallExample");
		visual.draw(root);
	}
}

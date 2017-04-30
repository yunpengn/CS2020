package sg.edu.nus.cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class TreeBuilder extends QuestionTree {
	/**
	 * Public Method: TreeNode<String> build(String)
	 * 
	 * @param fileName
	 *            is the path to the database file.
	 * 
	 * @return the root of the built tree.
	 */
	public TreeNode<String> build(String fileName) {
		ArrayList<QuestionObject> objects = readObjectFile(fileName);
		buildTree(objects);

		return m_root;
	}

	/**
	 * Reads in a specially formatted file.
	 */
	private ArrayList<QuestionObject> readObjectFile(String objectFileName) {
		try {
			// Open the file
			ArrayList<QuestionObject> objArray = null;
			FileReader f = new FileReader(objectFileName);
			BufferedReader buff = new BufferedReader(f);

			// Read the first line of the file and parse the number of objects
			String line = buff.readLine().trim();
			int objCount = Integer.parseInt(line);
			objArray = new ArrayList<QuestionObject>(objCount);

			// Read in each object
			for (int i = 0; i < objCount; i++) {
				// The 1st line of the object contains its name.
				String name = buff.readLine().trim();
				// The 2nd line of the object contains the number of properties.
				String propCountLine = buff.readLine().trim();
				int propCount = Integer.parseInt(propCountLine);

				// Now we loop and read in each property
				TreeSet<String> props = new TreeSet<String>();
				for (int j = 0; j < propCount; j++) {
					String propName = buff.readLine().trim();
					if (propName != null && propName != "") {
						props.add(propName);
					}
				}

				// Once we have all the properties, create a new QuestionObject
				if (name != null && name != "" && props.size() > 0) {
					objArray.add(new QuestionObject(name, props));
				}
			}

			// Close the file and buffer after we finish reading.
			f.close();
			buff.close();
			return objArray;
		} catch (Exception e) {
			// If there is an error reading in the file, there isn't much we can
			// do.
			// Print out an error, and later the program will exit.
			String message = "Unable to read in object database. Please check the ";
			message += "filename, the path, and that the file is formatted correctly.";

			System.out.println(e);
			System.out.println(message);
		}

		return null;
	}
}

/**
 * Class: Pseudorandom
 * 
 * Purpose: This class wants to distinguish texts by Shakespeare from texts by
 * some clever forgers using high dimensional vector model.
 * 
 * Static Method:
 * 		void main(String[] args):
 * 			Use high dimensional vector model to distinguish the authors of 6 texts.
 * 
 * @author Niu Yunpeng
 */
package sg.edu.nus.cs2020;

public class PS3 {
	/**
	 * Description: Creates a table which is filled in by the angles of every
	 * two texts and determine a threshold value to distinguish the authors.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		EvenBetterVectorTextFile cromwell = new EvenBetterVectorTextFile("src/sg/edu/nus/cs2020/texts/cromwell.txt");
		EvenBetterVectorTextFile hamlet = new EvenBetterVectorTextFile("src/sg/edu/nus/cs2020/texts/hamlet.txt");
		EvenBetterVectorTextFile henryv = new EvenBetterVectorTextFile("src/sg/edu/nus/cs2020/texts/henryv.txt");
		EvenBetterVectorTextFile macbeth = new EvenBetterVectorTextFile("src/sg/edu/nus/cs2020/texts/macbeth.txt");
		EvenBetterVectorTextFile mystery = new EvenBetterVectorTextFile("src/sg/edu/nus/cs2020/texts/mystery.txt");
		EvenBetterVectorTextFile vortigern = new EvenBetterVectorTextFile("src/sg/edu/nus/cs2020/texts/vortigern.txt");

		EvenBetterVectorTextFile[] vectors = { cromwell, hamlet, henryv, macbeth, mystery, vortigern };
		int numOfTexts = vectors.length;
		String[] textNames = new String[numOfTexts];
		String filePath = "";

		for (int i = 0; i < numOfTexts; i++) {
			// Get the text names from their paths in the file system.
			filePath = vectors[i].fileName;
			textNames[i] = filePath.substring(28, filePath.indexOf("."));
		}

		System.out.printf("%10s", "");
		for (int i = 0; i < numOfTexts; i++) {
			System.out.printf("%11s", textNames[i]);
		}
		System.out.println();

		for (int i = 0; i < numOfTexts; i++) {
			System.out.printf("%10s", textNames[i]);

			for (int j = 0; j < numOfTexts; j++) {
				// Prints the value (in radius) of angle between two texts.
				System.out.printf("%10.4f ", EvenBetterVectorTextFile.Angle(vectors[i], vectors[j]));
			}

			System.out.println();
		}
	}
}

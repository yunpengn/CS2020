package cs2020;

/**
 * This is a class to classify the six texts by looking for a threshold angle
 * that separates Shakespearean plays from non-Shakespearean plays.
 * @author gilbert
 *
 */
public class TextClassifier {
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args){
		
		// Here we define our six files.
		VectorTextFile[] files = new VectorTextFile[6];
		
		files[0] = new EvenBetterVectorTextFile("cromwell.txt");
		files[1] = new EvenBetterVectorTextFile("vortigern.txt");
		files[2] = new EvenBetterVectorTextFile("henryv.txt");
		files[3] = new EvenBetterVectorTextFile("macbeth.txt");
		files[4] = new EvenBetterVectorTextFile("hamlet.txt");
		files[5] = new EvenBetterVectorTextFile("mystery.txt");
		
		// Calculate the angle between every pair of files, using the threshold of 0.42
		// This shows the proper clustering.
		// A better solution would do a binary search on the angle until it
		// found a good clustering.
		for (int i=0; i<5; i++){
			for (int j=i+1; j<6; j++){
				double angle;
				if ((angle = VectorTextFile.Angle(files[i], files[j])) > 0.4742) {
					System.out.println("These are different: " + angle + ": " + i + "," + j);
				}
				else {
					System.out.println("These are the same: " + angle + ": " + i + "," + j);
				}
			}
				
		}

	}
}
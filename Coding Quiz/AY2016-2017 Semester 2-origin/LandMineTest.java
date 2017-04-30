/**
 * LandMineTest class for the CS2020 Coding Quiz 2017
 * 
 * This class is tests the LandMine class
 * 
 */

// This is the default package for the Coding Quiz for CS2020 2017
package cs2020;

// Import useful classes
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * LandMineTest
 * 
 * This class tests the LandMine class.  It can read in a file containing
 * a list of land mines, and it can execute the LandMine code using that input.
 */
public class LandMineTest {

	/**
	 * readMines
	 * 
	 * This method takes as input a file name, and returns 
	 * a list of mines found in the file.
	 * 
	 * If an error occurs, it throws an IOException.  If the file
	 * does not contain land mines, then errors may occur.  (Minimal
	 * error checking is done.)
	 * 
	 * @param fileName
	 * @return
	 */
	static ArrayList<Mine> readMines(String fileName) throws IOException {
		ArrayList<Mine> mines = new ArrayList<Mine>();
		
		String filename = fileName;
        try {
            FileReader reader = new FileReader(filename);         
            int nMines = 0;
 
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNextInt()){
                int xCoord = scanner.nextInt();
                int yCoord = scanner.nextInt();
                mines.add(new Mine(nMines, xCoord, yCoord));                
                nMines++;
                
            }
            scanner.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw(e);
        }
        return mines;
	}	
	
	/**
	 * main
	 * 
	 * The code is designed to test the LandMine class.  It reads in a set of
	 * mines from a text file, and then creates a LandMine object.
	 * 
	 * It then runs various tests on the LandMine class.
	 */
	public static void main(String[] args){
		
		// Read in a set of mines.
		ArrayList<Mine> mines = null;
		try {
			 mines = readMines("1.txt");
		} catch(IOException e){
			System.out.println("Bad file.  Error reading in mine field.");
			System.exit(1);
		}
		
		// Create a mineCalculate with blast radius 3
		LandMineSolution mineCalculator = new LandMineSolution(mines, 3);
		
		
		// Test the getExplodedMines method.
		// Check what happens when each mine is exploded.
		for (int i=0; i<mines.size(); i++){
			ArrayList<Mine> exploded = mineCalculator.getDisabledMines(mines.get(i));
			System.out.println("Mine: " + mines.get(i));
			for (Mine m : exploded){
				System.out.println(m);
			}			
		}
		
		// Test the destroyAllMines method.
		// Find a set of at most 3 mines that destroys all the mines.
		ArrayList<Mine> exploded = mineCalculator.disableAllMines(3);
		System.out.println("Count: " + 3);
		if (exploded == null) {
			System.out.println("Impossible.");
		}
		else {
			for (Mine m : exploded){
				System.out.println(m);
			}
		}
			
		// Test the minDrones method.
		// Find out the minimum number of drones needed to destroy all the mines.
		System.out.println("Minimum number of drones needed: " + mineCalculator.minDrones());		
	}
}

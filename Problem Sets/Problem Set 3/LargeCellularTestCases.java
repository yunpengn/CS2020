package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

/*
 * This file contains some very large test cases for Problem Set 3.
 * Sometimes, a test case is too large to include directly in a JUnit test.
 * This is an example of reading in a very large test case from a file.
 */
public class LargeCellularTestCases {
	// Pair: Stores a pair of variables
	private class Pair {
		// Member variables
		private int m_loc;
		private int m_size;
		
		// Constructor
		Pair (int loc, int size){
			m_loc = loc;
			m_size = size;
		}
		
		// Accesses location
		int loc() {
			return m_loc;
		}
		
		// Accesses size
		int size() {
			return m_size;
		}
	}
	
	// TestCase: Stores a single testCase read from the file.
	private class TestCase {
		// Member variables
		private int m_answer;
		private ArrayList<Pair> m_towers;			
		private int m_length = 0;
		
		// Constructor
		TestCase(){
			m_towers = new ArrayList<Pair>();
		}
		
		// Add the length
		void addLength(int length){
			m_length = length;
		}
		
		// Add the answer
		void addAnswer(int answer){
			m_answer = answer;
		}
		
		// Add a tower
		void addTower(int loc, int size){
			m_towers.add(new Pair(loc, size));			
		}
		
		// Return number of towers
		int numTowers(){
			return m_towers.size();
		}
		
		// Return a tower
		Pair getTower(int i){
			return m_towers.get(i);
		}
		
		// Return the length
		int getLength(){
			return m_length;
		}
		
		// Return the answer
		int getAnswer(){
			return m_answer;
		}
	}
	
	// Member variables: Store test cases here
	private ArrayList<TestCase> m_cases = new ArrayList<TestCase>();

	/*
	 * Reads the file "testcases.txt" which contains large test cases for the
	 * CoverageCalacultor. Each test begins with the string: NEW_CASE. The
	 * second line contains a single number: the length of the highway. The
	 * third line contains the purported answer. After that, each line
	 * represents one tower (x, y) where x is the location and y is the range.
	 */
	private void ReadFile()
	{		
		final String fileName = "src/sg/edu/nus/cs2020/testcases.txt";
		final String NEWCASE = "NEW_CASE";
		
		// Declare and initialize variables 
		File file = null;
		BufferedReader bufferFile = null;

		// Begin a block of code that handles exceptions
		try{
			// Open the file
			file = new File(fileName);
			bufferFile = new BufferedReader(new FileReader(file));
			
			String line;
			TestCase testCase = null;

			while ((line = bufferFile.readLine()) != null) {
				if (line.compareTo(NEWCASE) == 0) {
					// Begin a new case
					if (testCase != null){
						m_cases.add(testCase);
					}

					testCase = new TestCase();
					line = bufferFile.readLine();
					testCase.addLength(Integer.decode(line));
					line = bufferFile.readLine();
					testCase.addAnswer(Integer.decode(line));
					continue;
				} else {
					// Read the next tower
					String tokens[] = line.split(",");
					testCase.addTower(Integer.decode(tokens[0]), Integer.decode(tokens[1]));
				}
			}

			if (testCase != null){
				m_cases.add(testCase);
			}
																			
		} catch (Exception e) {
			System.out.println("Error reading testcases.txt.");
		}
		
		
	}

	// This big test loads 13 tests from a file and runs them against your code.
	// Below, change "CoverageCalculator" to the name of the class being tested.
	@Test
	public void BigTest() {
		ReadFile();
		
		for (int i = 0; i < m_cases.size(); i++) {
			TestCase testCase = m_cases.get(i);
			CoverageCalculator calc = new CoverageCalculator(testCase.getLength());

			for (int j = 0; j < testCase.numTowers(); j++) {
				Pair tower = testCase.getTower(j);
				calc.addTower(tower.loc(), tower.size());
			}

			int answer = calc.getCoverage();

			if (answer == testCase.getAnswer()){
				System.out.println("Test " + i + " successful.");	
			} else {
				System.out.println("Test " + i + " failed.");
				calc.print();
			}

			assertEquals(testCase.getAnswer(), answer);		
		}
	}

}

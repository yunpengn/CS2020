package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * TestAthletes
 * Class for Coding Quiz
 * CS2020, 2013
 * @author gilbert
 *
 * Framework code for testing out the
 * athlete enumerator.
 */
public class TestAthletes {
	
	// Athlete Enumerator
	private AthleteEnumerator m_athletes = new AthleteEnumerator();	
	
	/**
	 * Constructor
	 * @param fileName
	 * Reads in the file and enumerates
	 * all the athletes.
	 */
	TestAthletes(String fileName){
		addAthletes(fileName);
		m_athletes.printAllAthletes();
	}
	

	/**
	 * addAthletes
	 * @param fileName
	 * Reads in the students from the specified file and
	 * adds them to the athlete database.
	 */
	private void addAthletes(String fileName){
		try{
			// Open the file
			FileReader f = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(f);
			
			// Read the first line of the file and
			// parse the number of students
			String line = buff.readLine().trim();
			int number = Integer.parseInt(line);
			
			// Read in each object
			for (int i=0; i<number; i++){
				// The first line of the student contains its name
				String name = buff.readLine().trim();
				if ((name == null) || (name == "")){
					continue;
				}
				
				// The second line of the student contains its strength
				String strStrength = buff.readLine().trim();
				if ((strStrength == null) || (strStrength == "")){
					continue;
				}
				int intStrength = Integer.parseInt(strStrength);
				
				// The third line of the student contains its speed
				String strSpeed = buff.readLine().trim();
				if ((strSpeed == null) || (strSpeed == "")){
					continue;
				}
				int intSpeed = Integer.parseInt(strSpeed);

				// Add the new student
				Student student = new Student(name, intStrength, intSpeed);
				m_athletes.addStudent(student);
			}
			f.close();
			buff.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
	}
	

	/**
	 * Main
	 * @param args
	 * Creates and tests the student-athlete enumerator.
	 */
	public static void main(String[] args) {
		
		TestAthletes test = new TestAthletes("students10.txt");
	}

}

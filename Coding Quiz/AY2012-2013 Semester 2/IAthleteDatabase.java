package cs2020;

/**
 * IAthleteDatabase
 * Interface for Coding Quiz
 * CS2020, 2013
 * @author gilbert
 *
 * This interface specifies the required methods for the AthleteEnumerator class.  
 * The behavior of each method is described below.  Please see the Coding Quiz for
 * the detailed requirements.
 */
public interface IAthleteDatabase extends Iterable<Student>{
	
	// Adds a new student to the database
	public void addStudent(Student s);
	
	// Finds the weakest student with at least strength studentStrength.
	// If no student has sufficient strength, then it returns null.
	// If more than one student has the same strength, then it
	// returns the fastest student with this strength.
	// It never returns a student (name, x, y) if there is another
	// student (name2, x, y') where (y' > y).
	public Student searchStrongStudent(int studentStrength);
	
	// Prints out all the students that are athletes.
	// An athlete is defined to be a student for whom no other student
	// is both stronger and faster.
	public void printAllAthletes();
}

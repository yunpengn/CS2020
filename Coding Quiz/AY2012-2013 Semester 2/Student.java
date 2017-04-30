package cs2020;

/**
 * Student
 * Class for Coding Quiz
 * CS2020, 2013
 * @author gilbert
 *
 * This class encapsulates the basic functionality of a student.
 * It is not to be changed.
 */
public class Student {

	// Private class variables:
	// Name
	final private String m_name;
	// Strength
	final private int m_strength;
	// Speed
	final private int m_speed;
	
	/**
	 * Constructor
	 * @param name
	 * @param strength
	 * @param speed
	 * Builds a new student with the specified name/strength/speed.
	 */
	Student(String name, int strength, int speed){
		m_name = name;
		m_strength = strength; 
		m_speed = speed;
	}
	
	/**
	 * getName
	 * @return student's name
	 */
	public String getName(){
		return m_name;
	}
	
	/**
	 * getStrength
	 * @return student's strength
	 */
	public int getStrength(){
		return m_strength;
	}
	
	/**
	 * getSpeed
	 * @return student's speed
	 */
	public int getSpeed(){
		return m_speed;
	}
	
	/**
	 * toString
	 * @return string representation of the student
	 * Name: strength, speed
	 * Notice that you can use System.out.println(student) to print out 
	 * information on a student.
	 */
	public String toString(){
		return (getName() + ": " + Integer.toString(getStrength()) + "," + Integer.toString(getSpeed()));
	}
}

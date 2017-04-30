package sg.edu.nus.cs2020;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Public Class: QuestionObject
 * 
 * Description: This class encapsulates an object which contains a name and a
 * list of properties. Note that the class contains an iterator which lets you
 * access the object's properties, as well as a method to determine whether a
 * given property is contained in that object. Throughout the usage, we assume
 * that properties are stored in sorted order.
 * 
 * @author gilbert
 */
public class QuestionObject implements Comparable<QuestionObject> {
	// name of object
	private String m_name;
	
	// properties of object, stored in a Tree
	private TreeSet<String> m_properties;
	
	/**
	 * Constructor
	 * @param n name of object
	 * @param p properties of object
	 */
	public QuestionObject(String n, TreeSet<String> p) {
		m_name = n;
		m_properties = p;
	}
	
	/**
	 * This method returns the name of the object.
	 * 
	 * @return the name of the object
	 */
	public String getName(){
		return m_name;
	}
	
	/**
	 * This method returns the number of properties the object has
	 * 
	 * @return the number of properties the object has
	 */
	public int getPropCount(){
		return m_properties.size();
	}

	/**
	 * This method returns an iterator for the properties of this object
	 * 
	 * @return an iterator for the properties of this object
	 */
	public Iterator<String> propertyIterator(){
		return m_properties.iterator();
	}
	
	/**
	 * This method checks if the object contains the given property
	 * 
	 * @param s the property that is to be checked 
	 * @return true if the object contains the given property, false otherwise
	 */
	boolean containsProperty(String s) {
		return m_properties.contains(s);
	}
	
	/**
	 * This method returns the object as a string in the following format:
	 * name: <m_name>
	 * Properties: <property1>, <property2>, <property3>, ...
	 * 
	 * @return the object as a string
	 */
	@Override
	public String toString() {
		String output = "Object: " + m_name;
		output += '\n';
		output += "Properties: ";
		Iterator<String> iter = m_properties.iterator();

		while (iter.hasNext()) {
			String s = iter.next();
			output += s;
			if (iter.hasNext()) output += ',';
		}

		return output;
	}

	/**
	 * Define that x < y if x.numProperties > y.numProperties. In this way, we
	 * sort objects with more properties first.
	 * 
	 * @param other
	 *            is the QuestionObject to compare with.
	 */
	@Override
	public int compareTo(QuestionObject other) {
		if (this == other)
			return 0;
		int myLength = getPropCount();
		int yourLength = other.getPropCount();
		if (myLength < yourLength) {
			return 1;
		} else if (myLength > yourLength) {
			return -1;
		} else {
			return 0;
		}
	}
}

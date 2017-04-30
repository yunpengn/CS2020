package sg.edu.nus.cs2020;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Public Class: Query
 * 
 * Description: This class encapsulates a query. A query consists of two types
 * of properties: those that are required to be present, and those that are
 * required to be absent. For example, if object 1 has properties A and C, then
 * it will satisfy queries "A" AND "A,C" AND "A, -B" (where -B indicates that B
 * is absent). Properties will always be stored and returned in a sorted order.
 * 
 * Property: Note that the object contains two iterators, one that iterates the
 * positive properties and one that iterates the negative properties. It also
 * contains two methods to determine whether a given property or negative
 * property is contained in this query.
 * 
 * @author gilbert
 */
public class Query {
	// Properties that must be present
	private TreeSet<String> m_properties = new TreeSet<String>();
	// Properties that must be absent
	private TreeSet<String> m_notProperties = new TreeSet<String>();
	
	// This constructor builds an empty query.
	public Query() {

	}

	/**
	 * Public Constructor: Query(TreeSet<String>, TreeSet<String>)
	 * 
	 * Description: This constructor builds a query based on the properties and
	 * negative properties given.
	 * 
	 * @param props
	 *            is a treeSet of the (positive) properties.
	 * @param notProps
	 *            is a treeSet of the (negative) properties.
	 */
	public Query(TreeSet<String> props, TreeSet<String> notProps) {
		m_properties = props;
		m_notProperties = notProps;
	}


	/**********************************
	 * Methods for positive properties
	 **********************************/

	/**
	 * This method adds a property to the query based on the input string.
	 * 
	 * @param prop
	 *            is the name of the property that is to be added.
	 */
	public void addProperty(String prop) {
		// Error checking
		if (prop == null || prop == "") {
			throw new IllegalArgumentException("Bad property name");
		} else if (m_notProperties.contains(prop)) {
			String message = "Cannot add property: already exists as negative property.";
			throw new IllegalArgumentException(message);
		}

		m_properties.add(prop);
	}

	/**
	 * This method checks whether the query contains a specific property.
	 * 
	 * @param s
	 *            is the property that is to be checked.
	 * 
	 * @return true if the property is contained in this query, false otherwise.
	 */
	public boolean containsProperty(String s) {
		return m_properties.contains(s);
	}

	/**
	 * This method returns an iterator for the properties contained in this
	 * query/
	 * 
	 * @return an iterator for the properties contained in this query
	 */
	public Iterator<String> propertyIterator() {
		return m_properties.iterator();
	}

	/**
	 * This method returns the number of properties that are in the query
	 * 
	 * @return the number of properties that are in the query
	 */
	public int numProperties() {
		return m_properties.size();
	}
	
	
	/**********************************
	 * Methods for negative properties
	 **********************************/
	
	/**
	 * This method adds a negative property to the query
	 * 
	 * @param notProp
	 *            the negative property that is to be added
	 */
	public void addNotProperty(String notProp) {
		// Error checking
		if (notProp == null || notProp == "") {
			throw new IllegalArgumentException("Bad property name");
		} else if (m_properties.contains(notProp)) {
			String message = "Cannot add property: already exists as positive property.";
			throw new IllegalArgumentException(message);
		}

		m_notProperties.add(notProp);
	}

	/**
	 * This method checks if the query contains a specific negative property.
	 * 
	 * @param s
	 *            is the property that is to be checked.
	 * 
	 * @return true if the negative property is contained in this query, false
	 *         otherwise.
	 */
	public boolean containsNotProperty(String s) {
		return m_notProperties.contains(s);
	}

	/**
	 * This method returns an iterator for the negative properties
	 * 
	 * @return an iterator for the negative properties in this query
	 */
	public Iterator<String> notPropertyIterator() {
		return m_notProperties.iterator();
	}

	/**
	 * This method returns the number of negative properties
	 * 
	 * @return the number of negative properties
	 */
	public int numNotProperties() {
		return m_notProperties.size();
	}


	/**********************************
	 * Other method
	 **********************************/

	/**
	 * This method returns the query as a string. It merges the property and
	 * negative property lists together, maintaining a sorted order and negating
	 * the properties that must be absent.
	 */
	@Override
	public String toString() {
		String output = "Query: ";

		int count = m_properties.size() + m_notProperties.size();

		Iterator<String> props = m_properties.iterator();
		Iterator<String> notProps = m_notProperties.iterator();

		String p = null;
		String np = null;
		String next = null;

		if (props.hasNext()) {
			p = props.next();
		}
		if (notProps.hasNext()) {
			np = notProps.next();
		}

		for (int i = 0; i < count; i++) {
			if (p == null && np == null) {
				// Defensive programming. This shouldn't happen.
				continue;
			} else if (p == null) {
				// Negate the negative properties
				next = '-' + np;

				if (notProps.hasNext()) {
					np = notProps.next();
				} else {
					np = null;
				}
			} else if (np == null) {
				next = p;

				if (props.hasNext()) {
					p = props.next();
				} else {
					p = null;
				}
			} else {
				// Both are available
				int compare = p.compareTo(np);

				if (compare < 0) {
					// p comes first
					next = p;
					if (props.hasNext()) {
						p = props.next();
					} else {
						p = null;
					}
				} else if (compare > 0) {
					// np comes first
					next = '-' + np;
					if (notProps.hasNext()) {
						np = notProps.next();
					} else {
						np = null;
					}
				} else {
					// should never get here
					String message = "Bad query: some property ";
					message += "has been added as both negative and positive.";
					throw new IllegalStateException(message);
				}
			}

			output += next;
			if (i < count - 1) {
				output += ",";
			}
		}

		return output;
	}
}

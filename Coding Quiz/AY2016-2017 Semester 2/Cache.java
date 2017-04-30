/**
 * Cache class for the Coding Quiz for CS2020 2017
 * 
 * Complete the implementation of the Cache class.
 * It should correctly implement the ICache interface.
 * See the ICache interface for details of how the operations should work.
 * 
 * You may modify any part of the class that you need to complete the 
 * implementation, including adding member variables, changing existing
 * methods, and adding new methods.
 */


// This is the default package for the Coding Quiz for CS2020 2017
package sg.edu.nus.cs2020;

// This class will use the Java TreeMap.
// See the Coding Quiz for details of how to use the TreeMap that you may need.
import java.util.TreeMap;

/**
 * Cache
 * 
 * This is the main Cache class.  It has two parameters:
 *  (1) key type K and 
 *  (2) value type V.
 * 
 * It implements the ICache interface.  See the ICache interface
 * for details of how the operations should work.
 * 
 * The constructor of the Cache class specifies the size of the cache.
 *
 * @param <K>
 * @param <V>
 */
public class Cache<K extends Comparable<K>, V> implements ICache<K, V> {
	// Member variables for the cache class
	
	// The data in the cache is stored in this TreeMap
	// The TreeMap is parameterized by two types:
	//    (1) the key type K
	//    (2) a node type CNode that stores the information associated with the key.
	// Notice that the node type CNode can be used to implement a linked list that
	// keeps track of the order in which items are accessed.
	private TreeMap<K, CNode> m_cache;
	
	// Most recently and least recently accessed items.
	private CNode m_oldest = null;
	private CNode m_newest = null;
	
	// Add other members variables that you need here.
	// The current size
	private int size = 0;
	// The maximum size
	private int maxSize = 0;
	
	/**
	 * Constructor
	 * 
	 * @param cacheSize specifies the size of the cache.
	 */
	Cache(int cacheSize) throws IllegalArgumentException {
		if (cacheSize <= 0) {
			// The maximum size must be larger than 0.
			throw new IllegalArgumentException();
		}

		// Create a new TreeMap to store the cache.
		m_cache = new TreeMap<K, CNode>();
		// Initialize the maximum size of the cache.
		maxSize = cacheSize;

		// Size is 0 now
		size = 0;
	}
	
	/**
	 * insert
	 * 
	 * Adds item key/value to the cache as the most recent item.
	 * If the key is already in the cache, then it updates the value.
	 * 
	 * It also updates the linked list to reflect that it is the most recent
	 * item accessed.
	 * 
	 * @param key the key to insert
	 * @param value the value to insert
	 */
	@Override
	public void insert(K key, V value) {
		// To add something to the TreeMap m_cache, use the following syntax:
		//     m_cache.put(x, y) 
		// where x and y are of the two types that parameterize the TreeMap.

		CNode node = new CNode(key, value);


		if (m_oldest == null) {
			// There is still nothing in the cache, the oldest and newest both.
			m_oldest = node;
			m_newest = node;

			// The new one's previous one is the original last one
			node.prev = m_newest;
			// No next one for new node
			node.next = null;

			m_cache.put(key, node);
		} else if (m_cache.containsKey(key)) { // An existing one
			// Settle the original connections
			CNode origin = m_cache.get(key);

			if (maxSize == 1) {
				m_oldest = node;
				m_newest = node;
			} else if (m_newest == origin) {
				// It is the originally latest one
				CNode prev = origin.prev;
				prev.next = node;

				// The new one's previous one is the original last second
				node.prev = prev;
				// No next one for new node
				node.next = null;

				m_newest = node;
			} else if (m_oldest == origin) {
				// It is originally oldest one
				CNode newOld = origin.next;
				newOld.prev = null;
				m_oldest = newOld;

				// / Update m_newest
				m_newest.next = node;
				node.prev = m_newest;
				node.next = null;

			} else {
				CNode prev = origin.prev;
				CNode next = origin.next;
				prev.next = next;
				next.prev = prev;

				// The original last one has a new one instead now
				m_newest.next = node;

				// The new one's previous one is the original last one
				node.prev = m_newest;
				// No next one for new node
				node.next = null;

				// This is the newest
				m_newest = node;
			}


			// An existing value and key, not contains in the cache
			// Update the value
			m_cache.put(key, node);
		} else {
			// A new one, does not exist before
			// The original last one has a new one instead now
			m_newest.next = node;

			// The new one's previous one is the original last one
			node.prev = m_newest;
			// No next one for new node
			node.next = null;

			m_newest = node;
			m_cache.put(key, node);

			// The cache is already full
			if (size + 1 >= maxSize) {
				CNode newOld = m_oldest.next;

				// Break the connection
				newOld.prev = null;
				m_oldest.next = null;
				
				m_cache.remove(m_oldest.key);

				// Update the oldest one
				m_oldest = newOld;
			} else {
				// Increment the current size by 1.
				size++;
			}
		}
	}

	/**
	 * search
	 * 
	 * Returns the value associated with the specified key, if it is in the cache.
	 * If it is not in the cache, then return null.
	 * 
	 * After accessing an item, it should be the most recently accessed item.
	 * 
	 * @param key
	 */
	@Override
	public V search(K key) {		
		// To search for a key in the TreeMap m_cache, use the following syntax:
		//    m_cache.get(x) 
		// where x is the first type that parameterize the TreeMap.
		if (key == null) {
			return null;
		} else {
			// The result node for the key
			CNode result = m_cache.get(key);
			if (result == null) {
				// Does not find it.
				return null;
			} else {
				if (maxSize == 1) {
					// Do nothing
					;
				}if (result.next == null) {
					// Do nothing if it is already the newest one.
				} else if (result.prev == null) {
					// It is originally the oldest one
					CNode newOld = result.next;
					newOld.prev =null;
					m_oldest = newOld;
					
					m_newest.next = result;
					result.prev = m_newest;
					result.next = null;
					m_newest = result;
				} else {
					// It is not originally the new one, update m_newest
					CNode prev = result.prev;
					CNode next = result.next;

					prev.next = next;
					next.prev = prev;

					result.prev = m_newest;
					m_newest.next = result;
					result.next = null;
					m_newest = result;
				}

				return result.value;
			}
		}

	}

	/**
	 * printCacheKeys
	 * 
	 * This is a test routine that prints the keys in the cache in order of the keys.
	 */
	void printCacheKeys(){
		// Enumerate all the keys in the cache
		for (K key : m_cache.keySet()){
			// Get the associated value
			V value = m_cache.get(key).value;
			// Print (key, value)
			System.out.println(key + ", " + value);
		}
	}
	
	/**
	 * CNode
	 * 
	 * This is an inner class use to store the (key, value) pair in the cache.
	 * 
	 * It also stores next and and previous pointers to the items added
	 * immediately before and after it.
	 *
	 */
	private class CNode {
		
		// key and value for this node in the cache
		private K key;
		private V value;
		
		// next and prev pointers for the linked list that maintains the
		// order that nodes were accessed.
		private CNode next;
		private CNode prev;
		
		/**
		 * Constructor
		 * 
		 * Creates a new node with a given (key, value)
		 * 
		 * @param k key
		 * @param v value
		 */
		CNode(K k, V v){
			key = k;
			value = v;
			next = null;
			prev = null;
		}
	}
	
	/**
	 * main
	 * 
	 * Simple test code for the cache.
	 * 
	 * Inserts 20 items into a cache of size 5, and the prints out the
	 * keys in the cache.  It should print out the last 5 items inserted.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 5;
		Cache<Integer, Integer> cache = new Cache<Integer, Integer>(size);

		for (int i = 0; i < 20; i++) {
			cache.insert(20 - i, i);
		}

		cache.insert(16, 17);

		cache.printCacheKeys();
	}
	
}
